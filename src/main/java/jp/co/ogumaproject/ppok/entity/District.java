package jp.co.ogumaproject.ppok.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地域エンティティ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class District extends CommonEntity {

	private static final long serialVersionUID = -4147005411969906444L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 州都ID
	 */
	private Long shutoId;

	/**
	 * 地方ID
	 */
	private Long chihoId;

	/**
	 * 都道府県旗
	 */
	private String districtFlag;

	/**
	 * 地域地方関連
	 */
	private Chiho chiho;

	/**
	 * 地域都市関連リスト
	 */
	private List<City> cities;
}
