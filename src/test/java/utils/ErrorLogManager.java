package utils;

import PageObjectModel.GeneralSelectorActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogManager {

    private static final Logger logger = LoggerFactory.getLogger(GeneralSelectorActions.class);

    private static String getFormatterDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    private static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public static String getUniqueErrorCode(String errorCode) {
        return errorCode+Math.random()*1000+"-"+ getFormatterDate();
    }

    public static void logError(String errorCode, Exception e, String errorTitle) {
        logger.error("---------------------"+errorTitle+"------------------- \n");
        logger.error("Error code : "+errorCode+ " Date:"+ getCurrentDate() +"\n");
        logger.error("exception trace: "+e );
    }

    public static void logInfo(String dataToLog) {

        logger.error("Data : "+dataToLog+ " Date:"+ getCurrentDate() +"\n");
    }


}
