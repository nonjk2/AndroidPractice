package com.random.twojo.security.config;

import com.random.twojo.security.error.CustomAuthenticationFailureHandler;
import com.random.twojo.security.filter.JwtAuthenticationFilter;
import com.random.twojo.security.filter.JwtAuthorizationFilter;
import com.random.twojo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CorsFilter corsFilter;
    @Autowired
    MemberService memberService;
    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//시큐리티에서 세션 사용안함
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()//폼로그인 안씀
                .httpBasic().disable()//기본 로그인 방법안씀 JWT사용하는 로그인 방법 사용할거임
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),memberService))
                .authorizeRequests()
                .anyRequest().permitAll();
        http.formLogin().failureHandler(customAuthenticationFailureHandler);
    }
}
