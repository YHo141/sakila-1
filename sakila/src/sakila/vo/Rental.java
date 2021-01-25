package sakila.vo;

public class Rental {
	private int rentalId;
	private String rentalDate;
	private int inventoryId;
	private int customerId;
	private String returnDate;
	private int staffId;
	private String lastUpdate;
	private String scheduledReturnDate;
	private String arrears;
	private String customerName;
	
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getScheduledReturnDate() {
		return scheduledReturnDate;
	}
	public void setScheduledReturnDate(String scheduledReturnDate) {
		this.scheduledReturnDate = scheduledReturnDate;
	}
	public String getArrears() {
		return arrears;
	}
	public void setArrears(String arrears) {
		this.arrears = arrears;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", inventoryId=" + inventoryId
				+ ", customerId=" + customerId + ", returnDate=" + returnDate + ", staffId=" + staffId + ", lastUpdate="
				+ lastUpdate + ", scheduledReturnDate=" + scheduledReturnDate + ", arrears=" + arrears
				+ ", customerName=" + customerName + "]";
	}
}
