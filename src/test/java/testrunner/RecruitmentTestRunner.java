package testrunner;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import page.NavSection;
import page.Recruitment;
import setup.Setup;

public class RecruitmentTestRunner extends Setup {
    Recruitment recruitment;
    NavSection navSection;
    Faker faker;
    String firstName, lastName;

    @Test(priority = 2, description = "User can add candidates")
    public void addCandidates() throws InterruptedException {
        recruitment = new Recruitment(driver);
        navSection = new NavSection(driver);
        faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();

        navSection.clickOnRecruitment();
        recruitment.clickBtnAdd();
        recruitment.addCandidate(firstName, lastName);
        recruitment.assertCandidateAdd(firstName, lastName);
    }

    @Test(priority = 3, description = "User can shortlist a candidate")
    public void sortListCandidate() {
        recruitment = new Recruitment(driver);

        recruitment.openCandidateDetails(firstName, lastName);
        recruitment.doShortlist();
        recruitment.assertApplicationStatus();
    }

    @Test(priority = 4, description = "User can schedule an interview of a shortlisted candidate with two interviewers")
    public void scheduleInterview() throws InterruptedException {
        recruitment = new Recruitment(driver);

        recruitment.scheduleAnInterview();
    }


    @Test(priority = 5, description = "User can mark an interview and search the result")
    public void markInterviews() throws InterruptedException {
        recruitment = new Recruitment(driver);
        navSection = new NavSection(driver);

        recruitment.markInterview();
        navSection.clickOnRecruitment();
        recruitment.searchCandidate(firstName, lastName);
    }
}
