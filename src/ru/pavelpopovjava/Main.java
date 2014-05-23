package ru.pavelpopovjava;

public class Main {

    public static void main(String[] args) {

        //TODO Проверка на кол-во параметров

        String pathApacheErrorLogFile = args[0];    //Файл лога ошибок апача
        String email = args[1];                     //Емэил куда слать ошибки
        String pathDataFile = args[2];              //Файл с внутренними данными данной программы
        String pathLogsDir = args[3];               //Директория для логов выполнения данной программы

        //TODO сделать проверки на входные параметры

        Logger.setLogDir(pathLogsDir);

        Logger.writeLog("Program starts");

        

        Logger.writeLog("Program finishs\n");

    }
}