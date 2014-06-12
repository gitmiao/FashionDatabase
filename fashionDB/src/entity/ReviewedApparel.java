package entity;

public class ReviewedApparel {
	private final Apparel apparel;
	private final Integer reviewCount;
	private final Double avgRate;

	public ReviewedApparel(final Apparel apparel, final Integer reviewCount,
			final Double avgRate) {
		super();
		this.apparel = apparel;
		this.reviewCount = reviewCount;
		this.avgRate = avgRate;
	}

	public Apparel getApparel() {
		return apparel;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public Double getAvgRate() {
		return avgRate;
	}
}
