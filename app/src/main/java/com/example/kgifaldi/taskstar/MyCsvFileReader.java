package com.example.kgifaldi.taskstar;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MaggieThomann on 3/5/17.
 */

public class MyCsvFileReader {
    Context context;
    public MyCsvFileReader(Context applicationContext) {
        this.context = applicationContext;
    }

    public ArrayList<String[]> readCsvFile(int fileresource) {
        Log.d("CSVFileReader", "Entered read by comma");
        ArrayList<String[]> object = new ArrayList<>();
        InputStream fin = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            fin = context.getResources().openRawResource(fileresource);
            isr = new InputStreamReader(fin);
            reader = new BufferedReader(isr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                Log.d("CSVFileReader", "Reading");
                String[] objectInfo = line.split(",");
                Log.d("CSVFileReader", objectInfo[0]);
                object.add(objectInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fin != null)
                    fin.close();
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        return object;
    }

    public ArrayList<String[]> readCsvFileBySemiColon(int fileresource) {
        Log.d("CSVFileReader", "Entered read by semicolon");
        ArrayList<String[]> object = new ArrayList<>();
        InputStream fin = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            fin = context.getResources().openRawResource(fileresource);
            isr = new InputStreamReader(fin);
            reader = new BufferedReader(isr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                Log.d("CSVFileReader", "Reading file");
                String[] objectInfo = line.split(";");
                object.add(objectInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fin != null)
                    fin.close();
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        return object;
    }
}
