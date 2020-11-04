package dao;

import db.DBDataSource;
import entity.SkierDayVertical;
import entity.SkierRecords;
import io.swagger.client.model.SkierVertical;
import io.swagger.client.model.TopTen;
import io.swagger.client.model.TopTenTopTenSkiers;
import org.apache.commons.dbcp2.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkierRecordsDao {
    private static BasicDataSource dataSource;
    public SkierRecordsDao() {
        dataSource = DBDataSource.getDataSource();
    }

    public boolean createSkierRecord(SkierRecords skierRecord) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
            String insertQueryStatement = "INSERT INTO skier_records (resort_id, skier_id, lift_id, season_id, day_id, day_time) VALUES (?,?,?,?,?,?)";
        boolean succeed = true;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(insertQueryStatement);
            preparedStatement.setInt(1, skierRecord.getResortId());
            preparedStatement.setInt(2, skierRecord.getSkierId());
            preparedStatement.setInt(3, skierRecord.getLiftId());
            preparedStatement.setInt(4, skierRecord.getSeasonId());
            preparedStatement.setInt(5, skierRecord.getDayId());
            preparedStatement.setInt(6, skierRecord.getTime());
            // execute insert SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            succeed = false;
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return succeed;
    }

    public SkierDayVertical getSkierDayVertical(String resortID, String dayID, String skierID) {
        // /skiers/{resortID}/days/{dayID}/skiers/{skierID}
        String st = "SELECT resort_id, day_id, skier_id, SUM(vertical) as total_vertical " +
                "FROM skier_records INNER JOIN lifts_vertical " +
                "where resort_id = ? AND day_id = ? AND skier_id = ? ";
        BasicDataSource conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBDataSource.getDataSource();
            pst = conn.getConnection().prepareStatement(st);
            pst.setString(1, resortID);
            pst.setString(2, dayID);
            pst.setString(3, skierID);
            ResultSet res = pst.executeQuery();
//            SkierDayVertical(Integer resortId, Integer dayId, Integer skierId, int vertical)
            SkierDayVertical skierDayVertical = new SkierDayVertical(
                    res.getInt("resort_id"),
                    res.getInt("day_id"),
                    res.getInt("skier_id"),
                    res.getInt("total_vertical")
            );
            return skierDayVertical;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    public static SkierVertical getSkierTotalVertical(String resortID, int skierID) throws SQLException {
        final String st =  "SELECT resort_id, SUM(vertical) as total_vertical FROM skier_records INNER JOIN lifts_vertical " +
                "ON lifts_vertical.lift_id = skier_records.lift_id " +
                "WHERE resort_id = ? AND skier_id = ? ";
        BasicDataSource conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SkierVertical skierVerticalResult = new SkierVertical();
        try {
            conn = DBDataSource.getDataSource();
            preparedStatement = conn.getConnection().prepareStatement(st);
            preparedStatement.setString(1, resortID);
            preparedStatement.setInt(2, skierID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                skierVerticalResult = new SkierVertical();
                skierVerticalResult.setResortID(resultSet.getString("resort_id"));
                skierVerticalResult.setTotalVert(resultSet.getInt("total_vertical"));
            }
            return skierVerticalResult;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            throw se;
        } finally {
            // finally block used to close resources
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

        public static TopTen getTopTenVerticals(int dayID) throws SQLException {
        final String st =
                "SELECT skier_id, SUM(Vertical) AS totalVertical FROM skier_records INNER JOIN lifts_vertical " +
                        "ON skier_records.lift_id = lifts_vertical.lift_id " +
                        "WHERE day_id = ? " +
                        "GROUP BY skier_id " +
                        "ORDER BY totalVertical DESC " +
                        "LIMIT 10 ";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        TopTen top10Res = new TopTen();
        try {
            conn = DBDataSource.getDataSource().getConnection();

            preparedStatement = conn.prepareStatement(st);
            preparedStatement.setInt(1, dayID);
            res = preparedStatement.executeQuery();
            while (res.next()) {
                TopTenTopTenSkiers skier = new TopTenTopTenSkiers();
                skier.setSkierID(Integer.toString(res.getInt("skier_id")));
                skier.setVertcialTotal(res.getInt("TotalVertical"));
                top10Res.addTopTenSkiersItem(skier);
            }
            return top10Res;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            throw se;
        } finally {
            // finally block used to close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (res != null) {
                    res.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    }
