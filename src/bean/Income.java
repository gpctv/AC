package bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Income entity. @author MyEclipse Persistence Tools
 */

public class Income implements java.io.Serializable {

	// Fields

	private String idNum;
	private BigDecimal amount;
	private Date incomeDate;
	private String account;
	private Date modifyDate;

	// Constructors

	/** default constructor */
	public Income() {
	}

	/** minimal constructor */
	public Income(String idNum) {
		this.idNum = idNum;
	}

	/** full constructor */
	public Income(String idNum, BigDecimal amount, Date incomeDate,
			String account, Date modifyDate) {
		this.idNum = idNum;
		this.amount = amount;
		this.incomeDate = incomeDate;
		this.account = account;
		this.modifyDate = modifyDate;
	}

	// Property accessors

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getIncomeDate() {
		return this.incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}