package com.rcpl.tabapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Database2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
        EditText et1,et2;
        String str,s,s1;
        String S1;
        int S2;
        Spinner sp1,sp2;
        Button b6;

        String[] arr = {"Food","Travel","Shopping","Stay","Miscellaneous"};
        String[] arr1 = {Database1Activity.database1.CN_TNAME};
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_database2);

            et1 = (EditText) findViewById(R.id.editText1);
            et2 = (EditText) findViewById(R.id.editText2);
            sp1 = (Spinner) findViewById(R.id.spinner1);
            sp2 = (Spinner) findViewById(R.id.spinner2);
            b6 = (Button) findViewById(R.id.button6);

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adapter);

            ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr1);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp2.setAdapter(adapter1);

            sp1.setOnItemSelectedListener(this);
            sp2.setOnItemSelectedListener(this);

            database2 d = new database2(getBaseContext());
            SQLiteDatabase db = d.getWritableDatabase();
        }

    public final class Records1
    {
        String r1;
        int r2;
        public void setR1(String a)
        {
            r1 = a;
        }
        public void setR2(int a)
        {
            r2 = a;
        }
    }

        public void openCalendar(View v)
        {
            MyDateChooser ref = new MyDateChooser();
            Date date = new Date();
            GregorianCalendar gc = new GregorianCalendar();

            int yy = gc.get(Calendar.YEAR);
            int mm = gc.get(Calendar.MONTH);
            int dd = gc.get(Calendar.DATE);

            DatePickerDialog dialog = new DatePickerDialog(this,ref,yy,mm,dd);
            dialog.show();
        }

        class MyDateChooser implements DatePickerDialog.OnDateSetListener
        {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                str = year+"-"+(month+1)+"-"+dayOfMonth;
                et1.setText(str);
            }
        }

        public void insertRecord(View v)
        {
            String date = et1.getText().toString();
            String amt = et2.getText().toString();
            int value = Integer.parseInt(amt);

            Records1 r = new Records1();

            r.setR1(date);
            r.setR2(value);

            S1 = r.r1;
            S2 = r.r2;

            database2 data = new database2(getBaseContext());
            data.insertData();

            Toast.makeText(this, "Record Inserted.", Toast.LENGTH_SHORT).show();
        }

    public class database2 extends SQLiteOpenHelper implements BaseColumns
    {
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "Database1.db";

            public static final String TABLE_NAME = "EXPENSE_DETAILS";
            public static final String CN_TNAME = "tname";
            public static final String CN_DATE = "date";
            public static final String CN_AMT = "amt";
            public static final String CN_ACTIVITY = "activity";


            public static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY , " +
                    CN_TNAME + " TEXT , " + CN_DATE + " TEXT , " + CN_AMT + " INTEGER , " + CN_ACTIVITY + " TEXT )";

            public database2(Context context)
            {
                super(context, DATABASE_NAME,null,DATABASE_VERSION);
                SQLiteDatabase db = this.getWritableDatabase();
            }


            @Override
            public void onCreate(SQLiteDatabase db)
            {
                db.execSQL(SQL_CREATE_ENTRIES);
            }

            public void insertData()
            {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(CN_DATE,S1);
                values.put(CN_AMT,S2);

                db.insert(TABLE_NAME,null,values);
                Toast.makeText(Database2Activity.this, "Records added successfully.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
            {

            }

            @Override
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
            {
                onUpgrade(db, oldVersion, newVersion);
            }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        database2 d2 = new database2(getBaseContext());
        SQLiteDatabase db = d2.getWritableDatabase();
        s = parent.getItemAtPosition(position).toString();
        s1 = parent.getItemAtPosition(position).toString();
        ContentValues values = new ContentValues();
        values.put(database2.CN_ACTIVITY,s);
        values.put(database2.CN_TNAME,s1);
        db.insert(database2.TABLE_NAME,null,values);
        Toast.makeText(Database2Activity.this, "Records selected successfully.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

}


