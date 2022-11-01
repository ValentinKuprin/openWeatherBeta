import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OmayoBlogspotCom {

    public static final String URL = "http://omayo.blogspot.com/";
    // https://www.youtube.com/playlist?list=PLsjUcU8CQXGHwYF_cL_YvfT3KtbggJIOT
    // https://www.youtube.com/playlist?list=PLsjUcU8CQXGErA7pq8DGBSNiKdr-cKpdg

    /////// РАБОТА С ОКНАМИ /////////

    @Test
    public static void checkMessageAllert() { // Получить информацию из нового окна
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.findElement(By.xpath("//body//div[@class='column-right-outer']//input[@id='alert1']")).click(); //buttonGoToAlert
        Alert alert = driver.switchTo().alert(); // переключиться на поп ап, новое окно
        alert.getText(); // получить текст из окна
        System.out.println(alert.getText());
    }

    @Test
    public static void clickIframe() { // переключиться в окно
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.switchTo().frame("iframe2"); // переключить в окно
        driver.findElement(By.id("")).click(); // выбрать элемент в окне
    }

    @Test
    public static void getTitlePopUpWindow() { //Получить имя окна
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.findElement(By.linkText("Open a popup window")).click();
        Set<String> windiwsIds = driver.getWindowHandles(); //получить все индификаторы окон, возвращаемый тип набор строк

        Iterator<String> itr = windiwsIds.iterator(); //
        String defaultWindiwsId = itr.next(); // окно по умолчанию
        String childWindiwsId = itr.next(); //дочернее окно (попап)

        driver.switchTo().window(childWindiwsId); // переключиться на окно попапа
        System.out.println(driver.getTitle());
    }

    @Test
    public static void swichTowindows() { // Переключение между окнами
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        String firstWindow = driver.getWindowHandle(); //захват окна
        driver.findElement(By.linkText("Open a popup window")).click();
        Set<String> windows = driver.getWindowHandles(); //захват нескольких окон
        Iterator<String> itr = windows.iterator(); // перебор окон
        while (itr.hasNext()) { // зайти и посмотреть, есть ли окна ? если есть вернуть первое

            String window = itr.next();

            driver.switchTo().window(window); // переключиться на окно первой итерации
            if (driver.getTitle().equals("Basic Web Page Title")) { //ищем окно попапа
                driver.close();
            }

            driver.switchTo().window(firstWindow);
            driver.findElement(By.name("q")).sendKeys("Arun");
        }
    }

    /////////////////////////////////////////////////////// WAIT ///////////////////////////////////
    @Test
    public void checkCheckbox() { // + Ограничения загрузки элемента
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); // Ограничения загрузки страницы 5 сек.
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS); // Ожидание для скриптов с синхронным кодом, например нажатие кнопки
        driver.get(URL);
        System.out.println(driver.findElement(By.id("checkbox1")).isSelected());  // проверка стоит ли чекбок тру да фолс нет
    }

    @Test
    public void testDropdawn() { // + Неявное ожидание (глобальное)
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Неявное ожидание, время для какого то действия, дропдаун выпадает через 3 секунды, ждем выпадения дропдауна
        // и выполняем действие, (ожидание до момента действия но не более 10 сек.)

        driver.get(URL);
        driver.findElement(By.className("dropbtn")).click();
        driver.findElement(By.linkText("Facebook")).click();
        driver.navigate().back(); // вернуться на страницу назад
    }

    @Test
    public void testDropdawnAndWaitUntil() { // + Явное ожидание (для определенного поля)
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(URL);
        driver.findElement(By.className("dropbtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Facebook")));
        //Явное ожидание для конкретной кнопки, дропдаун выпадает 3 секунды, фэйсбук увидем через 3 сек и выполним
        // действие (выполняется после появления в зоне видимости, но не более чем через 10 сек)
        driver.findElement(By.linkText("Facebook")).click(); //
        driver.navigate().back();
        driver.findElement(By.className("dropbtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Flipkart")));
        driver.findElement(By.linkText("Flipkart")).click(); //
    }

    @Test
    public void testElementToBeClickable() { //Чек бокс доступен после нажатия на кнопку
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebDriverWait wait = new WebDriverWait(driver, 12);

        driver.findElement(By.xpath("//button[text()= 'Check this']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dte"))).click(); //Что бы элемент был кликабельным
    }

    @Test
    public void testInvisibilityOfElementLocated() { //Ожидание выполнения условия (кнопка нажимается по истечению времени)
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deletesuccess")));
        //Невидимость элемента раcположеного по локатору (Ждем пока элемент исчезнет)
        driver.findElement(By.id("alert2")).click();
    }

    @Test
    public void testAlertAcceptOrDismiss() { //Переход на оповещение
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        driver.findElement(By.id("alert1")).click();
        Alert alert = driver.switchTo().alert(); // Создаем объект класса
        String textOfAlert = alert.getText();
        System.out.println(textOfAlert);
        alert.accept(); //Принять предупреждение
        // alert.dismiss(); //Закроет вместо принятья
    }

    @Test
    public void testAlertIsPresent() { //Ожидание предупреждения
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        driver.findElement(By.id("alert1")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent()); //Команда ждет появления предупреждения (не более 10 сек.
        Alert alert = driver.switchTo().alert(); //Переключиться на окно (предупреждение)
        alert.accept();
    }

    @Test
    public void testSendKeysOfAlert() { //ввести текст в окне предупреждения
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Aurm"); //Ввод текста, проблемы с водом в браузере хром.
    }

    ///////////// ACTIONS ///////////////

    @Test
    public void testSendKeysOfWebElementAndSendKeysOfActions() { //ввести текст в окне предупреждения
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement userNameField = driver.findElement(By.name("userid"));
        userNameField.sendKeys("Arum"); // Для ввода текса в текстовые поля
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).build().perform(); // Для имитации клавиш клавиатуры
        WebElement passwordField = driver.findElement(By.name("pswrd"));
        passwordField.sendKeys("password"); // Для ввода текса в текстовые поля
        actions.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform(); // Для имитации клавиш клавиатуры
    }

    @Test
    public void testDragAndDropBy() { //передвинуть ползунок
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://omayo.blogspot.com/p/page3.html");

        WebElement minPriceOption = driver.findElement(By.xpath("//a[@aria-labelledby='price-min-label']"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(minPriceOption, 100, 0)// выбираем элемент который хотим передвинуть по горизонтали, на 100 пунктов, не привязано к шкале, y - вертикаль.
                .build() // Используется если действий  > 1 ->(sendKeys(Keys.Tab).sendKeys(Keys.ENTER)) IF <= 1 тогда build() не требуется, используем сразу .perform()
                .perform(); // Команда выполнить,
    }

    @Test
    public void testDragAndDrop() { //перетягивание элемента из точки А в точку Б
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

        WebElement oslo = driver.findElement(By.id("box1"));
        WebElement norway = driver.findElement(By.id("box101"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(oslo, norway).build().perform();
    }

    @Test
    public void testDragAndDropClickHold() throws InterruptedException { //перетягивание элемента с помощью мыши
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "D:\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();


//        Actions actions = new Actions(driver);
//        actions.contextClick() // клик ПКМ
//                .keyDown() // нажатие кнопки
//                .keyUp() //отжать кнопку
//                .pause(1000) // пауза для задержки

        try {
            driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
            Thread.sleep(2000); // Задержка для прогрузки сайта 2сек

            WebElement element = driver.findElement(By.id("draggable"));
            WebElement element2 = driver.findElement(By.id("droppable"));

            Actions actions = new Actions(driver);

            actions
                    .moveToElement(element) //навести курсор на элемент
                    .clickAndHold() // нажать и держать
                    .moveToElement(element2) //навести курсор на элемент
                    .release() // отпустить зажатую кнопку
                    .build() // собрать в едино действия выше
                    .perform(); // применить сборку

            // или можно сделать одним методом
            //.dragAndDropBy(element, element2);
            //.dragAndDropBy(element, 100, 100); // выбираем элемент который хотим переместить, а потом координаты по х и у

        } catch (InterruptedException e) { //отловить исключение
            e.printStackTrace(); // вывести исключение на экран

        } finally {
            Thread.sleep(1000);
            driver.quit();
        }
    }

    @Test
    public void testPagination() { // пагинация

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "D:\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://pagination.js.org/");
            Thread.sleep(1000);
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li")); //ПОлучить коллекцию строк
            List<WebElement> pages = driver.findElements(By.xpath("//div[@class='paginationjs-pages']/ul/li")); //ПОлучить коллекцию страницу
            String text = elements.get(5).getText();
            System.out.println(text);

            pages.get(2).click(); //выбрать вторую страницу
//            wait.until(ExpectedConditions.stalenessOf(elements.get(5))); //ждем когда пропадет 5 по индексу-6 элемент с базовой страницы

            elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li")); //ПОлучить коллекцию строк с новой страницы

            text = elements.get(5).getText();
            System.out.println(text);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testContextClickAndDoubleClick() { //нажать правую кнопку мыши, сделать двойное нажатие мыши
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement searchBoxField = driver.findElement(By.name("q"));
        Actions actions = new Actions(driver);
        actions.contextClick(); //Если просто нажать ПКМ где находится курсор
        actions.contextClick(searchBoxField).build().perform(); // Нажать ПКМ по элементу

        WebElement doubleClick = driver.findElement(By.id("testdoubleclick"));
        actions.doubleClick(doubleClick); // двойное нажатие по вэб элементу
    }

    @Test
    public void testKeyDownAndClick() { //Зажать кнопку на клавиатуры и кликнуть мышью
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        WebElement compendiumDevLink = driver.findElement(By.linkText("compendiumdev"));
        Actions actions = new Actions(driver);
        actions.moveToElement(compendiumDevLink).keyDown(Keys.CONTROL).click(); //Передвигается к элементу, зажимаем клавишу CONTROL и делаем click
    }

    @Test
    public void testPressSeveralKeys() throws InterruptedException { //Одновременное нажатие нескольких клавиш
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        driver.findElement(By.name("userid")).sendKeys("word");
        Thread.sleep(3000);
        driver.findElement(By.name("userid")).sendKeys(Keys.chord(Keys.CONTROL, "z"));
        // Вызываем метод ввода текста, chord - возможность зажать две клавиши
    }

    @Test
    public void testJavascriptExecutor() { //Запуск JavaStript отобразить предупреждение
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        JavascriptExecutor jse = (JavascriptExecutor) driver;// Интерфейск исполнитель яваскрипта
        jse.executeScript("alert('Arum Motoori');"); //Отобразить предупреждение
    }

    @Test
    public void testExecuteAsyncScript() { //Команда синхранизации (Выполнить синхронный код яваскрипт)
        //Нормальный тип ЯваСкрипт - выполняется поочередно
        //Синхронный тип яваСкрипт - тип основан на времени, первым выполняется тот который можно выполнить быстрее

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\Users\\xBrooKx\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(URL);

        JavascriptExecutor jse = (JavascriptExecutor) driver;// Интерфейск исполнитель яваскрипта
        jse.executeAsyncScript("window.setTimeout(function(){alert('world');},4000);alert('Hello');"); //Сначала выполнится Хэлло, потом ворлд
    }

}