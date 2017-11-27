package view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CookieTest {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\utlisateur\\Desktop\\files school\\web files\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/labo/servlet?action=");
	}
	
	private String giveColorFooter(){
		WebElement body = driver.findElement(By.tagName("footer"));
		if (body.getCssValue("background-color").toString().equals("rgba(185, 151, 3, 1)")) {
			return "yellow";
		}
		else {
			return "red";
		}
	}
	
	private String giveOppositeColor(String color) {
		if (color.equals("red")) {
			return "yellow";
		}
		else {
			return "red";
		}
	}

	//@After
	public void clean(){
		driver.quit();
	}
	
	private void checkColorFooter(String title){
		String color = giveColorFooter();
		WebElement button = driver.findElement(By.cssSelector("footer form input[type='submit']"));
		button.click();
		assertEquals(driver.getTitle(), title);
		assertEquals(giveColorFooter(), giveOppositeColor(color));

	}
	
	@Test
	public void testHomepageSwitchColorOnceCorrect() {
		checkColorFooter("Home");		
	}
	
	@Test 
	public void testOverviewUsersSwithcColorTwiceCorrect() {
		driver.get("http://localhost:8080/labo/servlet?action=naarPersonOverview");
		// first change
		checkColorFooter("Overview");
		//second change
		checkColorFooter("Overview");
		

	}
	
	@Test
	public void testSwitchColorAfterUpdateProductCorrect() {
		WebElement link, button;
		driver.get("http://localhost:8080/labo/servlet?action=naarProductOverview");
		link = driver.findElement(By.cssSelector("a[href^='servlet?action=naarUpdate']"));
		link.click();
		assertEquals("Update Product", driver.getTitle());
		button = driver.findElement(By.cssSelector("input[type='submit']"));
		button.click();
		assertEquals("Overview Products", driver.getTitle());
		
		checkColorFooter("Overview Products");
			}

}
