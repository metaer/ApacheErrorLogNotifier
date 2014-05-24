package ru.pavelpopovjava;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException{

        //TODO Проверка на кол-во параметров

        String pathApacheErrorLogFile = args[0];    //Файл лога ошибок апача
        String email = args[1];                     //Емэил куда слать ошибки
        String pathDataFile = args[2];              //Файл с датой-временем последней обработанной записи
        String pathLogsDir = args[3];               //Директория для логов выполнения данной программы

        //TODO сделать проверки на входные параметры

        Logger.setLogDir(pathLogsDir);

        createDataFileIfNotExists(pathDataFile);

        Logger.writeLog("Program starts");

        String lastRecordProcessedTime = getLastRecordProcessTimeFromFile(pathDataFile);

        ApacheErrorLogParser aelp = new ApacheErrorLogParser(lastRecordProcessedTime, pathApacheErrorLogFile);

        ArrayList<String> records = aelp.getNewRecords();

        String emailMessage = "";

        for (String line : records){
            emailMessage += line + "<br>\n";
        }

        Mailer.sendemail(email,"apacheErrorAbto",emailMessage,"admin@abto2.ru");

        String newLastTime;

        if (!records.isEmpty()){
            newLastTime = aelp.getDateTimeFromLine(records.get(records.size() - 1));
            Writer.writeMessageToFile(pathDataFile,newLastTime,false);
        }

        Logger.writeLog("Program finishs\n");

    }

    private static void createDataFileIfNotExists(String path) {
        File f = new File(path);
        if (!f.isFile()){
            try{
                f.createNewFile();
                Logger.writeLog("created app's Data file: " + path);
            }
            catch (IOException e){
                System.out.print("Can not create new file: " + path + "\n");
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private static String getLastRecordProcessTimeFromFile(String path) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
        if (lines.isEmpty()){
            return "";
        }
        return lines.get(0);
    }
}