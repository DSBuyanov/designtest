//package org.example.tests;
//
//import org.example.pom.LoginPage;
//import org.example.pom.MainPage;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import com.codeborne.selenide.Configuration;
//
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selenide.open;
//import static com.codeborne.selenide.Selenide.closeWebDriver;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import io.qameta.allure.*;
//
//public class GeekBrainsStandTests {
//
//    private LoginPage loginPage;
//    private MainPage mainPage;
//
//    private static String USERNAME;
//    private static String PASSWORD;
//
//    @BeforeAll
//    public static void setupClass() {
//        USERNAME = "GB202306611b511";
//        PASSWORD = "2ee4e216d5";
//        }
//
//    @BeforeEach
//    public void setupTest() {
//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "121";
//
//        open("https://test-stand.gb.ru/login");
//
//        loginPage = new LoginPage();
//        mainPage = new MainPage();
//    }
//
//    @Test
//    @Epic("Авторизация")
//    @Feature("Логин")
//    @Story("Пустые поля логина")
//    @Severity(SeverityLevel.BLOCKER)
//    @Description("Проверка логина с пустыми полями. Ожидается ошибка '401 Invalid credentials.'")
//    public void testLoginWithEmptyFields() {
//
//        loginPage.clickLoginButton();
//        assertEquals("401 Invalid credentials.", loginPage.getErrorBlockText());
//    }
//
//    @Test
//    @Epic("Управление группами")
//    @Feature("Создание группы")
//    @Story("Создание новой группы")
//    @Severity(SeverityLevel.CRITICAL)
//    @Description("Проверка создания новой группы и отображения ее в списке групп.")
//    public void testAddingGroupOnMainPage() {
//
//        loginPage.login(USERNAME, PASSWORD);
//        mainPage = new MainPage();
//        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));
//
//        String groupTestName = "New Test Group " + System.currentTimeMillis();
//        mainPage.createGroup(groupTestName);
//        mainPage.firstGroupNameCell.shouldHave(text(groupTestName));
//    }
//
//
//
//    @AfterEach
//    public void teardown() {
//        closeWebDriver();
//    }
//
//}

