package com.clearlabs.step_definitions;

import com.clearlabs.pages.SignIn;
import com.clearlabs.utilities.ConfigurationReader;
import com.clearlabs.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;

public class SignInStepDefs {

    SignIn signIn = new SignIn();

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("User should be able enter valid username and password")
    public void user_should_be_able_enter_valid_username_and_password() {
        signIn.email.sendKeys(ConfigurationReader.get("email"));
        signIn.password.sendKeys(ConfigurationReader.get("password"));
    }

    @When("User clicks to submit button")
    public void user_clicks_to_submit_button() {
        signIn.submitButton.click();
    }

    @Then("User should be able to see the login page")
    public void user_should_be_able_to_see_the_login_page() {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Expedia";

        Assert.assertTrue(actualTitle.equals(expectedTitle));
    }

    @When("User enter valid username and invalid password")
    public void user_enter_valid_username_and_invalid_password() {
        signIn.email.sendKeys(ConfigurationReader.get("email"));
        signIn.password.sendKeys("abekceh");
    }

    @Then("User should be able to see the error message displayed on the page")
    public void user_should_be_able_to_see_the_error_message_displayed_on_the_page() {
        Assert.assertTrue("You may have entered an unknown email address or an incorrect password. Click here to jump to the first invalid field. ",
                                     signIn.errorMessage.isDisplayed());
    }
}

