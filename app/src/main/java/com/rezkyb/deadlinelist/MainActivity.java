package com.rezkyb.deadlinelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String NameFromIntent1;
    String DetailFromIntent1;
    String DateFromIntent1;

    DeadlineViewModel mDeadlineViewModel;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    MainFragment MainFragment;

    Intent data = new Intent("com.rezkyb.deadlinelist.AddDeadlineActivity");

    static ArrayList<Deadline> deadlineArrayList = new ArrayList<Deadline>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDeadlineViewModel = new ViewModelProvider(this).get(DeadlineViewModel.class);
        MainFragment = new MainFragment();

        fragmentTransaction.replace(
                android.R.id.content, MainFragment);

        fragmentTransaction.commit();
    }

    public void onDataDelete(Deadline deadline){
        mDeadlineViewModel.delete(deadline);
    }

    public void addDeadline(View view) {
        startActivityForResult(data,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                NameFromIntent1 = data.getStringExtra("deadlineName");
                DetailFromIntent1 = data.getStringExtra("deadlineDetail");
                DateFromIntent1 = data.getStringExtra("deadlineDate");

                Deadline deadlinefromIntent = new Deadline(NameFromIntent1,DetailFromIntent1,DateFromIntent1);
                mDeadlineViewModel.insert(deadlinefromIntent);
                Toast.makeText(this, "Insert To Database", Toast.LENGTH_LONG).show();
            }
        }
        MainFragment.adapter.notifyDataSetChanged();
        super.onActivityResult(requestCode,resultCode,data);
    }
}
