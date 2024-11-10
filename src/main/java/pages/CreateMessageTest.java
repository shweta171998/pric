package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateMessageTest {

	WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup Chrome WebDriver (You can configure this to use other browsers as well)
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testCreateLockedMessage() {
        // Navigate to sign-in page and log in
        driver.get("https://app.thepric.com/");
        driver.findElement(By.name("mobile")).sendKeys("+91 1234567890");
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();

        // Navigate to "Create New Message"
        driver.findElement(By.xpath("//button[text()='Create New Message']")).click();

        // Enter message and lock it
        WebElement messageTextBox = driver.findElement(By.name("message"));
        messageTextBox.sendKeys("This is a locked message.");
        WebElement lockCheckbox = driver.findElement(By.name("lockMessage"));
        lockCheckbox.click();
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Verify success message and locked status
        String successMessage = driver.findElement(By.xpath("//div[@class='alert-success']")).getText();
        Assert.assertEquals(successMessage, "Message has been successfully created");

        WebElement lockedMessage = driver.findElement(By.xpath("//div[contains(text(), 'This is a locked message')]"));
        Assert.assertTrue(lockedMessage.getText().contains("locked"));
    }

    @Test
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
