package tau.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tau.base.BaseUtil;

import static org.junit.Assert.assertTrue;

public class Steps extends BaseUtil {

    private final BaseUtil baseUtil;

    public Steps(BaseUtil util) {
        this.baseUtil = util;
    }

    private WebDriver driver;

    @Before()
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("I am in the login page of the Para Bank Application")
    public void i_am_in_the_login_page_of_the_Para_Bank_Application() {

        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @When("I enter valid {string} and {string}")
    public void i_enter_valid_credentials_(String username, String password) {

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        driver.findElement(By.linkText("Log Out")).click();
    }

    @When("I enter valid {string} and {string} with {string}")
    public void i_enter_valid_credentials(String username, String password, String userFullName) {

        baseUtil.userFullName = userFullName;

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        driver.findElement(By.linkText("Log Out")).click();
    }

    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_Overview_page() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='smallText']")));

        String actualuserFullName = driver.findElement(By.xpath("//p[@class='smallText']")).getText();
        assertTrue(actualuserFullName, actualuserFullName.contains(baseUtil.userFullName));

        driver.findElement(By.linkText("Log Out")).click();
    }

/*    @When("I enter valid credentials")
    public void i_enter_valid_credentials(DataTable table) {

        List<String> loginForm = table.asList();

        driver.findElement(By.name("username")).sendKeys(loginForm.get(0));
        driver.findElement(By.name("password")).sendKeys(loginForm.get(1));
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        driver.findElement(By.linkText("Log Out")).click();
    }*/

    @After()
    public void quitBrowser() {
        driver.quit();
    }
}
