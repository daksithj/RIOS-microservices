package com.ds.rios.authservice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final List<AppUser> users = Arrays.asList(
                new AppUser(1, "warehouse", encoder.encode("12345"), "WAREHOUSE"),
                new AppUser(2, "delivery", encoder.encode("12345"), "DELIVERY"),
                new AppUser(3, "retail", encoder.encode("12345"), "RETAIL")
        );

        for(AppUser appUser: users) {
            if(appUser.getUsername().equals(username)) {

                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());

                return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
            }
        }
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

    private static class AppUser {
        private Integer id;
        private String username, password;
        private String role;

        public AppUser(Integer id, String username, String password, String role) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public Integer getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
