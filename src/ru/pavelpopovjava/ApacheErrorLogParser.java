package ru.pavelpopovjava;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ApacheErrorLogParser {

    private String lastRecordTime = "";
    private String apacheErrorLogFilePath;

    ApacheErrorLogParser(String str, String errorLogPath){
        lastRecordTime = str;
        apacheErrorLogFilePath = errorLogPath;
    }

    public ArrayList<String> getNewRecords() throws IOException{
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(apacheErrorLogFilePath), Charset.forName("UTF-8"));

        ArrayList<String> records = new ArrayList<String>();

        //Эта переменная показывает, что при обходе начались новые записи. Если последнее время не определено, тогда все записи - новые
        boolean newRecords = (lastRecordTime == "") || (lastRecordTime == null);

        for (String line : lines){
            //Строковое представление даты-времени текущей записи в логе ошибок
            String currentDateTime = getDateTimeFromLine(line);
            if (!newRecords && (currentDateTime.equals(lastRecordTime))){
                newRecords = true;
                continue;
                //С этого момента пойдут уже новые записи, которые будут добавляться в ArrayList
            }

            if (newRecords){
                records.add(line);
            }
        }

        return records;

    }

    public String getDateTimeFromLine(String line) {
        int endIndex = line.indexOf("]");
        String dateString = line.substring(1,endIndex);
        return dateString;
    }
}
