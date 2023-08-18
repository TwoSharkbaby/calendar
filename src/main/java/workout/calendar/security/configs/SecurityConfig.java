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

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder encoder;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> user = new ArrayList<>();
        UserDetails user1 = User.builder()
                .username("user")
                .password(encoder.encode("1111"))
                .roles("USER").build();
        UserDetails user2 = User.builder()
                .username("manager")
                .password(encoder.encode("1111"))
                .roles("MANAGER").build();
        UserDetails user3 = User.builder()
                .username("admin")
                .password(encoder.encode("1111"))
                .roles("ADMIN").build();
        user.add(user1);
        user.add(user2);
        user.add(user3);
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain formSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/", "/register", "/denied").permitAll()
                        .antMatchers("/try/user").hasRole("USER")
                        .antMatchers("/try/manager").hasRole("MANAGER")
                        .antMatchers("/try/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }
}
