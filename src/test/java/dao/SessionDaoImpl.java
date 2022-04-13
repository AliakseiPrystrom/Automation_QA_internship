package dao;

import tables.Session;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionDaoImpl implements SessionDao {
    @Override
    public void addSession(Session session) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = "INSERT session VALUES (default,?,now(),?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, session.getSession_key());
            statement.setInt(2, session.getBuild_number());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("session");
    }


}
