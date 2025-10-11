package com.example.demo.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskUITest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        // This sets up the Chrome browser driver automatically
        WebDriverManager.chromedriver().setup();
        // This makes sure the test runs in "headless" mode for CI/CD pipelines
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // Set up a wait timer for finding elements on the page
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        // This closes the browser after all tests are done
        if (driver != null) {
            driver.quit();
        }
    }

    // UI Scenario 1: Verify the page loads correctly
    @Test
    public void testPageTitleIsCorrect() {
        // Navigate to the React frontend application
        driver.get("http://localhost:3000"); // Use port 3001 if that's what you are using
        // Find the main heading by its tag name (h1)
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        // Assert that the heading text is "Task Manager"
        assertEquals("Task Manager", heading.getText());
    }

    // UI Scenario 2: Add a new task and verify it appears
    @Test
    public void testAddTaskAndVerifyInList() {
        // Navigate to the React app
        driver.get("http://localhost:3000"); // Use port 3001 if that's what you are using

        // Find the input field by its placeholder text
        WebElement inputField = driver.findElement(By.xpath("//input[@placeholder='Enter a new task']"));
        // Find the submit button
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Task']"));

        // Type the new task name into the input field
        String taskName = "My New Selenium Task";
        inputField.sendKeys(taskName);

        // Click the "Add Task" button
        addButton.click();

        // Wait for the new task to appear in the list and verify its text
        WebElement taskInList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='" + taskName + "']")));
        assertTrue(taskInList.isDisplayed());
    }
}