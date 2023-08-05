package com.gl.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigurationWithJDBC extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// JDBC authentication, method chaining
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
				.withUser(User.withUsername("employee").password(getPasswordEncoder().encode("staff"))
						.roles("Employee"))
				.withUser(
						User.withUsername("admin").password(getPasswordEncoder().encode("admin")).roles("EmployeeAdmin"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/employeeManagement/list", "/employeeManagement/showFormForUpdate").hasRole("Employee")
		.antMatchers("/employeeManagement/list", "/employeeManagement/showFormForUpdate", "/employeeManagement/showFormForAdd", "/employeeManagement/delete", "/employeeManagement/save").hasAnyRole("EmployeeAdmin")
		.antMatchers("/").permitAll()
		.and()
		.formLogin();
	}


	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");

	}
}
