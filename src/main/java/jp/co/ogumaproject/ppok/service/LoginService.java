package jp.co.ogumaproject.ppok.service;

import jp.co.ogumaproject.ppok.dto.LoginForm;

public interface LoginService {

	/**
	 * ログインメソッド
	 *
	 * @param form
	 * @return
	 */
	LoginForm selectLoginInfo(LoginForm form);
}
