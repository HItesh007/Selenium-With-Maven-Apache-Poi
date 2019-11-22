package com.qa.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static Properties propFile;
    public static WebDriver driver;
    public static final String USER_DIRECTORY = System.getProperty("user.dir");

    public TestBase() throws IOException {
        try {

            propFile = new Properties();
            FileInputStream fInputStream = new FileInputStream(USER_DIRECTORY + "/src/main/java/com/qa/selenium/" +
                    "config/config.properties");

            propFile.load(fInputStream);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

    }

    public void TestInitialization(String browser) {
        try {

            if(browser.trim().toUpperCase().equals("CHROME")) {
                System.setProperty("webdriver.chrome.driver", USER_DIRECTORY + "/src/main/driver/chrome/v78/chromedriver.exe");

                driver = new ChromeDriver();
            } else if(browser.trim().toUpperCase().equals("FIREFOX")) {
                System.setProperty("webdriver.gecko.driver", USER_DIRECTORY + "/src/main/driver/gecko-driver/geckodriver");

                driver  = new FirefoxDriver();
            }

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);

            driver.get(propFile.getProperty("appUrl"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
