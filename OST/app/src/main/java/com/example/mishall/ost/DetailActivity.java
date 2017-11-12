package com.example.mishall.ost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mishall.ost.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("Value");
        TextView tvName = (TextView)findViewById(R.id.text);
        tvName.setText(value1);
    }
}