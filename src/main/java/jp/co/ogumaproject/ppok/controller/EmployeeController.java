package jp.co.ogumaproject.ppok.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.commons.OgumaProjectURLConstants;
import jp.co.ogumaproject.ppok.dto.EmployeeDto;
import jp.co.ogumaproject.ppok.dto.RoleDto;
import jp.co.ogumaproject.ppok.entity.Role;
import jp.co.ogumaproject.ppok.service.IEmployeeService;
import jp.co.ogumaproject.ppok.service.IRoleService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 社員コントローラ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Controller
@RequestMapping(OgumaProjectURLConstants.URL_EMPLOYEE_COMMON)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmployeeController {

	/**
	 * 社員サービスインターフェス
	 */
	private final IEmployeeService iEmployeeService;

	/**
	 * 役割サービスインターフェス
	 */
	private final IRoleService iRoleService;

	/**
	 * 削除権限チェック
	 *
	 * @return ResultDto<String>
	 */
	@GetMapping(OgumaProjectURLConstants.URL_CHECK_DELETE)
	@ResponseBody
	public ResultDto<String> checkDelete() {
		return ResultDto.successWithoutData();
	}

	/**
	 * ログインアカウントを重複するかどうかを確認する
	 *
	 * @param loginAccount ログインアカウント
	 * @return ResultDto<String>
	 */
	@GetMapping(OgumaProjectURLConstants.URL_CHECK_DUPLICATED)
	@ResponseBody
	public ResultDto<String> checkDuplicated(@RequestParam("loginAcct") final String loginAccount) {
		return this.iEmployeeService.checkDuplicated(loginAccount);
	}

	/**
	 * IDによって社員情報を削除する
	 *
	 * @param userId 社員ID
	 * @return ResultDto<String>
	 */
	@DeleteMapping(OgumaProjectURLConstants.URL_INFO_DELETE)
	@ResponseBody
	public ResultDto<String> deleteInfo(@RequestParam("userId") final Long userId) {
		this.iEmployeeService.remove(userId);
		return ResultDto.successWithoutData();
	}

	/**
	 * キーワードによってページング検索
	 *
	 * @param pageNum ページ数
	 * @param keyword キーワード
	 * @return ResultDto<Pagination<Employee>>
	 */
	@GetMapping(OgumaProjectURLConstants.URL_PAGINATION)
	@ResponseBody
	public ResultDto<Pagination<EmployeeDto>> pagination(
			@RequestParam(name = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(name = "keyword", defaultValue = OgumaProjectUtils.EMPTY_STRING) final String keyword,
			@RequestParam(name = "userId", required = false) final Long userId,
			@RequestParam(name = "authChkFlag", defaultValue = "false") final String authChkFlag) {
		final Pagination<EmployeeDto> employees = this.iEmployeeService.getEmployeesByKeyword(pageNum, keyword, userId,
				authChkFlag);
		return ResultDto.successWithData(employees);
	}

	/**
	 * パスワードをリセット
	 *
	 * @param account     アカウント
	 * @param email       メール
	 * @param dateOfBirth 生年月日
	 * @return ModelAndView
	 */
	@PostMapping(OgumaProjectURLConstants.URL_RESET_PASSWORD)
	public ModelAndView resetPassword(@RequestParam("account") final String account,
			@RequestParam("email") final String email, @RequestParam("dateOfBirth") final String dateOfBirth) {
		final EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setLoginAccount(account);
		employeeDto.setEmail(email);
		employeeDto.setDateOfBirth(dateOfBirth);
		final Boolean resetPassword = this.iEmployeeService.resetPassword(employeeDto);
		if (Boolean.FALSE.equals(resetPassword)) {
			final ModelAndView modelAndView = new ModelAndView("admin-forgot");
			modelAndView.addObject("resetMsg", OgumaProjectConstants.MESSAGE_STRING_PROHIBITED);
			return modelAndView;
		}
		final ModelAndView modelAndView = new ModelAndView("admin-login");
		modelAndView.addObject("resetMsg", OgumaProjectConstants.MESSAGE_RESET_PASSWORD);
		modelAndView.addObject("registeredEmail", email);
		return modelAndView;
	}

	/**
	 * 情報追加
	 *
	 * @param employeeDto 社員情報DTO
	 * @return ResultDto<String>
	 */
	@PostMapping(OgumaProjectURLConstants.URL_INFO_SAVE)
	@ResponseBody
	public ResultDto<String> saveInfo(@RequestBody final EmployeeDto employeeDto) {
		this.iEmployeeService.save(employeeDto);
		return ResultDto.successWithoutData();
	}

	/**
	 * 情報追加初期表示
	 *
	 * @param userId ユーザID
	 * @return ModelAndView
	 */
	@GetMapping(OgumaProjectURLConstants.URL_TO_ADDITION)
	public ModelAndView toAddition() {
		final List<RoleDto> employeeRolesById = this.iRoleService.getEmployeeRolesByEmployeeId(null);
		final ModelAndView modelAndView = new ModelAndView("admin-addinfo");
		modelAndView.addObject(OgumaProjectConstants.ATTRNAME_EMPLOYEEROLES, employeeRolesById);
		return modelAndView;
	}

	/**
	 * 情報更新初期表示
	 *
	 * @param id 社員ID
	 * @return ModelAndView
	 */
	@GetMapping(OgumaProjectURLConstants.URL_TO_EDITION)
	public ModelAndView toEdition(@RequestParam("editId") final Long editId,
			@RequestParam(name = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(name = "authChkFlag", defaultValue = "false") final String authChkFlag) {
		final EmployeeDto employee = this.iEmployeeService.getEmployeeById(editId);
		if (Boolean.FALSE.equals(Boolean.valueOf(authChkFlag))) {
			final ModelAndView modelAndView = new ModelAndView("admin-editinfo2");
			modelAndView.addObject(OgumaProjectConstants.ATTRNAME_EDITED_INFO, employee);
			final Role role = this.iRoleService.getRoleById(employee.getRoleId());
			modelAndView.addObject(OgumaProjectConstants.ATTRNAME_EMPLOYEEROLES, role);
			modelAndView.addObject(OgumaProjectConstants.ATTRNAME_PAGE_NUMBER, pageNum);
			return modelAndView;
		}
		final List<RoleDto> employeeRolesById = this.iRoleService.getEmployeeRolesByEmployeeId(editId);
		final ModelAndView modelAndView = new ModelAndView("admin-editinfo");
		modelAndView.addObject(OgumaProjectConstants.ATTRNAME_EDITED_INFO, employee);
		modelAndView.addObject(OgumaProjectConstants.ATTRNAME_EMPLOYEEROLES, employeeRolesById);
		modelAndView.addObject(OgumaProjectConstants.ATTRNAME_PAGE_NUMBER, pageNum);
		return modelAndView;
	}

	/**
	 * 一覧画面へ遷移する
	 *
	 * @param pageNum ページ数
	 * @return ModelAndView
	 */
	@GetMapping(OgumaProjectURLConstants.URL_TO_PAGES)
	public ModelAndView toPages(@RequestParam(name = "pageNum", defaultValue = "1") final Integer pageNum) {
		final ModelAndView modelAndView = new ModelAndView("admin-pages");
		modelAndView.addObject(OgumaProjectConstants.ATTRNAME_PAGE_NUMBER, pageNum);
		return modelAndView;
	}

	/**
	 * 社員登録
	 *
	 * @param email       メール
	 * @param password    パスワード
	 * @param dateOfBirth 生年月日
	 * @return ModelAndView
	 */
	@PostMapping(OgumaProjectURLConstants.URL_DO_SIGN_UP)
	public ModelAndView toroku(@RequestParam("email") final String email,
			@RequestParam("password") final String password, @RequestParam("dateOfBirth") final String dateOfBirth) {
		final EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setEmail(email);
		employeeDto.setPassword(password);
		employeeDto.setDateOfBirth(dateOfBirth);
		final Boolean toroku = this.iEmployeeService.register(employeeDto);
		final ModelAndView mAndView = new ModelAndView("admin-login");
		if (Boolean.FALSE.equals(toroku)) {
			mAndView.addObject("torokuMsg", OgumaProjectConstants.MESSAGE_TOROKU_FAILURE);
		} else {
			mAndView.addObject("torokuMsg", OgumaProjectConstants.MESSAGE_TOROKU_SUCCESS);
		}
		mAndView.addObject("registeredEmail", email);
		return mAndView;
	}

	/**
	 * 情報更新
	 *
	 * @param employeeDto 社員情報DTO
	 * @return ResultDto<String>
	 */
	@PutMapping(OgumaProjectURLConstants.URL_INFO_UPDATE)
	@ResponseBody
	public ResultDto<String> updateInfo(@RequestBody final EmployeeDto employeeDto) {
		return this.iEmployeeService.update(employeeDto);
	}
}
