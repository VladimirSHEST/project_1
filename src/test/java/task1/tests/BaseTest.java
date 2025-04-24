package task1.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        // Настройка Selenide
        Configuration.browser = "chrome"; // Указываем браузер
        Configuration.browserSize = "1920x1080";  // Указать конкретный размер окна
        Configuration.holdBrowserOpen = true; // не закрывает браузер
        Configuration.timeout = 2000; // Таймаут ожидания элементов (в миллисекундах)
        Configuration.headless = false; // Режим без графического интерфейса (если true)

    }
}
