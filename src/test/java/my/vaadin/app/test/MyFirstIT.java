package my.vaadin.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;

import com.vaadin.testbench.TestBenchTestCase;

@RunWith(BlockJUnit4ClassRunner.class)
public class MyFirstIT extends TestBenchTestCase {

	private WebDriver driver;
	private DriverUtil driverUtil = new DriverUtil();

	@Before
	public void setup() {
		driver = driverUtil.getPreferredDriver();
	}

	@Test
	public void testMyStuff() {
		driver.get(driverUtil.getTestUrl());

		// findElement(By.className("primary")).click();
	}

	@After
	public void teardown() {
		driver.close();
	}
}
