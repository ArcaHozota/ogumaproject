package jp.co.ogumaproject.ppok.entity;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 勤怠情報エンティティ
 *
 * @author oguma
 * @since 1.02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CmsWorkHours extends BaseBean {

	/**
	 * 社員ID
	 */
	private String employeeId;

	/**
	 * 出勤年月日
	 */
	private Timestamp workDay;

	/**
	 * 出勤時間
	 */
	private Timestamp startTime;

	/**
	 * 退勤時間
	 */
	private Timestamp endTime;

	/**
	 * 勤務時間
	 */
	private Integer workHours;
}
