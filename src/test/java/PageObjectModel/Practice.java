package PageObjectModel;

import ResultPattern.Result;

public class Practice extends GeneralSelectorActions  {

    private final String errorCode = "Practice01";
    public Practice() {
        super();
    }

    public boolean openPracticePage(String url) {
        Result pageIsOpened =  openPage(url);
        if(pageIsOpened.isFailure()){
            this.getLogger().error("---------------------Error on home page------------------- \n");
            this.getLogger().error("Error code : "+ getPracticeErrorCode()+ " Date:"+ this.getDateTime() +"\n");
            this.getLogger().error("Error opening page: "+url );

            System.out.println("Error opening page: "+url);
            return false;
        }
        return true;
    }
    public String getPracticeErrorCode() {
        return errorCode;
    }
}
