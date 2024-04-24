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

	public static final AntPathRequestMatcher URL_STATIC_RESOURCE = new AntPathRequestMatcher("/static/**",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_PAGES = new AntPathRequestMatcher(
			"/pgcrowd/employee/toPages", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_PAGINATION = new AntPathRequestMatcher(
			"/pgcrowd/employee/pagination", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_INFOSAVE = new AntPathRequestMatcher(
			"/pgcrowd/employee/infoSave", RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_ADDITION = new AntPathRequestMatcher(
			"/pgcrowd/employee/toAddition", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_TO_EDITION = new AntPathRequestMatcher(
			"/pgcrowd/employee/toEdition", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_INFOUPD = new AntPathRequestMatcher(
			"/pgcrowd/employee/infoUpdate", RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_DELETE = new AntPathRequestMatcher(
			"/pgcrowd/employee/infoDelete/**", RequestMethod.DELETE.toString());

	public static final AntPathRequestMatcher URL_EMPLOYEE_CHECK_DELETE = new AntPathRequestMatcher(
			"/pgcrowd/employee/checkDelete", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_TO_PAGES = new AntPathRequestMatcher("/pgcrowd/role/toPages",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_PAGINATION = new AntPathRequestMatcher(
			"/pgcrowd/role/pagination", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_AUTHLIST = new AntPathRequestMatcher("/pgcrowd/role/getAuthlist",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_GET_ASSIGNED = new AntPathRequestMatcher(
			"/pgcrowd/role/getAssigned", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_INFOSAVE = new AntPathRequestMatcher("/pgcrowd/role/infoSave",
			RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_ROLE_INFOUPD = new AntPathRequestMatcher("/pgcrowd/role/infoUpdate",
			RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_ROLE_DO_ASSIGNMENT = new AntPathRequestMatcher(
			"/pgcrowd/role/doAssignment", RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_ROLE_DELETE = new AntPathRequestMatcher("/pgcrowd/role/infoDelete/**",
			RequestMethod.DELETE.toString());

	public static final AntPathRequestMatcher URL_ROLE_CHECK_DELETE = new AntPathRequestMatcher(
			"/pgcrowd/role/checkDelete", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_ROLE_CHECK_EDITION = new AntPathRequestMatcher(
			"/pgcrowd/role/checkEdition", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_DISTRICT_PAGIANTION = new AntPathRequestMatcher(
			"/pgcrowd/district/pagination", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_DISTRICT_INFOUPD = new AntPathRequestMatcher(
			"/pgcrowd/district/infoUpdate", RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_DISTRICT_CHECK_EDITION = new AntPathRequestMatcher(
			"/pgcrowd/district/checkEdition", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CITY_PAGIANTION = new AntPathRequestMatcher(
			"/pgcrowd/city/pagination", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CITY_DISTRICT_LIST = new AntPathRequestMatcher(
			"/pgcrowd/city/districtlist", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CITY_INFOSAVE = new AntPathRequestMatcher("/pgcrowd/city/infoSave",
			RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_CITY_INFOUPD = new AntPathRequestMatcher("/pgcrowd/city/infoUpdate",
			RequestMethod.PUT.toString());

	public static final AntPathRequestMatcher URL_CITY_CHECK_EDITION = new AntPathRequestMatcher(
			"/pgcrowd/city/checkEdition", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_TO_SIGN_UP = new AntPathRequestMatcher("/pgcrowd/employee/toSignUp",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_DO_SIGN_UP = new AntPathRequestMatcher("/pgcrowd/employee/toroku",
			RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_FORGET_PASSWORD = new AntPathRequestMatcher(
			"/pgcrowd/forget/password", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_RESET_PASSWORD = new AntPathRequestMatcher(
			"/pgcrowd/employee/reset/password", RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_TO_LOGIN = new AntPathRequestMatcher("/pgcrowd/employee/login",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_DO_LOGIN = new AntPathRequestMatcher("/pgcrowd/employee/doLogin",
			RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_LOG_OUT = new AntPathRequestMatcher("/pgcrowd/employee/logout",
			RequestMethod.POST.toString());

	public static final AntPathRequestMatcher URL_TO_MAINMENU = new AntPathRequestMatcher("/pgcrowd/to/mainmenu",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_MENU_INITIAL = new AntPathRequestMatcher("/pgcrowd/menu/initial",
			RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_CATEGORY_INITIAL = new AntPathRequestMatcher(
			"/pgcrowd/category/initial", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_TO_DISTRICT_PAGES = new AntPathRequestMatcher(
			"/pgcrowd/category/toDistrictPages", RequestMethod.GET.toString());

	public static final AntPathRequestMatcher URL_TO_CITY_PAGES = new AntPathRequestMatcher(
			"/pgcrowd/category/toCityPages", RequestMethod.GET.toString());
}
