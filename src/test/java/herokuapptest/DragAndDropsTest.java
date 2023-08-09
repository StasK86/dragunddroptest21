package herokuapptest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropsTest {

    @BeforeAll
    static void beforeAll(){
        baseUrl="https://the-internet.herokuapp.com/drag_and_drop";
        Configuration.browserSize="1920x1080";
        Configuration.pageLoadStrategy="eager";
    }
    @Test
    void actionsDragAndDrop() {
        // не работает!
        open(baseUrl);
        SelenideElement a = $("column-A");
        SelenideElement b = $("#column-B");
        actions().clickAndHold(a).moveToElement(b).release().build().perform();
        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));

    }

    @Test
    void testDragAndDrop() {
        // работает!
        open(baseUrl);
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}

