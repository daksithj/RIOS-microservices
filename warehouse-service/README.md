# RIOS: Warehouse Service

## Databases

#### Warehouse Items
These are the items available in the warehouse. These contain the item name, price and total available content.

#### Warehouse Orders
When the Retail service puts a new order, a warehouse order is created. This contains the retail id, status 
(0= pending, 1=order completed), and a one-to-many relationship with the Order items table.

#### Order items
A warehouse order can contain multiple items. Each Warehouse item is mapped to Warehouse Orders here, with the order amount.



## Rest Endpoints

#### Warehouse Items related endpoints
``GET: /warehouse/items`` Get all Warehouse Items (Items available to be ordered)

``GET: /warehouse/items/{itemId}`` Get Warehouse Item corresponding to the id

``POST: /warehouse/items`` Add a new item to the Warehouse Items

``PUT: /warehouse/items/{itemId}`` Update the Warehouse Item corresponding to the id

``DELETE: /warehouse/items/{itemId}`` Delete the Warehouse Item corresponding to the id

#### Warehouse Orders related endpoints
``GET: /warehouse/orders`` Get all Warehouse orders

``GET: /warehouse/pending-orders`` Get all Pending Warehouse orders (excluding the completed ones)

``GET: /warehouse/orders/{orderId}`` Get Warehouse Order corresponding to the id

``GET: /warehouse/pending-orders/{retailId}`` Get all Pending Warehouse Orders (incomplete orders) for a particular Retail Id

``POST: /warehouse/orders`` Add a new order to the Warehouse Items. Can be used by Retail Service to post a new order.
Example body of the request:
```bash
{
    "retailId": 1,
    "items" : [
        {
            "itemId": 10001,
            "quantity": 2
        },
        {
            "itemId": 10002,
            "quantity": 5
        }
    ]
}
```
If the quantities are greater than the available quantities for the particular Warehouse Item, an error is sent.
This also updates the Warehouse item quantity as well (quanitity - ordered quantity).

``PUT: /warehouse/order/{orderId}`` Update the Warehouse order corresponding to the id. Invoked when completing the order.
Example request body:
```bash
{
    "retailId": 1,
    "items" : [
        {
            "itemId": 10001,
            "quantity": 3
        },
        {
            "itemId": 10002,
            "quantity": 4
        }
    ]
}
```
Once this request is sent, for each item in the corresponding Warehouse Order, the quantities (in the request) are subtracted.
If the Warehouse Order's all quantities are 0, it sets the order status to completed. If not the order remains as pending.
If the any of the quantities exceed the ordered amount, an error is sent without updating.

``DELETE: /warehouse/orders/{orderId}`` Delete the Warehouse Order corresponding to the id. The item quantities in the
order are restored in the corresponding Warehouse Items.

