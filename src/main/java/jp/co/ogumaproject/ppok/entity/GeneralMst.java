package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 汎用マスタエンティティ
 *
 * @author oguma
 * @since 1.02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GeneralMst extends BaseBean {

	/**
	 * 汎用Key
	 */
	private String generalKey;

	/**
	 * 汎用ID
	 */
	private String generalId;

	/**
	 * 汎用名
	 */
	private String generalName;

	/**
	 * 汎用説明
	 */
	private String generalDescription;
}
