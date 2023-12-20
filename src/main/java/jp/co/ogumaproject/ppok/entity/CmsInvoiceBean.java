package jp.co.ogumaproject.ppok.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 請求書Bean
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsInvoiceBean extends BaseBean {

	private String employeeId;

	private String workMonth;

	private String companyName;

	private String requestFirst;

	private Integer standardBusinessHours;

	private Integer realWorkHours;

	private BigDecimal salary;

	private BigDecimal disabledGeneration;

	private Date registDay;

	private Date updateDay;

	private String employeeName;
}
