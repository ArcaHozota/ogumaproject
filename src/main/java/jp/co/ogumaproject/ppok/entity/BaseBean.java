package jp.co.ogumaproject.ppok.entity;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 基本エンティティ
 *
 * @author oguma
 * @since 1.02
 */
@Data
public class BaseBean {

	/**
	 * 登録される時間
	 */
	private Timestamp registrationTime;

	/**
	 * 更新時間
	 */
	private Timestamp updatedTime;

	/**
	 * 論理削除フラグ
	 */
	private String delFlg;
}
