package com.qa.selenium.pages.elements;

import com.qa.selenium.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class RegisterPageElements extends TestBase {


    public RegisterPageElements() throws IOException {
        super();
    }

    public WebElement FirstNameTxtElement() {
        return driver.findElement(By.name("first_name"));
    }

    public WebElement LastNameTxtElement() {
        return driver.findElement(By.name("last_name"));
    }

    public WebElement EmailTxtElement() {
        return driver.findElement(By.name("email"));
    }

    public WebElement PhoneNumberTxtElement() {
        return driver.findElement(By.name("phone"));
    }

    public WebElement AddressTxtElement() {
        return driver.findElement(By.name("address"));
    }

    public WebElement CityTxtElement() {
        return driver.findElement(By.name("city"));
    }

    public WebElement StateSelElement() {
        return driver.findElement(By.name("state"));
    }

    public WebElement ZipCodeTxtElement() {
        return driver.findElement(By.name("zip"));
    }

    public WebElement WebSiteTxtElement() {
        return driver.findElement(By.name("website"));
    }

    public List<WebElement> HasHostingRadioBtnElement() {
        return driver.findElements(By.name("hosting"));
    }

    public WebElement ProjectDescriptionElement() {
        return driver.findElement(By.name("comment"));
    }
}
