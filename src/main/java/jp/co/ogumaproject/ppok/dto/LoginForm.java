package jp.co.ogumaproject.ppok.dto;

import java.util.List;

import jp.co.ogumaproject.ppok.entity.CmsEmployee;
import lombok.Data;

/**
 * ログインフォーム
 */
@Data
public class LoginForm {

	/* 検索結果 */
	private List<CmsEmployee> results;

	private String loginId;

	private String password;
}