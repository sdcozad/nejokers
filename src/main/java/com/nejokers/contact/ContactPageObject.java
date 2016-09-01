package com.nejokers.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cri.main.utils.BasePage;

public class ContactPageObject extends BasePage {

    public ContactPageObject(WebDriver driver) {
        super(driver);

    }

    @FindBy(how = How.ID, using = "comp-ir6bwy4t1nameField")
    public WebElement Name;

    @FindBy(how = How.ID, using = "comp-ir6bwy4t1emailField")
    public WebElement Email;

    @FindBy(how = How.ID, using = "comp-ir6bwy4t1emailField")
    public WebElement Subject;

    @FindBy(how = How.ID, using = "comp-ir6bwy4t1messageField")
    public WebElement Message;

    @FindBy(how = How.ID, using = "comp-ir6bwy4t1submit")
    public WebElement Send;

    @FindBy(how = How.ID, using = "comp-ir6bwy4t1notifications")
    public WebElement Error;

}

