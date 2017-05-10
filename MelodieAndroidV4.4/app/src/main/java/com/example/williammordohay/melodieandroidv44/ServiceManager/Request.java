package com.example.williammordohay.melodieandroidv44.ServiceManager;

/**
 * Created by william.mordohay on 05/05/2017.
 */

public class Request {
    private String baseUrl;

    public Request(String baseUrl){
        this.baseUrl=baseUrl;
    }

    public String getCellsList(String lineNumber){
        return(baseUrl+"GetCellsList/"+lineNumber);
    }

    public String getLinesList(){
        return(baseUrl+"GetLinesList/");
    }
    public String getRunningMode(String lineNumber, String cellNumber){
        return(baseUrl+"GetRunningMode/"+lineNumber+"/"+cellNumber);
    }
    public String getHourProduction(String lineNumber){
        return(baseUrl+"GetHourProduction/"+lineNumber);
    }
    public String getDayProduction(String lineNumber){
        return(baseUrl+"GetDayProduction/"+lineNumber);
    }
    public String getWeekProduction(String lineNumber){
        return(baseUrl+"GetWeekProduction/"+lineNumber);
    }

}
