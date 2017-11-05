package com.androidvezbe.ftn.externalstoragefile;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME="testfile.txt";
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText)findViewById(R.id.editText);
    }

    public boolean isExternalStorageWritable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    public void writeFile(View view) {
        if (isExternalStorageWritable()) {
            try {
                File textFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
                FileOutputStream fileOutputStream = new FileOutputStream(textFile);
                fileOutputStream.write(mEditText.getText().toString().getBytes());
                fileOutputStream.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error writing file",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Cannot write to External Storage", Toast.LENGTH_LONG).show();
        }
    }

    public void readFile(View view) {
        if (isExternalStorageReadable()) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                //Pristup standardnim direktorijumima
                //File textFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), FILENAME);
                File textFile = new File(Environment.getExternalStorageDirectory(), FILENAME);

                //primer koriscenja getFreeSpace() metode
                //Toast.makeText(this, "Available Space: " + Environment.getExternalStorageDirectory().getFreeSpace(),Toast.LENGTH_SHORT).show();

                //prakticni primer upotrebe getFreeSpace() metode.  Potrebno definisati: RQUIRED_FILE_SPACE
                //if (Environment.getExternalStorageDirectory().getFreeSpace() < RQUIRED_FILE_SPACE) {
                //    //dovoljno memorije
                //} else {
                //    //nedovoljno memorije
                //}
                FileInputStream fileInputStream = new FileInputStream(textFile);
                if (fileInputStream != null ) {
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String newLine = null;
                    while ( (newLine = bufferedReader.readLine()) != null ) {
                        stringBuilder.append(newLine+"\n");
                    }
                    fileInputStream.close();
                }
                mEditText.setText(stringBuilder);
            } catch (java.io.IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading file",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Cannot read External Storage", Toast.LENGTH_LONG).show();
        }
    }
}
