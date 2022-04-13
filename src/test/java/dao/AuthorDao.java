package dao;

import tables.Author;

import java.util.List;

public interface AuthorDao {

    void addAuthor(Author author);

    boolean checkAuthorInDB(int id);

    void deleteAuthorId(int id);

    void deleteAllAuthors();

    void deleteAuthor(Author author);

    Author getAuthor(int id);

    List<Author> getAllAuthors();

    void updateAuthor(Author author);

    int returnLastAddedId();
}
