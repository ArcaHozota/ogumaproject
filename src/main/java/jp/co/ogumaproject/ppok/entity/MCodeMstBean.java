package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザ情報エンティティ
 *
 * @author oguma
 * @since 1.02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MCodeMstBean extends BaseBean {

	/**
	 * コードキー
	 */
	private String codeKey;

	/**
	 * コード名称
	 */
	private String codeName;

	/**
	 * コード
	 */
	private String code;

	/**
	 * 値
	 */
	private String value;
}
