import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//import org.apache.log4j.Logger;


public class Main {
    private static final Duration EXPIRE_TIME = Duration.ofSeconds(5);
    //    private static final Logger log = Logger.getLogger(main.class);
    static WebDriver driver = driverInit();
    public static void main(String[] args) throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.cibus-sodexo.co.il/");
        login();
        chooseStore();
    }


    private static WebDriver driverInit() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\U6071478\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");//        ChromeOptions handlingSSL = new ChromeOptions();
        ChromeOptions handlingSSL = new ChromeOptions();
        handlingSSL.setAcceptInsecureCerts(true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return new ChromeDriver();
    }

    private static void login() throws InterruptedException {
        By form = By.xpath("/html/body/div[1]/section/div[3]/iframe");
        waitAndSwitchFrame(form);
        WebElement user = waitAndGet(By.xpath("//input[@id = 'txtUsr']"));
        user.sendKeys("אלחי");
        WebElement pass = waitAndGet(By.xpath("//*[@id=\"txtPas\"]"));
        pass.sendKeys("Tzuri9919");
        WebElement comp = waitAndGet(By.xpath("//*[@id=\"txtCmp\"]"));
        comp.sendKeys("אקספריס איי טי אס" + Keys.ENTER);
    }

    private static void chooseStore() {
        driver.switchTo().defaultContent();
        driver.switchTo().parentFrame();
        System.out.println(("++++"+waitAndGet(By.xpath("/html/body/div[1]/div[2]/form/div[3]/div/div[1]/div[5]/a[2]"))));
    }


    public static WebElement waitAndGet(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, EXPIRE_TIME);
        return wait.until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public static void waitAndClick(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, EXPIRE_TIME);
        wait.until(ExpectedConditions.presenceOfElementLocated(selector)).click();
        System.out.println("clicked on: " + selector);
    }


    public static void waitAndSwitchFrame(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, EXPIRE_TIME);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(selector));
        System.out.println("frame switched to: " + selector);
    }


}
