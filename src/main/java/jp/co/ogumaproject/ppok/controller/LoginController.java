package jp.co.ogumaproject.ppok.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ogumaproject.ppok.dto.LoginForm;
import jp.co.ogumaproject.ppok.entity.CmsEmployee;
import jp.co.ogumaproject.ppok.service.LoginService;

/**
 * ログインコントローラ
 */
@Controller
@RequestMapping("/oguma/tsujo")
public class LoginController {

	@Resource
	private LoginService loginService;

	/**
	 * ユーザー情報検索
	 *
	 * @param userSearchRequest リクエストデータ
	 * @param model             Model
	 * @return ユーザー情報一覧画面
	 */
	@PostMapping(value = "/login", params = "gotoMenu")
	public ModelAndView gotoMenu(@ModelAttribute final LoginForm form) {
		final CmsEmployee requestBean = new CmsEmployee();
		requestBean.setLoginId(form.getLoginId());
		requestBean.setPassword(form.getPassword());
		final LoginForm formChk = this.loginService.selectLoginInfo(form);
		if ((formChk.getResults() != null) && (!formChk.getResults().isEmpty())) {
			final ModelAndView modelAndView = new ModelAndView("menu");
			modelAndView.addObject("employeeId", formChk.getResults().get(0).getEmployeeId());
			modelAndView.addObject("employeeName", formChk.getResults().get(0).getName());
			modelAndView.addObject("jobType", formChk.getResults().get(0).getJobType());
			return modelAndView;
		}
		final ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("error", "ユーザまたはパスワードが正しくありません");
		modelAndView.addObject("userForm", form);
		return modelAndView;
	}
//
//	/**
//	 * ユーザー情報検索画面を表示
//	 *
//	 * @param model Model
//	 * @return ユーザー情報一覧画面
//	 */
//	@GetMapping(value = "/login")
//	public ModelAndView init() {
//		final ModelAndView modelAndView = new ModelAndView("login");
//		modelAndView.addObject("token", "authorized");
//		modelAndView.addObject("userForm", new CmsEmployeeForm());
//		return modelAndView;
//	}
}