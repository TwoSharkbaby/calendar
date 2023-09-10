package workout.calendar.security.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
import workout.calendar.security.service.CustomOAuth2UserService;
import workout.calendar.security.service.CustomUserDetailsService;
import workout.calendar.security.service.SecurityResourceService;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final UrlResourcesMapFactoryBean urlResourcesMapFactoryBean;
    private final SecurityResourceService securityResourceService;

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }

    @Bean
    public SecurityFilterChain formSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/", "/user/register", "/denied", "/user/loginForm", "/workout").permitAll()
                        .antMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(customUserDetailsService)
                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .authenticationProvider(customAuthenticationProvider)
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .formLogin()
                .loginPage("/user/loginForm")
                .loginProcessingUrl("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                        userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)))
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        ProviderManager authenticationManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadatsSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager(authenticationConfiguration));
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
    public UrlFilterInvocationSecurityMetadatsSource urlFilterInvocationSecurityMetadatsSource() {
        return new UrlFilterInvocationSecurityMetadatsSource(urlResourcesMapFactoryBean.getObject(), securityResourceService);
    }
}
