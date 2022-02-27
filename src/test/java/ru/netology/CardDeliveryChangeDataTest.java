package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryChangeDataTest {

    private Faker faker;


    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        open("http://localhost:9999");
        faker = new Faker(new Locale("ru"));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }


    @Test
    public void shouldSentForm() {
        String dateFirst = DataGenerator.Registration.getDate(4);
        String dateSecond = DataGenerator.Registration.getDate(20);
        $("[data-test-id=city] input").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(dateFirst);
        $("[data-test-id=name] input").setValue(DataGenerator.Registration.generateName("ru"));
        $("[data-test-id = phone] input").setValue(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(visible)
                .shouldHave(exactText("Успешно!\n" + "Встреча успешно запланирована на " + dateFirst));
        $(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input input").setValue(dateSecond);
        $(".button").click();
        $("[data-test-id=replan-notification]").shouldHave(text
                ("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id=replan-notification] button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible).
                shouldHave(exactText("Успешно!\n" + "Встреча успешно запланирована на " + dateSecond));

    }


    @Test
    public void shouldSentInvalidCity() {
        String dateFirst = DataGenerator.Registration.getDate(4);
        $("[data-test-id=city] input").setValue("Тайшет");
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(dateFirst);
        $("[data-test-id=name] input").setValue(DataGenerator.Registration.generateName("ru"));
        $("[data-test-id = phone] input").setValue(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=city] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    public void shouldSentInvalidData() {
        String dateFirst = DataGenerator.Registration.getDate(4);
        $("[data-test-id=city] input").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id = date] input").doubleClick().sendKeys("12.01.2021");
        $("[data-test-id = date] input").setValue(dateFirst);
        $("[data-test-id=name] input").setValue(DataGenerator.Registration.generateName("ru"));
        $("[data-test-id = phone] input").setValue(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=date] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    public void shouldSentInvalidName() {
        String dateFirst = DataGenerator.Registration.getDate(4);
        $("[data-test-id=city] input").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(dateFirst);
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id = phone] input").setValue(DataGenerator.Registration.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void shouldSentInvalidPhone() {
        String dateFirst = DataGenerator.Registration.getDate(4);
        String dateSecond = DataGenerator.Registration.getDate(20);
        $("[data-test-id=city] input").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(dateFirst);
        $("[data-test-id=name] input").setValue(DataGenerator.Registration.generateName("ru"));
        $("[data-test-id = phone] input").setValue("89887776655");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone] .input__sub").shouldBe(visible)
                .shouldHave(exactText("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
    }

    @Test
    public void shouldSentInactiveCheckbox() {
        String dateFirst = DataGenerator.Registration.getDate(4);
        String dateSecond = DataGenerator.Registration.getDate(20);
        $("[data-test-id=city] input").setValue(DataGenerator.Registration.generateCity("ru"));
        $("[data-test-id = date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date] input").setValue(dateFirst);
        $("[data-test-id=name] input").setValue(DataGenerator.Registration.generateName("ru"));
        $("[data-test-id = phone] input").setValue(DataGenerator.Registration.generatePhone("ru"));
        $(".button").click();
        $("[data-test-id=agreement]").shouldBe(visible)
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }
}


