package ru.pavelpopovjava;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static void writeMessageToFile(String path, String message, boolean append) throws IOException
    {
        BufferedWriter out = null;
        FileWriter fstream = new FileWriter(path, append); //true tells to append data.
        out = new BufferedWriter(fstream);
        out.write(message);
        out.close();
    }

}
