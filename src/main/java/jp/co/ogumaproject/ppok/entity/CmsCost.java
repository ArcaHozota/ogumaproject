package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 費用エンティティ
 *
 * @author oguma
 * @since 1.02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class CmsCost extends BaseBean {

	/**
	 * 費用ID
	 */
	private String costId;

	/**
	 * 費用発生日
	 */
	private String costDay;

	/**
	 * 費用コメント
	 */
	private String costNote;

	/**
	 * 費用種別
	 */
	private String costType;

	private Integer costAmount;

	private String hasTax;

	private String taxNote;

	private String employeeId;

	/** 関連テーブル情報 */
	// 社員名
	private String employeeNm;

	// 費用種別名
	private String costTypeName;

	// 税金有無
	private String hasTaxName;

	// 費用発生年月（一覧画面検索項目）
	private String costMonth;
}
