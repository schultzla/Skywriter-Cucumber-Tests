package SkywriterTests.Cucumber;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		plugin= {
				"pretty",
				"html:target/cucumber-html-report",
				"json:target/cucumber-report.json",
				"com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport.html"
		}
		
		
		)

public class RunUserTest {
	
}
