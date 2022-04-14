package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import dao.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tables.*;
import tests.TestCase1;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestListenerForTestCase1 implements ITestListener {
    private final Test test = new Test();
    private final AuthorDaoImpl daoAuthor = new AuthorDaoImpl();
    private final ProjectDaoImpl daoProject = new ProjectDaoImpl();
    private final SessionDaoImpl daoSession = new SessionDaoImpl();
    private final TestDaoImpl daoTest = new TestDaoImpl();
    private final ISettingsFile  environment = new JsonSettingsFile("settings.json");


    @Override
    public void onTestStart(ITestResult iTestResult) {
        TestCase1.beforeInsert = daoTest.returnLastAddedId();
        daoAuthor.addAuthor(new Author(
                "testAuthor" + GenerationDataForTest.generateNameLoginPassMail(),
                "testLogin" + GenerationDataForTest.generateNameLoginPassMail(),
                "testEmail" + GenerationDataForTest.generateNameLoginPassMail()));
        test.setAuthor_id(daoAuthor.returnLastAddedId());
        test.setBrowser(environment.getValue("/browserName").toString());
        test.setStart_time(Timestamp.valueOf(LocalDateTime.now()));
        test.setEnv("anyNode" + GenerationDataForTest.generateNameLoginPassMail());
        test.setMethod_name(iTestResult.getMethod().getMethodName());
        test.setName(iTestResult.getName());
        daoProject.addProject(new Project("testProject" + GenerationDataForTest.generateNameLoginPassMail()));
        test.setProject_id(daoProject.returnLastAddedId());
        daoSession.addSession(new Session("uniqSessinoKey" + GenerationDataForTest.generateNameLoginPassMail(), GenerationDataForTest.generateRandomIntValue()));
        test.setSession_id(daoSession.returnLastAddedId());


    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ListenerUtil.onTestForTC_One(iTestResult,test);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ListenerUtil.onTestForTC_One(iTestResult,test);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ListenerUtil.onTestForTC_One(iTestResult,test);
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
