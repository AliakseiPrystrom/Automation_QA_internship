package tests;

import dao.TestDaoImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.GenerationDataForTest;
import util.TestListenerForTestCase1;

@Listeners(TestListenerForTestCase1.class)
public class TestCase1 {

    private final TestDaoImpl dao = new TestDaoImpl();
    public static int beforeInsert = 0; //последний записаный id теста

    @Test(testName = "addResultsTest",priority = 1)
    public void testRandomValue() {
        Assert.assertTrue(GenerationDataForTest.checkValue(), "Test did not pass");
    }

    @AfterClass
    public void checkTest() {
        Assert.assertTrue(dao.checkTestInDB(beforeInsert+1), "Test result did not insert");
    }

}
