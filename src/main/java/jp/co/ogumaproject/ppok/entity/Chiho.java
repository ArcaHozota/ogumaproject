package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地方エンティティ
 *
 * @author ArkamaHozota
 * @since 1.66
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class Chiho extends CommonEntity {

	private static final long serialVersionUID = 5192360218210693284L;

	/**
	 * 名称
	 */
	private String name;
}
