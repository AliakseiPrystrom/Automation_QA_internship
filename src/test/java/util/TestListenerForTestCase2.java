package util;

import dao.ProjectDaoImpl;
import dao.SessionDaoImpl;
import dao.TestDaoImpl;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tables.Test;
import tests.TestCase2;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TestListenerForTestCase2 implements ITestListener {
    private final TestDaoImpl daoTest = new TestDaoImpl();
    private final SessionDaoImpl daoSession = new SessionDaoImpl();
    private final ProjectDaoImpl daoProject = new ProjectDaoImpl();
    private final List<Test> listRepeatedId_10=daoTest.getAllTestsWithRepeatingDigitsNotMoreThen_10();


    @Override
    public void onTestStart(ITestResult iTestResult) {
        listRepeatedId_10.get(TestCase2.countTest).setAuthor_id(1);
        listRepeatedId_10.get(TestCase2.countTest).setSession_id(daoSession.returnLastAddedId());
        listRepeatedId_10.get(TestCase2.countTest).setProject_id(daoProject.returnLastAddedId());
        listRepeatedId_10.get(TestCase2.countTest).setStart_time(Timestamp.valueOf(LocalDateTime.now()));
        listRepeatedId_10.get(TestCase2.countTest).setBrowser("anotherBrowser");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        listRepeatedId_10.get(TestCase2.countTest).setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        listRepeatedId_10.get(TestCase2.countTest).setStatus_id(iTestResult.getStatus());
        daoTest.updateTest(listRepeatedId_10.get(TestCase2.countTest));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        listRepeatedId_10.get(TestCase2.countTest).setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        listRepeatedId_10.get(TestCase2.countTest).setStatus_id(iTestResult.getStatus());
        daoTest.updateTest(listRepeatedId_10.get(TestCase2.countTest));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        listRepeatedId_10.get(TestCase2.countTest).setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        listRepeatedId_10.get(TestCase2.countTest).setStatus_id(iTestResult.getStatus());
        daoTest.updateTest(listRepeatedId_10.get(TestCase2.countTest));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
