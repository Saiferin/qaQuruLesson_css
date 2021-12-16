package guru.qa;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestForms {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1080";
        Configuration.holdBrowserOpen = false;
    }

    @DisplayName("Автотесты формы")
    @Test
    void testForms (){

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("Dmitry");
        $("#lastName").setValue("Badikov");
        $("#userEmail").setValue("dv.badikov@bk.ru");
        $x("//label[contains(text(),'Male')]").click();
        $("#userNumber").setValue("1111111111");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1987");
        $x("//div[contains(text(),'24')]").click();

        $("#subjectsInput").hover().setValue("Arts").pressEnter();
        $("#subjectsInput").setValue("Phy").pressEnter();

        $x("//label[contains(text(),'Sports')]").click();
        $x("//label[contains(text(),'Music')]").click();

        $("#uploadPicture").uploadFromClasspath("testPass.png");

        $("#currentAddress").setValue("Bla bla bla bla");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldBe(visible);

        $$x("//*[@class='modal-body']//td[2]").shouldHave(CollectionCondition
                .exactTexts("Dmitry Badikov", "dv.badikov@bk.ru", "Male", "1111111111",
                        "24 December,1987", "Arts, Physics", "Sports, Music", "testPass.png",
                        "Bla bla bla bla", "NCR Delhi"));

    }

}
