package entity;

public abstract class Apparel {
	protected final long id;
	protected final Brand brand;
	protected final String brandName;
	protected final String name;
	protected final String season;
	protected final String color;
	protected final Double retail;
	private String avgRate;

	public Apparel(final long id, final Brand brand, final String name,
			final String season, final String color, final Double retail) {
		super();
		this.id = id;
		this.brand = brand;
		this.brandName = brand.getName();
		this.name = name;
		this.season = season;
		this.color = color;
		this.retail = retail;
		avgRate = null;
	}

	public long getId() {
		return id;
	}

	public Brand getBrand() {
		return brand;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getName() {
		return name;
	}

	public String getSeason() {
		return season;
	}

	public String getColor() {
		return color;
	}

	public Double getRetail() {
		return retail;
	}

	public String getAvgRate() {
		return avgRate == null ? "n/a" : avgRate;
	}

	public void setAvgRate(String avgRate) {
		this.avgRate = avgRate;
	}

	@Override
	public String toString() {
		return "Apparel [id=" + id + ", brand=" + brand + ", name=" + name
				+ ", season=" + season + ", color=" + color + ", retail="
				+ retail + "]";
	}
}
