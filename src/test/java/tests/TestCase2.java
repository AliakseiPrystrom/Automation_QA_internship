package tests;

import dao.TestDaoImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import util.GenerationDataForTest;
import util.TestListenerForTestCase2;

import java.util.ArrayList;
import java.util.List;


@Listeners(TestListenerForTestCase2.class)
public class TestCase2 {

    private final TestDaoImpl daoTest = new TestDaoImpl();
    public static int countTest = 0;
    private final List<tables.Test> list = daoTest.getAllTestsWithRepeatingDigitsNotMoreThen_10();
    private final List<tables.Test> listBeforeUpdate = new ArrayList<>();
    private final int listSize = list.size();


    @Test(invocationCount = 9,priority = 1)
    public void testDataProcessing() {
        listBeforeUpdate.addAll(list);
        Assert.assertTrue(GenerationDataForTest.checkValue(), "Test did not pass");
    }

    @AfterMethod
    public void changeCount() {
        if (countTest != listSize - 1) {
            countTest++;
        }
    }

    @Test(priority = 2)
    public void checkChangesInList() {
        Assert.assertNotSame(list, listBeforeUpdate, "Tests did not change");
    }

    @AfterClass
    public void detelePreconditions(){
        for (tables.Test test : list) {
            daoTest.deleteTest(test);
        }
        Assert.assertEquals(daoTest.checkIdInTests(list), 0, "Tests did not delete");
    }

}