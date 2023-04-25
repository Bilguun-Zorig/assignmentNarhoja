package test01;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import utilities.Methods;

public class Test extends Methods {

	static WebDriver driver;
	static WebDriverWait wait;

	@BeforeEach
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://venus.accurri.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterEach
	public void tearDown() {
	 driver.quit();
	}

	@org.junit.jupiter.api.Test
	public void test01() {
		String entityName = fake.name().username();
		String reportVersion = fake.name().username();
		System.out.println(reportVersion);
		String text = fake.lorem().sentence();
		WebElement username = driver.findElement(By.xpath("//*[@id='Login']"));
		WebElement password = driver.findElement(By.xpath("//*[@id='Password']"));
		WebElement loginButton = driver.findElement(By.xpath("//*[@type='submit']"));

		logIn(username, password);
		click(loginButton);
		click(driver.findElement(By.xpath("//*[@id='createButton']")));
		wait = new WebDriverWait(driver, 10);
		WebElement input01 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='name']")));
		sendKeysByConfig(input01, entityName);
		WebElement okButton01 = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='green-text']//following::button[1]")));
		click(okButton01);
		threadSleep(3);
		WebElement expected = driver.findElement(By.xpath("//*[@id=\"parentsDivisions\"]/tbody/tr[1]"));
		asserts(expected, entityName);
		threadSleep(2);
		WebElement reports = driver.findElement(By.xpath("//*[@id=\"mainmenu-bar\"]//a[2]/span"));
		click(reports);
		WebElement listButton = driver.findElement(By.xpath("//*[@id=\"submenu-bar\"]//a"));
		click(listButton);
		WebElement newReport = driver.findElement(By.xpath("//*[@id=\"createButton\"]"));
		WebElement newReportInout = driver.findElement(By.xpath("//*[@id=\"version\"]"));
		click(newReport);
		sendKeysByConfig(newReportInout, reportVersion);
		WebElement okButton02 = driver.findElement(By.xpath("//*[@id=\"dlgVersion\"]/form/div/div[3]/div/button[1]"));
		click(okButton02);
		threadSleep(15);
		WebElement section = driver.findElement(By.xpath("//*[@id=\"mainmenu-bar\"]/div/div[1]/a[4]"));
		click(section);
		WebElement notes = driver.findElement(By.xpath("//*[@id=\"submenu-bar\"]/div/div/a[8]"));
		click(notes);
		WebElement currentAssets = driver.findElement(By.xpath("//*[@id=\"toolbar\"]/button[2]"));
		click(currentAssets);
		WebElement otherNotePage = driver.findElement(By.xpath("//div[2]//div[2]//table//tr[14]/td[2]/div"));
		click(otherNotePage);
		
		WebElement firstLine = driver.findElement(By.xpath("//*[@id=\"CaoNoteParB0\"]//tr/td[3]/div/div"));
		WebElement paragraphInput = driver.findElement(By.xpath("//*[@id=\"dialog-edit-paragraph\"]/div/div[1]"));
		firstLine.click();
		paragraphInput.sendKeys(text);
		WebElement saveButton01 = driver
				.findElement(By.xpath("//*[@id=\"notereview-app\"]/dialog[6]/form//div[3]/div/button[1]"));
		click(saveButton01);
		threadSleep(2);
		
		WebElement secondLine = driver.findElement(By.xpath("//*[@id=\"CaoNoteParB1\"]//table/tbody//td[3]"));
		click(secondLine);
		paragraphInput.sendKeys(text);
		click(saveButton01);
		threadSleep(3);
		
		WebElement thirdLine = driver.findElement(By.xpath("//*[@id=\"CaoNoteParB2\"]//table/tbody//td[3]"));
		click(thirdLine);
		paragraphInput.sendKeys(text);
		click(saveButton01);
		threadSleep(2);
		
		WebElement table01 = driver.findElement(By.xpath("//*[@class='review-table is-turned-off is-editable']"));
		click(table01);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='review-table is-turned-off is-editable']")));
		threadSleep(2);
		By table = By.xpath("//table[@class='review-table table-edit is-turned-off']");
	
		// Call the method to generate random values and store them in an ArrayList. Then verify
		asserts(sendRandomValuesToTableRows(driver, table, 4));
		asserts(sendRandomValuesToTableRows(driver, table, 5));
		WebElement saveButton02 = driver
				.findElement(By.xpath("//*[@id=\"notereview-app\"]/dialog[7]/form/div/div[3]/div/button[1]"));
		click(saveButton02);
		threadSleep(5);

		WebElement previewButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='toolbar']/p[1]/div")));
		previewButton.click();
		
		WebElement  list = driver.findElement(By.id("nodes-list"));
		assertTrue(list.isDisplayed());
		previewButton.click();
		
		threadSleep(3);
		By reportsElement = By.xpath("//*[@id=\"mainmenu-bar\"]/div/div[1]/a[2]/span");
		clickOnElement(driver, reportsElement );
		threadSleep(3);
		
		By listElement = By.xpath("//*[@id=\"submenu-bar\"]/div/div/a[1]/span");
		clickOnElement(driver, listElement);
		
		threadSleep(3);
		List<WebElement> listOfReports = driver.findElements(By.xpath("//*[@id=\"reports-table\"]/tbody/tr/td[1]"));
		selection(listOfReports, reportVersion);
		
		By sectionElement = By.xpath("//*[@id=\"mainmenu-bar\"]/div/div[1]/a[4]");
		By notesElement = By.xpath("//*[@id=\"submenu-bar\"]/div/div/a[8]");
		By currentAssetsElement = By.xpath("//*[@id=\"toolbar\"]/button[2]");
		By otherNotePageElement = By.xpath("//div[2]//div[2]//table//tr[14]/td[2]/div");
		By table02 = By.id("nodes-list");
		
		threadSleep(3);
		clickOnElement(driver, sectionElement);
		threadSleep(3);
		clickOnElement(driver, notesElement);
		threadSleep(3);
		clickOnElement(driver, currentAssetsElement);
		threadSleep(3);
		clickOnElement(driver, otherNotePageElement);
		assertElementIsDisplayed(driver, table02);


		

		
	}

}
