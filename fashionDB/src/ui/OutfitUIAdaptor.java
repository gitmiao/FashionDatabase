package ui;

import entity.Bag;
import entity.Clothing;
import entity.Outfit;
import entity.Shoes;

public class OutfitUIAdaptor {
	private final String bagBrand;
	private final String bagName;
	private final String bagSeason;
	private final String bagColor;
	private final String shoesBrand;
	private final String shoesName;
	private final String shoesSeason;
	private final String shoesColor;
	private final String clothingBrand;
	private final String clothingName;
	private final String clothingSeason;
	private final String clothingColor;
	private final String source;

	public OutfitUIAdaptor(final Bag bag, final Shoes shoes,
			final Clothing clothing, final String source) {
		super();
		this.bagBrand = bag.getBrandName();
		this.bagName = bag.getName();
		this.bagSeason = bag.getSeason();
		this.bagColor = bag.getColor();
		this.shoesBrand = shoes.getBrandName();
		this.shoesName = shoes.getName();
		this.shoesSeason = shoes.getSeason();
		this.shoesColor = shoes.getColor();
		this.clothingBrand = clothing.getBrandName();
		this.clothingName = clothing.getName();
		this.clothingSeason = clothing.getSeason();
		this.clothingColor = clothing.getColor();
		this.source = source;
	}

	public OutfitUIAdaptor(final Outfit outfit) {
		this(outfit.getBag(), outfit.getShoes(), outfit.getClothing(), outfit
				.getSource());
	}

	public String getBagBrand() {
		return bagBrand;
	}

	public String getBagName() {
		return bagName;
	}

	public String getBagSeason() {
		return bagSeason;
	}

	public String getBagColor() {
		return bagColor;
	}

	public String getShoesBrand() {
		return shoesBrand;
	}

	public String getShoesName() {
		return shoesName;
	}

	public String getShoesSeason() {
		return shoesSeason;
	}

	public String getShoesColor() {
		return shoesColor;
	}

	public String getClothingBrand() {
		return clothingBrand;
	}

	public String getClothingName() {
		return clothingName;
	}

	public String getClothingSeason() {
		return clothingSeason;
	}

	public String getClothingColor() {
		return clothingColor;
	}

	public String getSource() {
		return source;
	}
}
