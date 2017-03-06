package bean;

import java.math.BigDecimal;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account implements java.io.Serializable {

	// Fields

	private String account;
	private BigDecimal isAdmin;
	private String password;
	private String idNum;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String account) {
		this.account = account;
	}

	/** full constructor */
	public Account(String account, BigDecimal isAdmin, String password,
			String idNum) {
		this.account = account;
		this.isAdmin = isAdmin;
		this.password = password;
		this.idNum = idNum;
	}

	// Property accessors

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(BigDecimal isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

}