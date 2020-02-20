package com.ispw.fixmycity.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAcceptOrRefuseJobSelApi {
	@Test
	public void testCitizenResgistration() {

		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/fixmycity/logout.jsp");

		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/div[2]/div/button")).click();

		driver.findElement(By.id("inputFirstNameCitId")).click();
		driver.findElement(By.id("inputFirstNameCitId")).sendKeys("name");
		driver.findElement(By.id("inputLastNameCitId")).click();
		driver.findElement(By.id("inputLastNameCitId")).sendKeys("surname");
		driver.findElement(By.id("inputUsernameCitId")).click();
		driver.findElement(By.id("inputUsernameCitId")).sendKeys("username124");
		driver.findElement(By.id("inputPasswordCitId")).click();
		driver.findElement(By.id("inputPasswordCitId")).sendKeys("password");
		driver.findElement(By.xpath("//*[@id=\"signupCitizenForm\"]/div[8]/div[2]/button")).click();
		String val = driver.findElement(By.linkText("Logout")).getText();
		assertEquals("Logout", val);

	}

	@Test
	public void testCompanyUserRegistration() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/fixmycity/logout.jsp");

		driver.get("http://localhost:8080/fixmycity/login.jsp");
		driver.findElement(By.cssSelector(".btn-secondary")).click();
		driver.findElement(By.id("nav-profile-tab")).click();
		driver.findElement(By.id("inputCompanyNameCompId")).click();
		driver.findElement(By.id("inputCompanyNameCompId")).sendKeys("company675");
		driver.findElement(By.id("signupCompanyForm")).click();
		driver.findElement(By.id("inputUsernameCompId")).sendKeys("company676");
		driver.findElement(By.id("login-bg")).click();
		driver.findElement(By.id("inputCategoryCompId")).click();
		{
			WebElement dropdown = driver.findElement(By.id("inputCategoryCompId"));
			dropdown.findElement(By.xpath("//option[. = 'Damaged street']")).click();
		}
		driver.findElement(By.id("inputCategoryCompId")).click();
		driver.findElement(By.cssSelector("#signupCompanyForm .btn-primary")).click();
		String val = driver.findElement(By.cssSelector(".navbar-right .navbar-brand")).getText();
		assertEquals("company676", val);
	}
}
