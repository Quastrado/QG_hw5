package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.testdata.TestDataPracticeForm.*;

public class PracticeFormTests extends TestBase {

   @Test // Заполнение всех полей формы
    void successfulFormCompletionTest(){
        // Открыть страницу с формой
        open("/automation-practice-form.html");
//        // Убрать баннер
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        // Заполнить поле FirstName
        $("[id=firstName]").click();
        $("[id=firstName]").setValue(firstName);
        // Заполнить поле LastName
        $("[id=lastName]").click();
        $("[id=lastName]").setValue(secondName);
        // Заполнить поле Email
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue(userEmail);
        // Установить значение Gender
        $("[id=gender-radio-3]").click();
        // Заполнить поле Mobile
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue(userNumber);
        // Заполнить поле Date of Birth
        $("[id=dateOfBirthInput]").click();
        $x("//*[@data-day='4']").click();
        // Заполнить поле Subjects
        $("[class=subjects-input-area]").click();
        $x("//*[contains(text(), 'Math')]").click();
        // Установить значение Hobbies
        $("[id=hobbies-checkbox-2]").click();
        // Добавить изображение
        $("[id=uploadPicture]").uploadFromClasspath(uploadPicture);
        // Заполнить поле Current Address
        $("[id=currentAddress]").click();
        $("[id=currentAddress]").setValue(currentAddress);
        // Заполнить поле State
        $("[id=state]").click();
        $x("//*[contains(text(), 'Uttar Pradesh')]").scrollIntoView(true);
        $x("//*[contains(text(), 'Uttar Pradesh')]").click();
        // Заполнить поле City
        $("[id=city]").click();
        $x("//*[contains(text(), 'Agra')]").click();
        // Выполнить отправку формы
        $("[id=submit]").scrollIntoView(true);
        $("[id=submit]").click();
        // Проверить что отправка формы была успешна
        $("[id=resultModal]").shouldHave(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text(successSubmitText));
    }

    @Test  // Заполнение только обязательных полей
    void requiredFieldsOnlyTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=firstName]").click();
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").click();
        $("[id=lastName]").setValue(secondName);
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue(userEmail);
        $("[id=gender-radio-3]").click();
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();
        $x("//*[@data-day='4']").click();
        $("[id=submit]").click();
        $("[id=resultModal]").shouldHave(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text(successSubmitText));
    }

    @Test  // Отправка пустой формы
    void unsuccessfulSubmitTest(){
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=submit]").scrollIntoView(true);
        $("[id=submit]").click();
        $("[id=formError]").shouldHave(text(formError));
    }

    @Test  // Отправка формы с некорректным номером телефона
    void incorrectNumberTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=firstName]").click();
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").click();
        $("[id=lastName]").setValue(secondName);
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue(userEmail);
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue(incorrextUserNumber);
        $("[id=submit]").click();
        $("[id=formError]").shouldHave(text(formError));
    }

    @Test  // Отправка формы с пустым полем First Name
    void submitWithoutNameTest() {
        open("/automation-practice-form.html");
        $("[aria-label=Close]").click();
        $("[id=lastName]").click();
        $("[id=lastName]").setValue(secondName);
        $("[id=userEmail]").click();
        $("[id=userEmail]").setValue(userEmail);
        $("[id=userNumber]").click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=submit]").click();
        $("[id=formError]").shouldHave(text(formError));
    }
}
