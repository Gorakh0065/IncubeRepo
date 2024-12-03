package com.incubyte.stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ComposeEmailSteps extends TestBase{

	public ComposeEmailSteps() {
		PageFactory.initElements(driver, this);
	}
	@Given("I am logged into Gmail") 
	public void iAmLoggedIntoGmail() 
	{ 
		SetUpBrowser("Chrome");
		LoginToGmail("gorakhsurya6565", "Gorakh@0065");

	} 
	
	@When("I compose an email with the subject {string} and body {string}") 
	public void iComposeAnEmailWithTheSubjectAndBody(String subject, String body) 
	{ 
		GoToCompose(subject, body);
	} 
	
	@When("I send the email to {string}") 
	public void iSendTheEmailTo(String recipient) { 
		SendEmailToReceipt(recipient);
		} 
	
	@Then("the email should be sent successfully") 
	public void theEmailShouldBeSentSuccessfully() {
		EmailSentSucessVerify();
		} 
	
	@When("I compose an email without a recipient") 
	public void iComposeAnEmailWithoutARecipient() { 
		// Navigate to compose email screen 
		GoToCompose("Incubyte", "QA test for Incubyte");
		} 
	
	
	@When("I attempt to send the email")
	public void i_attempt_to_send_the_email() {
		SendEmailWithoutRece();
	    throw new io.cucumber.java.PendingException();  
	}

	
	@Then("I should see an error message indicating a recipient is required") 
	public void iShouldSeeAnErrorMessageIndicatingARecipientIsRequired() { 
		VerifyErrorValidation();
		
		}
}

