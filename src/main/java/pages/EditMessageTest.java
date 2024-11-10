package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditMessageTest {

	WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testEditLockedMessage() {
        driver.get("https://app.thepric.com/");

        // Locate and edit the locked message
        driver.findElement(By.xpath("//div[contains(text(), 'This is a locked message')]/following-sibling::button[text()='Edit']")).click();
        WebElement messageTextBox = driver.findElement(By.name("message"));
        messageTextBox.clear();
        messageTextBox.sendKeys("Updated locked message content.");
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Verify the updated message
        String updatedMessage = driver.findElement(By.xpath("//div[contains(text(), 'Updated locked message content.')]")).getText();
        Assert.assertEquals(updatedMessage, "Updated locked message content.");
    }

    @Test
    public void tearDown() {
        driver.quit();
    }
}
