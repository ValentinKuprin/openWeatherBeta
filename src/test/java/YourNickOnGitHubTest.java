import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class YourNickOnGitHubTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://openweathermap.org/";

    @BeforeMethod
    public void setUp() {
        final String chromeDriver = "webdriver.chrome.driver";
        final String driverPath = "D:\\chromedriver.exe";
        final String driverPath1 = "C:\\Github\\\\tresh\\chromedriver.exe";
        final String driverPath2 = "/Users/xbrookx/Documents/chromedriver";

        if (Files.exists(Path.of(driverPath))) {
            System.setProperty(chromeDriver, driverPath);
        } else if (Files.exists(Path.of(driverPath1))) {
            System.setProperty(chromeDriver, driverPath1);
        } else if (Files.exists(Path.of(driverPath2))) {
            System.setProperty(chromeDriver, driverPath2);
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // Задержки перед каждым методом, вероятно нужно использовать в BeforeTest
    }

    @AfterMethod
    public void shutDown() {
        driver.quit();
    }

    /**
     * TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой страницы
     * OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testConfirmGoToPageAndTitlePage() throws InterruptedException {
        final String expectedGuidePage = "Guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(BASE_URL);

        Thread.sleep(5000);

        WebElement linkGuide = driver.findElement(By.xpath("//body/nav/ul/div/ul/li/a[@href = '/guide']"));
        linkGuide.click();
        WebElement h1Header = driver.findElement(By.xpath("//body/main//h1[@class= 'breadcrumb-title']"));
        String actualResult = h1Header.getText();
        String title = driver.getTitle();

        Assert.assertEquals(actualResult, expectedGuidePage);
        Assert.assertEquals(title, expectedTitle);
    }

    /**
     * TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test
    public void testChangeTemperature_FromCtoF() throws InterruptedException {
        final String expectedResult = "°F";

        driver.get(BASE_URL);

        Thread.sleep(5000);

        WebElement switchTemperature = driver.findElement(
                By.xpath("//body/main//div[@class = 'option' and contains (text(), 'Imperial:')]"));
        switchTemperature.click();
        WebElement heading = driver.findElement(By.xpath("//body/main//span[@class = 'heading']"));
        String temperature = heading.getText();
        String actualResult = temperature.substring(temperature.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
