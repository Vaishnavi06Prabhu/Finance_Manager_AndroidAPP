package com.example.moneystatic;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity {
    DBHelperUsers DB;
    Button getList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);


        getList=findViewById(R.id.testUserViewPage);
        DB=new DBHelperUsers(this);
        // Assuming you have a ListView in your layout
        ListView listView = findViewById(R.id.allUserviewList);


        getList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.Getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(AllUsersActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create ArrayList to store data
                ArrayList<String> dataList = new ArrayList<>();
                while (res.moveToNext()) {
                    String entry ="Id: " + res.getString(0) + "\n" +
                            "Name: " + res.getString(1) + "\n" +
                            "Email: " + res.getString(2) + "\n" +
                            "Password: " + res.getString(3);
                    dataList.add(entry);
                }

                // Create ArrayAdapter and set it to the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AllUsersActivity.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);

                // Optionally, set a click listener on the ListView items
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Handle item click if needed
                    }
                });
            }
        });

    }


}