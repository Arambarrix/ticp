package fr.istic.pdl.ticpbackend.config;

import fr.istic.pdl.ticpbackend.service.AdminService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService userDetailsService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(Constants.EQUIPES).permitAll()
                .antMatchers(HttpMethod.GET,Constants.TOURNOIS+"**").authenticated()
                .antMatchers(Constants.MATCHSPOULE).permitAll()
                .antMatchers(Constants.MATCHSTABLEAU).permitAll()
                .antMatchers(Constants.JOUEURS).permitAll()
                .antMatchers(Constants.ADMIN+"**").permitAll()
                .anyRequest()
                .permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}