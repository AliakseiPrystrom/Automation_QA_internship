package dao;

import tables.Author;
import util.GetConnection;
import util.ReturnQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public void addAuthor(Author author) {
        try (Connection connection = GetConnection.getConnection()) {
            Statement statement = connection.createStatement();
            String sql = ReturnQuery.addAuthor(author.getName(), author.getLogin(), author.getEmail());
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkAuthorInDB(int id) {
        return getAuthor(id).getId() != 0;
    }

    @Override
    public void deleteAuthorId(int id) {
        ReturnQuery.deleteFromTableId("author", id);
    }

    @Override
    public void deleteAllAuthors() {
        ReturnQuery.deleteAll("author");
    }

    @Override
    public void deleteAuthor(Author author) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.deleteQueryById("author", author.getId());
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author getAuthor(int id) {
        Author author = new Author();
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.getQueryById("author", id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setLogin(resultSet.getString("login"));
                author.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }


    @Override
    public List<Author> getAllAuthors() {
        List<Author> list = new ArrayList<>();
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.getAll("author");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setLogin(resultSet.getString("login"));
                author.setEmail(resultSet.getString("email"));
                list.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateAuthor(Author author) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = ReturnQuery.updateAuthor(author.getName(), author.getLogin(), author.getEmail(), author.getId());
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int returnLastAddedId() {
        return ReturnQuery.returnId("author");
    }

}