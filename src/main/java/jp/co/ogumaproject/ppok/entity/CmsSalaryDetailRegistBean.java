package jp.co.ogumaproject.ppok.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CmsSalaryDetailRegistBean {
	
	//社員ID
	private String employeeId;
	
	//名前
	private String name;
	
	//給料
	private BigDecimal salary;
	
	//年月
	private String month;
	
	//勤務時間
	private BigDecimal hours;

	//勤務時間上限
	private BigDecimal topWorkHour;

	//勤務時間下限
	private BigDecimal downWorkHour;
	
	//費用合計
	private BigDecimal costAmount;

}
