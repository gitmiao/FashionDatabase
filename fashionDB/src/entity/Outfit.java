package entity;

public class Outfit {
	private final Bag bag;
	private final Shoes shoes;
	private final Clothing clothing;
	private final String source;

	public Outfit(final Bag bag, final Shoes shoes, final Clothing clothing,
			final String source) {
		super();
		this.bag = bag;
		this.shoes = shoes;
		this.clothing = clothing;
		this.source = source;
	}

	public Bag getBag() {
		return bag;
	}

	public Shoes getShoes() {
		return shoes;
	}

	public Clothing getClothing() {
		return clothing;
	}

	public String getSource() {
		return source;
	}
}
