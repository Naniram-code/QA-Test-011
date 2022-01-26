


	

	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	
	public class datadriventest {
		
		// To achieve Data Driven Testing:
			// 1. Create test set/test data -> using @DataProvider annotation
			// 2. Establish connection between Test data and Test method -> using keyword "dataProvider"
			// 3. Make data flow into automation script -> method parameterization
		
		WebDriver driver;
		
		@BeforeMethod
		public void launchApp() {
			System.setProperty("webdriver.chrome.driver", "/Users/nanirambhujel/Documents/my doc/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.get("http://demo.guru99.com/test/newtours/index.php");
		}
		
		@AfterMethod
		public void closeBrowser() {
			driver.close();
		}
		
		@Test (dataProvider="positiveLoginData")
		public void positiveLoginFunctionalityTestClickingSubmitBtn(String username, String password) {
			WebElement usernameTxtBox = driver.findElement(By.name("userName"));
			usernameTxtBox.sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			WebElement submitBtn = driver.findElement(By.name("submit"));
			submitBtn.click();
			String expectedLoginSuccessPageUrl = "http://demo.guru99.com/test/newtours/login_sucess.php";
			String actualPageUrl = driver.getCurrentUrl(); 
			Assert.assertEquals(actualPageUrl, expectedLoginSuccessPageUrl);
		}
		
		@Test (dataProvider="positiveLoginData")
		public void positiveLoginFunctionalityTestHittingEnterKey(String username, String password) {
			WebElement usernameTxtBox = driver.findElement(By.name("userName"));
			usernameTxtBox.sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
			String expectedLoginSuccessPageUrl = "http://demo.guru99.com/test/newtours/login_sucess.php";
			String actualPageUrl = driver.getCurrentUrl(); 
			Assert.assertEquals(actualPageUrl, expectedLoginSuccessPageUrl);
		}
		
		// creating data set
		@DataProvider
		public Object[][] positiveLoginData() {
			Object[][] data = {	{"test", "123"},
								{"test12", "123"},
								{"test123", "123"},
								{"test1234", "12345"},
								{"test12345", "123"}};
			return data;
		}
		
//		@Test(dataProvider="Home Page link testing data")
		public void positiveMenuLinkofHomePageFunctionalityTest(String link, String expectedTitle) {
			driver.findElement(By.linkText(link)).click();
			String expectedFlightsPageTitle = expectedTitle;
			String actualFlightsPageTitle = driver.getTitle();
			Assert.assertEquals(actualFlightsPageTitle, expectedFlightsPageTitle);
		}
		
		@DataProvider (name="Home Page link testing data")
		public Object[][] linkTestingData() {
			Object[][] data = {	{"Home", "Welcome: Mercury Tours"},
								{"Flights", "Find a Flight: Mercury Tours"},
								{"SIGN-ON", "Sign-on: Mercury Tours"},
								{"REGISTER", "Register: Mercury Tours"},
								{"Hotels", "Hostels: Mercury Tours"},
								{"SUPPORT", "Support: Mercury Tours"}};
			return data;
		}
		// Assignment: amazon.com -> 1. Account & List | 2. Search functionality | 3. Menu options -> test using TestNG data driven testing

	}




