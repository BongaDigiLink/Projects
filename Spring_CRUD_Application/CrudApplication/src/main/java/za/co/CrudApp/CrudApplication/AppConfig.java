package za.co.CrudApp.CrudApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import za.co.CrudApp.CrudApplication.service.UserService;

@Configuration
@EnableWebSecurity
public class AppConfig
{
    private UserService userService;
    @Autowired
    public AppConfig(UserService userService) {
        this.userService = userService;
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/users").permitAll()
                .requestMatchers("/home").authenticated()
                .requestMatchers("/create-user").authenticated()
//                .requestMatchers("/update-user/**").authenticated()
                .requestMatchers("/update-user/**").permitAll()
//                .requestMatchers("/delete-user").authenticated()
                .requestMatchers("/delete-user").permitAll()
                .and().formLogin();

        return httpSecurity.build();
    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}