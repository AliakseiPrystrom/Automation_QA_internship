package util;

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
    private final StatusDaoImpl daoStatus = new StatusDaoImpl();
    private final TestDaoImpl daoTest = new TestDaoImpl();


    @Override
    public void onTestStart(ITestResult iTestResult) {
        TestCase1.beforeInsert = daoTest.returnLastAddedId();
        daoAuthor.addAuthor(new Author(
                "testAuthor" + GenerationDataForTest.generateNameLoginPassMail(),
                "testLogin" + GenerationDataForTest.generateNameLoginPassMail(),
                "testEmail" + GenerationDataForTest.generateNameLoginPassMail()));
        test.setAuthor_id(daoAuthor.returnLastAddedId());
        test.setBrowser("anyBrowser");
        test.setStart_time(Timestamp.valueOf(LocalDateTime.now()));
        test.setEnv("anyNode" + GenerationDataForTest.generateNameLoginPassMail());
        test.setMethod_name(iTestResult.getMethod().getMethodName());
        test.setName(iTestResult.getName());
        daoProject.addProject(new Project("testProject" + GenerationDataForTest.generateNameLoginPassMail()));
        test.setProject_id(daoProject.returnLastAddedId());
        daoSession.addSession(new Session("uniqSessinoKey" + GenerationDataForTest.generateNameLoginPassMail(), 147));
        test.setSession_id(daoSession.returnLastAddedId());


    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        daoStatus.addStatus(new Status("Success"));
        test.setStatus_id(iTestResult.getStatus());
        test.setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        daoTest.addTest(test);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        daoStatus.addStatus(new Status("Failure"));
        test.setStatus_id(iTestResult.getStatus());
        test.setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        daoTest.addTest(test);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        daoStatus.addStatus(new Status("Skipped"));
        test.setStatus_id(iTestResult.getStatus());
        test.setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        daoTest.addTest(test);
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
