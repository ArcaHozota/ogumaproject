package jp.co.ogumaproject.ppok.commons;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * プロジェクトURLコンスタント
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OgumaProjectURLConstants {

	public static final String URL_EMPLOYEE_COMMON = "/oguma/employee";

	public static final String URL_ROLE_COMMON = "/oguma/role";

	public static final String URL_DISTRICT_COMMON = "/oguma/district";

	public static final String URL_CITY_COMMON = "/oguma/city";

	public static final String URL_CATEGORY_COMMON = "/oguma/category";

	public static final String URL_PAGINATION = "/pagination";

	public static final String URL_TO_PAGES = "/toPages";

	public static final String URL_INFO_SAVE = "/infoSave";

	public static final String URL_INFO_UPDATE = "/infoUpdate";

	public static final String URL_INFO_DELETE = "/infoDelete/**";

	public static final String URL_TO_ADDITION = "/toAddition";

	public static final String URL_TO_EDITION = "/toEdition";

	public static final String URL_CHECK_DELETE = "/checkDelete";

	public static final String URL_CHECK_EDITION = "/checkEdition";

	public static final String URL_GET_AUTHLIST = "/getAuthlist";

	public static final String URL_GET_ASSIGNED = "/getAssigned";

	public static final String URL_DO_ASSIGNMENT = "/doAssignment";

	public static final String URL_GET_DISTRICTS = "/getDistricts";

	public static final String URL_TO_SIGN_UP = "/toSignUp";

	public static final String URL_DO_SIGN_UP = "/toroku";

	public static final String URL_FORGET_PASSWORD = "/forget/password";

	public static final String URL_RESET_PASSWORD = "/reset/password";

	public static final String URL_TO_LOGIN = "/login";

	public static final String URL_DO_LOGIN = "/doLogin";

	public static final String URL_LOG_OUT = "/logout";

	public static final String URL_INITIAL = "/initial";

	public static final String URL_MENU_INITIAL = "/menuInitial";

	public static final String URL_TO_MAINMENU = "/toMainmenu";

	public static final String URL_TO_CITY_PAGES = "/toCityPages";

	public static final String URL_TO_DISTRICT_PAGES = "/toDistrictPages";

	public static final AntPathRequestMatcher URL_STATIC_RESOURCE = new AntPathRequestMatcher("/static/**",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_INDEX = new AntPathRequestMatcher("/index",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_PAGES = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_TO_PAGES), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_PAGINATION = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_PAGINATION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_INFOSAVE = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_INFO_SAVE), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_ADDITION = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_TO_ADDITION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_EDITION = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_TO_EDITION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_INFOUPD = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_INFO_UPDATE), RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_DELETE = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_INFO_DELETE), RequestMethod.DELETE.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_CHECK_DELETE = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_CHECK_DELETE), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_TO_PAGES = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_TO_PAGES), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_PAGINATION = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_PAGINATION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_AUTHLIST = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_GET_AUTHLIST), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_GET_ASSIGNED = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_GET_ASSIGNED), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_INFOSAVE = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_INFO_SAVE), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_ROLE_INFOUPD = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_INFO_UPDATE), RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_ROLE_DO_ASSIGNMENT = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_DO_ASSIGNMENT), RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_ROLE_DELETE = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_INFO_DELETE), RequestMethod.DELETE.toString());

	public static final AntPathRequestMatcher URL_ROLE_CHECK_DELETE = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_CHECK_DELETE), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_CHECK_EDITION = new AntPathRequestMatcher(
			URL_ROLE_COMMON.concat(URL_CHECK_EDITION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_DISTRICT_PAGIANTION = new AntPathRequestMatcher(
			URL_DISTRICT_COMMON.concat(URL_PAGINATION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_DISTRICT_INFOUPD = new AntPathRequestMatcher(
			URL_DISTRICT_COMMON.concat(URL_INFO_UPDATE), RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_DISTRICT_CHECK_EDITION = new AntPathRequestMatcher(
			URL_DISTRICT_COMMON.concat(URL_CHECK_EDITION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CITY_PAGIANTION = new AntPathRequestMatcher(
			URL_CITY_COMMON.concat(URL_PAGINATION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CITY_DISTRICT_LIST = new AntPathRequestMatcher(
			URL_CITY_COMMON.concat(URL_GET_DISTRICTS), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CITY_INFOSAVE = new AntPathRequestMatcher(
			URL_CITY_COMMON.concat(URL_INFO_SAVE), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_CITY_INFOUPD = new AntPathRequestMatcher(
			URL_CITY_COMMON.concat(URL_INFO_UPDATE), RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_CITY_CHECK_EDITION = new AntPathRequestMatcher(
			URL_CITY_COMMON.concat(URL_CHECK_EDITION), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_SIGN_UP = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_TO_SIGN_UP), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TOROKU = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_DO_SIGN_UP), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_FORGET_PASSWORD = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_FORGET_PASSWORD), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_RESET_PASSWORD = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_RESET_PASSWORD), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_LOGIN = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_TO_LOGIN), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_DO_LOGIN = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_DO_LOGIN), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_LOG_OUT = new AntPathRequestMatcher(
			URL_EMPLOYEE_COMMON.concat(URL_LOG_OUT), RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_CATEGORY_TO_MAINMENU = new AntPathRequestMatcher(
			URL_CATEGORY_COMMON.concat(URL_TO_MAINMENU), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CATEGORY_MENU_INITIAL = new AntPathRequestMatcher(
			URL_CATEGORY_COMMON.concat(URL_MENU_INITIAL), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CATEGORY_INITIAL = new AntPathRequestMatcher(
			URL_CATEGORY_COMMON.concat(URL_INITIAL), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CATEGORY_TO_DISTRICT_PAGES = new AntPathRequestMatcher(
			URL_CATEGORY_COMMON.concat(URL_TO_DISTRICT_PAGES), RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CATEGORY_TO_CITY_PAGES = new AntPathRequestMatcher(
			URL_CATEGORY_COMMON.concat(URL_TO_CITY_PAGES), RequestMethod.GET.toString());
}
