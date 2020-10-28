package sakila.vo;

public class RentalAndFilm {
	private Rental rental;
	private Film film;
	
	public Rental getRental() {
		return rental;
	}
	public void setRental(Rental rental) {
		this.rental = rental;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	
	@Override
	public String toString() {
		return "RentalAndFilm [rental=" + rental + ", film=" + film + "]";
	}
}
