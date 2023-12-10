package com.example.moneystatic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Drawer_nav_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Button ExpenceBtn, IncomeBtn;
    EditText emailEditText, passwordEditText;
    Button insert, update, delete, testing;
    EditText Savings,email;
    ArrayList<String> Incomes = new ArrayList<>();
    ArrayList<String> Expenceses = new ArrayList<>();
    float sum = 0;
    float expens = 0;
    TextView textView;
    DBHelper DB;
    String emailValue,FromIncome ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_nav);
//
        // Retrieve the data from the Intent

        //
        DB = new DBHelper(this);  // Initialize your DBHelp
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //NoTIle
        getSupportActionBar().setTitle("");
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0); // Assumes the header is the first item
        TextView textViewEmail = headerView.findViewById(R.id.textViewEmail);

// Get the email value (replace "your_email@example.com" with your actual value)

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("UserID")) {
            String receivedValue = intent.getStringExtra("UserID");

            emailValue=receivedValue;;// 0 is the default value if the key is not found

        }

// Set the email value to the TextView
//        textViewEmail.setText(emailValue);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


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

                Intent intent = new Intent(Drawer_nav_Activity.this, AddingExpenceOrIncomeActivity.class);

                intent.putExtra("KEY_USERID", emailValue);

                startActivity(intent);
            }
        });
        //Expence
        ExpenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click on the "Forgot Password" button
                // For example, you might navigate to a ForgotPasswordActivity
                Intent intent = new Intent(Drawer_nav_Activity.this, AddExpensesActivity.class);



                intent.putExtra("KEY_USERID", emailValue);
                startActivity(intent);
            }
        });
//        View List
        // Assuming you have a ListView in your layout
        ListView listView = findViewById(R.id.getDatalistView);
        displayDataInListView(listView);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//         Handle navigation item clicks here
//        Toast.makeText(Drawer_nav_Activity.this, "Case 1 Pressed", Toast.LENGTH_SHORT).show();
        String itemId = item.getTitle().toString();

//      Toast.makeText(Drawer_nav_Activity.this, "item:"+itemId, Toast.LENGTH_SHORT).show();
//        int logout=2131231238;
//        openSettingsPage();
        switch (itemId) {
            case "My Account":
                // Handle Item 1 click
                Toast.makeText(Drawer_nav_Activity.this, "My Account Pressed", Toast.LENGTH_SHORT).show();
                break;
            case "Settings":
                // Handle Settings click
                Toast.makeText(Drawer_nav_Activity.this, "Settings Pressed", Toast.LENGTH_SHORT).show();
                break;
            case "Logout":
                // Handle Settings click
                openSettingsPage();
                break;
            default:
                // Handle other cases if needed
                break;
        }


        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }






    private void openSettingsPage() {
        // Start the LoginActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // Finish the current activity
        finish();
    }

    private void displayDataInListView(ListView listView) {
        // Retrieve data from the database
//        Cursor res = DB.Getdata();
        Cursor res = DB.getDataByUserId(emailValue);
        if (res.getCount() == 0) {
            Toast.makeText(Drawer_nav_Activity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create ArrayList to store data
        ArrayList<String> dataList = new ArrayList<>();
        List<String> types = new ArrayList<>(); // Add this line to create the types list

        while (res.moveToNext()) {
            String id = res.getString(0);
            String incomeAmount = res.getString(1);
            String type = res.getString(2);

            String category = res.getString(3);
            String userId = res.getString(4);
            String entry = type + "\n" +
                    "Amount: "+"$"+ incomeAmount + "\n" +
                    "Category: " + category + "\n";
//                    "UserId:" + userId;

            dataList.add(entry);
            types.add(type); // Add this line to populate the types list

            if ("Income".equals(type)) {
                Incomes.add(incomeAmount);
            }
            if ("Expense".equals(type)) {
                Expenceses.add(incomeAmount);
            }
        }
// Assuming you want to get the name from the first row

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
        CustomAdapter adapter = new CustomAdapter(Drawer_nav_Activity.this, dataList, types);
        listView.setAdapter(adapter);

        // Optionally, set a click listener on the ListView items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click if needed
            }
        });
    }
}