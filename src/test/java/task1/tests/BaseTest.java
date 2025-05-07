package task1.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = BaseTest.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading application.properties", e);
        }
    }

    @BeforeAll
    public static void setUp() {
        // Настройка Selenide
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true; // браузер не закроется автоматически
        Configuration.holdBrowserOpen = false; // браузер закроется автоматически
        Configuration.timeout = 2000;
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 45000;

        // Установка базового URL из properties
        Configuration.baseUrl = properties.getProperty("base.url");

    }

}
