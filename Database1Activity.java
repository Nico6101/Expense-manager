package com.rcpl.tabapp;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Database1Activity extends AppCompatActivity implements View.OnClickListener
{

    EditText et1,et2,et3,et4,et5,et6;
    String str,str1;
    static String R1,R2,R3,R4,R5,R6,R7;
    static int amount,amount1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database1);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        b1 = (Button) findViewById(R.id.button3);
        database1 d = new database1(getBaseContext());
        SQLiteDatabase db = d.getWritableDatabase();

        b1.setOnClickListener(this);
    }

    public static final class Records
    {
        Records()
        {

        }
        String r1,r2,r3,r4,r5,r6;
        int r7;
        public void setR1(String a)
        {
            r1 = a;
        }
        public void setR2(String a)
        {
            r2 = a;
        }
        public void setR3(String a)
        {
            r3 = a;
        }
        public void setR4(String a)
        {
            r4 = a;
        }
        public void setR5(String a)
        {
            r5 = a;
        }
        public void setR6(String a)
        {
            r6 = a;
        }
        public void setR7(int a)
        {
            r7 = a;
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

    public void openCalendar1(View v)
    {
        MyDateChooser1 ref1 = new MyDateChooser1();
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();

        int yy = gc.get(Calendar.YEAR);
        int mm = gc.get(Calendar.MONTH);
        int dd = gc.get(Calendar.DATE);

        DatePickerDialog dialog1 = new DatePickerDialog(this,ref1,yy,mm,dd);
        dialog1.show();
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button3)
            insertRecord();
    }

    public void insertRecord()
    {
        String tname = et1.getText().toString();
        String loc1 = et2.getText().toString();
        String loc2 = et3.getText().toString();
        String doj1 = et4.getText().toString();
        String doj2 = et5.getText().toString();
        String abudget = et6.getText().toString();

        Records r = new Records();
        r.setR1(tname);
        r.setR2(loc1);
        r.setR3(loc2);
        r.setR4(doj1);
        r.setR5(doj2);
        r.setR6(abudget);

        R1 = r.r1;
        R2 = r.r2;
        R3 = r.r3;
        R4 = r.r4;
        R5 = r.r5;
        R6 = r.r6;

        database1 data = new database1(getBaseContext());
        SQLiteDatabase db = data.getWritableDatabase();
        data.insertData(getApplicationContext());

    }

    class MyDateChooser implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            str = year+"-"+(month+1)+"-"+dayOfMonth;
            et4.setText(str);
        }
    }

    class MyDateChooser1 implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            str1 = year+"-"+(month+1)+"-"+dayOfMonth;
            et5.setText(str1);
        }
    }


    public static class database1 extends SQLiteOpenHelper implements BaseColumns
    {
            public static final int DATABASE_VERSION = 2;
            public static final String DATABASE_NAME = "Database1.db";

            public static final String TABLE_NAME = "TRIP_DETAILS";

            public static final String CN_TNAME = "tname";
            public static final String CN_LOC1 = "loc1";
            public static final String CN_LOC2 = "loc2";
            public static final String CN_DOJ1 = "doj1";
            public static final String CN_DOJ2 = "doj2";
            public static final String CN_ABUDGET = "abudget";
            public static final String CN_AMT = "amt";

            public database1(Context context)
            {
                super(context, DATABASE_NAME,null,DATABASE_VERSION);
            }


            @Override
            public void onCreate(SQLiteDatabase db)
            {
                String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY," +
                        CN_TNAME + " TEXT," + CN_LOC1 + " TEXT," + CN_LOC2 + " TEXT," + CN_DOJ1 + " TEXT," + CN_DOJ2 + " TEXT,"
                        + CN_ABUDGET + " INTEGER," + CN_AMT + " INTEGER" + ")";

                db.execSQL(SQL_CREATE_ENTRIES);
            }

            public void insertData(Context context)
            {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(CN_TNAME,R1);
                values.put(CN_LOC1,R2);
                values.put(CN_LOC2,R3);
                values.put(CN_DOJ1,R4);
                values.put(CN_DOJ2,R5);
                values.put(CN_ABUDGET,R6);

                Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
                while(cursor.moveToNext())
                {
                    amount = cursor.getInt(5);
                }
                try
                {
                    Cursor cursor1 = db.rawQuery("SELECT * FROM " + Database2Activity.database2.TABLE_NAME, null);
                    while (cursor.moveToNext())
                    {
                        amount1 = cursor1.getInt(2);
                    }
                    int diff = amount - amount1;

                    values.put(CN_AMT,diff);

                }
                catch (Exception e)
                {
                    values.put(CN_AMT,0);
                }
                db.insert(TABLE_NAME,null,values);
                Toast.makeText(context, "Records are inserted.", Toast.LENGTH_SHORT).show();
                db.close();
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

}
