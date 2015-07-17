package com.princewillohuabunwa.myresume;

/**
 * Created by princewillohuabunwa on 15-07-06.
 */
import java.util.HashMap;

import android.os.Bundle;

import android.widget.EditText;

import android.app.Activity;
import android.content.Intent;

import android.view.View;

public class EditInfo extends Activity{

    // Allows access to data in the EditTexts

    EditText infoType;
    EditText name;
    EditText details;
    EditText pit;

    // The database tool class

    DBTools dbTools = new DBTools(this);

    // Sets up everything when the Activity is displayed

    public void onCreate(Bundle savedInstanceState) {

        // Get saved data if there is any

        super.onCreate(savedInstanceState);

        // Designate that edit_info.xml is the interface used

        setContentView(R.layout.edit_information);

        // Get the EditText objects

        infoType = (EditText) findViewById(R.id.infoType);
        name = (EditText) findViewById(R.id.name);
        details = (EditText) findViewById(R.id.details);
        pit = (EditText) findViewById(R.id.pit);

        // Intent defines that an operation will be performed

        Intent theIntent = getIntent();

        // Get the extended data provided to this activity
        // putExtra("infoId", infoIdValue); in MainActivity
        // will pass infoId here

        String infoId = theIntent.getStringExtra("infoId");

        // Get the HashMap of data associated with the infoId

        HashMap<String, String> infoList = dbTools.getInfo(infoId);

        // Make sure there is something in theinfoList

        if(infoList.size()!=0) {

            // Put the values in the EditText boxes

            infoType.setText(infoList.get("infoType"));
            name.setText(infoList.get("name"));
            details.setText(infoList.get("details"));
            pit.setText(infoList.get("pit"));

        }
    }
    public void editInfo(View view) {
        HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

        // Get the EditText objects

        infoType = (EditText) findViewById(R.id.infoType);
        name = (EditText) findViewById(R.id.name);
        details = (EditText) findViewById(R.id.details);
        pit = (EditText) findViewById(R.id.pit);

        // Intent defines that an operation will be performed

        Intent theIntent = getIntent();

        // Get the extended data provided to this activity
        // putExtra("infoId", infoIdValue); in MainActivity
        // will pass infoId here

        String infoId = theIntent.getStringExtra("infoId");

        // Put the values in the EditTexts in the HashMap

        queryValuesMap.put("infoId", infoId);
        queryValuesMap.put("infoType", infoType.getText().toString());
        queryValuesMap.put("name", name.getText().toString());
        queryValuesMap.put("details", details.getText().toString());
        queryValuesMap.put("pit", pit.getText().toString());

        // Send the HashMap to update the data in the database

        dbTools.updateInfo(queryValuesMap);

        // Call for MainActivity

        this.callMainActivity(view);

    }
    public void removeInfo(View view) {
        Intent theIntent = getIntent();
        String infoId = theIntent.getStringExtra("infoId");

        // Call for the info with the infoId provided
        // to be deleted

        dbTools.deleteInfo(infoId);

        // Call for MainActivity

        this.callMainActivity(view);

    }

    // Calls for a switch to the MainActivity

    public void callMainActivity(View view) {

        // getApplication returns an Application object which allows
        // you to manage your application and respond to different actions.
        // It returns an Application object which extends Context.

        // A Context provides information on the environment your application
        // is currently running in. It provides services like how tp obtain
        // access to a database and preferences.

        // Google says a Context is an entity that represents various
        // environment data. It provides access to local files, databases,
        // class loaders associated to the environment, services including
        // system-level services, and more.

        // The following Intent states that you want to switch to a new
        // Activity being the MainActivity

        Intent objIntent = new Intent(getApplication(), MyResume.class);
        finish();
        startActivity(objIntent);
    }
}