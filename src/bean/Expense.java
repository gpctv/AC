package bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Expense entity. @author MyEclipse Persistence Tools
 */

public class Expense implements java.io.Serializable {

	// Fields

	private String idNum;
	private String itemType;
	private Date expenseDate;
	private BigDecimal itemNum;
	private String itemName;
	private BigDecimal itemPrice;
	private Date modifyDate;

	// Constructors

	/** default constructor */
	public Expense() {
	}

	/** minimal constructor */
	public Expense(String idNum) {
		this.idNum = idNum;
	}

	/** full constructor */
	public Expense(String idNum, String itemType, Date expenseDate,
			BigDecimal itemNum, String itemName, BigDecimal itemPrice,
			Date modifyDate) {
		this.idNum = idNum;
		this.itemType = itemType;
		this.expenseDate = expenseDate;
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.modifyDate = modifyDate;
	}

	// Property accessors

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Date getExpenseDate() {
		return this.expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public BigDecimal getItemNum() {
		return this.itemNum;
	}

	public void setItemNum(BigDecimal itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}