package codingExercise;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FetchCodingExercise2 {
    public static void main(String[] args) throws InterruptedException {
        // Initialize the WebDriver and WebDriverWait
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Navigate to the target URL
        driver.get("http://sdetchallenge.fetch.com/");

        // Define initial weighing plates values
        int[] initLeftPlate = { 0, 1, 2 };
        int[] initRightPlate = { 3, 4, 5 };

        // Initialize the list to store result symbols
        List<String> measurementResults = new ArrayList<>();
        
        // Perform the initial weighing
        initialWeighing(driver, initLeftPlate, initRightPlate);
        
        // Wait until the results are displayed
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("li")));
        
        // Capture the initial weighing result
        String initialResult = resultSymbol(driver);
        
        // Add initial result to the list
        measurementResults.add(initialResult);
        
        // Determine the index of the fake gold bar based on initial result
        int fakeCoinIndex = findingFakeGoldBar(driver, initialResult, measurementResults);
        
        // Print the list of measurement results and alert
        System.out.println("Total Weighings: " + measurementResults.size());
        System.out.println("Weighings:");
        getWeighingResults(driver);
        
        // Click on the identified fake coin
        clickOnCoin(driver, fakeCoinIndex);
        
        // Wait for the alert to be present and display the alert message
        wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert Message: " + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        
        // Close the driver
        driver.quit();
    }

    // Method to determine the fake gold bar based on initial weighing result
    public static int findingFakeGoldBar(WebDriver driver, String initialResult, List<String> measurementResults) throws InterruptedException {
        int fakeCoinIndex = -1;

        if (initialResult.equals("<")) {
            // Left side is heavier
            clickResetButton(driver);
            driver.findElement(By.id("left_0")).sendKeys("0");
            driver.findElement(By.id("right_0")).sendKeys("1");
            clickWeighButton(driver);
            Thread.sleep(2000); // Waiting for the weighing result
            String result = resultSymbol(driver);
            measurementResults.add(result); // Add result to the list
            if (result.equals("<")) {
                fakeCoinIndex = 0;
            } else if (result.equals(">")) {
                fakeCoinIndex = 1;
            } else {
                fakeCoinIndex = 2;
            }
        } else if (initialResult.equals("=")) {
            // Both sides are equal
            clickResetButton(driver);
            driver.findElement(By.id("left_0")).sendKeys("6");
            driver.findElement(By.id("right_0")).sendKeys("7");
            clickWeighButton(driver);
            Thread.sleep(2000); // Waiting for the weighing result
            String result = resultSymbol(driver);
            measurementResults.add(result); // Add result to the list
            if (result.equals("<")) {
                fakeCoinIndex = 6;
            } else if (result.equals(">")) {
                fakeCoinIndex = 7;
            } else {
                fakeCoinIndex = 8;
            }
        } else if (initialResult.equals(">")) {
            // Right side is heavier
            clickResetButton(driver);
            driver.findElement(By.id("left_0")).sendKeys("3");
            driver.findElement(By.id("right_0")).sendKeys("4");
            clickWeighButton(driver);
            Thread.sleep(2000); // Waiting for the weighing result
            String result = resultSymbol(driver);
            measurementResults.add(result); // Add result to the list
            if (result.equals("<")) {
                fakeCoinIndex = 3;
            } else if (result.equals(">")) {
                fakeCoinIndex = 4;
            } else {
                fakeCoinIndex = 5;
            }
        }

        return fakeCoinIndex;
    }

    // Method to perform the initial weighing
    public static void initialWeighing(WebDriver driver, int[] leftPlateValues, int[] rightPlateValues) {
        for (int i = 0; i < leftPlateValues.length; i++) {
            driver.findElement(By.id("left_" + i)).sendKeys(String.valueOf(leftPlateValues[i]));
            driver.findElement(By.id("right_" + i)).sendKeys(String.valueOf(rightPlateValues[i]));
        }
        clickWeighButton(driver);
    }

    // Method to click the weigh button
    public static void clickWeighButton(WebDriver driver) {
        driver.findElement(By.id("weigh")).click();
    }

    // Method to get the result symbol after weighing
    public static String resultSymbol(WebDriver driver) {
        return driver.findElement(By.xpath("//div[@class='result']/button[@id='reset']")).getText();
    }

    // Method to click the reset button
    public static void clickResetButton(WebDriver driver) {
        driver.findElement(By.xpath("//button[@id='reset' and text()='Reset']")).click();
    }

    // Method to click on the identified fake coin
    public static void clickOnCoin(WebDriver driver, int coinNumber) {
        driver.findElement(By.id("coin_" + coinNumber)).click();
    }
    
    // Method to print all weighing results
    public static void getWeighingResults(WebDriver driver) {
        List<WebElement> weighingElements = driver.findElements(By.xpath("//ol/li"));
        List<String> weighingResults = new ArrayList<>();
        for (WebElement element : weighingElements) {
            weighingResults.add(element.getText());
            System.out.println(element.getText());
        }
    }
}
