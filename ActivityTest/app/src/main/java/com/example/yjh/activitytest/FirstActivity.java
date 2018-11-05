package com.example.yjh.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
 *在安卓编程中，到底Context（上下文）类是什么，有什么用？
 *简单地说，是当前程序/对象的现在的状态。Context让新建的对象知道到底是怎么一回事。
 *你可以通过context获取关于你程序的另外一部分（activity和application）的信息。
 *你可以通过调用以下的方法来获得context。
 * getApplicationContext（）
 * getContext()
 * getBaseContext()
 * this (在activity类)
 */
public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给活动设置一个布局
        setContentView(R.layout.first_layout);
        //该方法用于获取在布局文件中定义的元素，返回一个view对象
        Button button1 = (Button) findViewById(R.id.button_1);
        //该方法用于注册一个监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //该静态方法用于创建一个Toast对象，然后调用show()方法将Toast显示出来
                //第一个参数是Context，即Toast要求的上下文
                //活动本身就是一个Context对象，因此这里直接传入FirstActivity.this
                //第二个参数是Toast显示的文本内容
                //第三个参数是Toast显示的时长
                //有两个内置常量可以选择：Toast.LENGTH_SHORTge Toast.LENGTH_LONG
                /*Toast.makeText(FirstActivity.this, "You clicked Button_1",
                        Toast.LENGTH_SHORT).show();*/
                /*Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);*/
                SecondActivity.actionActivity(FirstActivity.this, "data1", "data2");
//                finish();
            }
        });
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "这是测试电话的按钮",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "这是显式Intent的按钮",
                        Toast.LENGTH_SHORT).show();
                //创建一个Intent对象
                //“意图”在FirstActivity的基础上打开SecondActivity
                //显式Intent
                String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //该方法用于启动活动，执行这个Intent
                //第一个参数是键，用于后面从Intent中取值
                //第二个参数是要传递的数据
                intent.putExtra("extra_data", data);
                startActivity(intent);
                /*Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);*/
            }
        });
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(FirstActivity.this, "这是隐式Intent的按钮",
                        Toast.LENGTH_SHORT).show();
                //隐式Intent
                Intent intent = new Intent("com.example.activity.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);*/
                Intent intent = new Intent(FirstActivity.this, ForthActivity.class);
                Bundle data = new Bundle();
                data.putString("data_key", "yjh728");
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "这是测试打开网页的按钮",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
    }

    //第一个参数是requestCode，即在活动启动时传入的请求码
    //第二个参数是resultCode，即在返回数据的时候传入的处理结果
    //第三个参数是data，即携带着返回数据的Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    Log.d(TAG, returnData);
                }
                break;
            default:
        }
    }

    //创建菜单并显示
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //用于得到MenuInflater对象，然后调用inflate()创建菜单
        //inflate()方法有两个参数：第一个用于指定通过哪一个资源文件来创建菜单
        //第二个参数用于指定菜单项将添加到哪一个Menu对象中
        //返回true表示语序创建的菜单显示出来。false表示后才能关键的菜单无法显示
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //定义菜单响应事件(让菜单可用)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private static final int EXIT_TIME = 2000;  //两次按返回键的间隔判断
    private long firstExitTime = 0L;    //用来保存第一次按返回键的时间
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();  //获取当前系统时间——单位为毫秒
        if (curTime-firstExitTime<EXIT_TIME) {
            finish();
        } else {
            Toast.makeText(FirstActivity.this, "请再按下一次返回键退出",
                    Toast.LENGTH_SHORT).show();
            firstExitTime = curTime;
        }
    }
}
