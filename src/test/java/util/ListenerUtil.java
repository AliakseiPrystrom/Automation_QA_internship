package util;

import dao.StatusDaoImpl;
import dao.TestDaoImpl;
import org.testng.ITestResult;
import tables.Status;
import tables.Test;
import tests.TestCase2;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ListenerUtil {
    private static final StatusDaoImpl daoStatus = new StatusDaoImpl();
    private static final TestDaoImpl daoTest = new TestDaoImpl();

    public static void onTestForTC_One(ITestResult iTestResult, Test test) {
        daoStatus.addStatus(new Status("Failure"));
        test.setStatus_id(iTestResult.getStatus());
        test.setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        daoTest.addTest(test);
    }

    public static void onTestForTC_Two(ITestResult iTestResult, List<Test> listRepeatedId_10) {
        listRepeatedId_10.get(TestCase2.countTest).setEnd_time(Timestamp.valueOf(LocalDateTime.now()));
        listRepeatedId_10.get(TestCase2.countTest).setStatus_id(iTestResult.getStatus());
        daoTest.updateTest(listRepeatedId_10.get(TestCase2.countTest));
    }


}
