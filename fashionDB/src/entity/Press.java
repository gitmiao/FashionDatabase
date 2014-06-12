package entity;

public class Press {
	private final long id;
	private final String name;
	private final String type;
	private final String country;

	public Press(final long id, final String name, final String type,
			final String country) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getCountry() {
		return country;
	}

	public String toUIDisplayString() {
		return name + "," + country + "," + type;
	}

	@Override
	public String toString() {
		return "Press [id=" + id + ", name=" + name + ", type=" + type
				+ ", country=" + country + "]";
	}
}
