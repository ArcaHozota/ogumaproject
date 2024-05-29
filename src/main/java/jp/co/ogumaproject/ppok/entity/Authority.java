package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 権限エンティティ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class Authority extends CommonEntity {

	private static final long serialVersionUID = -1152271767975364197L;

	/**
	 * 権限名称
	 */
	private String name;

	/**
	 * 権限論理名称
	 */
	private String title;

	/**
	 * 権限親ID
	 */
	private Long categoryId;
}
