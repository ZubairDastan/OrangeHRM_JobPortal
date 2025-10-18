package page;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utils;

import java.util.List;

public class Recruitment {
    WebDriver driver;
    Actions actions;

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> btnCandidate;

    @FindBy(xpath = "//button[text()=' Add ']")
    WebElement btnAdd;

    @FindBy(name = "firstName")
    WebElement txtFirstName;

    @FindBy(name = "lastName")
    WebElement txtLastName;

    @FindBy(className = "oxd-select-text-input")
    WebElement dropdownVacancy;

    @FindBy(className = "oxd-input")
    List<WebElement> inputField;

    @FindBy(xpath = "//input[@type='file']")
    WebElement resumeUpload;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    WebElement txtKeyword;

    @FindBy(className = "oxd-checkbox-input")
    WebElement checkbox;

    @FindBy(xpath = "//button[text()=' Save ']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']/div[@role='cell'][3]//div") //Candidate column
    List<WebElement> candidateCells;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']") //All table rows
    List<WebElement> tableRows;

    @FindBy(xpath = "//button[text()=' Shortlist ']")
    WebElement btnShortlist;

    @FindBy(xpath = "//button[text()=' Save ']")
    WebElement btnSaveShortlist;

    @FindBy(className = "orangehrm-recruitment-status")
    WebElement applicationStatus;

    @FindBy(xpath = "//button[text()=' Schedule Interview ']")
    WebElement btnScheduleInterview;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    List<WebElement> searchUser;

    @FindBy(className = "orangehrm-input-field-bottom-space")
    WebElement btnAddAnother;

    @FindBy(xpath = "//button[text()=' Mark Interview Passed ']")
    WebElement btnPassInterview;

    @FindBy(xpath = "//button[text()=' Search ']")
    WebElement btnSearch;


    public Recruitment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBtnAdd() {
        btnAdd.click();
    }

    public void addCandidate(String firstName, String lastName) throws InterruptedException {
        Faker faker = new Faker(); // Faker to generate random data
        actions = new Actions(driver);
        Utils.waitForElement(driver, By.name("firstName"), 10);

        //Insert first name
        txtFirstName.sendKeys(firstName);

        //Insert last name
        txtLastName.sendKeys(lastName);

        //Select vacancy
        dropdownVacancy.click();
        Thread.sleep(1000);
        actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

        //Insert email
        inputField.get(4).sendKeys(faker.internet().emailAddress());

        //Insert Phone number of 10 digits
        inputField.get(5).sendKeys(faker.number().digits(10));

        //Upload pdf resume
        resumeUpload.sendKeys(System.getProperty("user.dir") + "/src/test/resources/testdata/SamplePDF.pdf");

        //Insert keyword
        txtKeyword.sendKeys("Automation");

        //Check the checkbox
        checkbox.click();

        //Click save button
        btnSave.click();
    }

    public void assertCandidateAdd(String firstName, String lastName) {
        btnCandidate.get(0).click();

        Utils.waitForElement(driver, By.xpath("//div[@class='oxd-table-card']"), 10);

        List<String> actualNames = candidateCells.stream()
                .map(WebElement::getText)
                .toList();

        //Assert the created candidate
        Assert.assertTrue(actualNames.contains(firstName + " " + lastName));
    }

    public void openCandidateDetails(String firstName, String lastName) {
        String targetCandidate = firstName + "  " + lastName;
        Utils.waitForElement(driver, By.xpath("//div[@class='oxd-table-body']"), 10);

        for (int i = 0; i < tableRows.size(); i++) {
            try {
                //Re-fetching the row every time inside the loop to avoid staleness
                WebElement currentRow = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@role='row']")).get(i);
                String candidateName = currentRow.findElement(By.xpath("./div[@role='cell'][3]")).getText().trim();

                if (normalize(candidateName).equalsIgnoreCase(normalize(targetCandidate))) {
                    WebElement eyeIcon = currentRow.findElement(By.xpath(".//i[contains(@class, 'bi-eye-fill')]"));
                    Utils.waitForElement(driver, By.xpath(".//i[contains(@class, 'bi-eye-fill')]"), 5);
                    eyeIcon.click();

                    break;
                }
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Stale element encountered — retrying row " + i);
                // Retry same index by decrementing i
                i--;
            }
        }
    }

    public void doShortlist() {
        Utils.waitForElement(driver, By.xpath("//button[text()=' Shortlist ']"), 10);
        btnShortlist.click();

        Utils.waitForElement(driver, By.xpath("//button[text()=' Save ']"), 10);
        btnSaveShortlist.click();
    }

    public void assertApplicationStatus() {
        Utils.waitForElement(driver, By.className("orangehrm-recruitment-status"), 10);
        String actualStatus = applicationStatus.getText();
        Assert.assertTrue(actualStatus.contains("Shortlisted"));
    }

    public void scheduleAnInterview() throws InterruptedException {
        actions = new Actions(driver);

        btnScheduleInterview.click();

        //Insert interview title
        inputField.get(5).sendKeys("SQA Engineer");

        //Search interviewer 1
        searchUser.get(0).sendKeys("John Doe");

        //Select interviewer 1 from suggestions
        Thread.sleep(2000);
        actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

        //Add another interviewer
        btnAddAnother.click();

        //Search interviewer 2
        searchUser.get(1).sendKeys("Emma");

        //Select interviewer 2 from suggestions
        Thread.sleep(2000);
        actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

        //Select interview date
        inputField.get(6).sendKeys("2025-10-30");

        //Select interview time
        inputField.get(7).sendKeys("02:30 PM");

        //Click save button
        btnSave.click();
    }

    public void markInterview() {
        Utils.waitForElement(driver, By.xpath("//button[text()=' Mark Interview Passed ']"), 10);
        btnPassInterview.click();
        Utils.waitForElement(driver, By.xpath("//button[text()=' Save ']"), 10);
        btnSave.click();
    }

    public void searchCandidate(String firstName, String lastName) throws InterruptedException {
        actions = new Actions(driver);
        String targetCandidate = firstName + "  " + lastName;

        Utils.waitForElement(driver, By.xpath("//input[@placeholder='Type for hints...']"), 10);

        //Insert candidate search keyword
        searchUser.get(0).sendKeys(firstName);
        Thread.sleep(2000);
        actions.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).perform();
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();

        //Click on search button
        btnSearch.click();

        Utils.waitForElement(driver, By.xpath("//div[@class='oxd-table-body']"), 10);

        //Search the desired candidate row and check status
        for (WebElement row : tableRows) {
            String candidateName = row.findElement(By.xpath("./div[@role='cell'][3]")).getText().trim();

            if (normalize(candidateName).equalsIgnoreCase(normalize(targetCandidate))) {
                String status = row.findElement(By.xpath("./div[@role='cell'][6]")).getText().trim();
                Assert.assertEquals(status, "Interview Passed");

                break;
            }
        }
    }

    //This is a text normalizer method
    public static String normalize(String name) {
        return name.replaceAll("\\s+", " ").trim();
    }

}
