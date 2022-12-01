package com.example.seleniumprueba;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800"; // Resolución pantalla que se va a abrir
        SelenideLogger.addListener("allure", new AllureSelenide()); // Abrir pantalla
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");     // Página que va a abrirse
    }

    @Test
    public void search() {
        mainPage.searchButton.click();  // Aquí busca un botón usando el asistente y le hace click

        $("[data-test='search-input']").sendKeys("Selenium"); // Busca la etiqueta y escribe "Selenium"
        $("button[data-test='full-search-button']").click();  // Busca especificamente un botón y le clicka

        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
        // En caso de no encontrar algún elemento de lo que busca salta error
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        $("div[data-test='main-submenu']").shouldBe(visible);  // Busca que el main menú sea visible
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeAllToolsButton.click();

        $("#products-page").shouldBe(visible);  // Busca la página y ve si es visible

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
        // Mira que el título de la página sea el siguiente
    }
}
