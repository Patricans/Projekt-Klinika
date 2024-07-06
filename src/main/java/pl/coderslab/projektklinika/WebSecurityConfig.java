package pl.coderslab.projektklinika;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import pl.coderslab.projektklinika.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/patient/**").hasAnyRole("PATIENT")
                        .requestMatchers("/nurse/**").hasAnyRole("NURSE")
                        .requestMatchers("/doctor/**").hasAnyRole("DOCTOR")
                        .requestMatchers("/logowanie","/logowanie.jsp").permitAll()
                        .requestMatchers("/rejestracja","/rejestracja.jsp", "/home","/home.jsp", "/wyloguj").permitAll()
                        .requestMatchers("/error","/","/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll()
                ).formLogin((o) -> o
                        .loginPage("/logowanie").defaultSuccessUrl("/home").permitAll()
                ).logout((o) -> o
                        .logoutUrl("/wyloguj")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        StrictHttpFirewall httpFirewall = new StrictHttpFirewall();
        httpFirewall.setAllowSemicolon(true);
        return (web) -> web.httpFirewall(httpFirewall);
    }
}
