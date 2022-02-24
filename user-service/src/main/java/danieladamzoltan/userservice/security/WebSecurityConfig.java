package danieladamzoltan.userservice.security;

import danieladamzoltan.userservice.jwt.AuthEntryPointJwt;
import danieladamzoltan.userservice.jwt.filter.JwtRequestFilter;
import danieladamzoltan.userservice.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
//        jsr250Enabled = true
//        securedEnabled = true
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomUserDetailsService customUserDetailsService;

    private final AuthEntryPointJwt authEntryPointJwt;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, AuthEntryPointJwt authEntryPointJwt) {
        this.customUserDetailsService = customUserDetailsService;
        this.authEntryPointJwt = authEntryPointJwt;
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter(){
        return new JwtRequestFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(31);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/authentication/**").permitAll()
                .antMatchers("/recipe/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
