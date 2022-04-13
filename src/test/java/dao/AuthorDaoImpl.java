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
            String sql = "INSERT author VALUES (default,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author.getName());
            statement.setString(2, author.getLogin());
            statement.setString(3, author.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkAuthorInDB(int id) {
        return getAuthor(id).getId()!=0;
    }

    @Override
    public void deleteAuthorId(int id) {
        ReturnQuery.deleteFromTableId("author",id);
    }

    @Override
    public void deleteAllAuthors() {
        ReturnQuery.deleteAll("author");
    }

    @Override
    public void deleteAuthor(Author author) {
        try (Connection connection = GetConnection.getConnection()) {
            String sql = "DELETE FROM author WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, author.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author getAuthor(int id) {
        Author author = new Author();
        try (Connection connection = GetConnection.getConnection()) {
            String sql = "SELECT * FROM author WHERE id =" + id;
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
            String sql = "SELECT * FROM author";
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
            String sql = "UPDATE author SET name = ?, login = ?, email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, author.getName());
            statement.setString(2, author.getLogin());
            statement.setString(3, author.getEmail());
            statement.setInt(4, author.getId());
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