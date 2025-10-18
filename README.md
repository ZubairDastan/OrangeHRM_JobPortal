# OrangeHRM Job Portal Automation

## Project Overview
This project is an automated testing suite for **Orange HRM** web application using **Selenium WebDriver** with **TestNG**. It includes test cases for login, add candidate, shortlist the candidate, set interview schedule for that candidate and take mark the taken interview.

## Technologies Used
- **Java** (Programming Language)
- **Selenium WebDriver** (UI Automation)
- **TestNG** (Test Framework)
- **WebDriverManager** (Driver Management)
- **JavaFaker** (Random Data Generation)
- **Gradle** (Build Automation)
- **Allure** (HTML Report Generator)

## Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/
│   ├── test/                        # All test are in this folder
│   │   ├── java/
│   │   │   ├── pages/               # Page Object Model (POM) Classes
│   │   │   ├── setup/               # WebDriver Setup
│   │   │   ├── testrunner/          # TestNG Test Runner Classes
│   │   │   ├── utils/               # Other utility methods that are commonly used allover the project
│   │   ├── resources/
│   │   │   ├── screenshots/         # Keeps generated screenshots for faild tests (Ignored in git)
│   │   │   ├── testdata/            # Keeps test data
│   │   │   ├── suites/              # Test suites
│   │   │   ├── config.properties    # Keeps config items like base URL
├── .gitignore                       # GitIgnore file
├── build.gradle                     # Gradle build file
├── README.md                        # Project Documentation
```

## Prerequisites
Before running the project, ensure you have the following installed:
- Java 17+.
- Make sure the gradle bin folder is defined in system .env path.
- Git (optional, for version control).
- Make sure system has installed the latest version of Allure for generating html report.

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/ZubairDastan/OrangeHRM_JobPortal.git
   ```
2. Navigate to the project directory:
   ```sh
   cd OrangeHRM_JobPortal
   ```


## Running Tests
- Run all tests:
  ```sh
  gradle clean test
  ```
- Run a specific test class:
  ```sh
  gradle test --tests org.gradle.SomeTest.someSpecificFeature
  ```


## Generating Allure Report
- After running all tests, run the mentioned commands,
  ```sh
  allure serve allure-results
  ```


## Test Cases Overview
### 1. **Login Tests**
- Valid admin user login (`LoginTestRunner.java`)
- Verify dashboard redirection after login

### 2. **Recruitment Tests**
- Create a new candidate (`RecruitmentTestRunner.java`)
- Shortlist the candidate
- Schedule an interview for that candidate
- Mark the interview as passed/failed


## Contributing
1. Fork the repository.
2. Create a new branch:
   ```sh
   git checkout -b feature-branch
   ```
3. Add all the changes:
   ```sh
   git add .
   ```
4. Commit changes:
   ```sh
   git commit -m "Add new feature"
   ```
5. Push the branch:
   ```sh
   git push origin your_local_branchName
   ```
6. Finally, create a pull request.


## Future Improvements
- Implement CI/CD integration


## Contributors
- [Zubair Hasan](https://github.com/Zubair1545)


## License
This project is licensed under the [MIT License](LICENSE).