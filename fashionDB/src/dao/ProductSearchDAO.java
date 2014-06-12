package dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import entity.Address;
import entity.Apparel;
import entity.Bag;
import entity.Brand;
import entity.Clothing;
import entity.Outfit;
import entity.Press;
import entity.ReviewedApparel;
import entity.Shoes;
import entity.Store;

import ui.ApparelAvailSearchParams;
import ui.ApparelSearchParameters;

public class ProductSearchDAO extends AbstractDAO {
	private static final String PERMENANT = "permenant";

	public ProductSearchDAO() {
		connectToDB();
	}

	public List<String> getAllBrandNames() {
		final List<String> names = new ArrayList<String>();
		try {
			rs = stmt.executeQuery("SELECT DISTINCT(name) FROM Brand");
			while (rs.next()) {
				names.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return names;
	}

	public List<String> getAllSeasons() {
		final List<String> names = new ArrayList<String>();
		try {
			rs = stmt
					.executeQuery("SELECT DISTINCT(season) FROM Apparel WHERE season <> "
							+ stringArgu(PERMENANT));
			while (rs.next()) {
				names.add(rs.getString("season"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return names;
	}

	private static final String COMMON_APPAREL_FIELDS = "SELECT b.bid, b.name as brandName, b.country_of_origin, "
			+ "b.year_established, a.aid, a.name as apparelName, a.season, a.color, a.retail, ";
	private static final String COMMON_APPAREL_TABLEJOINS = "FROM Brand b JOIN Apparel a ON (a.brand_id=b.bid) ";

	private final List<Bag> findBags(final String otherJoins,
			final String whereClause) {
		final List<Bag> bags = new ArrayList<Bag>();
		final String sql = COMMON_APPAREL_FIELDS
				+ "bag.category, bag.height, bag.width, bag.depth "
				+ COMMON_APPAREL_TABLEJOINS
				+ " JOIN Bag bag ON (bag.aid=a.aid) " + otherJoins
				+ whereClause;
		try {
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				final BigDecimal retail = rs.getBigDecimal("retail");
				bags.add(new Bag(rs.getLong("aid"), new Brand(
						rs.getLong("bid"), rs.getString("brandName"), rs
								.getString("country_of_origin"), rs
								.getInt("year_established")), rs
						.getString("apparelName"), rs.getString("season"), rs
						.getString("color"), retail == null ? null : retail
						.doubleValue(), rs.getString("category"), rs
						.getDouble("height"), rs.getDouble("width"), rs
						.getDouble("depth")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bags;
	}

	private final List<Shoes> findShoes(final String otherJoins,
			final String whereClause) {
		final List<Shoes> shoes = new ArrayList<Shoes>();
		final String sql = COMMON_APPAREL_FIELDS
				+ "shoes.category, shoes.height " + COMMON_APPAREL_TABLEJOINS
				+ " JOIN Shoes shoes ON (shoes.aid=a.aid) " + otherJoins
				+ whereClause;
		try {
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				final BigDecimal retail = rs.getBigDecimal("retail");
				shoes.add(new Shoes(rs.getLong("aid"), new Brand(rs
						.getLong("bid"), rs.getString("brandName"), rs
						.getString("country_of_origin"), rs
						.getInt("year_established")), rs
						.getString("apparelName"), rs.getString("season"), rs
						.getString("color"), retail == null ? null : retail
						.doubleValue(), rs.getString("category"), rs
						.getDouble("height")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return shoes;
	}

	private final List<Clothing> findClothing(final String otherJoins,
			final String whereClause) {
		final List<Clothing> clothings = new ArrayList<Clothing>();
		final String sql = COMMON_APPAREL_FIELDS
				+ "clothing.category, clothing.occasion "
				+ COMMON_APPAREL_TABLEJOINS
				+ " JOIN Clothing clothing ON (clothing.aid=a.aid) "
				+ otherJoins + whereClause;
		try {
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				final BigDecimal retail = rs.getBigDecimal("retail");
				clothings.add(new Clothing(rs.getLong("aid"), new Brand(rs
						.getLong("bid"), rs.getString("brandName"), rs
						.getString("country_of_origin"), rs
						.getInt("year_established")), rs
						.getString("apparelName"), rs.getString("season"), rs
						.getString("color"), retail == null ? null : retail
						.doubleValue(), rs.getString("category"), rs
						.getString("occasion")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return clothings;
	}

	private boolean whereOrAnd(final StringBuilder str,
			final boolean firstCondition) {
		if (firstCondition) {
			str.append(" WHERE ");
		} else {
			str.append(" AND ");
		}
		return false;
	}

	public List<Apparel> findApparel(final ApparelSearchParameters params) {
		final StringBuilder whereClause = new StringBuilder();
		boolean firstCondition = true;
		if (params.getBrandName() != null) {
			firstCondition = whereOrAnd(whereClause, firstCondition);
			whereClause.append(" b.name =" + stringArgu(params.getBrandName()));
		}
		if (params.getSeason() != null) {
			firstCondition = whereOrAnd(whereClause, firstCondition);
			whereClause.append(" (a.season =" + stringArgu(params.getSeason())
					+ " OR a.season= " + stringArgu(PERMENANT) + ")");
		}
		if (params.getRetailFrom() != null) {
			firstCondition = whereOrAnd(whereClause, firstCondition);
			whereClause.append(" a.retail >=" + params.getRetailFrom());
		}
		if (params.getRetailTo() != null) {
			firstCondition = whereOrAnd(whereClause, firstCondition);
			whereClause.append(" a.retail <=" + params.getRetailTo());
		}

		final StringBuilder otherJoins = new StringBuilder();
		if (params.getPressId() != null) {
			otherJoins.append("JOIN Featured f ON (f.apparel_id = a.aid) ");
			firstCondition = whereOrAnd(whereClause, firstCondition);
			whereClause.append("f.press_id =" + params.getPressId());
			if (params.getPublishDate() != null) {
				firstCondition = whereOrAnd(whereClause, firstCondition);
				whereClause.append("f.publish_date ="
						+ stringArgu(new SimpleDateFormat("dd-MMM-yyyy")
								.format(params.getPublishDate())));
			}
		}

		if (params.getMinRate() != null) {
			firstCondition = whereOrAnd(whereClause, firstCondition);
			whereClause
					.append("NVL("
							+ "(SELECT AVG(rate) FROM Reviewed WHERE apparel_id=a.aid), -1) >"
							+ params.getMinRate());
		}
		final List<Apparel> apparels = new ArrayList<Apparel>();
		if ("bag".equals(params.getType())) {
			for (final Bag bag : findBags(otherJoins.toString(),
					whereClause.toString())) {
				final Double avgRate = getAvgRate(bag.getId());
				if (avgRate != null) {
					bag.setAvgRate(avgRate.toString());
				}
				apparels.add(bag);
			}
		} else if ("shoes".equals(params.getType())) {
			for (final Shoes shoes : findShoes(otherJoins.toString(),
					whereClause.toString())) {
				final Double avgRate = getAvgRate(shoes.getId());
				if (avgRate != null) {
					shoes.setAvgRate(avgRate.toString());
				}
				apparels.add(shoes);
			}
		} else {
			for (final Clothing clothing : findClothing(otherJoins.toString(),
					whereClause.toString())) {
				final Double avgRate = getAvgRate(clothing.getId());
				if (avgRate != null) {
					clothing.setAvgRate(avgRate.toString());
				}
				apparels.add(clothing);
			}
		}
		return apparels;
	}

	private final Double getAvgRate(final long apparelId) {
		Double rate = null;
		final String sql = "SELECT AVG(rate) AS avgRate FROM Reviewed WHERE apparel_id= "
				+ apparelId;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				final BigDecimal avgRate = rs.getBigDecimal("avgRate");
				if (avgRate != null) {
					rate = avgRate.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				}
			}
			return rate;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Map<Long, String> getAllProductDisplayNames() {
		final Map<Long, String> names = new LinkedHashMap<Long, String>();
		final String sqlBase = "SELECT a.aid, a.name FROM Apparel a";
		try {
			rs = stmt.executeQuery(sqlBase
					+ " JOIN Bag b ON (b.aid = a.aid) ORDER BY a.name");
			while (rs.next()) {
				names.put(rs.getLong("aid"), "Bag: " + rs.getString("name"));
			}
			rs = stmt.executeQuery(sqlBase
					+ " JOIN Shoes s ON (s.aid = a.aid) ORDER BY a.name");
			while (rs.next()) {
				names.put(rs.getLong("aid"), "Shoes: " + rs.getString("name"));
			}
			rs = stmt.executeQuery(sqlBase
					+ " JOIN Clothing c ON (c.aid = a.aid) ORDER BY a.name");
			while (rs.next()) {
				names.put(rs.getLong("aid"),
						"Clothing: " + rs.getString("name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return names;
	}

	public Double getRetailprice(final long apparelId) {
		final String sql = "SELECT retail FROM Apparel WHERE retail IS NOT NULL AND aid = "
				+ apparelId;
		Double retail = null;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				retail = rs.getDouble("retail");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return retail;
	}

	/**
	 * 
	 * @return pair of store id and price
	 */
	public Map<Long, Double> findAvailability(
			final ApparelAvailSearchParams searchParams) {
		final Map<Long, Double> result = new LinkedHashMap<Long, Double>();
		try {
			boolean found = false;
			rs = stmt
					.executeQuery("SELECT store_id, price FROM Bag_Availability WHERE bag_id ="
							+ searchParams.getApparelId() + " ORDER BY price");
			while (rs.next()) {
				found = true;
				result.put(rs.getLong("store_id"), rs.getDouble("price"));
			}
			for (final String type : new String[] { "Shoes", "Clothing" }) {
				if (found) {
					return result;
				}
				final String sql = "SELECT store_id, sizes, price FROM type_Availability WHERE type_id = "
						+ searchParams.getApparelId() + " ORDER BY Price";
				rs = stmt.executeQuery(sql.replaceAll("type", type));
				while (rs.next()) {
					found = true;
					final String sizes = rs.getString("sizes");
					boolean add = false;
					if (searchParams.getSize() != null) {
						for (final String size : sizes.split(",")) {
							if (Double.valueOf(size).equals(
									searchParams.getSize())) {
								add = true;
								break;
							}
						}
					} else {
						add = true;
					}
					if (add) {
						result.put(rs.getLong("store_id"),
								rs.getDouble("price"));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public Store findStore(final long storeId) {
		final String sql = "SELECT s.sid, s.name, s.phone_number, s.website, "
				+ "a.id, a.country, a.state, a.city, a.street, a.zipcode "
				+ "FROM Store s JOIN Address a ON (a.id = s.address_id) "
				+ "WHERE s.sid= " + storeId;
		try {
			Store store = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				store = new Store(rs.getLong("sid"), rs.getString("name"),
						new Address(rs.getLong("id"), rs.getString("country"),
								rs.getString("state"), rs.getString("city"), rs
										.getString("street"), rs
										.getString("zipcode")),
						rs.getString("phone_number"), rs.getString("website"));
			}
			return store;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static final class OutfitSearchResult {
		private final long bagId;
		private final long clothingId;
		private final long shoesId;
		private final String source;

		public OutfitSearchResult(final long bagId, final long clothingId,
				final long shoesId, final String source) {
			super();
			this.bagId = bagId;
			this.clothingId = clothingId;
			this.shoesId = shoesId;
			this.source = source;
		}
	}

	// if apparelId is null, search all outfits
	public List<Outfit> getOutfits(final Long apparelId) {
		final List<Outfit> result = new ArrayList<Outfit>();
		final List<OutfitSearchResult> searchResult = new ArrayList<OutfitSearchResult>();
		final String sql;
		if (apparelId != null) {
			sql = "SELECT bag_id, clothing_id, shoes_id, source FROM Outfit "
					+ "WHERE bag_id =" + apparelId + " OR clothing_id="
					+ apparelId + " OR shoes_id= " + apparelId;
		} else {
			sql = "SELECT bag_id, clothing_id, shoes_id, source FROM Outfit ";
		}
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				final long bagId = rs.getLong("bag_id");
				final long clothingId = rs.getLong("clothing_id");
				final long shoesId = rs.getLong("shoes_id");
				final String source = rs.getString("source");
				searchResult.add(new OutfitSearchResult(bagId, clothingId,
						shoesId, source));
			}
			for (final OutfitSearchResult oneResult : searchResult) {
				final Bag bag = findBags("", "WHERE a.aid =" + oneResult.bagId)
						.get(0);
				final Clothing clothing = findClothing("",
						"WHERE a.aid =" + oneResult.clothingId).get(0);
				final Shoes shoes = findShoes("",
						"WHERE a.aid =" + oneResult.shoesId).get(0);
				result.add(new Outfit(bag, shoes, clothing, oneResult.source));
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Press> getAllPresses() {
		final List<Press> presses = new ArrayList<Press>();
		try {
			rs = stmt.executeQuery("SELECT * FROM Press");
			while (rs.next()) {
				presses.add(new Press(rs.getLong("pid"), rs.getString("name"),
						rs.getString("type"), rs.getString("country")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return presses;
	}

	public List<Date> getAllPublishDates() {
		final List<Date> dates = new ArrayList<Date>();
		try {
			rs = stmt
					.executeQuery("SELECT DISTINCT(publish_date) FROM Featured");
			while (rs.next()) {
				dates.add(rs.getDate("publish_date"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dates;
	}

	public void findHottestApparel(final int daysBack,
			final List<ReviewedApparel> hottestApparel,
			final List<ReviewedApparel> bestApparel) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -daysBack);
		final Date startDate = cal.getTime();
		final String sqlBase = "SELECT a.aid, COUNT(*) AS num, AVG(rate) AS avg_rate FROM Apparel a "
				+ "JOIN Reviewed r ON (r.apparel_id =a.aid) ";
		for (final String type : new String[] { "JOIN Bag b ON (b.aid=a.aid) ",
				"JOIN Shoes s ON (s.aid=a.aid) ",
				"JOIN Clothing c ON (c.aid=a.aid) " }) {
			final List<Long> hottestProductIds = new ArrayList<Long>();
			int mostNumOfReviewed = 0;
			final List<Long> bestProductIds = new ArrayList<Long>();
			BigDecimal bestAvgRate = BigDecimal.valueOf(-1);
			try {
				final String sql = sqlBase
						+ type
						+ " WHERE review_date >="
						+ stringArgu(new SimpleDateFormat("dd-MMM-yyyy")
								.format(startDate)) + " GROUP BY a.aid";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					final long productId = rs.getLong("aid");
					final int count = rs.getInt("num");
					final BigDecimal rate = rs.getBigDecimal("avg_rate");
					if (rate != null) {
						// count can't be 0 if rate is not null
						if (count >= mostNumOfReviewed) {
							if (count > mostNumOfReviewed) {
								hottestProductIds.clear();
							}
							mostNumOfReviewed = count;
							hottestProductIds.add(productId);
						}
						if (rate.compareTo(bestAvgRate) >= 0) {
							if (rate.compareTo(bestAvgRate) > 0) {
								bestProductIds.clear();
							}
							bestAvgRate = rate;
							bestProductIds.add(productId);
						}
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			for (final Long productId : hottestProductIds) {
				final Apparel apparel;
				if (type.contains("Bag")) {
					apparel = findBags("", "WHERE a.aid =" + productId).get(0);
				} else if (type.contains("Shoes")) {
					apparel = findShoes("", "WHERE a.aid =" + productId).get(0);
				} else {
					apparel = findClothing("", "WHERE a.aid =" + productId)
							.get(0);
				}
				// avg rate if "Unknown" for hottest product
				hottestApparel.add(new ReviewedApparel(apparel,
						mostNumOfReviewed, null));
			}
			final double bestAvgRateDouble = bestAvgRate.setScale(3,
					RoundingMode.HALF_UP).doubleValue();
			for (final Long productId : bestProductIds) {
				final Apparel apparel;
				if (type.contains("Bag")) {
					apparel = findBags("", "WHERE a.aid =" + productId).get(0);
				} else if (type.contains("Shoes")) {
					apparel = findShoes("", "WHERE a.aid =" + productId).get(0);
				} else {
					apparel = findClothing("", "WHERE a.aid =" + productId)
							.get(0);
				}
				// number of rate if "Unknown" for best product
				bestApparel.add(new ReviewedApparel(apparel, null,
						bestAvgRateDouble));
			}
		}
	}
}
