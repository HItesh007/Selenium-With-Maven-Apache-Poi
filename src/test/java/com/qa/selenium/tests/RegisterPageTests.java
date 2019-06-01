package com.qa.selenium.tests;

import com.qa.selenium.base.TestBase;
import com.qa.selenium.constant.ExcelConstant;
import com.qa.selenium.pages.actions.RegisterPageActions;
import com.qa.selenium.util.ExcelUtility;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterPageTests extends TestBase {

    private RegisterPageActions regPageAction;

    public RegisterPageTests() throws IOException {
        super();
    }

    @DataProvider
    public Object[][] getExcelData() {
        ExcelUtility xlUtil = new ExcelUtility();

        xlUtil.LoadExcelFileForRead(USER_DIRECTORY
                + ExcelConstant.TEST_DATA_DIR_PATH
                + ExcelConstant.REGISTER_DATA_FILE_NAME_DP, ExcelConstant.USER_DATA_SHEET);

        return xlUtil.GetExcelDataAsArray();
    }

    @BeforeSuite
    public void SetUp() throws IOException {
        TestInitialization(propFile.getProperty("browserName"));
        regPageAction = new RegisterPageActions();
    }

    @Test(priority = 0, testName = "Register User - With Data Provider", dataProvider = "getExcelData")
    public void RegisterUserUsingDataProvider(String firstName, String lastName) throws InterruptedException {
        regPageAction.EnterFirstName(firstName);
        regPageAction.EnterLastName(lastName);
        Thread.sleep(500);
    }

    @Test(priority = 1, testName = "Register User - Without Data Provider")
    public void RegisterUserWithoutDataProvider() {
        ExcelUtility xlUtil = new ExcelUtility();

        xlUtil.LoadExcelFileForRead(USER_DIRECTORY
                + ExcelConstant.TEST_DATA_DIR_PATH
                + ExcelConstant.REGISTER_DATA_FILE_NAME, ExcelConstant.USER_DATA_SHEET);

        for(int i=1; i<= 100; i++) {

            String firstName = xlUtil.GetCellData(i, ExcelConstant.FIRST_NAME);
            String lastName = xlUtil.GetCellData(i, ExcelConstant.LAST_NAME);
            String state = xlUtil.GetCellData(i,ExcelConstant.STATE);

            regPageAction.EnterFirstName(firstName);
            regPageAction.EnterLastName(lastName);
            regPageAction.SelectState(state);
        }

    }

    @AfterSuite
    public void TearDown() {
        driver.quit();

    }


}
