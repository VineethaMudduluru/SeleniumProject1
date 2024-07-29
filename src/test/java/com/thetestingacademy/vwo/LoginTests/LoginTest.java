package com.thetestingacademy.vwo.LoginTests;

import com.thetestingacademy.basetest.CommonToAllTest;
import com.thetestingacademy.pages.PageObjectModel.DashboardPage_POM;
import com.thetestingacademy.pages.PageObjectModel.LoginPage_POM;
import com.thetestingacademy.utils.PropertyReader;
import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;

public class LoginTest extends CommonToAllTest {

    private static int loginAttemptCounter = 0;

    @Test(priority = 1)
    public void testLoginWithInvalidCredentials() throws Exception {
        // Increment login attempt counter
        loginAttemptCounter++;
        // Print current login attempt count
        System.out.println("Login Attempt: " + loginAttemptCounter);

        // Initialize the login page object model
        LoginPage_POM loginPage = new LoginPage_POM();
        // Open the login URL
        loginPage.openURL(PropertyReader.readKey("url"));
        // Attempt to login with invalid credentials and capture the error message
        String actualErrorMessage = loginPage.loginToVWONegative();
        // Validate the error message
        Assertions.assertThat(actualErrorMessage)
                .isNotNull()
                .isNotBlank()
                .contains(PropertyReader.readKey("error_message"));
    }

    @Test(priority = 2)
    public void testLoginWithValidCredentials() throws Exception {
        // Increment login attempt counter
        loginAttemptCounter++;
        // Print current login attempt count
        System.out.println("Login Attempt: " + loginAttemptCounter);

        // Initialize the login page object model
        LoginPage_POM loginPage = new LoginPage_POM();
        // Open the login URL
        loginPage.openURL(PropertyReader.readKey("url"));
        // Perform login with valid credentials and navigate to the dashboard
        DashboardPage_POM dashboardPage = loginPage.loginToVWOPositive().afterLogin();
        // Capture the logged-in username
        String actualUsername = dashboardPage.loggedInUserName();
        // Validate the username
        Assertions.assertThat(actualUsername)
                .isNotNull()
                .isNotBlank()
                .contains(PropertyReader.readKey("expected_username"));
    }
}