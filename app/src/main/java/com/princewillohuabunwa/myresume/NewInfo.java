package com.princewillohuabunwa.myresume;

/**
 * Created by princewillohuabunwa on 15-07-06.
 */
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;


public class NewInfo extends Activity{

    // The EditText objects

    EditText infoType;
    EditText name;
    EditText details;
    EditText pit;

    DBTools dbTools = new DBTools(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Get saved data if there is any

        super.onCreate(savedInstanceState);

        // Designate that add_new_info.xml is the interface used

        setContentView(R.layout.add_new_info);

        // Initialize the EditText objects

        infoType = (EditText) findViewById(R.id.infoType);
        name = (EditText) findViewById(R.id.name);
        details = (EditText) findViewById(R.id.details);
        pit = (EditText) findViewById(R.id.pit);

    }

    public void addNewInformation(View view) {

        // Will hold the HashMap of values

        HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

        // Get the values from the EditText boxes

        queryValuesMap.put("infoType", infoType.getText().toString());
        queryValuesMap.put("name", name.getText().toString());
        queryValuesMap.put("details", details.getText().toString());
        queryValuesMap.put("pit", pit.getText().toString());

        // Call for the HashMap to be added to the database

        dbTools.insertInfo(queryValuesMap);

        // Call for MainActivity to execute

        this.callMainActivity(view);
    }

    public void callMainActivity(View view) {
        Intent theIntent = new Intent(getApplicationContext(), MyResume.class);
        finish();
        startActivity(theIntent);
    }
}