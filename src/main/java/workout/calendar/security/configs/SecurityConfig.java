package workout.calendar.security.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import workout.calendar.security.handler.CustomAccessDeniedHandler;
import workout.calendar.security.handler.CustomAuthenticationFailureHandler;
import workout.calendar.security.handler.CustomAuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final PasswordEncoder encoder;

    @Bean
    public SecurityFilterChain formSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/", "/register", "/denied", "/loginForm*").permitAll()
                        .antMatchers("/try/user").hasRole("USER")
                        .antMatchers("/try/manager").hasRole("MANAGER")
                        .antMatchers("/try/admin").hasRole("ADMIN")
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }
}
