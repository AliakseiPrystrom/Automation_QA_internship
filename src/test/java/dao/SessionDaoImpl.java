package dao;

import tables.Session;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDaoImpl implements SessionDao {
    @Override
    public void addSession(Session session) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.addSession(session.getSession_key(), session.getBuild_number());
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("session");
    }


}
