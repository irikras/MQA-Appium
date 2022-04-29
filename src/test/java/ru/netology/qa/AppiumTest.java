package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.Locator;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppiumTest {
    private AndroidDriver driver;
    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 4XL (Edited) API 32");
        desiredCapabilities.setCapability("appium:app", "C:\\!Testing\\mobile\\mqa-homeworks-main\\2.2 UI Automator\\sample\\app\\build\\intermediates\\apk\\debug\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }
    @Order(1)
    @Test
    public void testEmptyText() {
        Locator locator = new Locator(driver);
        String textBefore = locator.textToBeChanged.getText();
        locator.userInput.sendKeys(" ");
        locator.buttonChange.click();

        assertEquals(textBefore, locator.textToBeChanged.getText());
    }
    @Order(2)
    @Test
    public void testNewActivity() {
        Locator locator = new Locator(driver);
        String textToSet = "Netology";
        locator.userInput.sendKeys(textToSet);
        locator.buttonActivity.click();

        assertEquals(textToSet, locator.resultText.getText());
    }
    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}


