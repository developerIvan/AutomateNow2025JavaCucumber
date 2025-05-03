package utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoRecorder {
    private Process ffmpegProcess;
    private String outputFile;

    public void startRecording(String scenarioName) throws IOException {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path finalPath = currentPath.resolve("Recordings");
        outputFile = finalPath.toString()+"\\"+ sanitizeFileName(scenarioName) + ".mp4";
        //linux op
        /*String cmd = String.format(
                "ffmpeg -y -video_size 1920x1080 -framerate 25 -f x11grab -i :99 -codec:v libx264 -preset ultrafast %s",
                outputFile
        );*/

        ProcessBuilder builder = new ProcessBuilder(
                "ffmpeg", "-y",
                "-f", "gdigrab",
                "-framerate", "30",
                "-i", "desktop",
                "-preset", "ultrafast",
                "-vcodec", "libx264",
                "testVideo.avi"
        );
        builder.redirectErrorStream(true);
        ffmpegProcess = builder.start();
    }

    public void stopRecording() {
        if (ffmpegProcess != null) {
            ffmpegProcess.destroy(); // o usa kill -INT si prefieres
        }
    }

    public String getOutputFile() {
        return outputFile;
    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
