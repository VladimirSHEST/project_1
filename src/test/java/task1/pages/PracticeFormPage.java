package task1.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    public SelenideElement firstNameInput() {
        return $x("//input[@id='firstName']").as("Поле ввода имени");
    }

    public SelenideElement lastNameInput(){
        return $x("//input[@id='lastName']").as("Поле ввода фамилии");
    }

    public SelenideElement emailInput(){
        return $x("//input[@id='userEmail']").as("Поле ввода е-мейла");
    }

    public SelenideElement genderRadioMale(){
        return $x("//label[.='Other']").as("кнопка выбора пола");
    }

    public SelenideElement mobileInput(){
        return $x("//input[@id='userNumber']").as("Поле ввода номера телефона");
    }

    // выбор дня рождения
    public SelenideElement dateOfBirthInput(){
        return $x("//input[@id='dateOfBirthInput']").as("Поле ввода дата рождения");
    }
    public SelenideElement dateYear(){
        return $x("//select[@class='react-datepicker__year-select']").as("Дроп списка для выбора года");
    }
    public SelenideElement dateYearValue(){
        return $x("//option[@value='1989']").as("Выбор года рождения");
    }
    public SelenideElement dateMonth(){
        return $x("//select[@class='react-datepicker__month-select']").as("Дроп списка для выбора месяца");
    }
    public SelenideElement dateMonthValue(){
        return $x("//option[@value='5']").as("Выбор месяца рождения");
    }
    public SelenideElement dateDay(){
        return $x("//div[@class='react-datepicker__day react-datepicker__day--019']")
                .as("Выбор числа рождения");
    }

   // поле предметы Subjects
    public SelenideElement subjectsInput(){
        return $x("//input[@id='subjectsInput']").as("Поле для ввода предмета");
    }

    // поле Hobbies
    public SelenideElement hobbiesCheckbox(){
        return $x("//label[text()='Sports']").as("Выбор хобби");
    }
    // поле для загрузки фото
    public SelenideElement uploadPicture(){
        return $x("//input[@id='uploadPicture']").as("Кнопка для загрузки фото");
    }

    // поле выбора адреса
    public SelenideElement addressInput(){
        return $x("//textarea[@id='currentAddress']").as("Поле ввода адреса");
    }

    // штат и город
    public SelenideElement stateDropdown(){
        return $x("//div[@class=' css-1hwfws3']").as("Кнопка для выпадения штата");
    }
    public SelenideElement stateDropdown2(){
        return $x("//div[@id='react-select-3-option-2']").as("Выбор штата");
    }
    public SelenideElement cityDropdown(){
        return $x("//div[@id='city']").as("Кнопка для выпадения города");
    }
    public SelenideElement cityDropdown2(){
        return $x("//div[text()='Karnal']").as("Выбор города");
    }

    // кнопка подтвердить
    public SelenideElement submitButton(){
        return $x("//button[@id='submit']").as("Кнопка подтвердить");
    }
    // заполненная форма
    public SelenideElement submittingTheForm(){
        return $x("//div[@class='modal-header']").as("Заполненная форма");
    }
}
