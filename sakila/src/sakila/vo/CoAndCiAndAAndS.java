package sakila.vo;

public class CoAndCiAndAAndS {
	private Country country;
	private City city;
	private Address address;
	private Staff staff;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	@Override
	public String toString() {
		return "CoAndCiAndAAndS [country=" + country + ", city=" + city + ", address=" + address + ", staff=" + staff
				+ "]";
	}
}
