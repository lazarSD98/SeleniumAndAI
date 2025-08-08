package Base;

import Pages.LoginPage;
import Pages.inventoryPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public LoginPage loginPage;
    public inventoryPage inventoryPage;
    ChromeOptions options = new ChromeOptions();



    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.setExperimentalOption("prefs", Map.of(
                "profile.password_manager_leak_detection", false
        ));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = ((JavascriptExecutor) driver);
        loginPage = new LoginPage(driver);
        inventoryPage=new inventoryPage(driver);
    }
}
