package utils;
import org.monte.media.Format;
import org.monte.media.FormatKeys.*;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

public class ScreenRecorderUtil extends ScreenRecorder {
    public static ScreenRecorder screenRecorder;
    private static String name;


    private ScreenRecorder screenRecorderInstance;
    private String movieName;

    public  String getVideoName(){
        return movieName;
    }

    public  void setVideoName(String movieName){
        this.movieName= movieName;
    }
    private static Path currentPath = Paths.get(System.getProperty("user.dir"));
    private static Path finalPath = currentPath.resolve("Recordings");

    public ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                              Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.screenRecorderInstance =new ScreenRecorder(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
    //    this.name = name;
        this.setVideoName(name);
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        return new File(movieFolder,
                this.getVideoName() +"."+Registry.getInstance().getExtension(fileFormat));
    }





    public static void startRecord(String recordedVideoName) throws Exception {
        File file = new File(finalPath.toString());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();
        screenRecorder = new ScreenRecorderUtil(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, recordedVideoName);
        screenRecorder.start();
    }

    public  void startRecord() throws Exception {
        screenRecorderInstance.start();
    }

    public static void stopRecord(String videoName) throws Exception {
        screenRecorder.stop();
        String inputPath = finalPath.resolve(name + ".avi").toString();
        String outputPath = finalPath.resolve(name + ".mp4").toString();
    /*    String ffmpegCommand = String.format("ffmpeg -y -i \"%s\" -vcodec libx264 -crf 24 \"%s\"", inputPath, outputPath);

        String outputMp4Path = inputPath.replace(".avi", ".mp4");

        String command = String.format(
                "ffmpeg -y -i \"%s\" -vcodec mpeg4 -preset slow -crf 23 -acodec aac -b:a 128k \"%s\"",
                inputPath, outputMp4Path);



       // String ffmpegCommand = String.format("ffmpeg  -i \"%s\" -map 0:1 -c copy \"%s\"", inputPath, outputPath);

        //"ffmpeg -i recordings/testGoogleSearch.mov -vcodec libx264 -crf 24 recordings/testGoogleSearch.mp4")

        Runtime.getRuntime().exec(command);*/


    }


    public  void stopRecord() throws Exception {
        screenRecorderInstance.stop();
        String inputPath = finalPath.resolve(this.getVideoName() + ".avi").toString();
        String outputPath = finalPath.resolve(name + ".mp4").toString();
    /*    String ffmpegCommand = String.format("ffmpeg -y -i \"%s\" -vcodec libx264 -crf 24 \"%s\"", inputPath, outputPath);

        String outputMp4Path = inputPath.replace(".avi", ".mp4");

        String command = String.format(
                "ffmpeg -y -i \"%s\" -vcodec mpeg4 -preset slow -crf 23 -acodec aac -b:a 128k \"%s\"",
                inputPath, outputMp4Path);



       // String ffmpegCommand = String.format("ffmpeg  -i \"%s\" -map 0:1 -c copy \"%s\"", inputPath, outputPath);

        //"ffmpeg -i recordings/testGoogleSearch.mov -vcodec libx264 -crf 24 recordings/testGoogleSearch.mp4")

        Runtime.getRuntime().exec(command);*/


    }
    public static void deleteRecords() {
        File directory = new File(finalPath.toString());
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }
    }
}