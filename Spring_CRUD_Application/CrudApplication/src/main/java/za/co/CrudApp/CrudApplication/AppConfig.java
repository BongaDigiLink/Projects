package za.co.CrudApp.CrudApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import za.co.CrudApp.CrudApplication.service.UserService;

@Configuration
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
                .requestMatchers("/create-contact").authenticated()
                .requestMatchers("/update-user/**").authenticated()
                .requestMatchers("/delete-contact").authenticated()
                .and().formLogin()
                .and()
                .httpBasic();

        return httpSecurity.build();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
//    {
//        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}