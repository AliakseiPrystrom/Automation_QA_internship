package dao;

import tables.Test;
import util.GetConnection;
import util.ReturnQuery;
import util.TwoRandomRepeatingDigits;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoImpl implements TestDao {
    @Override
    public void addTest(Test test) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.addTest(
                    test.getName(), test.getStatus_id(), test.getMethod_name(), test.getProject_id(), test.getSession_id(),
                    test.getStart_time(), test.getEnd_time(), test.getEnv(), test.getBrowser(), test.getAuthor_id());
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean checkTestInDB(int id) {

        return getTest(id).getId() != 0;
    }

    @Override
    public void deleteTestId(int id) {
        ReturnQuery.deleteFromTableId("test", id);
    }

    @Override
    public void deleteAllTests() {
        ReturnQuery.deleteAll("test");
    }

    @Override
    public void deleteTest(Test test) {
        ReturnQuery.deleteFromTableId("test", test.getId());
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("test");
    }

    @Override
    public Test getTest(int id) {
        Test test = new Test();
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.getQueryById("test", id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                test.setAuthor_id(resultSet.getInt("author_id"));
                test.setBrowser(resultSet.getString("browser"));
                test.setEnd_time(resultSet.getTimestamp("end_time"));
                test.setEnv(resultSet.getString("env"));
                test.setId(resultSet.getInt("id"));
                test.setMethod_name(resultSet.getString("method_name"));
                test.setName(resultSet.getString("name"));
                test.setProject_id(resultSet.getInt("project_id"));
                test.setSession_id(resultSet.getInt("session_id"));
                test.setStart_time(resultSet.getTimestamp("start_time"));
                test.setStatus_id(resultSet.getInt("status_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public List<Test> getAllTests() {
        List<Test> list = new ArrayList<>();
        for (int i = 1; i <= returnLastAddedId(); i++) {
            list.add(getTest(i));
        }
        return list;
    }

    @Override
    public void updateTest(Test test) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.updateTest(
                    test.getName(), test.getStatus_id(), test.getMethod_name(), test.getProject_id(), test.getSession_id(),
                    test.getStart_time(), test.getEnd_time(), test.getEnv(), test.getBrowser(), test.getAuthor_id(), test.getId()
            );
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Test> getAllTestsWithRepeatingDigits() {
        List<Test> list = new ArrayList<>();
        for (Test test : getAllTests()) {
            if (test.getId() > 9 && test.getId() < 100) {
                if (TwoRandomRepeatingDigits.getCompareResult(test.getId())) {
                    list.add(test);
                }
            }
        }
        return list;
    }

    public List<Test> getAllTestsWithRepeatingDigitsNotMoreThen_10() {
        List<Test> list = new ArrayList<>();
        List<Test> list2 = getAllTestsWithRepeatingDigits();
        for (int i = 0; i < 9; i++) {
            list.add(i, list2.get(i));
        }
        return list;
    }

    public int checkIdInTests(List<Test> list) {
        List<Test> getAll = getAllTests();
        int valueOfIsExistID = 0;
        for (Test test : getAll) {
            for (Test test1 : list) {
                if (test.getId() == test1.getId()) {
                    valueOfIsExistID += 1;
                }
            }
        }
        return valueOfIsExistID;
    }

}
