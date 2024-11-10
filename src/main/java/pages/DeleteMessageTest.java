package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteMessageTest {

	WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testDeleteLockedMessage() {
        driver.get("https://app.thepric.com/");

        // Locate and delete the locked message
        driver.findElement(By.xpath("//div[contains(text(), 'Updated locked message content.')]/following-sibling::button[text()='Delete']")).click();
        driver.findElement(By.xpath("//button[text()='Confirm']")).click();

        // Verify the message was deleted
        boolean messageExists = driver.findElements(By.xpath("//div[contains(text(), 'Updated locked message content.')]")).size() > 0;
        Assert.assertFalse(messageExists, "Message should be deleted.");
    }

    @Test
    public void tearDown() {
        driver.quit();
    }
}
