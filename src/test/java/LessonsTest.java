import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LessonsTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/D:\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "/Users/xbrookx/Documents/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

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






        driver.close();
    }
}