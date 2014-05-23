package ru.pavelpopovjava;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Logger {

    static private String logDir = "";

    public static void setLogDir(String logDir) {
        Logger.logDir = logDir;
    }

    public static void writeLog(String message)
    {

        if (logDir == ""){
            throw new RuntimeException("ERROR: log dir has not been set");
        }

        Date currentDate = new Date();

        String fullMessage = currentDate.toString() + ": " + message + "\n";

        //Будем на каждый день создавать отдельный файл для логов данной программы.
        //Имя файла: YYYY.mm.dd.log

        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String selfLogFilePath = logDir + String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day) + ".log";

        createDirIfNotExist(logDir);


        try{
            writeMessageToFile(selfLogFilePath, fullMessage);
        }
        catch (IOException e){
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException();
        }

    }

    private static void createDirIfNotExist(String dir) {

        File theDir = new File(dir);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            if (theDir.mkdir()){
                throw new RuntimeException("Не удалось создать директорию " + dir);
            }
        }
    }

    private static void writeMessageToFile(String path, String message) throws IOException
    {
        BufferedWriter out = null;

        FileWriter fstream = new FileWriter(path, true); //true tells to append data.
        out = new BufferedWriter(fstream);
        out.write(message);
        out.close();

    }
}
