package sakila.vo;

public class JoinToTable {
	private Address address;
	private Film film;
	private City city;
	private Country country;
	private Rental rental;
	private Customer customer;
	private Staff staff;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Rental getRental() {
		return rental;
	}
	public void setRental(Rental rental) {
		this.rental = rental;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	@Override
	public String toString() {
		return "JoinToTable [address=" + address + ", film=" + film + ", city=" + city + ", country=" + country
				+ ", rental=" + rental + ", customer=" + customer + ", staff=" + staff + "]";
	}
}
