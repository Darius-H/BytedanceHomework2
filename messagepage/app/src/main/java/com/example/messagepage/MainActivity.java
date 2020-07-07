package com.example.messagepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.messagepage.recycler.LinearItemDecoration;
import com.example.messagepage.recycler.MyAdapter;
import com.example.messagepage.recycler.TestData;
import com.example.messagepage.recycler.TestDataSet;

public class MainActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
     //   gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(3000);
//        recyclerView.setItemAnimator(animator);
    }

    public void onItemCLick(int position, TestData data) {
        Toast.makeText(MainActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("num",position);
        startActivity(intent);
        //mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
    }

    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(MainActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        //databuffer.dt=position;
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("num",position);
        startActivity(intent);
        //mAdapter.removeData(position);
    }
}