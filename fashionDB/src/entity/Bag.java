package entity;

public class Bag extends Apparel {

	private final String category;
	private final double height;
	private final double width;
	private final double depth;

	public Bag(final long id, final Brand brand, final String name,
			final String season, final String color, final Double retail,
			final String category, final double height, final double width,
			final double depth) {
		super(id, brand, name, season, color, retail);
		this.category = category;
		this.height = height;
		this.width = width;
		this.depth = depth;
	}

	public String getCategory() {
		return category;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getDepth() {
		return depth;
	}

	@Override
	public String toString() {
		return "Bag [category=" + category + ", height=" + height + ", width="
				+ width + ", depth=" + depth + ", id=" + id + ", brand="
				+ brand + ", brandName=" + brandName + ", name=" + name
				+ ", season=" + season + ", color=" + color + ", retail="
				+ retail + "]";
	}
}
