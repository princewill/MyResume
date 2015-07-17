package com.princewillohuabunwa.myresume;

/**
 * Created by princewillohuabunwa on 15-07-05.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// SQLiteOpenHelper helps you open or create a database

public class DBTools  extends SQLiteOpenHelper {

    // Context : provides access to application-specific resources and classes

    public DBTools(Context applicationContext) {

        // Call use the database or to create it

        super(applicationContext, "infoBook.db", null, 1);

    }

    // onCreate is called the first time the database is created
    @Override
    public void onCreate(SQLiteDatabase database) {

        // How to create a table in SQLite
        // Make sure you don't put a ; at the end of the query

        String query = "CREATE TABLE info ( infoId INTEGER PRIMARY KEY, infoType TEXT, " +
                "name TEXT, details TEXT, pit TEXT)";

        String query2 = "INSERT into info ( infoID, infoType, name, details, pit) values(1, 'My Contact', 'Princewill Ohuabunwa', 'email: wohuabunwa@gmail.com', '1992 - Present')";
        String query3 = "INSERT into info ( infoID, infoType, name, details, pit) values(2, 'My Education', 'Carleton University', 'B.Eng Electrical', '2010 - 2015')";
        String query4 = "INSERT into info ( infoID, infoType, name, details, pit) values(3, 'My Software Skills', 'OS, Languages and Applications ', 'LINUX, Windows OS," +
                        " Mac OS, Microsoft Office, Adobe PhoneGap, SQLite/MySQL,RUBY, HTML, Java, JScript, C++, C, Lua, PSPICE, AutoCAD, ProE, MATLAB.', 'N/A')";
        String query5 = "INSERT into info ( infoID, infoType, name, details, pit) values(4, 'My Hardware Skills', 'Laboratory hardware', 'Digital and analog oscilloscopes," +
                        "voltmeter, ammeter, waveform function generators, experience with high gate count FPGA designs', 'N/A')";
        String query6 = "INSERT into info ( infoID, infoType, name, details, pit) values(5, 'My Personal Skills', 'Qualities and Attributes', 'I am flexible and a team player." +
                        "I am not only innovative but also a quick learner who thrives well in a fast paced environment  ', 'N/A')";
        String query7 = "INSERT into info ( infoID, infoType, name, details, pit) values(6, 'My  Work Experience (1)', '- Market Researcher\n- Elemental Data Collections Inc" +
                        "\n- Ottawa, ON', '- Conduct surveys.\n- Ensure customer satisfaction by listening," +
                        "\n- Communicate effectively and providing overall positive experience.'," +
                        " 'May 2015 - Present')";
        String query8 = "INSERT into info ( infoID, infoType, name, details, pit) values(7, 'My Work Experience (2)', '- Application Developer\n- Hubub Inc.\n- Toronto, On'," +
                "'- Assisted to effectively develop web applications for cross-mobile platforms.\n" +
                "- Used Corona SDK and ADOBE PhoneGap frameworks for development.\n', 'May 2013 - Sept 2013')";
        String query9 = "INSERT into info ( infoID, infoType, name, details, pit) values(8, 'My Projects (1)', 'The FreeScale Cup', '- Designed and built an autonomous model car" +
                " to race a track for speed using embedded programming.\n" +
                "- Project implementation was carried out using the FRDM-KL25Z micro-controller and a TFC shield as an interface. ', 'Sept 2015 - April 2015')";
        String query10 = "INSERT into info ( infoID, infoType, name, details, pit) values(9, 'My Projects (2)', 'Wireless Remote Controlled Door Lock', '- Part of a four-student team;" +
                " designed a remote-controlled door lock, using micro-controllers and an infrared camera.\n" +
                "- Used a two Jeenodes and used RF signals to communicate between both micro-controllers.\n" +
                "- Assembled H-bridge circuit for the DC motors.', 'Jan 2014 - April 2014')";


        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data

        database.execSQL(query);
        database.execSQL(query2);
        database.execSQL(query3);
        database.execSQL(query4);
        database.execSQL(query5);
        database.execSQL(query6);
        database.execSQL(query7);
        database.execSQL(query8);
        database.execSQL(query9);
        database.execSQL(query10);

    }

    // onUpgrade is used to drop tables, add tables, or do anything
    // else it needs to upgrade
    // This is droping the table to delete the data and then calling
    // onCreate to make an empty table
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS info";

        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data

        database.execSQL(query);
        onCreate(database);
    }

    public void insertInfo(HashMap<String, String> queryValues) {

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data
        // ContentValues data type is needed because the database
        // requires its data type to be passed

        ContentValues values = new ContentValues();

        values.put("infoType", queryValues.get("infoType"));
        values.put("name", queryValues.get("name"));
        values.put("details", queryValues.get("details"));
        values.put("pit", queryValues.get("pit"));


        // Inserts the data in the form of ContentValues into the
        // table name provided

        database.insert("info", null, values);

        // Release the reference to the SQLiteDatabase object

        database.close();
    }

    public int updateInfo(HashMap<String, String> queryValues) {

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data

        ContentValues values = new ContentValues();

        values.put("infoType", queryValues.get("infoType"));
        values.put("name", queryValues.get("name"));
        values.put("details", queryValues.get("details"));
        values.put("pit", queryValues.get("pit"));


        // update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)

        return database.update("info", values, "infoId" + " = ?", new String[] { queryValues.get("infoId") });
    }

    // Used to delete a contact with the matching contactId

    public void deleteInfo(String id) {

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM  info WHERE infoId='"+ id +"'";

        // Executes the query provided as long as the query isn't a select
        // or if the query doesn't return any data

        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllInfo() {

        // ArrayList that contains every row in the database
        // and each row key / value stored in a HashMap

        ArrayList<HashMap<String, String>> infoArrayList;

        infoArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM info";

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Cursor provides read and write access for the
        // data returned from a database query

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);

        // Move to the first row

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> infoMap = new HashMap<String, String>();

                // Store the key / value pairs in a HashMap
                // Access the Cursor data by index that is in the same order
                // as used when creating the table

                infoMap.put("infoId", cursor.getString(0));
                infoMap.put("infoType", cursor.getString(1));
                infoMap.put("name", cursor.getString(2));
                infoMap.put("details", cursor.getString(3));
                infoMap.put("pit", cursor.getString(4));

                infoArrayList.add(infoMap);
            } while (cursor.moveToNext()); // Move Cursor to the next row
        }

        cursor.close();
        // return contact list
        return infoArrayList;
    }

    public HashMap<String, String> getInfo(String id) {
        HashMap<String, String> infoMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM info WHERE infoId='"+id+"'";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                infoMap.put("infoId", cursor.getString(0));
                infoMap.put("infoType", cursor.getString(1));
                infoMap.put("name", cursor.getString(2));
                infoMap.put("details", cursor.getString(3));
                infoMap.put("pit", cursor.getString(4));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return infoMap;
    }
}
