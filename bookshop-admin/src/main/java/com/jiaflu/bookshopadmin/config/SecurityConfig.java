package com.jiaflu.bookshopadmin.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private DataSource dataSource;


    @Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		tokenRepository.setCreateTableOnStartup(true);
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/book").permitAll()
//                .anyRequest().authenticated();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        SpringSocialConfigurer configurer = new SpringSocialConfigurer();
        configurer.signupUrl("/regist.html");

        http.httpBasic()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/auth")
                .usernameParameter("user")
                .passwordParameter("pass")
                .and()
//			.rememberMe()
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(60)
//				.and()
//			.sessionManagement()
//				.sessionFixation().changeSessionId()
//				.invalidSessionUrl("/session.html")
//				.maximumSessions(1)
//				.maxSessionsPreventsLogin(true)
//				.and()
//				.and()
                .csrf().disable()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.and()
                .authorizeRequests()
                .antMatchers("/webjars/**",
                        "/login", "/book", "/login.html", "/regist.html",
                        "/auth/**", "/getRegistUserInfo","/registUser", "/session.html")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(configurer);
        //.access("@bookSecurity.check(authentication,request)");

    }


}
