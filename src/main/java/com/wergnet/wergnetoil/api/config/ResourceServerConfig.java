package com.wergnet.wergnetoil.api.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Profile("oauth-security")
@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter { // .hhtpBasic().and()
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	
//	@Override
	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROLE");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/cards").permitAll()
//				.anyRequest().authenticated()
//				.and()
////			.httpBasic().and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//			.csrf().disable();
//	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}
}

