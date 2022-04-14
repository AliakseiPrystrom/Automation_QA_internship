package dao;

import tables.Project;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectDaoImpl implements ProjectDao {
    @Override
    public void addProject(Project project) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.addProject(project.getName());
            Statement statement = connection.createStatement();
            statement.execute(sql);
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
