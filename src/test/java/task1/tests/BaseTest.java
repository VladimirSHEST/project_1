package task1.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BaseTest {
    private static final Properties properties = new Properties();

    @BeforeAll
    public static void setUp() throws IOException {

        InputStream input = BaseTest.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(input);

        Configuration.browser = properties.getProperty("browser");
        Configuration.browserSize = properties.getProperty("browser.size");
        Configuration.holdBrowserOpen = Boolean.parseBoolean(properties.getProperty("holdBrowserOpen")); // браузер не закроется
        Configuration.headless = Boolean.parseBoolean(properties.getProperty("headless"));
        Configuration.pageLoadTimeout = 45000;

    }
}
