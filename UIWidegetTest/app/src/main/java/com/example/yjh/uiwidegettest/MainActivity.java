package com.example.yjh.uiwidegettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private static final int EXIT_TIME = 2000;
    private long firstExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Button button5 = (Button) findViewById(R.id.button_5);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar1 = (ProgressBar) findViewById(R.id.progress_bar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progress_bar2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                String inputText = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputText,
                        Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
                break;
            case R.id.button_2:
                /*
                 * 通过setVisibility()来设置控件的可见属性
                 * 通过getVisibility()来获取控件的可见属性
                 */
                if (progressBar1.getVisibility() == View.GONE) {
                    progressBar1.setVisibility(View.VISIBLE);
                } else {
                    progressBar1.setVisibility(View.GONE);
                }
                break;
            case R.id.button_3:
                if (progressBar2.getProgress() < progressBar2.getMax()) {
                    int progress = progressBar2.getProgress();
                    progress += 1;
                    progressBar2.setProgress(progress);
                } else {
                    progressBar2.setVisibility(View.GONE);
                }
                break;
            case R.id.button_4:
//                新建一个AlertDialog实例，AlertDialog可以在当前界面弹出一个对话框，置于所有界面元素之上
//                因能屏蔽掉其他所有控件的交互能力，因此一般用于提示把一些重要内容或警告信息
//                如为了防止用户误删重要内容，在删除前弹出一个确认对话框
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                设置标题
                dialog.setTitle("This is a dialog");
//                设置提示信息
                dialog.setMessage("Something important.");
//                设置Back键是否可用
                dialog.setCancelable(false);
//                设置确认按钮的点击事件
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
//                设置取消按钮的点击事件
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
//                显示对话框
                dialog.show();
                break;
            case R.id.button_5:
//                创建一个ProgressDialog实例
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//                设置标题
                progressDialog.setTitle("This is a ProgressDialog");
//                设置提示信息
                progressDialog.setMessage("Loading...");
//                设置Back键是否可用
//                若设置为false,在数据加载完之后一定要
                progressDialog.setCancelable(true);
//                显示对话框
                progressDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        if (curTime - firstExitTime < EXIT_TIME) {
            finish();
        } else {
            Toast.makeText(MainActivity.this, "请再按下一次返回键退出",
                    Toast.LENGTH_SHORT).show();
            firstExitTime = curTime;
        }
    }
}
