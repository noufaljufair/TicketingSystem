package com.example.TicketingSystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

   // @Override
   // protected void configure(HttpSecurity http) throws Exception {
       // http
           //     .authorizeRequests()
            //    .antMatchers("/", "/users").permitAll()
             //   .anyRequest().authenticated()
              //  .and()
              //  .formLogin()
              //  .loginPage("/login")
              //  .permitAll()
              //  .and()
              //  .logout()
               // .permitAll(); }

   // @Bean
    //@Override
  // public UserDetailsService userDetailsService() {
       // UserDetails user = withDefaultPasswordEncoder()
                     //   .username("email")
                      //  .password("password")
                      //  .roles("USER")
                       // .build();

       // return new InMemoryUserDetailsManager(user);  }
}




