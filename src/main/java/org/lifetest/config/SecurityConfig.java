package org.lifetest.config;

import org.lifetest.security.customize.JWTAuthenticationEntryPoint;
import org.lifetest.security.customize.JWTAuthenticationProvider;
import org.lifetest.security.filter.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JWTAuthenticationProvider authProvider;

  @Autowired
  JWTAuthenticationEntryPoint entryPoint;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .anyRequest().authenticated().and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .authenticationProvider(authProvider).exceptionHandling()
        .authenticationEntryPoint(entryPoint);

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // ignore 登入
    web.ignoring().antMatchers("/auth/login");
  }

}
