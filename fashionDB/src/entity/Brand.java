package entity;

public class Brand {
	private final long id;
	private final String name;
	private final String countryOfOrigin;
	private final int yearEstablished;

	public Brand(final long id, final String name,
			final String countryOfOrigin, final int yearEstablished) {
		super();
		this.id = id;
		this.name = name;
		this.countryOfOrigin = countryOfOrigin;
		this.yearEstablished = yearEstablished;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public int getYearEstablished() {
		return yearEstablished;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", countryOfOrigin="
				+ countryOfOrigin + ", yearEstablished=" + yearEstablished
				+ "]";
	}
}
