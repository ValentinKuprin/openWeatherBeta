import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class Marshal {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // driver.get("https://google.com");

        // WebElement input = driver.findElement(By.id("input"));
        // input.click();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // WebElement element = (new WebDriverWait(driver, Duration.ofSeconds())).until(Expre);
        //   driver.get("https://www.avito.ru/");
//
        //   WebElement element1 = driver.findElement(By.xpath("(//a[text() ='Личные вещи'])"));
        //   String par = element1.getAttribute("offsetWidth");
        //   System.out.println(par);


//       try { /1-35
//           driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
//           Thread.sleep(2000);

//           WebElement element = driver.findElement(By.id("draggable"));
//           WebElement element2 = driver.findElement(By.id("draggable"));


//           Actions actions = new Actions(driver);

//           actions
//                   //.dragAndDropBy(element, 100, 100);

//                   .pause(1000)

//                   .moveToElement(element)

//                   .clickAndHold()

//                   .moveToElement(element2)

//                   .release()

//                   .build()

//                   .perform();


//       } catch (InterruptedException e) {
//           e.printStackTrace();
//       }
//       finally {
//           Thread.sleep(20000);
//           driver.quit();
//       }
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//
//
//        try {
//            driver.get("http://pagination.js.org/");
//            Thread.sleep(2000);
//            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
//            List<WebElement> pages = driver.findElements(By.xpath("//div[@class='paginationjs-pages']/ul/li"));
//            String text = elements.get(5).getText();
//            System.out.println(text);
//
//
//            pages.get(2).click();
//            wait.until(ExpectedConditions.stalenessOf(elements.get(5)));
//
//            elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
//
//            text = elements.get(5).getText();
//            System.out.println(text);
//
//
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            driver.quit();
//
//
//    }
//
//
//
//
    }
}
