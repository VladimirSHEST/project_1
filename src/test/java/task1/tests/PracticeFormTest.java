package task1.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task1.pages.PracticeFormPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static task1.pages.PracticeFormPage.*;
import static task1.pages.PracticeFormPage.USER_EMAIL;
import static task1.repository.PageRepository.practiceFormPage;

public class PracticeFormTest extends BaseTest {

    @Test
    @DisplayName("Заполняем форму валидными данными")
    public void fillFormPositive() {

        step("Открываем страницу Practice Form", () -> {
            open(PracticeFormPage.url());
        });

        step("Заполняем валидную форму", () -> {
            practiceFormPage.firstNameInput().setValue(NAME);
            practiceFormPage.lastNameInput().setValue(LAST_NAME);
            practiceFormPage.emailInput().setValue(USER_EMAIL);
            practiceFormPage.genderRadioMale().click();
            practiceFormPage.mobileInput().setValue(USER_NUMBER);
            practiceFormPage.dateOfBirthInput().click();
            practiceFormPage.dateYear().click();
            practiceFormPage.dateYearValue().click();
            practiceFormPage.dateMonth().click();
            practiceFormPage.dateMonthValue().click();
            practiceFormPage.dateDay().click();
            practiceFormPage.subjectsInput().setValue("Maths").pressEnter();
            practiceFormPage.hobbiesCheckbox().click();
            practiceFormPage.uploadPicture().uploadFromClasspath("img/аватарка.jpg");
            practiceFormPage.addressInput().setValue("город Самара");
            practiceFormPage.stateDropdown().scrollIntoView("{block: 'center'}").click();
            practiceFormPage.stateDropdown2().click();
            practiceFormPage.cityDropdown().click();
            practiceFormPage.cityDropdown2().click();
        });

        step("Нажимаем кнопку подтвердить", () -> {
            practiceFormPage.submitButton().click();
        });

        step("Проверяем данные формы", () -> {
            practiceFormPage.isVisibleForm();
            practiceFormPage.isVisibleText();
        });
    }

    @ParameterizedTest(name = "Невалидный номер: {0}")
    @ValueSource(strings = {
            "123456789",  // 9 цифр
            "",           // пусто
            "abcde",      // буквы
            "123-456-78", // символы
            "123 456 7890" // пробелы
    })
    @DisplayName("Заполняем номер телефона невалидными данными")
    public void testInvalidMobileNumbers(String phoneNumber) {
        step("Открываем страницу Practice Form", () -> {
            open(PracticeFormPage.url());
        });

        step("Заполняем обязательные поля валидными значениями", () -> {
            practiceFormPage.firstNameInput().setValue(NAME);
            practiceFormPage.lastNameInput().setValue(LAST_NAME);
            practiceFormPage.genderRadioMale().click();
        });

        step("Вводим невалидный номер телефона", () -> {
            practiceFormPage.mobileInput().setValue(phoneNumber);
        });

        step("Нажимаем кнопку подтвердить", () -> {
            practiceFormPage.stateDropdown().scrollIntoView("{block: 'center'}").click();
            practiceFormPage.submitButton().click();
        });

        step("Проверяем,что форма не заполнена", () -> {
            practiceFormPage.isNotVisibleForm().shouldNot(exist);
        });
    }

    @ParameterizedTest(name = "Невалидный e-mail: {0}")
    @ValueSource(strings = {
            "testmail.com",  // Без @
            "test@",  // Без домена
    })

    @DisplayName("Заполняем форму невалидным email")
    public void testInvalidMobileEmail(String email) {

        step("Открываем страницу Practice Form", () -> {
            open(PracticeFormPage.url());
        });

        step("Заполняем обязательные поля валидными значениями", () -> {
            practiceFormPage.firstNameInput().setValue(NAME);
            practiceFormPage.lastNameInput().setValue(LAST_NAME);
            practiceFormPage.genderRadioMale().click();
        });

        step("Вводим невалидный email", () -> {
            practiceFormPage.emailInput().setValue(email);
        });

        step("Нажимаем кнопку подтвердить", () -> {
            practiceFormPage.stateDropdown().scrollIntoView("{block: 'center'}").click();
            practiceFormPage.submitButton().click();
        });

        step("Проверяем,что форма не заполнена", () -> {
            practiceFormPage.isNotVisibleForm().shouldNot(exist);
        });
    }
}
