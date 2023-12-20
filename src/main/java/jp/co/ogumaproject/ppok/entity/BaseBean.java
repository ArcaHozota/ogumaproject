package jp.co.ogumaproject.ppok.entity;

import java.time.LocalDateTime;

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
	private LocalDateTime registeredTime;

	/**
	 * 更新時間
	 */
	private LocalDateTime updatedTime;

	/**
	 * 論理削除フラグ
	 */
	private String delFlg;
}
