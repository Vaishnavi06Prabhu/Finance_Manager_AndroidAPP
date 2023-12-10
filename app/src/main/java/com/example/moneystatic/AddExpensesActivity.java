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

import java.util.ArrayList;

public class AddExpensesActivity extends AppCompatActivity {
    Button HealthBtn,BillBtn,ClothesBtn,HouseBtn,FoodBtn,GiftBtn,OtherExpensBtn;
    EditText Amount,OtherExpensTXT;
    DBHelper DB;
    String userIDString;
    //
    ArrayList<String> Incomes = new ArrayList<>();
    ArrayList<String> Expenceses = new ArrayList<>();
    float sum = 0;
    float expens = 0;
    float result=0;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        //Inseet Saving
        Amount=findViewById(R.id.BalanceTextview_Expn);
        OtherExpensTXT=findViewById(R.id.OtherExpensEdit);
        HealthBtn=findViewById(R.id.healthBtn);
        BillBtn=findViewById(R.id.BillBtn);
        ClothesBtn=findViewById(R.id.clothesBtn);
        HouseBtn=findViewById(R.id.houseBtin);
        FoodBtn=findViewById(R.id.foodBtn);
        GiftBtn=findViewById(R.id.giftBtin);
        OtherExpensBtn=findViewById(R.id.OtherExpensBtn);
        DB = new DBHelper(this);  // Initialize your DBHelp
//
        // Assuming you have a ListView in your layout
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("KEY_USERID")) {
            String receivedValue = intent.getStringExtra("KEY_USERID");

            userIDString=receivedValue;;// 0 is the default value if the key is not found

        }
        displayDataInListView();
        ///
        HealthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Expense";
                String category="Health";
                String userId=userIDString;
                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else {
                    if (result > Float.parseFloat(amount)) {
                        Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);
                        if (checkinsertdata == true) {
                            //Home Page Navigation
                            navigateToHomePage();
                            //New Entry Inserted
                            Toast.makeText(AddExpensesActivity.this, category + "\t" + ":\t$" + amount + "\t" + "added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddExpensesActivity.this, "Not enough  Money!!!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //Bill
        BillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Expense";
                String category="Bill";
                String userId=userIDString;
                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else {

                    if (result > Float.parseFloat(amount)) {
                        Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);
                        if (checkinsertdata == true) {
                            //Home Page Navigation
                            navigateToHomePage();
                            // New Entry Inserted
                            //New Entry Inserted
                            Toast.makeText(AddExpensesActivity.this, category + "\t" + ":\t$" + amount + "\t" + "added", Toast.LENGTH_SHORT).show();
                            ;
                        } else {
                            Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddExpensesActivity.this, "Not enough  Money!!!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        //
        ClothesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Expense";
                String category="Clothes";
                String userId=userIDString;
                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else {

                    if (result > Float.parseFloat(amount)) {
                        Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);
                        if (checkinsertdata == true) {
                            //Home Page Navigation
                            navigateToHomePage();
                            // New Entry Inserted
                            //New Entry Inserted
                            Toast.makeText(AddExpensesActivity.this, category + "\t" + ":\t$" + amount + "\t" + "added", Toast.LENGTH_SHORT).show();
//                        // Assuming 'category' is a variable representing the expense category,
//                        // and 'amount' is the expense amount
//
//                        String debitMessage = "Expense debited for " + category + ": $\t-" + amount;
//                        Toast.makeText(AddExpensesActivity.this, debitMessage, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddExpensesActivity.this, "Not enough  Money!!!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //Houes
        HouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type = "Expense";
                String category = "House";
                String userId=userIDString;
                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else
                {
                if (result > Float.parseFloat(amount)) {
                    Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);
                    if (checkinsertdata == true) {
                        //Home Page Navigation
                        navigateToHomePage();
                        // New Entry Inserted
                        //New Entry Inserted
                        Toast.makeText(AddExpensesActivity.this, category + "\t" + ":\t$" + amount + "\t" + "added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddExpensesActivity.this, "Not enough  Money!!!!", Toast.LENGTH_SHORT).show();
                }
            }


            }
        });
        //Food
        FoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Expense";
                String category="Food";
                String userId=userIDString;

                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else {
                    if (result > Float.parseFloat(amount)) {
                        Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);
                        if (checkinsertdata == true) {
                            //Home Page Navigation
                            navigateToHomePage();
                            // New Entry Inserted
                            //New Entry Inserted
                            Toast.makeText(AddExpensesActivity.this, category + "\t" + ":\t$" + amount + "\t" + "added", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddExpensesActivity.this, "Not enough  Money!!!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        //Gift
        GiftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Expense";
                String category="Gift";
                String userId=userIDString;
                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else {

                    if (result > Float.parseFloat(amount)) {
                        Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);
                        try{
                        if (checkinsertdata == true) {
                            //Home Page Navigation
                            navigateToHomePage();
                            // New Entry Inserted
                            // Assuming 'category' is a variable representing the expense category,
                            //New Entry Inserted
                            Toast.makeText(AddExpensesActivity.this, category+"\t"+":\t$"+amount+"\t"+"added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                        }catch (Exception e){

                            Toast.makeText(AddExpensesActivity.this, "Exception Error:\t"+e, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddExpensesActivity.this, "Not enough  Money!!!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //Other
        OtherExpensBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type = "Expense";
                String userId=userIDString;
                //OtherSource
                String category = OtherExpensTXT.getText().toString();

                if(amount.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else if(category.isEmpty())
                {
                    Toast.makeText(AddExpensesActivity.this, "Please Enter Category", Toast.LENGTH_SHORT).show();
                }else
                {
                    if (result > Float.parseFloat(amount)) {
                        Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);

                        if (checkinsertdata == true) {
                            //Home Page Navigation
                            navigateToHomePage();
                            //New Entry Inserted
                            Toast.makeText(AddExpensesActivity.this, category + "\t" + ":\t$" + amount + "\t" + "added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddExpensesActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
    //
    private void displayDataInListView() {

        // Retrieve data from the database
        Cursor res = DB.Getdata();
        if (res.getCount() == 0) {
            Toast.makeText(AddExpensesActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create ArrayList to store data
        ArrayList<String> dataList = new ArrayList<>();


        while (res.moveToNext()) {
            String id = res.getString(0);
            String incomeAmount = res.getString(1);
            String type = res.getString(2);
            String optional = res.getString(3);

            String entry =  /*"Type: " + */type + "\n" +
                    "Amount: " + incomeAmount + "\n" +
                    "Category: " + optional;

            dataList.add(entry);
            if ("Income".equals(type)) {
                Incomes.add(incomeAmount);
            }
            if ("Expense".equals(type)) {
                Expenceses.add(incomeAmount);
            }
        }
// Calculate sum for AmmountData
        for (String amount : Incomes) {
            sum += Float.parseFloat(amount);
        }

// Calculate sum for Expenceses
        for (String expense : Expenceses) {
            expens += Float.parseFloat(expense);
        }

         result = sum - expens;

// Display the result in a TextView
//        textView.setText(String.valueOf(result));



    }
    @Override
    public void onBackPressed() {
        // Add your custom logic here
        // For example, show a toast message
        Toast.makeText(this, "Back button pressed!", Toast.LENGTH_SHORT).show();
// Start the second activity
        //        Intent intent = new Intent(AddExpensesActivity.this, HomePageActivity.class);
        Intent intent = new Intent(AddExpensesActivity.this, Drawer_nav_Activity.class);
        intent.putExtra("UserID", userIDString);
        startActivity(intent);
        startActivityForResult(intent, 1);
        // If you want to keep the default behavior (going back in the navigation stack), call super.onBackPressed()
        super.onBackPressed();
    }
    void navigateToHomePage()
    {
        // Add your custom logic here
        // For example, show a toast message
//        Toast.makeText(this, "Back button pressed!", Toast.LENGTH_SHORT).show();
// Start the second activity
        //        Intent intent = new Intent(AddExpensesActivity.this, HomePageActivity.class);
        Intent intent = new Intent(AddExpensesActivity.this, Drawer_nav_Activity.class);
        intent.putExtra("UserID", userIDString);
        startActivity(intent);
        startActivityForResult(intent, 1);
        // If you want to keep the default behavior (going back in the navigation stack), call super.onBackPressed()
        super.onBackPressed();
    }
}