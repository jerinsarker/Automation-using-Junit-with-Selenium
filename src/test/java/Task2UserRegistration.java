import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task2UserRegistration {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed");
        driver = new ChromeDriver(ops);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    @DisplayName("Check if User Registration  Demo site is showing properly")
    public void getTittle() {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        String titleActual = driver.getTitle();
        String titleExpected = "User Registration";
        System.out.println(titleActual);
        Assertions.assertTrue(titleActual.contains(titleExpected));

    }
    @Test
    public void GuestRegistrationForm() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        //Generate short email using number by 1000 and 9999
        int randomNum=(int) (Math.random()+9000)+1000;
        String randomEmail="jerin" + randomNum +"@example.com";
        List<WebElement> textBoxElm = driver.findElements(By.className("input-text"));
        textBoxElm.get(0).sendKeys("Jerin");
        textBoxElm.get(1).sendKeys("jerin3488@gmail.com");
        textBoxElm.get(2).sendKeys("JERINuits36&&");
        textBoxElm.get(3).sendKeys("Sarker");
        textBoxElm.get(5).sendKeys("Bangladeshi");

        WebElement gender = driver.findElement(By.id("radio_1665627729_Female"));
        gender.click();

        Select country = new Select(driver.findElement(By.id("country_1665629257")));
        country.selectByValue("BD");

        List<WebElement> dateOfBirthElement = driver.findElements(By.cssSelector("[type=text]"));
        WebElement firstDob = dateOfBirthElement.get(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '1999-02-22')", firstDob);
        WebElement phoneNumber = driver.findElement(By.id("phone_1665627880"));
        phoneNumber.sendKeys("01712517978");

        Utils.scroll(driver, 500);

        WebElement termsAndConditions = driver.findElement(By.id("privacy_policy_1665633140"));
        termsAndConditions.click();
        Thread.sleep(500);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("ur-submit-button")));
        Actions actions = new Actions(driver);
        actions.moveToElement(submitButton).click().perform();

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ur-submit-message-node")));
        String actualMessage = confirmationMessage.getText();
        String expectedMessage = "User successfully registered.";
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }



    @AfterAll
    public void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

}




