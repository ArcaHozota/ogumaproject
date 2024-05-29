package jp.co.ogumaproject.ppok.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 役割エンティティ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class Role extends CommonEntity {

	private static final long serialVersionUID = 7411663286924761234L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 役割権限関連リスト
	 */
	private List<RoleAuth> roleAuths;
}
