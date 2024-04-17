package jp.co.ogumaproject.ppok.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 社員エンティティ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Data
public final class Employee implements Serializable {

	private static final long serialVersionUID = -7478708453453699683L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * アカウント
	 */
	private String loginAccount;

	/**
	 * ユーザ名称
	 */
	private String username;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * メール
	 */
	private String email;

	/**
	 * 生年月日
	 */
	private LocalDate dateOfBirth;

	/**
	 * 作成時間
	 */
	private LocalDateTime createdTime;

	/**
	 * 論理削除フラグ
	 */
	private String delFlg;
}
