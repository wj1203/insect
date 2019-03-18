package com.example.yangyang.insect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.yangyang.insect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        textView = findViewById(R.id.text);
        String s = "";
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("result"));
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i <1;i++ ){
                JSONObject obj = (JSONObject) jsonArray.get(i);
                s = s+obj.getString("name")+"\n";
            }
            textView.setText(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
