package sakila.vo;

public class Address {
	private int addressId;
	private String address;
	private String address2;
	private String distirct;
	private int cityId;
	private String postalCode;
	private String phone;
	private String lastUpdate;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDistirct() {
		return distirct;
	}
	public void setDistirct(String distirct) {
		this.distirct = distirct;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", address=" + address + ", address2=" + address2 + ", distirct="
				+ distirct + ", cityId=" + cityId + ", postalCode=" + postalCode + ", phone=" + phone + ", lastUpdate="
				+ lastUpdate + "]";
	}
	
	
}
