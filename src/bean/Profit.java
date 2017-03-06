package bean;

import java.math.BigDecimal;

/**
 * Profit entity. @author MyEclipse Persistence Tools
 */

public class Profit implements java.io.Serializable {

	// Fields

	private ProfitId id;
	private BigDecimal profitAmount;
	private BigDecimal profitRemain;

	// Constructors

	/** default constructor */
	public Profit() {
	}

	/** minimal constructor */
	public Profit(ProfitId id) {
		this.id = id;
	}

	/** full constructor */
	public Profit(ProfitId id, BigDecimal profitAmount, BigDecimal profitRemain) {
		this.id = id;
		this.profitAmount = profitAmount;
		this.profitRemain = profitRemain;
	}

	// Property accessors

	public ProfitId getId() {
		return this.id;
	}

	public void setId(ProfitId id) {
		this.id = id;
	}

	public BigDecimal getProfitAmount() {
		return this.profitAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public BigDecimal getProfitRemain() {
		return this.profitRemain;
	}

	public void setProfitRemain(BigDecimal profitRemain) {
		this.profitRemain = profitRemain;
	}

}