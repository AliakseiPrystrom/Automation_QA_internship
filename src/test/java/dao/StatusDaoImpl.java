package dao;

import tables.Status;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatusDaoImpl implements StatusDao {
    @Override
    public void addStatus(Status status) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.addStatus(status.getName());
            Statement statement = connection.createStatement();
            statement.execute(sql);
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
        ReturnQuery.deleteFromTableId("status", id);
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("ststus");
    }
}
