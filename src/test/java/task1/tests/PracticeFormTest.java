package task1.tests;


import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static task1.repository.PageRepository.practiceFormPage;

public class PracticeFormTest extends BaseTest {

    @Test
    public void fillFormPositive() {
        open("automation-practice-form");

        step("Ввод имени в поле", () -> {
            practiceFormPage.firstNameInput().setValue("Jon").pressEnter();
        });
        step("Ввод фамилии в поле", () -> {
            practiceFormPage.lastNameInput().setValue("Smith").pressEnter();
        });
        step("Поле ввода е-мейла", () -> {
            practiceFormPage.emailInput().setValue("test@test.com").pressEnter();
        });
        step("кнопка выбора пола", () -> {
            practiceFormPage.genderRadioMale().click();
        });
        step("Поле ввода номера телефона", () -> {
            practiceFormPage.mobileInput().setValue("1234567890");
        });
        // выбор дня рождения
        step("Поле ввода дата рождения", () -> {
            practiceFormPage.dateOfBirthInput()
                    .shouldBe(visible, Duration.ofSeconds(10)) // явно указываем 10 секунд ожидания
                    .click();
        });

        step("Дроп списка для выбора года", () -> {
            practiceFormPage.dateYear().click();
        });
        step("Выбор года рождения", () -> {
            practiceFormPage.dateYearValue().click();
        });
        step("Дроп списка для выбора месяца", () -> {
            practiceFormPage.dateMonth().click();
        });
        step("Выбор месяца рождения", () -> {
            practiceFormPage.dateMonthValue().click();
        });
        step("Выбор числа рождения", () -> {
            practiceFormPage.dateDay().click();
        });

          // поле предметы Subjects
        step("Выбор хобби", () -> {
            practiceFormPage.subjectsInput().setValue("Maths").pressEnter();
        });
        // поле Hobbies
        step("Ввод предмета", () -> {
            practiceFormPage.hobbiesCheckbox()
                    .click();
        });

        // поле для загрузки фото
        step("Кнопка для загрузки фото", () -> {
            practiceFormPage.uploadPicture().uploadFromClasspath("img/аватарка.jpg");
        });
        // поле выбора адреса
        step("Поле ввода адреса", () -> {
            practiceFormPage.addressInput().setValue("Адрес");
        });
        // штат и город
        step("Кнопка для выпадения штата", () -> {
            practiceFormPage.stateDropdown()
                    .scrollIntoView("{block: 'center'}") // Прокрутить к центру экрана
                    .click();
        });
        step("Выбор штата", () -> {
            practiceFormPage.stateDropdown2().click();
        });
        step("Кнопка для выпадения города", () -> {
            practiceFormPage.cityDropdown().click();
        });
        step("Выбор города", () -> {
            practiceFormPage.cityDropdown2().click();
        });
        step("Кнопка подтвердить", () -> {
            practiceFormPage.submitButton().click();
        });
        step("Заполненная форма", () -> {
            practiceFormPage.submittingTheForm().click();
        });

    }
}
