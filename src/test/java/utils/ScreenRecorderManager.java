package utils;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;

public class ScreenRecorderManager {

    private ThreadLocal<ScreenRecorderMonte> screenRecorder = new ThreadLocal<>();

    private static final Path currentPath = Paths.get(System.getProperty("user.dir"));
    private static final Path finalPath = currentPath.resolve("Recordings");

    public  ScreenRecorderMonte getRecorder(String videoName) throws IOException, AWTException {
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
                    new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, file, videoName));

        }
        return screenRecorder.get();
    }

    public static void deleteRecords() {
        File directory = new File(finalPath.toString());
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }
    }
}
