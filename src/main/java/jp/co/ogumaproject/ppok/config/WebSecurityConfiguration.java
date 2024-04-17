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

import jp.co.sony.ppog.commons.CrowdProjectConstants;
import jp.co.sony.ppog.commons.CrowdProjectURLConstants;
import jp.co.sony.ppog.exception.CrowdProjectException;
import jp.co.sony.ppog.listener.CrowdProjectUserDetailsService;
import jp.co.sony.ppog.utils.CrowdProjectUtils;
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
	private CrowdProjectUserDetailsService crowdPlusUserDetailsService;

	@Bean
	protected AuthenticationManager authenticationManager(final AuthenticationManagerBuilder auth) {
		return auth.authenticationProvider(this.daoAuthenticationProvider()).getObject();
	}

	@Bean
	protected DaoAuthenticationProvider daoAuthenticationProvider() {
		final OgumaProjectDaoAuthenticationProvider provider = new OgumaProjectDaoAuthenticationProvider();
		provider.setUserDetailsService(this.crowdPlusUserDetailsService);
		provider.setPasswordEncoder(new OgumaProjectPasswordEncoder());
		return provider;
	}

	@Bean
	protected SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(CrowdProjectURLConstants.URL_STATIC_RESOURCE, CrowdProjectURLConstants.URL_TO_SIGN_UP,
						CrowdProjectURLConstants.URL_DO_SIGN_UP, CrowdProjectURLConstants.URL_FORGET_PASSWORD,
						CrowdProjectURLConstants.URL_RESET_PASSWORD)
				.permitAll()
				.requestMatchers(CrowdProjectURLConstants.URL_EMPLOYEE_TO_PAGES,
						CrowdProjectURLConstants.URL_EMPLOYEE_PAGINATION,
						CrowdProjectURLConstants.URL_EMPLOYEE_TO_EDITION, CrowdProjectURLConstants.URL_EMPLOYEE_INFOUPD)
				.hasAuthority("employee%retrieve")
				.requestMatchers(CrowdProjectURLConstants.URL_EMPLOYEE_INFOSAVE,
						CrowdProjectURLConstants.URL_EMPLOYEE_TO_ADDITION)
				.hasAuthority("employee%edition")
				.requestMatchers(CrowdProjectURLConstants.URL_EMPLOYEE_DELETE,
						CrowdProjectURLConstants.URL_EMPLOYEE_CHECK_DELETE)
				.hasAuthority("employee%delete")
				.requestMatchers(CrowdProjectURLConstants.URL_ROLE_TO_PAGES,
						CrowdProjectURLConstants.URL_ROLE_PAGINATION)
				.hasAuthority("role%retrieve")
				.requestMatchers(CrowdProjectURLConstants.URL_ROLE_INFOSAVE, CrowdProjectURLConstants.URL_ROLE_INFOUPD,
						CrowdProjectURLConstants.URL_ROLE_CHECK_EDITION, CrowdProjectURLConstants.URL_ROLE_AUTHLIST)
				.hasAuthority("role%edition")
				.requestMatchers(CrowdProjectURLConstants.URL_ROLE_DO_ASSIGNMENT,
						CrowdProjectURLConstants.URL_ROLE_DELETE, CrowdProjectURLConstants.URL_ROLE_CHECK_DELETE)
				.hasAuthority("role%delete").requestMatchers(CrowdProjectURLConstants.URL_DISTRICT_PAGIANTION)
				.hasAuthority("district%retrieve")
				.requestMatchers(CrowdProjectURLConstants.URL_DISTRICT_INFOUPD,
						CrowdProjectURLConstants.URL_DISTRICT_CHECK_EDITION)
				.hasAuthority("district%edition")
				.requestMatchers(CrowdProjectURLConstants.URL_CITY_DISTRICT_LIST,
						CrowdProjectURLConstants.URL_CITY_PAGIANTION)
				.hasAuthority("city%retrieve")
				.requestMatchers(CrowdProjectURLConstants.URL_CITY_INFOSAVE, CrowdProjectURLConstants.URL_CITY_INFOUPD,
						CrowdProjectURLConstants.URL_CITY_CHECK_EDITION)
				.hasAuthority("city%edition").anyRequest().authenticated())
				.csrf(csrf -> csrf.ignoringRequestMatchers(CrowdProjectURLConstants.URL_STATIC_RESOURCE)
						.csrfTokenRepository(new CookieCsrfTokenRepository()))
				.exceptionHandling(
						handling -> handling.authenticationEntryPoint((request, response, authenticationException) -> {
							final ResponseLoginDto responseResult = new ResponseLoginDto(
									HttpStatus.UNAUTHORIZED.value(), authenticationException.getMessage());
							CrowdProjectUtils.renderString(response, responseResult);
						}).accessDeniedHandler((request, response, accessDeniedException) -> {
							final ResponseLoginDto responseResult = new ResponseLoginDto(HttpStatus.FORBIDDEN.value(),
									CrowdProjectConstants.MESSAGE_SPRINGSECURITY_REQUIRED_AUTH);
							CrowdProjectUtils.renderString(response, responseResult);
						}))
				.formLogin(formLogin -> {
					formLogin.loginPage(CrowdProjectURLConstants.URL_TO_LOGIN.getPattern())
							.loginProcessingUrl(CrowdProjectURLConstants.URL_DO_LOGIN.getPattern())
							.defaultSuccessUrl(CrowdProjectURLConstants.URL_TO_MAINMENU.getPattern()).permitAll()
							.usernameParameter("loginAcct").passwordParameter("userPswd");
					try {
						formLogin.and()
								.logout(logout -> logout.logoutUrl(CrowdProjectURLConstants.URL_LOG_OUT.getPattern())
										.logoutSuccessUrl(CrowdProjectURLConstants.URL_TO_LOGIN.getPattern()));
					} catch (final Exception e) {
						throw new OgumaProjectException(CrowdProjectConstants.MESSAGE_STRING_FATAL_ERROR);
					}
				}).rememberMe().key(UUID.randomUUID().toString())
				.tokenValiditySeconds(CrowdProjectConstants.DEFAULT_TOKEN_EXPIRED);
		log.info(CrowdProjectConstants.MESSAGE_SPRING_SECURITY);
		return http.build();
	}
}
