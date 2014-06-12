package ui;

public class ApparelAvailSearchParams {
	private final long apparelId;
	private final Double size;

	public ApparelAvailSearchParams(final long apparelId, final Double size) {
		super();
		this.apparelId = apparelId;
		this.size = size;
	}

	public long getApparelId() {
		return apparelId;
	}

	public Double getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "ApparelAvailSearchParams [apparelId=" + apparelId + ", size="
				+ size + "]";
	}
}
