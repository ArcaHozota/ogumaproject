package jp.co.ogumaproject.ppok.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MarketGoods extends BaseBean {

	private String goodsId;

	private String goodsName;

	private String type;

	private String maker;

	private BigDecimal purchasePrice;

	private BigDecimal salesPrice;
}
