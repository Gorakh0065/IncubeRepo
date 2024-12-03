package Base;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

public static WebDriver driver; // Globally driver declared

	public void SetUpBrowser(String browser) {

		//String br = "Chrome"; // For Selecting specific Browser

		if (browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			//WebDriverManager.chromedriver().clearDriverCache().browserVersion("131.0.6778.86").setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		} else {
			System.out.println("you have Entered Incorrect Browser name");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.get("https://mail.google.com");	
	}
	public void LoginToGmail(String Email, String Password) {
		WebElement EnterEmailText= driver.findElement(By.xpath("//input[@type='email']"));
		EnterEmailText.click();
		EnterEmailText.sendKeys(Email);
		
		WebElement ClickOnNextBtn= driver.findElement(By.xpath("//span[@jsname='V67aGc' and text()='Next']"));
		ClickOnNextBtn.click();
		
		WebElement TryAnotherWayBtn= driver.findElement(By.xpath("//span[@jsname='V67aGc' and text()='Try another way']"));
		TryAnotherWayBtn.click();
		
		WebElement EnterPassSele= driver.findElement(By.xpath("//div[@class='l5PPKe' and text()='Enter your password']"));
		EnterPassSele.click();
		
		WebElement EnterPassText=driver.findElement(By.xpath("//div[@jsname='YRMmle' and text()='Enter your password']"));
		EnterPassText.sendKeys(Password);
		
		WebElement ClickOnNextBtn1= driver.findElement(By.xpath("//span[@jsname='V67aGc' and text()='Next']"));
		ClickOnNextBtn1.click();
		
	}
	
	public void GoToCompose(String subject, String body) {
		
		// Wait for the inbox to load (better handled with explicit waits) 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		WebElement composeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@jscontroller='eIu7Db']"))); 
	
		composeButton.click();
		
		// Wait for the "Subject" field to be visible and enter the subject
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement subjectField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("subjectbox")));
		subjectField.sendKeys(subject);
		
		// Wait for the email body field to be visible and enter the body text
		WebElement bodyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Message Body']")));
		bodyField.sendKeys(body);		
	}
	
	public void SendEmailToReceipt(String recipient) {
		// Wait for the "To" field to be visible and enter the recipient email
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement toField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to"))); 
		toField.sendKeys(recipient);
		
		// Wait for the "Send" button to be visible and click it
		WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Send ‪(Ctrl-Enter)‬']")));
		sendButton.click();
		
	}
	
	public void EmailSentSucessVerify() {
		// Verification logic: Wait for a confirmation message or navigate to Sent Items and verify
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Message sent')]")));
		assert confirmation.isDisplayed();
	}
	
	public void SendEmailWithoutRece() {
		// Wait for the "Send" button to be visible and click it
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Send ‪(Ctrl-Enter)‬']")));
	    sendButton.click();
	}
	
	public void VerifyErrorValidation() {
		// Verification logic: Wait for the error message to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Please specify at least one recipient.')]")));
		assert errorMessage.isDisplayed();
	}
	
	
	@AfterClass
	public void CloseBrowser() {
		
		driver.quit();
	}
	/*
	 * public static void main(String[] args) { TestBase tb= new TestBase();
	 * tb.SetUpBrowser("Chrome"); }
	 */
}

