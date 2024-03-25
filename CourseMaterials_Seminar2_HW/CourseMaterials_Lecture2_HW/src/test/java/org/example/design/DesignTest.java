package org.example.design;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.comparison.ImageMarkupPolicy;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.*;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Epic("Дизайн")
@Feature("Проверка визуального дизайна")
public class DesignTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @Story("Проверка дизайна на разрешении 1920px")
    @DisplayName("Проверка дизайна на разрешении 1920px")
    public void testDesignOn1920px() throws IOException {
        // Открываем веб-страницу
        open("https://ru.wikipedia.org/wiki/Angular_(%D1%84%D1%80%D0%B5%D0%B9%D0%BC%D0%B2%D0%BE%D1%80%D0%BA)"); // Замените "your_website_url_here" на адрес вашего сайта
        Selenide.sleep(3000);

        // Снимаем скриншот страницы
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(300))
                .coordsProvider(new WebDriverCoordsProvider())
                .imageCropper(new IndentCropper()) // Обрезаем изображение, если требуется
                .takeScreenshot(WebDriverRunner.getWebDriver());

        // Загружаем эталонный скриншот
        BufferedImage expectedImage = ImageIO.read(new File("C:\\Users\\denis.buyanov\\Downloads\\CourseMaterials_Seminar2_HWфдд\\CourseMaterials_Seminar2_HW\\CourseMaterials_Lecture2_HW\\src\\test\\resources\\expected_screenshots\\expected_design_1920px.png"));

        // Создаем объект для сравнения скриншотов
        ImageDiffer imageDiffer = new ImageDiffer();

        // Устанавливаем политику выделения различий (цвет выделения)
        imageDiffer.withDiffMarkupPolicy(new ImageMarkupPolicy().withDiffColor(Color.RED));

        // Сравниваем скриншоты
        ImageDiff diff = imageDiffer.makeDiff(expectedImage, actualScreenshot.getImage());

        // Проверяем, есть ли различия между скриншотами
        if (diff.getDiffSize() != 0) {
            // Если есть различия, добавляем метку в отчет Allure
            Allure.label("testType", "screenshotDiff");

            // Прикрепляем скриншоты к отчету Allure
            attachImg("expected", expectedImage);
            attachImg("actual", actualScreenshot.getImage());

            // Прикрепляем выделенное изображение с различиями к отчету Allure
            attachImg("diff", diff.getMarkedImage());

            // Прикрепляем прозрачное изображение с различиями к отчету Allure
            attachImg("diff (прозрачность)", diff.getTransparentMarkedImage());

            // Проверяем, что отличий нет
            Assertions.assertAll(
                    () -> assertTrue(diff.hasDiff(), "Дизайн страницы не соответствует эталону. Обнаружены отличия."),
                    () -> assertEquals(0, diff.getDiffSize(), "Количество отличий больше нуля.")
            );
        }
    }
    // Метод для прикрепления изображения к отчету Allure
    public static void attachImg(String name, BufferedImage image) {
        try {
            // Преобразуем изображение в массив байтов
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Прикрепляем изображение к отчету Allure
            Allure.addAttachment(name, "image/png", new ByteArrayInputStream(imageBytes), "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterEach
    public void teardown() {
        closeWebDriver();
    }
}
