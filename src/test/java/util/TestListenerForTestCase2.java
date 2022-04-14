package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import dao.AuthorDaoImpl;
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
    private final AuthorDaoImpl daoAuthor = new AuthorDaoImpl();
    private final SessionDaoImpl daoSession = new SessionDaoImpl();
    private final ProjectDaoImpl daoProject = new ProjectDaoImpl();
    private final List<Test> listRepeatedId_10=daoTest.getAllTestsWithRepeatingDigitsNotMoreThen_10();
    private final ISettingsFile environment = new JsonSettingsFile("settings.json");



    @Override
    public void onTestStart(ITestResult iTestResult) {
        GetDefaultAuthor.setAuthorInDB();
        listRepeatedId_10.get(TestCase2.countTest).setAuthor_id(daoAuthor.returnLastAddedId());
        listRepeatedId_10.get(TestCase2.countTest).setSession_id(daoSession.returnLastAddedId());
        listRepeatedId_10.get(TestCase2.countTest).setProject_id(daoProject.returnLastAddedId());
        listRepeatedId_10.get(TestCase2.countTest).setStart_time(Timestamp.valueOf(LocalDateTime.now()));
        listRepeatedId_10.get(TestCase2.countTest).setBrowser(environment.getValue("/browserName").toString());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ListenerUtil.onTestForTC_Two(iTestResult,listRepeatedId_10);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ListenerUtil.onTestForTC_Two(iTestResult,listRepeatedId_10);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ListenerUtil.onTestForTC_Two(iTestResult,listRepeatedId_10);
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
