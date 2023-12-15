package jp.co.ogumaproject.ppok.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * プロジェクトコンスタント
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OgumaConstants {

	/** 必須入力 */
	public static final String ERROR_NOT_EMPTY = "ご入力ください。";

	/** 数位入力 */
	public static final String ERROR_NUMBER_ONLY = "数字をご入力ください。";

	/** データ存在チェック（参照、更新、削除の場合） */
	public static final String NOT_EXIST_CHECK = "データがすでに存在しません。";

	/** データ存在チェック（参照、更新、削除の場合） */
	public static final String RECORD_IS_CHANGED = "対象レコードがすでに更新されました。";

	/** データ存在チェック（参照、更新、削除の場合） */
	public static final String RECORD_IS_DELETED = "対象レコードがすでに削除されました。";

	/** 一覧検索結果が存在しない */
	public static final String NO_SUCH_RECORD = "検索結果はありません。";

	/** メッセージコンバーターの設置 */
	public static final String MESSAGE_SPRING_MVCCONVERTOR = "拡張メッセージコンバーターの設置は完了しました。";

	/** 静的リソースのマッピングが開始 */
	public static final String MESSAGE_SPRING_MAPPER = "静的リソースのマッピングが開始しました。";

	/** アプリは正常に起動 */
	public static final String MESSAGE_SPRING_APPLICATION = "アプリは正常に起動しました。";

	/** SpringSecurity作動中 */
	public static final String MESSAGE_SPRING_SECURITY = "スプリングセキュリティ作動中。";

	/* 社員管理 画面遷移 */
	public static final String GOTO_USER_LIST = "/employee/employeelist";
	public static final String GOTO_USER_ADD = "/employee/employeeadd";
	public static final String GOTO_USER_VIEW = "/employee/employeeview";
	public static final String GOTO_USER_EIDT = "/employee/employeeedit";
	public static final String GOTO_SAMPLE = "/sample/samplepage";

	/* 社員管理 画面遷移（REDIRECT） */
	public static final String GOTO_USER_LIST_REDIRECT = "redirect:/employee/employeelist";
	public static final String GOTO_USER_ADD_REDIRECT = "redirect:/employee/employeeadd";
	public static final String GOTO_USER_VIEW_REDIRECT = "redirect:/employee/employeeview";
	public static final String GOTO_USER_EIDT_REDIRECT = "redirect:/employee/employeeedit";
	public static final String GOTO_SAMPLE_REDIRECT = "redirect:/sample/samplepage";

	/* 会社管理 画面遷移 */
	public static final String GOTO_COMPANY_LIST = "/company/companylist";
	public static final String GOTO_COMPANY_ADD = "/company/companyadd";
	public static final String GOTO_COMPANY_VIEW = "/company/companyview";
	public static final String GOTO_COMPANY_EIDT = "/company/companyedit";

	/* 会社管理 画面遷移（REDIRECT） */
	public static final String GOTO_COMPANY_LIST_REDIRECT = "redirect:/company/companylist";
	public static final String GOTO_COMPANY_ADD_REDIRECT = "redirect:/company/companyadd";
	public static final String GOTO_COMPANY_VIEW_REDIRECT = "redirect:/company/companyview";
	public static final String GOTO_COMPANY_EIDT_REDIRECT = "redirect:/company/companyedit";

	/* 社員一覧 */
	public static final String CMS_EMPLOYEE_LIST = "/employee/cmsemployeelist";
	public static final String CMS_EMPLOYEE_ADD = "/employee/cmsemployeeadd";
	public static final String CMS_EMPLOYEE_VIEW = "/employee/cmsemployeeview";
	public static final String CMS_EMPLOYEE_EDIT = "/employee/cmsemployeeedit";

	/* 社員一覧（REDIRECT） */
	public static final String CMS_EMPLOYEE_LIST_REDIRECT = "redirect:/employee/cmsemployeelist";
	public static final String CMS_EMPLOYEE_ADD_REDIRECT = "redirect:/employee/cmsemployeeadd";
	public static final String CMS_EMPLOYEE_VIEW_REDIRECT = "redirect:/employee/cmsemployeeview";
	public static final String CMS_EMPLOYEE_EDIT_REDIRECT = "redirect:/employee/cmsemployeeedit";

	/* 費用一覧 */
	public static final String CMS_COST_LIST = "/cost/cmscostlist";
	public static final String CMS_COST_ADD = "/cost/cmscostadd";
	public static final String CMS_COST_VIEW = "/cost/cmscostview";
	public static final String CMS_COST_EDIT = "/cost/cmscostedit";

	/* 費用一覧（REDIRECT） */
	public static final String CMS_COST_LIST_REDIRECT = "redirect:/cost/cmscostlist";
	public static final String CMS_COST_ADD_REDIRECT = "redirect:/cost/cmscostadd";
	public static final String CMS_COST_VIEW_REDIRECT = "redirect:/cost/cmscostview";
	public static final String CMS_COST_EDIT_REDIRECT = "redirect:/cost/cmscostedit";

	/* 勤怠一覧 */
	public static final String CMS_WORKHOURS_LIST = "/workhour/cmsworkhourlist";
	public static final String CMS_WORKHOURS_ADD = "/workhour/cmsworkhouradd";
	public static final String CMS_WORKHOURS_VIEW = "/workhour/cmsworkhourview";
	public static final String CMS_WORKHOURS_EDIT = "/workhour/cmsworkhouredit";

	/* 勤怠一覧（REDIRECT） */
	public static final String CMS_WORKHOURS_LIST_REDIRECT = "redirect:/cmsworkhour/cmsworkhourlist";
	public static final String CMS_WORKHOURS_ADD_REDIRECT = "redirect:/cmsworkhour/cmsworkhouradd";
	public static final String CMS_WORKHOURS_VIEW_REDIRECT = "redirect:/cmsworkhour/cmsworkhourview";
	public static final String CMS_WORKHOURS_EDIT_REDIRECT = "redirect:/cmsworkhour/cmsworkhouredit";

	/* 給料一覧 */
	public static final String CMS_SALARY_LIST = "/salary/cmssalarylist";
}
