package com.clearlabs.pages;

import com.clearlabs.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

    public SignIn(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//div[@class=\"gc-custom-header-nav-bar-acct-menu\"]")
    public WebElement signIn;

    @FindBy(xpath = "//a[@class=\"uitk-button uitk-button-small uitk-button-fullWidth uitk-button-has-text uitk-button-primary\"]")
    public WebElement signInButton;

    @FindBy(id = "//input[@id=\"signin-loginid\"]")
    public WebElement email;

    @FindBy(id = "signin-password")
    public WebElement password;

    @FindBy(id = "submitButton")
    public WebElement submitButton;

    @FindBy(xpath = "//h5[@class='alert-message']")
    public WebElement errorMessage;
}
