package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;


public class Methods extends Assertions {
	public static Faker fake = new Faker();
	
	final static String username = "narhoja.omarhoja@gmail.com";
	final static String password = "6n6XoCE9jb50XSz#";
	
	public void logIn(WebElement element01, WebElement element02) {
		element01.sendKeys(username);
		element02.sendKeys(password);
	}
	public void click(WebElement element) {
		element.click();
	}
	
	public void sendKeysByConfig(WebElement element, String keyValue) {
		element.sendKeys(keyValue);
		
	}
	public void selection(List<WebElement> elements, String value) {
		
		for(WebElement element: elements) {
			if(element.getText().contains(value)) {
				System.out.println(element.getText());
				threadSleep(2);
				element.click();
				break;
			}
		}
	}
	
	public static void threadSleep(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Integer> sendRandomValuesToTableRows(WebDriver driver, By row, int column) {
	    Random rand = new Random();
	    Actions actions = new Actions(driver);
	    ArrayList<Integer> randomValues = new ArrayList<Integer>();
	    int rowCount = driver.findElements(By.xpath("//*[@class='review-table table-edit is-turned-off']/tbody/tr"))
	                          .size();
	    System.out.println("Total rows: " + rowCount);
	    for (int i = 1; i <= rowCount; i++) {
	        WebElement rowElement = driver.findElement(By.xpath("//*[@class='review-table table-edit is-turned-off']/tbody/tr[" + i + "]/td[" + column + "]"));
	        actions.moveToElement(rowElement).click().sendKeys(Keys.BACK_SPACE).build().perform();
	        threadSleep(1);
	        int randomValue = rand.nextInt(10000);
	        actions.moveToElement(rowElement).click().sendKeys(Integer.toString(randomValue)).build().perform();
	        randomValues.add(randomValue);
	        threadSleep(1);
	    }
	    return randomValues;
	}
	
	public static void clickOnElement(WebDriver driver, By by) {
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    int retries = 0;
	    while (retries < 3) {
	        try {
	            WebElement element = driver.findElement(by);
	            element.click();
	            break;
	        } catch (StaleElementReferenceException e) {
	            driver.navigate().refresh();
	            retries++;
	            wait.until(ExpectedConditions.presenceOfElementLocated(by));
	        }
	    }
	}
}
