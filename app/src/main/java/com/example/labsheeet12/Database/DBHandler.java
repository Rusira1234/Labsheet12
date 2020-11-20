package com.example.labsheeet12.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Labsheet12.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseMaster.User.TABLE_NAME + " (" +
                    DatabaseMaster.User._ID + " INTEGER PRIMARY KEY," +
                    DatabaseMaster.User.COL1 + " TEXT," +
                    DatabaseMaster.User.COL2 + " TEXT," +
                    DatabaseMaster.User.COL3 + " TEXT)";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + DatabaseMaster.Message.TABLE_NAME + " (" +
                    DatabaseMaster.Message._ID + " INTEGER PRIMARY KEY," +
                    DatabaseMaster.Message.COL1 + " TEXT," +
                    DatabaseMaster.Message.COL2 + " TEXT," +
                    DatabaseMaster.Message.COL3 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseMaster.User.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + DatabaseMaster.Message.TABLE_NAME;



    public long AddUser(String Name, String Password, String Type) {
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DatabaseMaster.User.COL1, Name);
            values.put(DatabaseMaster.User.COL2, Password);
            values.put(DatabaseMaster.User.COL3, Type);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(DatabaseMaster.User.TABLE_NAME, null, values);
            return newRowId;
        }
        public long AddMessage(String User, String Subject, String Message) {
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DatabaseMaster.Message.COL1, User);
            values.put(DatabaseMaster.Message.COL2, Subject);
            values.put(DatabaseMaster.Message.COL3, Message);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(DatabaseMaster.Message.TABLE_NAME, null, values);
            return newRowId;
        }

        /*public List GetUserType(String UserName) {
            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
// you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    DatabaseMaster.User.COL3
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = DatabaseMaster.User.COL1 + " = ?";
            String[] selectionArgs = {"UserName"};

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    DatabaseMaster.User.COL3 + " ASC";

            Cursor cursor = db.query(
                    DatabaseMaster.User.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            List UserTypes = new ArrayList<>();
            while(cursor.moveToNext()) {
                String UserType = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.User.COL3));
                UserTypes.add(UserType);
            }
            cursor.close();
            return UserTypes;
        }*/

        public ArrayList GetMessages() {
            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    DatabaseMaster.Message.COL1,
                    DatabaseMaster.Message.COL2,
                    DatabaseMaster.Message.COL3

            };

            // Filter results WHERE "title" = 'My Title'
            //String selection = DatabaseMaster.User.COL1 + " = ?";
            //String[] selectionArgs = {"UserName"};

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    DatabaseMaster.Message.COL3 + " ASC";

            Cursor cursor = db.query(
                    DatabaseMaster.Message.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            ArrayList Messages = new ArrayList<>();
            while(cursor.moveToNext()) {
                String Message1 = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COL3));
                String Subject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COL2));
                String Name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COL1));
                Messages.add(Subject);
                Messages.add(Message1);
                Messages.add(Name);
            }
            cursor.close();
            return Messages;
        }

    public List checkusernamepassword(String username , String password){
        List UTypes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where Name = ? and Password = ?", new String[] {username,password});
        while(cursor.moveToNext()){
            String User1 = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.User.COL3));
            UTypes.add(User1);
        }
        return UTypes;
        /*if(cursor.getCount() > 0 ){
            return true;
        }
        else{
            return false;
        }*/

    }

    public List GetMessagesLast() {
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseMaster.Message.COL1,
                DatabaseMaster.Message.COL2,
                DatabaseMaster.Message.COL3

        };

        // Filter results WHERE "title" = 'My Title'
        //String selection = DatabaseMaster.User.COL1 + " = ?";
        //String[] selectionArgs = {"UserName"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseMaster.Message._ID + " DESC LIMIT 1";

        Cursor cursor = db.query(
                DatabaseMaster.Message.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List Messages2 = new ArrayList<>();
        while(cursor.moveToNext()) {
            String Message1 = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COL3));
            String Subject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COL2));
            String User = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COL1));
            Messages2.add(Subject);
            Messages2.add(Message1);
            Messages2.add(User);
        }
        cursor.close();
        return Messages2;
    }

}

