package com.example.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG="mytag";
    public int CountViewNum(View root) {
        int viewCount = 0;
        if (root == null) {
            Log.i(TAG, "CountViewNum:null"+viewCount);
            return viewCount;
        }
        if ( root instanceof ViewGroup) {
            viewCount++;
            Log.i(TAG, "CountViewNum:not null"+viewCount);
            for (int i = 0; i < ((ViewGroup) root).getChildCount(); i++) {
                View view = ((ViewGroup) root).getChildAt(i);
                if (view instanceof ViewGroup) {
                    viewCount += CountViewNum(view);
                } else {
                    viewCount++;
                }
            }
        }
        Log.i(TAG, "CountViewNum:return"+viewCount);
        return viewCount;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View vw= this.getWindow().getDecorView();
        int num=CountViewNum(vw);
        Toast.makeText(MainActivity.this, "view 数量为 "+num, Toast.LENGTH_SHORT).show();
    }
}