package jp.co.ogumaproject.ppok.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsSalaryDetailBean extends BaseBean {

	// 社員ID
	private String employeeId;

	// 給料年月
	private String salaryMonth;

	// 給料
	private BigDecimal salary;

	// 勤務時間
	private BigDecimal workhours;

	// 残業控除
	private BigDecimal disabledGeneration;

	// 費用
	private BigDecimal cost;

	// 実給(税込)
	private BigDecimal actualSalary;

	// 結合項目
	private String employeeNm;

}
