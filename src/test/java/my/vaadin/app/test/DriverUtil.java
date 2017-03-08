package my.vaadin.app.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DriverUtil {

	public static final String DEFAULT_CONFIG_PROPERTIES = "/config.properties";
	private Properties prop = new Properties();

	public DriverUtil(String propertyFileName) {
		try (InputStream in = DriverUtil.class.getResourceAsStream(propertyFileName)) {
			if (in != null) {
				prop.load(in);
			} else {
				throw new RuntimeException("Property file not found");
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to open Property file.", e);
		}
	}

	public DriverUtil() {
		this(DriverUtil.DEFAULT_CONFIG_PROPERTIES);
	}

	public WebDriver getPreferredDriver() {
		try {
			String headless = prop.getProperty("test.headless", "false");
			if (Boolean.TRUE.equals(Boolean.valueOf(headless))) {
				PhantomJSDriver driver = new PhantomJSDriver();
				driver.manage().window().setSize(new Dimension(1280, 720));
				return driver;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return new ChromeDriver();
	}

	public String getTestUrl() {
		return String.format("http://%s:%s", getTestHostname(), getTestPort());
	}

	public String getTestPort() {
		return prop.getProperty("jetty.port", "8080");
	}

	public String getTestHostname() {
		return prop.getProperty("test.hostname", "localhost");
	}
}
