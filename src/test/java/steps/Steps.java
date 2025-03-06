package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class Steps {
    private String username;
    private String password;
    private String message;

    @Given("User is in login page")
    public void userEnLoginPage() {
        System.out.println("User is in login page");
    }

    @When("The user sets the username {string} and password {string}")
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;

        // Simulamos validación de credenciales
        if (username.equals("admin") && password.equals("password123")) {
            this.message = "Success";
        } else {
            this.message = "invalid credentials";
        }
    }

    @Then("It should see the message {string}")
    public void verifyMessage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, message, "Message show is not the expected.");
    }
}
