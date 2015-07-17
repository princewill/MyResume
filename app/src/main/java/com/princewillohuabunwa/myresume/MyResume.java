package com.princewillohuabunwa.myresume;

import java.util.ArrayList;
import java.util.HashMap;

import com.princewillohuabunwa.myresume.DBTools;
import com.princewillohuabunwa.myresume.NewInfo;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;

import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MyResume extends ListActivity {

    // The Intent is used to issue that an operation should
    // be performed

    Intent intent;
    TextView infoId;
    ImageView contactImage;

    // The object that allows me to manipulate the database

    DBTools dbTools = new DBTools(this);

    // Called when the Activity is first called

    protected void onCreate(Bundle savedInstanceState) {
        // Get saved data if there is any

        super.onCreate(savedInstanceState);

        // Designate that activity_main.xml is the interface used

        setContentView(R.layout.activity_my_resume);
        contactImage = (ImageView) findViewById(R.id.imgView);
        // Gets all the data from the database and stores it
        // in an ArrayList

        ArrayList<HashMap<String, String>> infoList =  dbTools.getAllInfo();

        // Check to make sure there are contacts to display

        if(infoList.size()!=0) {

            // Get the ListView and assign an event handler to it

            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    // When an item is clicked get the TextView
                    // with a matching checkId

                    infoId = (TextView) view.findViewById(R.id.infoId);

                    // Convert that contactId into a String

                    String infoIdValue = infoId.getText().toString();

                    // Signals an intention to do something
                    // getApplication() returns the application that owns
                    // this activity

                    Intent theIntent = new Intent(getApplication(),EditInfo.class);

                    // Put additional data in for EditContact to use

                    theIntent.putExtra("infoId", infoIdValue);

                    // Calls for EditContact
                    startActivity(theIntent);
                }
            });

            // A list adapter is used bridge between a ListView and
            // the ListViews data

            // The SimpleAdapter connects the data in an ArrayList
            // to the XML file

            // First we pass in a Context to provide information needed
            // about the application
            // The ArrayList of data is next followed by the xml resource
            // Then we have the names of the data in String format and
            // their specific resource ids

            ListAdapter adapter = new SimpleAdapter( MyResume.this,infoList, R.layout.info_entry, new String[] { "infoId","infoType"}, new int[] {R.id.infoId, R.id.infoType});

            // setListAdapter provides the Cursor for the ListView
            // The Cursor provides access to the database data

            setListAdapter(adapter);
        }

        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Contact Image"), 1);

            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){

        if (resCode == RESULT_OK){
            if(reqCode==1)
                contactImage.setImageURI(data.getData());
        }
    }

    // When showAddContact is called with a click the Activity
    // NewContact is called

    public void showAddInformation(View view) {
        Intent theIntent = new Intent(getApplication(), NewInfo.class);
        startActivity(theIntent);
    }
}