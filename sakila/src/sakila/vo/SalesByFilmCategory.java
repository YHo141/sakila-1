package sakila.vo;

public class SalesByFilmCategory {
	private String category;
	private float totalSales;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(float totalSales) {
		this.totalSales = totalSales;
	}
	
	@Override
	public String toString() {
		return "SalesByFilmCategory [category=" + category + ", totalSales=" + totalSales + "]";
	}
}
