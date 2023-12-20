package jp.co.ogumaproject.ppok.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MarketStore {

	private String storeId;

	private String storeName;

	private String phoneNo;

	private String address;

	private String startDay;

	private String finishDay;
}