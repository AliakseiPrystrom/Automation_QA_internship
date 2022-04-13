package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnQuery {

    public static int returnId(String table){
        int id = 0;
        try (Connection connection = GetConnection.getConnection()) {
            PreparedStatement stat;
            ResultSet rs;
            String sql = String.format("SELECT MAX(id) AS max_id FROM %s",table);
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                id = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void deleteFromTableId(String table,int id){
        try (Connection connection = GetConnection.getConnection()) {
            String sql = String.format("DELETE FROM %s WHERE id = ?",table);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll(String table){
        try (Connection connection = GetConnection.getConnection()) {
            String sql = String.format("DELETE FROM %s WHERE id>0",table);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
