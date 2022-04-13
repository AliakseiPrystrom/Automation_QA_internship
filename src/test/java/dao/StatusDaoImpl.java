package dao;

import tables.Status;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusDaoImpl implements StatusDao {
    @Override
    public void addStatus(Status status) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = "INSERT status VALUES (default,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllStatuses() {
        ReturnQuery.deleteAll("status");
    }

    @Override
    public void deleteStatusId(int id) {
        ReturnQuery.deleteFromTableId("status",id);
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("ststus");
    }
}
