package utils;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;


public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ScreenRecorderMonte> screenRecorder = new ThreadLocal<>();

    private static Path currentPath = Paths.get(System.getProperty("user.dir"));
    private static Path finalPath = currentPath.resolve("Recordings");

    public static WebDriver getDriver(String browser,String browserHeight,String browserWidth) {
        if (driver.get() == null) {
            if(browserHeight == null) browserHeight = "1920";
            if(browserWidth == null) browserWidth = "1080";
            switch (browser.toLowerCase()) {
                case "chrome":
                   ChromeOptions chromeOptions = new ChromeOptions();
                //    chromeOptions.addArguments("--headless");
                //   chromeOptions.addArguments("--headless=new"); // prueba con o sin esto
               //    chromeOptions.addArguments("--remote-allow-origins=*");
               //    chromeOptions.addArguments("--window-size="+browserHeight+","+browserWidth+"");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                  //  firefoxOptions.addArguments("--headless");
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

    public static ScreenRecorderMonte getRecorder(String videoName) throws IOException, AWTException {
        if(screenRecorder.get()==null){
            File file = new File(finalPath.toString());
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;
            Rectangle captureSize = new Rectangle(0, 0, width, height);

            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice()
                    .getDefaultConfiguration();

            screenRecorder.set(new ScreenRecorderMonte(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, file, videoName));

        }
        return screenRecorder.get();
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

    public static void stopRecording() {
        if (screenRecorder.get() != null) {
            try {
                screenRecorder.get().stopRecord();
            } catch (Exception e) {
                ErrorLogManager.logError("Error closing the web Driver: " + e.getMessage(),e,"------Error on closing driver 44545645-------------");
            }
        }
    }
}
