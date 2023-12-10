package com.example.moneystatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingExpenceOrIncomeActivity extends AppCompatActivity {
    Button SavingsBtn,DepositBtn,SalaryBtn,OtherIncomeSourceBtn;
    EditText Amount,OtherIncomeSourceTXT;
    DBHelper DB;
   String userIDString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_expence_or_income);
        Amount=findViewById(R.id.BalanceTextview);
        OtherIncomeSourceTXT=findViewById(R.id.OtherEdit);
        DepositBtn=findViewById(R.id.depostiButton);
        SavingsBtn=findViewById(R.id.savingButton);
        SalaryBtn=findViewById(R.id.salaryButton);
        OtherIncomeSourceBtn=findViewById(R.id.OtherSourceBtn);

        DB = new DBHelper(this);

        //// Initialize your DBHelp
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("KEY_USERID")) {
            String receivedValue = intent.getStringExtra("KEY_USERID");

            userIDString=receivedValue;;// 0 is the default value if the key is not found

        }


        //
        DepositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Income";
                String category="Deposits";
                String userId="1";
                if(amount.isEmpty())
                {
                    Toast.makeText(AddingExpenceOrIncomeActivity.this, ":", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);


                    if (checkinsertdata == true) {
                        //Home Page Navigation
                        navigateToHomePage();
                        //New Entry Inserted
                        Toast.makeText(AddingExpenceOrIncomeActivity.this, category+"\t"+":\t$"+amount+"\t"+"added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddingExpenceOrIncomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //
        SalaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Income";
                String category="Salary";
                String userId=userIDString;

                if(amount.isEmpty()||amount=="0")
                {
                    Toast.makeText(AddingExpenceOrIncomeActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkinsertdata = DB.insertuserdata(amount, Type, category,userId);


                    if (checkinsertdata == true) {
                        //Home Page Navigation
                        navigateToHomePage();
                        //New Entry Inserted
                        Toast.makeText(AddingExpenceOrIncomeActivity.this, category+"\t"+":\t$"+amount+"\t"+"added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddingExpenceOrIncomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //Inseet Saving

        SavingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount=Amount.getText().toString();
                 amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type="Income";
                String category="Savings";
                String userId=userIDString;


if(amount.isEmpty()||amount=="0")
{
    Toast.makeText(AddingExpenceOrIncomeActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
}else
                {
                    Boolean checkinsertdata=DB.insertuserdata(amount,Type,category,userId);
                    if (checkinsertdata == true) {
                        //Home Page Navigation
                        navigateToHomePage();
                        //New Entry Inserted
                        Toast.makeText(AddingExpenceOrIncomeActivity.this, category+"\t"+":\t$"+amount+"\t"+"added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddingExpenceOrIncomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
//Other
        OtherIncomeSourceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = Amount.getText().toString();
                amount= String.valueOf(amount).replaceFirst("^0+(?!$)", "");
                String Type = "Income";
                String userId=userIDString;
                //OtherSource
                String category = OtherIncomeSourceTXT.getText().toString();

                if(amount.isEmpty()||amount=="0")
                {
                    Toast.makeText(AddingExpenceOrIncomeActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else if(category.isEmpty())
                {
                    Toast.makeText(AddingExpenceOrIncomeActivity.this, "Please Enter Category", Toast.LENGTH_SHORT).show();
                }else
                {
                    Boolean checkinsertdata=DB.insertuserdata(amount,Type,category,userId);

                if (checkinsertdata == true) {
                    //Home Page Navigation
                    navigateToHomePage();
                    //New Entry Inserted
                    Toast.makeText(AddingExpenceOrIncomeActivity.this, category+"\t"+":\t$"+amount+"\t"+"added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddingExpenceOrIncomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }

            }
        });

    }
    //
    @Override
    public void onBackPressed() {
        // Add your custom logic here
        // For example, show a toast message
        Toast.makeText(this, "Back button pressed!", Toast.LENGTH_SHORT).show();
// Start the second activity
//        Intent intent = new Intent(AddingExpenceOrIncomeActivity.this, HomePageActivity.class);
        Intent intent = new Intent(AddingExpenceOrIncomeActivity.this, Drawer_nav_Activity.class);
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
        Intent intent = new Intent(AddingExpenceOrIncomeActivity.this, Drawer_nav_Activity.class);
        intent.putExtra("UserID", userIDString);
        startActivity(intent);
        startActivityForResult(intent, 1);
        // If you want to keep the default behavior (going back in the navigation stack), call super.onBackPressed()
        super.onBackPressed();
    }

}