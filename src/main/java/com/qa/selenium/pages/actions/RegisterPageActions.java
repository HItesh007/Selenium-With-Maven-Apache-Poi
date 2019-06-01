package com.qa.selenium.pages.actions;

import com.qa.selenium.pages.elements.RegisterPageElements;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class RegisterPageActions {

    public static RegisterPageElements regPage;

    public RegisterPageActions() throws IOException {
        regPage = new RegisterPageElements();
    }

    public void EnterFirstName(String firstName) {
        regPage.FirstNameTxtElement().clear();
        regPage.FirstNameTxtElement().sendKeys(firstName);
    }

    public void EnterLastName(String lastName) {
        regPage.LastNameTxtElement().clear();
        regPage.LastNameTxtElement().sendKeys(lastName);
    }

    public void SelectState(String stateName) {
        Select sel = new Select(regPage.StateSelElement());
        sel.selectByVisibleText(stateName);
    }
}
