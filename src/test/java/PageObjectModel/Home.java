package PageObjectModel;

import ResultPattern.Result;

import java.util.Date;

public class Home extends GeneralSelectorActions {

    private final String errorCode = "Home01";
    Date currentDate = new Date();
    public Home() {
        super();
    }

    public boolean openHomePage(String url) {
        Result pageIsOpened =  openPage(url);
        if(pageIsOpened.isFailure()){
            this.getLogger().error("---------------------Error on home page------------------- \n");
            this.getLogger().error("Error code : "+ getHomeErrorCode()+ " Date:"+ this.getDateTime() +"\n");
            this.getLogger().error("Error opening page: "+url );

          System.out.println("Error opening page: "+url);
          return false;
        }
        return true;
    }

    public String getHomeErrorCode() {
        return errorCode;
    }



}
