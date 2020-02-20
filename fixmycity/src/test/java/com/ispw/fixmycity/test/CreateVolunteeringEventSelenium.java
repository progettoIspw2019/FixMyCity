package com.ispw.fixmycity.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateVolunteeringEventSelenium {
	
	String username = "admin";
	String password = "admin";

	@Test
	public void testCreateEvent() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe"); 
		
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().setSize(new Dimension(1800, 800));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.get("http://localhost:8080/fixmycity/logout.jsp");

		driver.get("http://localhost:8080/fixmycity/login.jsp");
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[1]/div/div/input")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[2]/div/div/input")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[3]/div/button")).click();
		
		driver.findElement(By.xpath("//*[@id=\"createEventModalBtnId\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[1]/div[2]/div/div/input")).click();
		driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[1]/div[2]/div/div/input")).sendKeys("Per una Roma migliore");

		driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[1]/div[2]/div/div/input")).click();
		driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[1]/div[3]/div/div/textarea")).sendKeys("Puliamo la strada");

		WebElement date = driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[1]/div[4]/div/div/input"));
		Actions builder = new Actions(driver);   
		builder.moveToElement(date, 0, 0).click().build().perform();
		date.sendKeys("10042020");
		
		WebElement time = driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[1]/div[5]/div/div/input"));
		builder.moveToElement(time, 0, 0).click().build().perform();
		time.sendKeys("100");
		
		driver.findElement(By.xpath("//*[@id=\"newEvent\"]/div/div/form/div[2]/div/div/button")).click();
		
		driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/div[2]/a[2]")).click();
		
		WebElement event = driver.findElement(By.linkText("Per una Roma migliore"));
		
		assertEquals("Per una Roma migliore", event.getText());
	}
}
