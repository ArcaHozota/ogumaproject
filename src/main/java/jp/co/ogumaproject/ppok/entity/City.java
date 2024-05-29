package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 都市エンティティ
 *
 * @author ArkamaHozota
 * @since 2.31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class City extends CommonEntity {

	private static final long serialVersionUID = 6544430264440743625L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 読み方
	 */
	private String pronunciation;

	/**
	 * 都道府県ID
	 */
	private Long districtId;

	/**
	 * 都道府県名称
	 */
	private String districtName;

	/**
	 * 人口数量
	 */
	private Long population;

	/**
	 * 市町村旗
	 */
	private String cityFlag;
}
