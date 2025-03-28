package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    driver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Usuported broswer: " + browser);
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static String getSessionInfo() {
       return "Thread ID: " + Thread.currentThread().getName() +
                " - WebDriver HashCode: " + driver.hashCode();
    }

    public static void terminateDrivers() {

    }
    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                driver.remove();
            } catch (Exception e) {
                ErrorLogManager.logError("Error closing the web Driver: " + e.getMessage(),e,"------Error on closing driver 44545645-------------");
            }
        }
    }
}
