package workout.calendar.security.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import workout.calendar.security.factory.UrlResourcesMapFactoryBean;
import workout.calendar.security.handler.CustomAccessDeniedHandler;
import workout.calendar.security.handler.CustomAuthenticationFailureHandler;
import workout.calendar.security.handler.CustomAuthenticationSuccessHandler;
import workout.calendar.security.metadatasource.UrlFilterInvocationSecurityMetadatsSource;
import workout.calendar.security.provider.CustomAuthenticationProvider;
import workout.calendar.service.SecurityResourceService;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final UrlResourcesMapFactoryBean urlResourcesMapFactoryBean;
    private final SecurityResourceService securityResourceService;

    private final PasswordEncoder encoder;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }

    @Bean
    public SecurityFilterChain formSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/", "/register", "/denied", "/loginForm*").permitAll()
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
                )
                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .authenticationProvider(customAuthenticationProvider)
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
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadatsSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        return filterSecurityInterceptor;
    }

    private AccessDecisionManager affirmativeBased() {
        return new AffirmativeBased(getAccessDecisionVoters());
    }

    private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
        List<AccessDecisionVoter<? extends Object>> accessDecisionVoterList = new ArrayList<>();
        accessDecisionVoterList.add(roleVoter());
        return accessDecisionVoterList;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        return new RoleHierarchyImpl();
    }

    @Bean
    public UrlFilterInvocationSecurityMetadatsSource urlFilterInvocationSecurityMetadatsSource(){
        return new UrlFilterInvocationSecurityMetadatsSource(urlResourcesMapFactoryBean.getObject(), securityResourceService);
    }
}
