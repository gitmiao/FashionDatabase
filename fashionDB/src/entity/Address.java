package entity;

public class Address {
	private final long id;
	private final String country;
	private final String state;
	private final String city;
	private final String street;
	private final String zipCode;

	public Address(final long id, final String country, final String state,
			final String city, final String street, final String zipCode) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public long getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String toUSFormatString() {
		return street + " " + city + ", " + state + " " + country + " "
				+ zipCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", state="
				+ state + ", city=" + city + ", street=" + street
				+ ", zipCode=" + zipCode + "]";
	}
}
