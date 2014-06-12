package entity;

public class Store {
	private final long id;
	private final String name;
	private final Address address;
	private final String phone;
	private final String website;

	public Store(final long id, final String name, final Address address,
			final String phone, final String website) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.website = website;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", website=" + website + "]";
	}
}
