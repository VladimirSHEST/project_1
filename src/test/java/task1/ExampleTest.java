package task1;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ExampleTest extends BaseTest {
    @Test
    public void testGoogleSearch() {
        // Открываем страницу Google
        open("https://www.google.ru/?hl=ru");

    }
}
