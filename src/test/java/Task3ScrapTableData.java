import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Task3ScrapTableData {
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
    @DisplayName("Check if all values are stored in a text file")
    public void getTittle() throws IOException {
        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");

        List<WebElement> table = driver.findElements(By.className("table"));
        List<WebElement> allRows = table.get(1).findElements(By.tagName("tr"));

        FileWriter writer = new FileWriter("./src/test/resources/store.txt", true);

        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                String data = cell.getText();
                System.out.print(data);
                writer.write(" " + data + " ");
            }
            System.out.println();
            writer.write("\n");
        }
        writer.close();

    }

    @AfterAll
    public void closeDriver() {
        driver.quit();
    }

}

