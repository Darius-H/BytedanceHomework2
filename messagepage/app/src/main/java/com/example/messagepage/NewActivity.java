package com.example.messagepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent i=getIntent();
        //if(i==null)Toast.makeText(NewActivity.this, "按了第" + 10000 + "条", Toast.LENGTH_SHORT).show();;

        int position=i.getIntExtra("num",-1);
        //int position=databuffer.dt;
        TextView tv=findViewById(R.id.textView);
        tv.setText("按了第" + position + "条");
    }

}