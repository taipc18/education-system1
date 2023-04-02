package Main.Config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import Main.Util.PasswordUtil;
import Main.entity.Account;
import Main.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService acSV;
	@Autowired
	PasswordUtil passwordUtil;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(username -> {
			try {
				Account user = acSV.findById(username);
				String password = passwordUtil.getBCryptPasswordEncoder().encode(user.getPassword());
				boolean activeacc = user.isActive();
				String[] roles = user.getAuthorities().stream()
						.map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).disabled(!activeacc).build();
			} catch (Exception e) {
				e.printStackTrace();
				throw new UsernameNotFoundException(username + "not found");
			}
		});
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/dathang/**").authenticated()
		.antMatchers("/quanly/**").hasAnyRole("STAF", "ADMIN")
		.antMatchers("/rest/authorities").hasRole("ADMIN")
		.anyRequest().permitAll();
		
		http.formLogin()
		.loginPage("/baomat/login/form")
		.loginProcessingUrl("/baomat/login")
		.defaultSuccessUrl("/baomat/login/success", false)
		.failureUrl("/baomat/login/error");
		
		http.rememberMe()
		.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
		.accessDeniedPage("/baomat/unauthoried");
		
		http.logout()
		.logoutUrl("/baomat/logoff")
		.logoutSuccessUrl("/baomat/logoff/success");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
