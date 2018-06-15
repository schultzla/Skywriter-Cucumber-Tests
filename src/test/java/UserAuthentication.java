import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserAuthentication {
	WebDriver driver;
	
	@Given("^User is on the main login page$")
	public void userOnMainPage() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://localhost:8080/");
	}
	
	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\" as credentials$")
	public void userEntersCredentials(String arg1, String arg2) {
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(arg1);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(arg2);
	}
	
	@Then("^Logging in \"([^\"]*)\"$")
	public void skywriterLogsIn(String arg1) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if (arg1.equals("Passes")) {
	 		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/form[1]/div[4]/div/button")).click();
	 		
	 		assert(driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/jhi-home/div[1]/h1")).getText().contains("Dashboard"));
		} else {
			//TODO add failure cases 
		}
 		
 		driver.quit();
	}
}