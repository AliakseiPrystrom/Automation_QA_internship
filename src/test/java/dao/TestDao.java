package dao;

import tables.Test;

import java.util.List;

public interface TestDao {

    void addTest(Test test);

    boolean checkTestInDB(int id);

    void deleteTestId(int id);

    void deleteAllTests();

    void deleteTest(Test test);

    Test getTest(int id);

    List<Test> getAllTests();

    void updateTest(Test test);

    int returnLastAddedId();


}
