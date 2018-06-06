

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserAuthentication {
	WebDriver driver;
	
	@Given("^User is on the main login page$")
	public void userOnMainPage() {
		System.setProperty("webdriver.chrome.driver", "/Users/loganschultz/Desktop/github/jhipster/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://skywriter.innvosolutions.com");
	}
	
	@When("^User enters dev.admin into user and password$")
	public void userEntersCredentials() {
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("dev.admin");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");
	}
	
	@Then("^Skywriter logs in successfully$")
	public void skywriterLogsIn() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

 		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/form[1]/div[4]/div/button")).click();
 		
 		assert(driver.getPageSource().contains("Welcome"));
 		
 		driver.quit();
	}
}