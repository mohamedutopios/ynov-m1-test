package org.example.crudthymeleafselenium;

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

import static org.assertj.core.api.Assertions.assertThat;
public class ProductSeleniumTest {


    private static WebDriver driver;
    private static String host;

    @BeforeAll
    public static void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origin=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\Moham\\Downloads\\chromedriver-win64\\chromedriver.exe"); pour windows
        driver = new ChromeDriver(options);

        host = "http://localhost:8080";
    }

    @AfterAll
    public static void tearDown() {
        if(driver != null) {
            driver.quit();
        }

    }

    @Test
    public void testCreateProduct(){
        driver.get(host + "/products/create");

        WebElement nameFied = driver.findElement(By.name("name"));
        WebElement priceFied = driver.findElement(By.name("price"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameFied.sendKeys("Test Product");
        priceFied.sendKeys("100");
        submitButton.click();

        assertThat(driver.getCurrentUrl()).isEqualTo(host + "/products");

        driver.get(host + "/products");

        WebElement productName = driver.findElement(By.xpath("//td[contains(text(),'Test Product')]"));
        assertThat(productName).isNotNull();
        WebElement productPrice = driver.findElement(By.xpath("//td[contains(text(),'100')]"));
        assertThat(productPrice).isNotNull();

    }

    @Test
    public void testEditProduct(){
        driver.get(host + "/products");
        WebElement editLink = driver.findElement(By.xpath("//a[contains(@href,'/products/edit')]"));
        editLink.click();

        // assertThat(driver.getCurrentUrl()).isEqualTo(host + "/products/edit");

       // driver.get(host + "/products/edit");
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("Updated Product");
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(host + "/products"));

        driver.get(host + "/products");
        WebElement productName = driver.findElement(By.xpath("//td[contains(text(),'Updated Product')]"));
        assertThat(productName).isNotNull();
    }

    @Test
    public void testDeleteProduct() {

        driver.get(host + "/products");
        WebElement deleteLink = driver.findElement(By.xpath("//a[contains(@href,'/products/delete')]"));
        deleteLink.click();

        assertThat(driver.getCurrentUrl()).isEqualTo(host + "/products");

        driver.get(host + "/products");
        boolean isProductDeleted = driver.findElements(By.xpath("//td[contains(text(),'Updated Product')]")).isEmpty();
        assertThat(isProductDeleted).isTrue();
    }



}
