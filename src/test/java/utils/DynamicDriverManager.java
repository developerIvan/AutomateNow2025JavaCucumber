package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DynamicDriverManager {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public  WebDriver getDriver(String browser,String browserHeight,String browserWidth,boolean isHeadless) {
        if (driver.get() == null) {
            if(browserHeight == null) browserHeight = "1920";
            if(browserWidth == null) browserWidth = "1080";
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver.set(new ChromeDriver(getChormeOptions(isHeadless,browserHeight,browserWidth)));
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver(getFireFoxOptions(isHeadless,browserHeight,browserWidth)));
                    break;
                case "edge":
                    driver.set(new EdgeDriver(getEdgeOptions(isHeadless,browserHeight,browserWidth)));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            if(!isHeadless){
              driver.get().manage().window().maximize();
            }

        }
        return driver.get();
    }


    public ChromeOptions getChormeOptions(boolean isHeadless,String browserHeight,String browserWidth){
        ChromeOptions chromeOptions = new ChromeOptions();
        if(isHeadless){
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size="+browserHeight+","+browserWidth+"");
        }
        return chromeOptions;
    }

    public FirefoxOptions getFireFoxOptions(boolean isHeadless,String browserHeight,String browserWidth){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if(isHeadless){
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--width="+browserWidth+"");
            firefoxOptions.addArguments("--height="+browserHeight+"");
        }

        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions(boolean isHeadless,String browserHeight,String browserWidth){
        EdgeOptions edgeOptions = new EdgeOptions();
        if(isHeadless){
            edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("--window-size="+browserHeight+","+browserWidth+"");
        }
        return edgeOptions;
    }
    public  void quitDriver() {
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
