package com.harrisonwells.funding.security;

import com.harrisonwells.funding.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration
        extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/public/**"))
                .permitAll();
        // http.rememberMe().alwaysRemember(false);
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetails admin =
                User.withUsername("admin")
                        .password("{noop}123")
                        .roles("ADMIN")
                        .build();
        UserDetails entrepreneur =
                User.withUsername("entrepreneur")
                        .password("{noop}123")
                        .roles("ENTREPRENEUR")
                        .build();
        UserDetails investor =
                User.withUsername("investor")
                        .password("{noop}123")
                        .roles("INVESTOR")
                        .build();
        return new InMemoryUserDetailsManager(admin, entrepreneur, investor);
    }
}