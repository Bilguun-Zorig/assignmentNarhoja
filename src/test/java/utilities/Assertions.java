package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Assertions {

	public void asserts(List<WebElement> elements, String actual) {
		
		for(WebElement expected : elements) {
			if(expected.getText().contentEquals(actual)) {
				String expectedValue = expected.getText();
				System.out.println(expectedValue);
				assertEquals(expectedValue, actual);
				break;
			}
		}
	}
	
	public void asserts(WebElement element, String actual) {
		assertEquals(element.getText(), actual + " PAR");
	}
	
	public void asserts(ArrayList<Integer> randomValues) {
		  // Loop through the ArrayList and assert each value
	    for (int i = 0; i < randomValues.size(); i++) {
	        int value = randomValues.get(i);
	        assertTrue(value >= 0 && value <= 9999);
	    }
	}
	
	public static void assertElementIsDisplayed(WebDriver driver, By by) {
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    WebElement element = null;
	    int retries = 0;
	    while (retries < 3) {
	        try {
	            element = driver.findElement(by);
	            assertTrue(element.isDisplayed());
	            break;
	        } catch (StaleElementReferenceException e) {
	            driver.navigate().refresh();
	            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	            retries++;
	        }
	    }
	}
}
