package task1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        // Настройка Selenide
        Configuration.browser = "chrome"; // Указываем браузер
        Configuration.browserSize = "1920x1080";  // Указать конкретный размер окна
//        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000; // Таймаут ожидания элементов (в миллисекундах)
        Configuration.headless = false; // Режим без графического интерфейса (если true)

    }
}
