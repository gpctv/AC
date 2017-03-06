package bean;

/**
 * SpendItem entity. @author MyEclipse Persistence Tools
 */

public class SpendItem implements java.io.Serializable {

	// Fields

	private String idNum;
	private String itemName;
	private String itemType;

	// Constructors

	/** default constructor */
	public SpendItem() {
	}

	/** minimal constructor */
	public SpendItem(String idNum) {
		this.idNum = idNum;
	}

	/** full constructor */
	public SpendItem(String idNum, String itemName, String itemType) {
		this.idNum = idNum;
		this.itemName = itemName;
		this.itemType = itemType;
	}

	// Property accessors

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}