package task1.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task1.pages.PracticeFormPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static task1.repository.PageRepository.practiceFormPage;

public class PracticeFormTest extends BaseTest {

    @Test
    @DisplayName("Заполнение формы валидными данными")
    public void fillFormPositive() {

        step("Открытие страницы Practice Form", () -> {
            open(PracticeFormPage.url());
        });

        step("Заполнение формы", () -> {
            practiceFormPage.firstNameInput().setValue("Jon");
            practiceFormPage.lastNameInput().setValue("Smith");
            practiceFormPage.emailInput().setValue("test@mail.com");
            practiceFormPage.genderRadioMale().click();
            practiceFormPage.genderRadioMale().click();
            practiceFormPage.mobileInput().setValue("1234567890");
            practiceFormPage.dateOfBirthInput().click();
            practiceFormPage.dateYear().click();
            practiceFormPage.dateYearValue().click();
            practiceFormPage.dateMonth().click();
            practiceFormPage.dateMonthValue().click();
            practiceFormPage.dateMonthValue().click();
            practiceFormPage.dateDay().click();
            practiceFormPage.subjectsInput().setValue("Maths").pressEnter();
            practiceFormPage.hobbiesCheckbox().click();
            practiceFormPage.uploadPicture().uploadFromClasspath("img/аватарка.jpg");
            practiceFormPage.addressInput().setValue("Адрес");
            practiceFormPage.stateDropdown().scrollIntoView("{block: 'center'}").click();
            practiceFormPage.stateDropdown2().click();
            practiceFormPage.cityDropdown().click();
            practiceFormPage.cityDropdown2().click();
        });

        step("Кнопка подтвердить", () -> {
            practiceFormPage.submitButton().click();
        });

        step("Появление заполненной формы", () -> {
            practiceFormPage.submittingTheForm().click();
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

    @DisplayName("Заполнение формы невалидными данными")
    public void testInvalidMobileNumbers(String phoneNumber) {

        step("Открытие страницы Practice Form", () -> {
            open(PracticeFormPage.url());
        });

        step("Заполняем обязательные поля валидными значениями", () -> {
            practiceFormPage.firstNameInput().setValue("Jon");
            practiceFormPage.lastNameInput().setValue("Smith");
            practiceFormPage.genderRadioMale().click();
        });

        step("Ввод невалидного номера телефона", () -> {
            practiceFormPage.mobileInput().setValue(phoneNumber);
        });

        step("Кнопка подтвердить", () -> {
            practiceFormPage.stateDropdown().scrollIntoView("{block: 'center'}").click();
            practiceFormPage.submitButton().click();
        });

        step("Проверка ошибки, форма не заполнена", () -> {
            practiceFormPage.submittingTheForm().shouldNot(exist);
        });
    }

    @ParameterizedTest(name = "Невалидный e-mail: {0}")
    @ValueSource(strings = {
            "testmail.com",  // Без @
            "test@",  // Без домена

    })

    @DisplayName("Заполнение формы невалидными данными")
    public void testInvalidMobileEmail(String email) {

        step("Открытие страницы Practice Form", () -> {
            open(PracticeFormPage.url());
        });

        step("Заполняем обязательные поля валидными значениями", () -> {
            practiceFormPage.firstNameInput().setValue("Jon");
            practiceFormPage.lastNameInput().setValue("Smith");
            practiceFormPage.genderRadioMale().click();
        });

        step("Ввод невалидного номера телефона", () -> {
            practiceFormPage.emailInput().setValue(email);
        });

        step("Кнопка подтвердить", () -> {
            practiceFormPage.stateDropdown().scrollIntoView("{block: 'center'}").click();
            practiceFormPage.submitButton().click();
        });

        step("Проверка ошибки, форма не заполнена", () -> {
            practiceFormPage.submittingTheForm().shouldNot(exist);
        });
    }
}
