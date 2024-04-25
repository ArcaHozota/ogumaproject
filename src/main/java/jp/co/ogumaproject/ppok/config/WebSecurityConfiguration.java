package jp.co.ogumaproject.ppok.config;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.commons.OgumaProjectURLConstants;
import jp.co.ogumaproject.ppok.exception.OgumaProjectException;
import jp.co.ogumaproject.ppok.listener.OgumaProjectUserDetailsService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import lombok.extern.log4j.Log4j2;

/**
 * SpringSecurity配置クラス
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Log4j2
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	/**
	 * ログインサービス
	 */
	@Resource
	private OgumaProjectUserDetailsService ogumaProjectUserDetailsService;

	@Bean
	protected AuthenticationManager authenticationManager(final AuthenticationManagerBuilder auth) {
		return auth.authenticationProvider(this.daoAuthenticationProvider()).getObject();
	}

	@Bean
	protected DaoAuthenticationProvider daoAuthenticationProvider() {
		final OgumaProjectDaoAuthenticationProvider provider = new OgumaProjectDaoAuthenticationProvider();
		provider.setUserDetailsService(this.ogumaProjectUserDetailsService);
		provider.setPasswordEncoder(new OgumaProjectPasswordEncoder());
		return provider;
	}

	@Bean
	protected SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(OgumaProjectURLConstants.URL_STATIC_RESOURCE, OgumaProjectURLConstants.URL_TO_SIGN_UP,
						OgumaProjectURLConstants.URL_DO_SIGN_UP, OgumaProjectURLConstants.URL_FORGET_PASSWORD,
						OgumaProjectURLConstants.URL_RESET_PASSWORD)
				.permitAll()
				.requestMatchers(OgumaProjectURLConstants.URL_EMPLOYEE_TO_PAGES,
						OgumaProjectURLConstants.URL_EMPLOYEE_PAGINATION,
						OgumaProjectURLConstants.URL_EMPLOYEE_TO_EDITION, OgumaProjectURLConstants.URL_EMPLOYEE_INFOUPD)
				.hasAuthority("employee%retrieve")
				.requestMatchers(OgumaProjectURLConstants.URL_EMPLOYEE_INFOSAVE,
						OgumaProjectURLConstants.URL_EMPLOYEE_TO_ADDITION)
				.hasAuthority("employee%edition")
				.requestMatchers(OgumaProjectURLConstants.URL_EMPLOYEE_DELETE,
						OgumaProjectURLConstants.URL_EMPLOYEE_CHECK_DELETE)
				.hasAuthority("employee%delete")
				.requestMatchers(OgumaProjectURLConstants.URL_ROLE_TO_PAGES,
						OgumaProjectURLConstants.URL_ROLE_PAGINATION)
				.hasAuthority("role%retrieve")
				.requestMatchers(OgumaProjectURLConstants.URL_ROLE_INFOSAVE, OgumaProjectURLConstants.URL_ROLE_INFOUPD,
						OgumaProjectURLConstants.URL_ROLE_CHECK_EDITION, OgumaProjectURLConstants.URL_ROLE_AUTHLIST)
				.hasAuthority("role%edition")
				.requestMatchers(OgumaProjectURLConstants.URL_ROLE_DO_ASSIGNMENT,
						OgumaProjectURLConstants.URL_ROLE_DELETE, OgumaProjectURLConstants.URL_ROLE_CHECK_DELETE)
				.hasAuthority("role%delete").requestMatchers(OgumaProjectURLConstants.URL_DISTRICT_PAGIANTION)
				.hasAuthority("district%retrieve")
				.requestMatchers(OgumaProjectURLConstants.URL_DISTRICT_INFOUPD,
						OgumaProjectURLConstants.URL_DISTRICT_CHECK_EDITION)
				.hasAuthority("district%edition")
				.requestMatchers(OgumaProjectURLConstants.URL_CITY_DISTRICT_LIST,
						OgumaProjectURLConstants.URL_CITY_PAGIANTION)
				.hasAuthority("city%retrieve")
				.requestMatchers(OgumaProjectURLConstants.URL_CITY_INFOSAVE, OgumaProjectURLConstants.URL_CITY_INFOUPD,
						OgumaProjectURLConstants.URL_CITY_CHECK_EDITION)
				.hasAuthority("city%edition").anyRequest().authenticated())
				.csrf(csrf -> csrf.ignoringRequestMatchers(OgumaProjectURLConstants.URL_STATIC_RESOURCE)
						.csrfTokenRepository(new CookieCsrfTokenRepository()))
				.exceptionHandling(
						handling -> handling.authenticationEntryPoint((request, response, authenticationException) -> {
							final ResponseLoginDto responseResult = new ResponseLoginDto(
									HttpStatus.UNAUTHORIZED.value(), authenticationException.getMessage());
							OgumaProjectUtils.renderString(response, responseResult);
						}).accessDeniedHandler((request, response, accessDeniedException) -> {
							final ResponseLoginDto responseResult = new ResponseLoginDto(HttpStatus.FORBIDDEN.value(),
									OgumaProjectConstants.MESSAGE_SPRINGSECURITY_REQUIRED_AUTH);
							OgumaProjectUtils.renderString(response, responseResult);
						}))
				.formLogin(formLogin -> {
					formLogin.loginPage(OgumaProjectURLConstants.URL_TO_LOGIN.getPattern())
							.loginProcessingUrl(OgumaProjectURLConstants.URL_DO_LOGIN.getPattern())
							.defaultSuccessUrl(OgumaProjectURLConstants.URL_TO_MAINMENU.getPattern()).permitAll()
							.usernameParameter("loginAcct").passwordParameter("userPswd");
					try {
						formLogin.and()
								.logout(logout -> logout.logoutUrl(OgumaProjectURLConstants.URL_LOG_OUT.getPattern())
										.logoutSuccessUrl(OgumaProjectURLConstants.URL_TO_LOGIN.getPattern()));
					} catch (final Exception e) {
						throw new OgumaProjectException(OgumaProjectConstants.MESSAGE_STRING_FATAL_ERROR);
					}
				}).rememberMe(remember -> remember.key(UUID.randomUUID().toString())
						.tokenValiditySeconds(OgumaProjectConstants.DEFAULT_TOKEN_EXPIRED));
		log.info(OgumaProjectConstants.MESSAGE_SPRING_SECURITY);
		return http.build();
	}
}
