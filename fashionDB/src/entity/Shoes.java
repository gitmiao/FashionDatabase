package entity;

public class Shoes extends Apparel {

	private final String category;
	private final double height;

	public Shoes(final long id, final Brand brand, final String name,
			final String season, final String color, final Double retail,
			final String category, final double height) {
		super(id, brand, name, season, color, retail);
		this.category = category;
		this.height = height;
	}

	public String getCategory() {
		return category;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "Shoes [category=" + category + ", height=" + height + ", id="
				+ id + ", brand=" + brand + ", brandName=" + brandName
				+ ", name=" + name + ", season=" + season + ", color=" + color
				+ ", retail=" + retail + "]";
	}
}
