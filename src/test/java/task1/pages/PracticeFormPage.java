package task1.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    public SelenideElement firstNameInput = $x("//input[@id='firstName']");
    public SelenideElement lastNameInput = $x("//input[@id='lastName']");
    public SelenideElement emailInput = $x("//input[@id='userEmail']");
    public SelenideElement genderRadioMale = $x("//label[text()='Male']");
    public SelenideElement mobileInput = $x("//input[@id='userNumber']");
    // выбор дня рождения
    public SelenideElement dateOfBirthInput = $x("//input[@id='dateOfBirthInput']");
    public SelenideElement dateYear = $x("//select[@class='react-datepicker__year-select']");
    public SelenideElement dateYearValue = $x("//option[@value='1989']");
    public SelenideElement dateMonth = $x("//select[@class='react-datepicker__month-select']");
    public SelenideElement dateMonthValue = $x("//option[@value='5']");
    public SelenideElement dateDay = $x("//div[@class='react-datepicker__day react-datepicker__day--019']");
    // поле предметы Subjects
    public SelenideElement subjectsInput = $x("//input[@id='subjectsInput']");
    // поле Hobbies
    public SelenideElement hobbiesCheckbox = $x("//label[text()='Sports']");
    // поле для загрузки фото
    public SelenideElement uploadPicture = $x("//input[@id='uploadPicture']");
    // поле выбора адреса
    public SelenideElement addressInput = $x("//textarea[@id='currentAddress']");
    // штат и город
    public SelenideElement stateDropdown = $x("//div[@class=' css-tlfecz-indicatorContainer']");
    public SelenideElement stateDropdown2 = $x("//div[@id='react-select-3-option-2']");
    public SelenideElement cityDropdown = $x("//div[text()='Select City']");
    public SelenideElement cityDropdown2 = $x("//div[text()='Select City']");
    // кнопка подтвердить
    public SelenideElement submitButton = $x("//button[@id='submit']");
    // заполненная форма
    public SelenideElement submittingTheForm = $x("//div[@class='modal-header']");

    // Методы взаимодействия
    public PracticeFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $x("//div[@class='practice-form-wrapper']").shouldHave(text("Practice Form"));
        return this;
    }

    public PracticeFormPage setFirstName(String name) {
        firstNameInput.setValue(name);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public PracticeFormPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public PracticeFormPage setGender() {
        genderRadioMale.click();
        return this;
    }

    public PracticeFormPage setMobile(String number) {
        mobileInput.setValue(number);
        return this;
    }

    public PracticeFormPage setBirth() {
        dateOfBirthInput.click();
        dateYear.click();
        dateYearValue.click();
        dateMonth.click();
        dateMonthValue.click();
        dateDay.click();
        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage setHobby() {
        hobbiesCheckbox.click();
        return this;
    }

    public PracticeFormPage setPicture() {
        uploadPicture.uploadFromClasspath("img/аватарка.jpg");
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        addressInput.setValue(address);

        return this;
    }

    public PracticeFormPage setState() {
        stateDropdown.click();
        stateDropdown2.click();
        return this;
    }

    public PracticeFormPage setCity() {
        cityDropdown.click();
        cityDropdown2.click();
        return this;
    }

    public PracticeFormPage submitForm() {
        submitButton.click();
        return this;


    }public PracticeFormPage submittingForm() {
        submittingTheForm.shouldHave(text("Thanks for submitting the form"));
        return this;
    }
}
