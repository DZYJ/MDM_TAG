package com.multigold.model.inventory_transaction;

import java.io.Serializable;
import java.util.List;

/**
 * @author wang bin XML讯息交易
 */
public class InventoryTransactions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryTransactions() {
		super();
	}

	private String transaction_id; // XML讯息交易ID
	private String transaction_date; // XML讯息生成时间
	private String buid; // 国家业务代码
	private String partner_code; // 合作商代码

	private List<InventoryTransaction> inventory_transactions;// 库存交易

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transactionDate) {
		transaction_date = transactionDate;
	}

	public String getBuid() {
		return buid;
	}

	public void setBuid(String buid) {
		this.buid = buid;
	}

	public String getPartner_code() {
		return partner_code;
	}

	public void setPartner_code(String partnerCode) {
		partner_code = partnerCode;
	}

	public List<InventoryTransaction> getInventory_transactions() {
		return inventory_transactions;
	}

	public void setInventory_transactions(
			List<InventoryTransaction> inventoryTransactions) {
		inventory_transactions = inventoryTransactions;
	}
}
