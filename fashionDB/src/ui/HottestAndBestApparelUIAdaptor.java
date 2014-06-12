package ui;

import entity.Bag;
import entity.ReviewedApparel;
import entity.Shoes;

public class HottestAndBestApparelUIAdaptor {
	private final String type;
	private final String brand;
	private final String name;
	private final String season;
	private final String color;
	private final String numOfRate;
	private final String avgRate;

	public HottestAndBestApparelUIAdaptor(final ReviewedApparel apparel) {
		if (apparel.getApparel() instanceof Bag) {
			type = "Bag";
		} else if (apparel.getApparel() instanceof Shoes) {
			type = "Shoes";
		} else {
			type = "Clothing";
		}
		brand = apparel.getApparel().getBrandName();
		name = apparel.getApparel().getName();
		season = apparel.getApparel().getSeason();
		color = apparel.getApparel().getColor();
		numOfRate = apparel.getReviewCount() == null ? "n/a" : apparel
				.getReviewCount().toString();
		avgRate = apparel.getAvgRate() == null ? "n/a" : apparel.getAvgRate()
				.toString();
	}

	public String getType() {
		return type;
	}

	public String getBrand() {
		return brand;
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

	public String getNumOfRate() {
		return numOfRate;
	}

	public String getAvgRate() {
		return avgRate;
	}
}
