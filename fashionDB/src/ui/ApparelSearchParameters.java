package ui;

import java.util.Date;

public class ApparelSearchParameters {

	private final String type;
	private final String brandName;
	private final String season;
	private final Double retailFrom;
	private final Double retailTo;
	private final Long pressId;
	private final Date publishDate;
	private final Double minRate;

	public ApparelSearchParameters(final String type, final String brandName,
			final String season, final Double retailFrom,
			final Double retailTo, final Long pressId, final Date publishDate,
			final Double minRate) {
		super();
		this.type = type;
		this.brandName = brandName;
		this.season = season;
		this.retailFrom = retailFrom;
		this.retailTo = retailTo;
		this.pressId = pressId;
		this.publishDate = publishDate;
		this.minRate = minRate;
	}

	public String getType() {
		return type;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getSeason() {
		return season;
	}

	public Double getRetailFrom() {
		return retailFrom;
	}

	public Double getRetailTo() {
		return retailTo;
	}
	
	public Long getPressId() {
		return pressId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public Double getMinRate() {
		return minRate;
	}

	@Override
	public String toString() {
		return "ApparelSearchParamsters [type=" + type + ", brandName="
				+ brandName + ", season=" + season + ", retailFrom="
				+ retailFrom + ", retailTo=" + retailTo + "]";
	}
}
