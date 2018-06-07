

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserAuthentication {
	WebDriver driver;
	
	@Given("^User is on the main login page$")
	public void userOnMainPage() {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);

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
 		
 		assert(driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/jhi-home/div[1]/h1")).getText().contains("User Dashboard"));
 		
 		driver.quit();
	}
}