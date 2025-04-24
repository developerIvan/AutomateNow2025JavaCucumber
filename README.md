Selenium Java Automation Portfolio

This is a portfolio project demonstrating web automation using **Selenium, Java, and Cucumber**. The project includes various test cases covering UI automation for a sample web application.

## 📋 Table of Contents
- [📌 About the Project](#-about-the-project)
- [🛠 Technologies Used](#-technologies-used)
- [⚙️ Setup and Installation](#️-setup-and-installation)
- [🚀 Running the Tests](#-running-the-tests)
- [📢 Reporting](#-reporting)
- [📌 Future Enhancements](#-future-enhancements)
- [📞 Contact](#-contact)
## 📌 About the Project
This project showcases **automated UI testing** for a web application using **Selenium WebDriver**, **Java**, and **Cucumber**. It follows the **Page Object Model (POM)** design pattern for better maintainability and scalability.

## 🛠 Technologies Used
- **Java 17+**
- **Selenium WebDriver**
- **Cucumber (BDD Framework)**
- **TestNG** (or JUnit)
- **Maven** (for dependency management)
- **Git & GitHub** (for version control)
- **Allure Reports** (for version control)



## ⚙️ Setup and Installation
1. **Clone the repository**
   ```sh
   git clone https://github.com/your-username/your-repo.git

   cd SeleniumJavaautomateNow2025PorfolioProject
   
   mvn clean install
   ```
2. **Create .env file**
- Create a `.env` file in the root directory of the project.
- Add the following environment variables:
  ```env
  browserName=firefox
  browserHeight= desired heigth, eg, example 1920
  browserWidth=desired width, eg, example 1080
  ```
## 🚀 Running the Tests
1. **Run all tests**
   ```sh
   mvn test
   ```

## 🚀 Reporting

1.**Execute the framework**
   ```sh
    mvn clean test
    ```
3.**Generate Allure report**
   ```sh
      mvn allure:report   
   ```
4.**Open Allure report**
   ```sh
     mvn allure:serve
   ```