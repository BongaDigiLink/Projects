package co.zaCrudApplication;

import co.zaCrudApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .requestMatchers("/create-contact").permitAll()
                .requestMatchers("/users").permitAll()
                .requestMatchers("/update-user/**").permitAll()
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
