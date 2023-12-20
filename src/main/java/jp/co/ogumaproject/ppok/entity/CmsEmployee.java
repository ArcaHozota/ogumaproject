package jp.co.ogumaproject.ppok.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public final class CmsEmployee extends BaseBean {

	private String employeeId;

	private String name;

	private String sex;

	private String sexForList;

	private String birthday;

	private String address;

	private String phoneNo;

	private String joiningDate;

	private String mail;

	private String jobType;

	private String jobLevel;

	private String employeeKbn;

	private String employeeKbnForList;

	private BigDecimal salary;

	private String employeeType;

	private String hasTax;

	private Integer topWorkHour;

	private Integer downWorkHour;

	private String loginId;

	private String password;
}
