package us.syh.datasavingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;

import java.io.File;

public class FileSaveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save_data);
        String filename="data.txt";
        File file=new File(getApplicationContext().getFilesDir(),filename);
        //KeyGenParameterSpec keyGenParameterSpec=MasterKeys.;
    }
}
