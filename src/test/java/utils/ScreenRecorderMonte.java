package utils;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenRecorderMonte extends ScreenRecorder {



    private String videoName;


    private static Path currentPath = Paths.get(System.getProperty("user.dir"));
    private static Path finalPath = currentPath.resolve("Recordings");

    public final File MOVIE_FILE = new File(finalPath.toString());
    public final Dimension SCREEN_SIZE  = Toolkit.getDefaultToolkit().getScreenSize();
   public final int WIDTH  = SCREEN_SIZE.width;
    public final int HEIGHT  = SCREEN_SIZE.height;
    Rectangle captureSize = new Rectangle(0, 0, WIDTH, HEIGHT);

    GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
            getDefaultScreenDevice()
            .getDefaultConfiguration();


    public ScreenRecorderMonte(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                              Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        //    this.name = name;
        this.setVideoName(name);
    }


    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoName() {
        return this.videoName;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        return new File(movieFolder,
                this.getVideoName() +"."+ Registry.getInstance().getExtension(fileFormat));
    }

    public void startRecording()throws Exception{
        this.start();
    }

    public  void stopRecord() throws Exception {
        this.stop();
        String inputPath = finalPath.resolve(this.getVideoName() + ".avi").toString();
        String outputPath = finalPath.resolve(this.getVideoName() + ".mp4").toString();
      // String ffmpegCommand = String.format("ffmpeg -y -i \"%s\" -vcodec libx264 -crf 24 \"%s\"", inputPath, outputPath);

        String outputMp4Path = inputPath.replace(".avi", ".mp4");

        String command = String.format(
                "ffmpeg -y -i \"%s\" -vcodec mpeg4 -preset slow -crf 23 -acodec aac -b:a 128k \"%s\"",
                inputPath, outputMp4Path);


        Runtime.getRuntime().exec(command);


    }


}
