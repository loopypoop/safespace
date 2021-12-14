package kz.iitu.gateway.security;

import kz.iitu.gateway.security.filter.JwtTokenAuthenticationFilter;
import kz.iitu.gateway.security.filter.JwtTokenAuthorizationFilter;
import kz.iitu.gateway.service.UserDetailsServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig (JwtConfig jwtConfig,
                           BCryptPasswordEncoder passwordEncoder,
                           UserDetailsServiceImpl userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilter(new JwtTokenAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
