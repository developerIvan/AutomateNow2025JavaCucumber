package utils;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.page.Page;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenRecorderDevtools {

    private  DevTools devTools;
    private final AtomicInteger frameCounter = new AtomicInteger(0);
    private File outputDir;

    public ScreenRecorderDevtools(DevTools devTools, String outputDirectory) {
        this.devTools = devTools;
        this.outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    public ScreenRecorderDevtools(){

    }

    public void setDevTools( DevTools devTools){
        this.devTools = devTools;
    }

    public void setFileOutpuDir(File outputDir){
        this.outputDir = outputDir;
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    public void startRecording(int maxWidth,int maxHeight) {
        Optional<Page.StartScreencastFormat> format = Optional.of(Page.StartScreencastFormat.JPEG);
        Optional<Integer> maxWidthOp = maxWidth>0?  Optional.of(maxWidth):Optional.of(1080);
        Optional<Integer> maxHeigthOp = maxHeight>0?  Optional.of(maxHeight):Optional.of(1920);
        devTools.createSessionIfThereIsNotOne();
        devTools.send(Page.enable(Optional.of(true)));
        devTools.send(Page.startScreencast(format,Optional.of(100),maxWidthOp,maxHeigthOp,Optional.of(1)));

        devTools.addListener(Page.screencastFrame(), frame -> {
            String base64Image = frame.getData();
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            int frameNumber = frameCounter.getAndIncrement();
            File frameFile = new File(outputDir, String.format("frame-%05d.png", frameNumber));

            try (FileOutputStream fos = new FileOutputStream(frameFile)) {
                fos.write(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Importante: enviar acuse para seguir recibiendo frames
            devTools.send(Page.screencastFrameAck(frame.getSessionId()));
        });
    }

    public void combineFramesToVideo(String framesFolder, String outputVideoPath) throws IOException, InterruptedException {
        String ffmpegCommand = String.format(
                "ffmpeg -framerate 15 -i %s/frame-%%05d.png -c:v libx264 -pix_fmt yuv420p %s",
                framesFolder,
                outputVideoPath
        );

        Process process = new ProcessBuilder("bash", "-c", ffmpegCommand).inheritIO().start();
        process.waitFor();
    }

    public void stopRecording() {
        devTools.send(Page.stopScreencast());
    }
}
