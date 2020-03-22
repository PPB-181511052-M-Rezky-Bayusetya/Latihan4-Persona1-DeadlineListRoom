package com.rezkyb.deadlinelist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddDeadlineActivity extends AppCompatActivity {

    EditText deadlineName;
    EditText deadlineDetail;
    EditText deadlineDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deadline);

        deadlineName = (EditText)findViewById(R.id.deadlineNameForm);
        deadlineDetail = (EditText)findViewById(R.id.deadlineDetailForm);
        deadlineDate = (EditText)findViewById(R.id.deadlineDateForm);

        Button button1 = findViewById(R.id.insertButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                String Name = deadlineName.getText().toString();
                String Detail = deadlineDetail.getText().toString();
                String Date = deadlineDate.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("deadlineName",Name);
                bundle.putString("deadlineDetail",Detail);
                bundle.putString("deadlineDate",Date);

                i.putExtras(bundle);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
