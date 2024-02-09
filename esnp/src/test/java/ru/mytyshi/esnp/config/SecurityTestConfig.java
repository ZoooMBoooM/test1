package ru.mytyshi.esnp.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.mytyshi.esnp.models.Person;

import java.util.Arrays;
import java.util.HashSet;

@TestConfiguration
public class SecurityTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
//        Person personMocked = new Person(10, "Person Mocked", "USER", "00000",
//                new HashSet<>(1), new HashSet<>(1));

        UserDetails userDetails = (UserDetails) new Person(10, "Person Mocked", "USER", "00000",
                new HashSet<>(1), new HashSet<>(1));

              return new InMemoryUserDetailsManager(Arrays.asList(userDetails));
    }
}
