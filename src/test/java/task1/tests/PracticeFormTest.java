package task1.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import task1.repository.PageRepository;

public class PracticeFormTest extends BaseTest{

    @Test
    public void fillFormPositive(){
        Configuration.pageLoadTimeout = 45000;

        PageRepository.practiceFormPage.openPage().setFirstName("Ivan").setLastName("Ivanov").setEmail("test@rest.com").
                setGender().setMobile("1234567890").setBirth().setSubject("Maths").setHobby().setPicture().
        setAddress("Moscow").setState().setCity().submitForm().submittingForm();

    }
}
