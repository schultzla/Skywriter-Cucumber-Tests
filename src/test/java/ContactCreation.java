import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactCreation {

	String firstName, lastName;

	WebDriver driver;

	@Given("^User is on the add contact page$")
	public void userOnMainPage() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://localhost:8080/");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("dev.admin");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("admin");	

		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/form[1]/div[4]/div/button")).click();

		driver.findElement(By.xpath("//*[@id=\"contact-menu\"]/span")).click(); 
		driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/jhi-contact-skywriter/div/div/div/div/div[1]/ul/li[3]/button")).click();
	}
	

	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\" name$")
	public void userEntersAFirstAndLastName(String arg1, String arg2) {
		firstName = arg1;
		lastName = arg2;

		driver.findElement(By.xpath("//*[@id=\"nameFirst\"]")).sendKeys(arg1);
		driver.findElement(By.xpath("//*[@id=\"nameLast\"]")).sendKeys(arg2);
		driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/jhi-new-contact/div/div[2]/form/div/div[1]/div[2]/button[2]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^Skywriter \"([^\"]*)\" creating contact$")
	public void skywriterCreatesThisContactSuccesfully(String arg1) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if (arg1.equals("Passes")) {
			String name = firstName + " " + lastName;

			String siteName = driver.findElement(By.xpath(
					"//*[@id=\"main-wrapper\"]/div/div/jhi-contact-skywriter-detail/div/div[1]/div[1]/div/div[1]/div[2]")).getText();
			assert(name.contains(siteName));
		} else {
			boolean failure = driver.findElement(By.xpath("//*[@id=\"contactinfo\"]/div/div/div/div[2]/div[2]")).
					getText().equals("This field is required.");

			assert(failure);
		}

		driver.quit();
	}

}
