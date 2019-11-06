package us.syh.datasavingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save_data);
        this.setTitle("文件保存数据");

        textview_file_console=findViewById(R.id.textView_file_console);

        //文件被写入app内部储存
        final String filename="data.txt";
        findViewById(R.id.button_file_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                String string="Hello world，"+sdf.format(date)+"\n";
                //在内部目录创建文件
                //File file=new File(getApplicationContext().getFilesDir(),filename);
                FileOutputStream outputStream;
                try{
                    outputStream=openFileOutput(filename, Context.MODE_PRIVATE);//MODE类型(!)[append:追加,private:覆盖写入]
                    outputStream.write(string.getBytes());
                    outputStream.close();
                    Toast.makeText(FileSaveDataActivity.this,"写入完成",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.button_file_read).setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(FileSaveDataActivity.this,"读取完成",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
