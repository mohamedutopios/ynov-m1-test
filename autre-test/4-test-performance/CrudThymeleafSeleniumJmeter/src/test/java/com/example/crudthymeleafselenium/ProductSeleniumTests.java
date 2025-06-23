package com.example.crudthymeleafselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSeleniumTests {

    private static WebDriver driver;
    private static String host;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Moham\\Downloads\\chromedriver-win64\\chromedriver.exe"); // Assurez-vous d'indiquer le bon chemin vers chromedriver
        driver = new ChromeDriver(options);
        host = "http://localhost:8084";
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateProduct() {
        driver.get(host + "/products/create");
        WebElement nameField = driver.findElement(By.name("name"));
        WebElement priceField = driver.findElement(By.name("price"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameField.sendKeys("Test Product");
        priceField.sendKeys("10.0");
        submitButton.click();

        // Vérification de la redirection après la soumission du formulaire
        assertThat(driver.getCurrentUrl()).isEqualTo(host + "/products");

        // Vérification que le produit a été créé
        driver.get(host + "/products");
        WebElement productName = driver.findElement(By.xpath("//td[contains(text(),'Test Product')]"));
        assertThat(productName).isNotNull();

        // Vérification du prix du produit créé
        WebElement productPrice = driver.findElement(By.xpath("//td[contains(text(),'10.0')]"));
        assertThat(productPrice).isNotNull();
    }

    @Test
    public void testEditProduct() {
        driver.get(host + "/products");
        WebElement editLink = driver.findElement(By.xpath("//a[contains(@href,'/products/edit')]"));
        editLink.click();

        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("Updated Product");
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        // Vérification de la redirection après la soumission du formulaire
        assertThat(driver.getCurrentUrl()).isEqualTo(host + "/products");

        // Vérification que le produit a été modifié
        driver.get(host + "/products");
        WebElement productName = driver.findElement(By.xpath("//td[contains(text(),'Updated Product')]"));
        assertThat(productName).isNotNull();
    }

    @Test
    public void testDeleteProduct() {
        driver.get(host + "/products");
        WebElement deleteLink = driver.findElement(By.xpath("//a[contains(@href,'/products/delete')]"));
        deleteLink.click();

        // Vérification de la redirection après la suppression
        assertThat(driver.getCurrentUrl()).isEqualTo(host + "/products");

        // Vérification que le produit a été supprimé
        driver.get(host + "/products");
        boolean isProductDeleted = driver.findElements(By.xpath("//td[contains(text(),'Updated Product')]")).isEmpty();
        assertThat(isProductDeleted).isTrue();
    }


    // fonctionnalite existe pas
    /*@Test
    public void testProductDetails() {
        driver.get(host + "/products");
        WebElement productLink = driver.findElement(By.xpath("//a[contains(@href,'/products/details')]"));
        productLink.click();

        // Vérification de la page de détails du produit
        WebElement detailsHeading = driver.findElement(By.xpath("//h1[contains(text(),'Product Details')]"));
        assertThat(detailsHeading).isNotNull();

        // Vérification des informations sur le produit
        WebElement productName = driver.findElement(By.xpath("//td[contains(text(),'Updated Product')]"));
        assertThat(productName).isNotNull();
        WebElement productPrice = driver.findElement(By.xpath("//td[contains(text(),'10.0')]"));
        assertThat(productPrice).isNotNull();
    }*/
}
