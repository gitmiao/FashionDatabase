package ui;

public class AvailabilityUIAdaptor {
	public static final String NA = "n/a";

	// show "n/a" if it is null
	private final String price;
	// show "n/a" if price is null;
	private final String discount;
	private final String storeName;
	private final String phone;
	// show "n/a" for null;
	private final String website;
	private final String address;

	public AvailabilityUIAdaptor(final String price, final String discount,
			final String storeName, final String phone, final String website,
			final String address) {
		super();
		this.price = price;
		this.discount = discount;
		this.storeName = storeName;
		this.phone = phone;
		this.website = website;
		this.address = address;
	}

	public String getPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	public String getAddress() {
		return address;
	}
}
