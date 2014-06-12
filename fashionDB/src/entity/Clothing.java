package entity;

public class Clothing extends Apparel {

	private final String category;
	private final String occasion;

	public Clothing(final long id, final Brand brand, final String name,
			final String season, final String color, final Double retail,
			final String category, final String occasion) {
		super(id, brand, name, season, color, retail);
		this.category = category;
		this.occasion = occasion;
	}

	public String getCategory() {
		return category;
	}

	public String getOccasion() {
		return occasion;
	}

	@Override
	public String toString() {
		return "Clothing [category=" + category + ", occasion=" + occasion
				+ ", id=" + id + ", brand=" + brand + ", brandName="
				+ brandName + ", name=" + name + ", season=" + season
				+ ", color=" + color + ", retail=" + retail + "]";
	}
}
