package dao;

import java.sql.SQLException;

public class UserReviewDAO extends AbstractDAO {
	public UserReviewDAO() {
		connectToDB();
	}

	public Long findUserIdByName(final String userName) {
		Long userId = null;
		final String sql = "SELECT usid FROM UserAccount WHERE name = "
				+ stringArgu(userName);
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userId = rs.getLong("usid");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return userId;
	}

	public void addReview(final long userId, final long apparelId,
			final double rate) {
		final String sql = "INSERT INTO Reviewed (apparel_id, user_id, rate, review_date) VALUES ("
				+ apparelId + "," + userId + "," + rate + ",trunc(sysdate))";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
