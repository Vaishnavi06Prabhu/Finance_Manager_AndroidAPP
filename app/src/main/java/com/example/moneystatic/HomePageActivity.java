
package com.example.moneystatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;




import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    Button ExpenceBtn,IncomeBtn;
    EditText emailEditText, passwordEditText;
    Button insert,update,delete,testing;
    EditText Savings;
    ArrayList<String> Incomes = new ArrayList<>();
    ArrayList<String> Expenceses = new ArrayList<>();
    float sum = 0;
    float expens = 0;
    TextView textView;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Assuming you have a ListView in your layout
//



        //

        DB = new DBHelper(this);  // Initialize your DBHelp

        //
        IncomeBtn = findViewById(R.id.incomeButton);
        ExpenceBtn = findViewById(R.id.expenseButton);
//        testing = findViewById(R.id.Testing);
         textView = findViewById(R.id.BalanceTextview);
        // Adding OnClickListener for the "Forgot Password" button
        IncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click on the "Forgot Password" button
                // For example, you might navigate to a ForgotPasswordActivity
                Intent intent = new Intent(HomePageActivity.this, AddingExpenceOrIncomeActivity.class);
                startActivity(intent);
            }
        });
//Expence
        ExpenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click on the "Forgot Password" button
                // For example, you might navigate to a ForgotPasswordActivity
                Intent intent = new Intent(HomePageActivity.this, AddExpensesActivity.class);
                startActivity(intent);
            }
        });
//        View List
        // Assuming you have a ListView in your layout
        ListView listView = findViewById(R.id.getDatalistView);
        displayDataInListView(listView);
//        testing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                // Now you can use the value of AmmountData[0]
//                if (Incomes.size() > 0) {
//                    Toast.makeText(HomePageActivity.this, "Value: " + Incomes.get(0)+sum, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(HomePageActivity.this, "AmmountData is empty", Toast.LENGTH_SHORT).show();
//                }
//                try {
//                    displayDataInListView(listView);
//                } catch (Exception e) {
//                    Toast.makeText(HomePageActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();  // Print the exception stack trace for debugging
//                }
//
//
//            }
//        });

    }


    private void displayDataInListView(ListView listView) {
        // Retrieve data from the database
        Cursor res = DB.Getdata();
        if (res.getCount() == 0) {
            Toast.makeText(HomePageActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create ArrayList to store data
        ArrayList<String> dataList = new ArrayList<>();
        List<String> types = new ArrayList<>(); // Add this line to create the types list

        while (res.moveToNext()) {
            String id = res.getString(0);
            String incomeAmount = res.getString(1);
            String type = res.getString(2);
            String optional = res.getString(3);

            String entry = type + "\n" +
                    "Amount: "+"$"+ incomeAmount + "\n" +
                    "Category: " + optional;

            dataList.add(entry);
            types.add(type); // Add this line to populate the types list

            if ("Income".equals(type)) {
                Incomes.add(incomeAmount);
            }
            if ("Expense".equals(type)) {
                Expenceses.add(incomeAmount);
            }
        }

        // Calculate sum for Incomes and Expenceses (assuming these lists are already defined)
        for (String amount : Incomes) {
            sum += Float.parseFloat(amount);

        }

        for (String expense : Expenceses) {
            expens += Float.parseFloat(expense);
        }

        float result = sum - expens;

        // Display the result in a TextView
        String formattedResult = String.format("$%.2f", result);

// Set the formatted result to your textView
        textView.setText(formattedResult);

        // Create custom adapter and set it to the ListView
        CustomAdapter adapter = new CustomAdapter(HomePageActivity.this, dataList, types);
        listView.setAdapter(adapter);

        // Optionally, set a click listener on the ListView items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click if needed
            }
        });
    }

//Display or get Data

//Custome


}