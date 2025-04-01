package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser,String browserHeight,String browserWidth) {
        if (driver.get() == null) {
            if(browserHeight == null) browserHeight = "1920";
            if(browserWidth == null) browserWidth = "1080";
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--window-size="+browserHeight+","+browserWidth+"");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width="+browserWidth+"");
                    firefoxOptions.addArguments("--height="+browserHeight+"");
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--window-size="+browserHeight+","+browserWidth+"");
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static String getSessionInfo() {
       return "Thread ID: " + Thread.currentThread().getName() +
                " - WebDriver HashCode: " + driver.hashCode();
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
