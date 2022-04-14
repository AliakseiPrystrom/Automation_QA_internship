package util;

import dao.AuthorDaoImpl;
import tables.Author;

public class GetDefaultAuthor {
    private static final AuthorDaoImpl dao = new AuthorDaoImpl();

    public static void setAuthorInDB() {
        Author author = new Author(
                GenerationDataForTest.generateNameLoginPassMail(),
                GenerationDataForTest.generateNameLoginPassMail(),
                GenerationDataForTest.generateNameLoginPassMail()
        );
        dao.addAuthor(author);
    }
}
