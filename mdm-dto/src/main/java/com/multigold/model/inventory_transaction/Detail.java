package com.multigold.model.inventory_transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangbin 销售订单
 */
public class Detail implements Serializable {

	public Detail() {
		super();
	}

	private static final long serialVersionUID = 1L;
	private String tie_number; // EC销售订单系号
	private String line_number; // 采购订单行编号
	private String style_number; // 款式码
	private String part_number; // sku码
	private String promotion_code; // 促销单号
	private String qty; // 商品数量
	private String vendor_part_cost; // 供应商商品成本价（出库后）"
	private String part_cost; // 多边商品成本价（无税）
	private String part_price; // EC商品销售价格
//	private String base_fee; // 基础费用(按克计）
//	private String process_fee_bg; // 加工费(按克计）
//	private String process_fee_bu; // 加工费(按件计）
//	private String fb_fee_bg; // 运费(按克计）
//	private String check_fee_bu; // 检测费(按件计）
//	private String other_fee_bu; // 其他费用(按克计）
//	private String other_fee_bg; // 其他费用(按件计）
	private String part_cost_tax; //成本（含税）
	private String process_cost_tax; //加工费（含税）
	
	private String net_price; // SKU级销售净价（去除满减，不去除红、蓝券的金额）
	private String item_type; // 商品类型
	private String item_category; // 商品分类
	private String po_type; // 采购订单类型
	private String weight;// 克重

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	private List<ProcessCodeList> process_code_lists;// 流水号

	public String getTie_number() {
		return tie_number;
	}

	public void setTie_number(String tieNumber) {
		tie_number = tieNumber;
	}

	public String getLine_number() {
		return line_number;
	}

	public void setLine_number(String lineNumber) {
		line_number = lineNumber;
	}

	public String getStyle_number() {
		return style_number;
	}

	public void setStyle_number(String styleNumber) {
		style_number = styleNumber;
	}

	public String getPart_number() {
		return part_number;
	}

	public void setPart_number(String partNumber) {
		part_number = partNumber;
	}

	public String getPromotion_code() {
		return promotion_code;
	}

	public void setPromotion_code(String promotionCode) {
		promotion_code = promotionCode;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getVendor_part_cost() {
		return vendor_part_cost;
	}

	public void setVendor_part_cost(String vendorPartCost) {
		vendor_part_cost = vendorPartCost;
	}

	public String getPart_cost() {
		return part_cost;
	}

	public void setPart_cost(String partCost) {
		part_cost = partCost;
	}

	public String getPart_price() {
		return part_price;
	}

	public void setPart_price(String partPrice) {
		part_price = partPrice;
	}

	public String getNet_price() {
		return net_price;
	}

	public void setNet_price(String netPrice) {
		net_price = netPrice;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String itemType) {
		item_type = itemType;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String itemCategory) {
		item_category = itemCategory;
	}

	public String getPo_type() {
		return po_type;
	}

	public void setPo_type(String poType) {
		po_type = poType;
	}

	public List<ProcessCodeList> getProcess_code_lists() {
		return process_code_lists;
	}

	public void setProcess_code_lists(List<ProcessCodeList> processCodeLists) {
		process_code_lists = processCodeLists;
	}

	public String getPart_cost_tax() {
		return part_cost_tax;
	}

	public void setPart_cost_tax(String partCostTax) {
		part_cost_tax = partCostTax;
	}

	public String getProcess_cost_tax() {
		return process_cost_tax;
	}

	public void setProcess_cost_tax(String processCostTax) {
		process_cost_tax = processCostTax;
	}

}
