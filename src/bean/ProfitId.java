package bean;

import java.util.Date;

/**
 * ProfitId entity. @author MyEclipse Persistence Tools
 */

public class ProfitId implements java.io.Serializable {

	// Fields

	private Date profitDate;
	private String idNum;

	// Constructors

	/** default constructor */
	public ProfitId() {
	}

	/** full constructor */
	public ProfitId(Date profitDate, String idNum) {
		this.profitDate = profitDate;
		this.idNum = idNum;
	}

	// Property accessors

	public Date getProfitDate() {
		return this.profitDate;
	}

	public void setProfitDate(Date profitDate) {
		this.profitDate = profitDate;
	}

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProfitId))
			return false;
		ProfitId castOther = (ProfitId) other;

		return ((this.getProfitDate() == castOther.getProfitDate()) || (this
				.getProfitDate() != null && castOther.getProfitDate() != null && this
				.getProfitDate().equals(castOther.getProfitDate())))
				&& ((this.getIdNum() == castOther.getIdNum()) || (this
						.getIdNum() != null && castOther.getIdNum() != null && this
						.getIdNum().equals(castOther.getIdNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getProfitDate() == null ? 0 : this.getProfitDate()
						.hashCode());
		result = 37 * result
				+ (getIdNum() == null ? 0 : this.getIdNum().hashCode());
		return result;
	}

}