package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.sql.*;

public class ReturnQuery {
    private final static ISettingsFile environment = new JsonSettingsFile("queries.json");

    public static int returnId(String table) {
        int id = 0;
        try (Connection connection = GetConnection.getConnection()) {
            PreparedStatement stat;
            ResultSet rs;
            String sql = String.format("SELECT MAX(id) AS max_id FROM %s", table);
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

    public static void deleteFromTableId(String table, int id) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = String.format("DELETE FROM %s WHERE id = ?", table);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll(String table) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = String.format("DELETE FROM %s WHERE id>0", table);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getQueryById(String table,int id) {
        return String.format(environment.getValue("/getById").toString(),table, id);
    }

    public static String deleteQueryById(String table,int id) {
        return String.format(environment.getValue("/deleteById").toString(),table, id);
    }

    public static String getAll(String table){
        return String.format(environment.getValue("/getAll").toString(),table);
    }

    public static String addAuthor(String authorName,String authorLogin,String authorEmail){
        return String.format(environment.getValue("/addAuthor").toString(),authorName,authorLogin,authorEmail);
    }

    public static String updateAuthor(String authorName,String authorLogin,String authorEmail,int id){
        return String.format(environment.getValue("/updateAuthor").toString(),authorName,authorLogin,authorEmail,id);
    }

    public static String addTest(
            String name, int status, String methodN, int projectId, int sessionId, Timestamp start,Timestamp end,String env,String browser,int authorId){
        return String.format(environment.getValue("/addTest").toString(),
                name,status,methodN,projectId,sessionId,start,end,env,browser,authorId);
    }

    public static String updateTest(
            String name, int status, String methodN, int projectId, int sessionId, Timestamp start,Timestamp end,String env,String browser,int authorId,int id){
        return String.format(environment.getValue("/updateTest").toString(),
                name,status,methodN,projectId,sessionId,start,end,env,browser,authorId,id);
    }

    public static String addProject(String name){
        return String.format(environment.getValue("/addProject").toString(),name);
    }

    public static String addSession(String sessionKey,int buildNumber){
        return String.format(environment.getValue("/addSession").toString(),sessionKey,buildNumber);
    }

    public static String addStatus(String name){
        return String.format(environment.getValue("/addStatus").toString(),name);
    }




}
