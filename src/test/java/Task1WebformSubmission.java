import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Task1WebformSubmission {

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
    @DisplayName("Check if Submissionform is showing properly")
    public void getTittle() {
        driver.get("https://www.digitalunite.com/practice-webform-learners/");
        String titleActual = driver.getTitle();
        String titleExpected = "Digital Unite";
        System.out.println(titleActual);
        Assertions.assertTrue(titleActual.contains(titleExpected));

    }
    @Test
    public void SubmissionForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners/");
        WebElement btnElement=driver.findElement(By.id("onetrust-accept-btn-handler"));
        Actions actions = new Actions(driver);
        actions.click(btnElement).perform();

        Thread.sleep(1000);
        Utils.scroll(driver, 500);

        List<WebElement> textBox = driver.findElements(By.className("form-control"));
        textBox.get(0).sendKeys("Jerin Sarker");
        textBox.get(1).sendKeys("01752206819");
        textBox.get(2).sendKeys("12/29/2024");
        textBox.get(3).sendKeys("test@gmail.com");
        textBox.get(4).sendKeys("I learn to this website");

        WebElement uploadImage = driver.findElement(By.id("edit-uploadocument-upload"));
        uploadImage.sendKeys(System.getProperty("user.dir") + "/src/test/resources/task1.jpg");
        Thread.sleep(3000);


        WebElement clickCheckBox=driver.findElement(By.id("edit-age"));
        //Actions actions = new Actions(driver);
        actions.click(clickCheckBox).perform();
        Thread.sleep(1000);

        WebElement SubmitButton=driver.findElement(By.id("edit-submit"));
        actions.click(SubmitButton).perform();




        WebElement confirmMessage = driver.findElement(By.tagName("h1"));
        String actualMessage = confirmMessage.getText();
        String expectedMessage = "Thank you for your submission!";
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }

    @AfterAll
    public void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

}
