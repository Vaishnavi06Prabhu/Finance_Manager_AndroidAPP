package com.example.moneystatic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> dataList;
    private List<String> types; // List to store the type of each item
    int creamColor = getCreamColor();

    public CustomAdapter(Context context, List<String> dataList, List<String> types) {
        super(context, android.R.layout.simple_list_item_1, dataList);
        this.context = context;
        this.dataList = dataList;
        this.types = types;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        // Set different background colors based on the type
        if (position < types.size()) {
            String type = types.get(position);
            TextView textView = view.findViewById(android.R.id.text1);

            if ("Income".equals(type)) {
                view.setBackgroundColor(Color.WHITE);
                textView.setTextColor(creamColor); // Set text color for Income items to green
                textView.setTypeface(null, Typeface.BOLD); // Remove bold style
                setLayoutGravity(view, Gravity.START); // Align to the left
            } else if ("Expense".equals(type)) {
                view.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.RED);
                textView.setTypeface(null, Typeface.BOLD); // Set text color for Expense items to red and make it bold
                setLayoutGravity(view, Gravity.END); // Align to the right
            }

        }

        return view;
    }

    private void setLayoutGravity(View view, int gravity) {
        // Set the layout parameters to align the view to the specified gravity
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = gravity;
        view.setLayoutParams(params);
    }

    private int getCreamColor() {
        // Define your cream color (adjust alpha, red, green, and blue values as needed)
        return Color.rgb(0, 100, 0); // Cream color
    }
}
