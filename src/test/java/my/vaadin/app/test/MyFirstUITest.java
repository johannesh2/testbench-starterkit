package my.vaadin.app.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TextFieldElement;

@RunWith(BlockJUnit4ClassRunner.class)
public class MyFirstUITest extends TestBenchTestCase {

	private WebDriver driver;

	@Before
	public void setup() {
		setDriver(driver = new FirefoxDriver());
	}

	@Test
	public void testMyStuff() {
		driver.get("http://localhost:8080");
		$(ButtonElement.class).caption("Add new customer").first().click();
		assertTrue($(TextFieldElement.class).caption("Email").exists());
	}

	@After
	public void teardown() {
		driver.close();
	}
}
