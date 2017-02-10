package com.aigestudio.wheelpicker.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ppoya on 2017-02-05.
 */
public class ContentActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        String item = intent.getExtras().getString("item");

        TextView selectedItem=(TextView)findViewById(R.id.selected_item);
        selectedItem.setText(item);
    }
}
