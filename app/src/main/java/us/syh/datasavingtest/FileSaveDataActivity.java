package us.syh.datasavingtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileSaveDataActivity extends AppCompatActivity {

    private TextView textview_file_console;
    //读写权限申请
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save_data);
        this.setTitle("文件保存数据");

        textview_file_console=findViewById(R.id.textView_file_console);

        //文件被写入app内部储存
        final String filename="data.txt";
        findViewById(R.id.button_file_write_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                String string="From Internal:\n"+"Hello world，"+sdf.format(date)+"\n";
                //在内部目录创建文件
                //File file=new File(getApplicationContext().getFilesDir(),filename);
                FileOutputStream outputStream;
                try{
                    outputStream=openFileOutput(filename, Context.MODE_PRIVATE);//MODE类型(!)[append:追加,private:覆盖写入]
                    outputStream.write(string.getBytes());
                    outputStream.close();
                    Toast.makeText(FileSaveDataActivity.this,"From Internal:\n"+"写入完成",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.button_file_read_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream inputStream;
                try{
                    inputStream=openFileInput(filename);
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    byte[] buffer=new byte[1024];
                    int length=-1;
                    while((length=inputStream.read(buffer))!=-1){
                        stream.write(buffer,0,length);
                    }
                    stream.close();
                    inputStream.close();
                    textview_file_console.setText(stream.toString());
                    Toast.makeText(FileSaveDataActivity.this,"From Internal:\n"+"读取完成",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.button_file_write_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions(FileSaveDataActivity.this);
                if(isExternalStorageWritable()){
                    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                    sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                    Date date = new Date();// 获取当前时间
                    String string="From External:\n"+"Hello world，"+sdf.format(date)+"\n";
                    File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/DataSavingTest/data.txt");
                    try{
                        //保存在手机储存DataSavingTest目录下
                        if(!file.exists()){
                            File dir=new File(file.getParent());
                            dir.mkdirs();
                            file.createNewFile();
                        }
                        FileOutputStream outputStream=new FileOutputStream(file);
                        outputStream.write(string.getBytes());
                        outputStream.close();
                        Toast.makeText(FileSaveDataActivity.this,"From External:\n"+"写入完成",Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        findViewById(R.id.button_file_read_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions(FileSaveDataActivity.this);
                if(isExternalStorageReadable()){
                    try{
                        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/DataSavingTest/data.txt");
                        FileInputStream inputStream=new FileInputStream(file);
                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
                        byte[] buffer=new byte[1024];
                        int length=-1;
                        while((length=inputStream.read(buffer))!=-1){
                            stream.write(buffer,0,length);
                        }
                        stream.close();
                        inputStream.close();
                        textview_file_console.setText(stream.toString());
                        Toast.makeText(FileSaveDataActivity.this,"From External:\n"+"读取完成",Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == REQUEST_EXTERNAL_STORAGE){
            for(int i=0;i<permissions.length;i++){
                Toast.makeText(FileSaveDataActivity.this,permissions[i]+",申请通过!",Toast.LENGTH_LONG).show();
            }
        }
    }
    //验证储存可用
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
