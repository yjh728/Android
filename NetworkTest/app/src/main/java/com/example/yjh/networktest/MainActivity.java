package com.example.yjh.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private TextView textView;

    private Button send_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_reponse_text);
        send_request = findViewById(R.id.btn_send_message);
        send_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_message:
//                sendRequestWithOkHttp();
                HttpUtil.sendOkHttpRequest("http://10.0.2.2:728/get_data.json", new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseJSONWithGSON(responseData);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:728/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                    showResponse(responseData);
//                    parseXMLWhitPull(responseData);
//                    parseXMLWhitSAX(responseData);
//                    parseJSONWithJSONObiect(responseData);
                    parseJSONWithGSON(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        List<App> list = gson.fromJson(responseData, new TypeToken<List<App>>() {
        }.getType());
        for (App app : list) {
            Log.d(TAG, "id is: " + app.getId());
            Log.d(TAG, "name is: " + app.getName());
            Log.d(TAG, "version is: " + app.getVersion());
        }
    }

    private void parseJSONWithJSONObiect(String responseData) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d(TAG, "id is: " + id);
                Log.d(TAG, "name is: " + name);
                Log.d(TAG, "version is: " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWhitSAX(String responseData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader reader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(new StringReader(responseData)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWhitPull(String s) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(s));
            int enventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (enventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (enventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.d(TAG, "id is: " + id);
                            Log.d(TAG, "name is: " + name);
                            Log.d(TAG, "version is: " + version);
                        }
                        break;
                    default:
                        break;
                }
                enventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showResponse(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(s);
            }
        });
    }
}
