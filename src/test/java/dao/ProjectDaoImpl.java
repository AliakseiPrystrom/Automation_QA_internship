package dao;

import tables.Project;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDaoImpl implements ProjectDao {
    @Override
    public void addProject(Project project) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = "INSERT project VALUES (default,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(int id) {
        ReturnQuery.deleteFromTableId("project", id);
    }

    @Override
    public void deleteAllProjects() {
        ReturnQuery.deleteAll("projects");
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("project");
    }


}
