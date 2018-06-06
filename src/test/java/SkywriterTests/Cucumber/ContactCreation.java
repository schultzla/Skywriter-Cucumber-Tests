package SkywriterTests.Cucumber;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ContactCreation {
	
	String firstName, lastName;
	
	WebDriver driver;
	
	@Given("^User is on the add contact page$")
	public void userOnMainPage() {
		System.setProperty("webdriver.chrome.driver", "/Users/loganschultz/Desktop/github/jhipster/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://skywriter.innvosolutions.com");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("dev.admin");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");		

 		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/form[1]/div[4]/div/button")).click();
 		
 		driver.findElement(By.xpath("//*[@id=\"sidebarnav\"]/li[2]/a")).click();
 		driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/jhi-contact-skywriter/div/div/div/div/div[1]/ul/li[3]/button")).click();
	}
	
	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\" name$")
	public void userEntersAFirstAndLastName(String arg1, String arg2) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstName = arg1;
		lastName = arg2;
		
	    driver.findElement(By.xpath("//*[@id=\"nameFirst\"]")).sendKeys(arg1);
	    driver.findElement(By.xpath("//*[@id=\"nameLast\"]")).sendKeys(arg2);
	    driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/jhi-new-contact/div/div[2]/form/div/div[1]/div[2]/button[2]")).click();
	}

	@Then("^Skywriter \"([^\"]*)\" creating contact$")
	public void skywriterCreatesThisContactSuccesfully(String arg1) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if (arg1.equals("Passes")) {
			String name = firstName + " " + lastName;
			driver.get("https://skywriter.innvosolutions.com/#/contact-skywriter");
			
			assert(driver.getPageSource().contains(name));
		} else {
			boolean failure = driver.findElement(By.xpath("//*[@id=\"contactinfo\"]/div/div/div/div[2]/div[2]")).
					getText().equals("This field is required.");
			
			assert(failure);
		}
		
	    driver.quit();
	}

}
