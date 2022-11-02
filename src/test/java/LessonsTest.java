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

public class LessonsTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUP() {

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "D:\\chromedriver.exe";
        String driverPath1 = "C:\\Github\\\\tresh\\chromedriver.exe";
        String driverPath2 = "/Users/xbrookx/Documents/chromedriver";

        if (Files.exists(Path.of(driverPath))) { // проверка существует или нет
            System.setProperty(chromeDriver, driverPath);
        } else if (Files.exists(Path.of(driverPath1))) {
            System.setProperty(chromeDriver, driverPath1);
        } else if (Files.exists(Path.of(driverPath2))){
            System.setProperty(chromeDriver, driverPath2);
        }
//        System.setProperty("webdriver.chrome.driver", "/D:\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "/C:\\Github\\\\tresh\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "/Users/xbrookx/Documents/chromedriver");
//        или
//          static {  создаем экземпляр драйвера
//        WebDriverManager.chromeDriver().setup(); или WebDriverManager.firefoxDriver().setup() и driver = new FirefoxDriver();
//    }

        driver = new ChromeDriver();
    }

    @AfterMethod
    public void shutDown() {
        driver.quit();
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        final String url = "https://openweathermap.org/";
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";

        driver.get(url);

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = driver.findElement(By.xpath("//button[@type =  'submit']"));
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']"));
        parisFRChoiceInDropdownMenu.click();

        Thread.sleep(1500);

        WebElement h2CityCountryHeader = driver.findElement(By.xpath("//div[@id = 'weather-widget']//h2"));
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}