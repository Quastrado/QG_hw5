package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static tests.testdata.TestDataTextBox.*;

public class TextBoxTests extends TestBase {

    @Test // Успешное заполнение и отправка формы
    void successfulFormCompletionTest(){
        open("/text-box.html");
        $("input[id=userName]").setValue(userName);
        $("input[id=userEmail]").setValue(userEmail);
        $("textarea[id=currentAddress]").setValue(address);
        $("textarea[id=permanentAddress]").setValue(address);
        $("button[id=submit]").click();

        $("p[id=name]").shouldHave(text(expectedName));
        $("p[id=email]").shouldHave(text(expectedEmail));
        $("p[id=currentAddress]").shouldHave(text(expectedCurrentAddress));
        $("p[id=permanentAddress]").shouldHave(text(expectedPermanentAddress));
    }

    @Test // Отправка формы при некорректном значении Email
    void incorrectEmail(){
        open("/text-box.html");
        $("input[id=userEmail]").setValue(incorrectUserEmail);
        $("button[id=submit]").click();
        $("p[id=email]").shouldNotHave(visible);
    }
}
