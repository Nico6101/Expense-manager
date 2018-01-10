package com.rcpl.tabapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseView3Activity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    ListView listView1;
    String tid,loc1,loc2,date,eid,total;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view3);

        listView1 = (ListView) findViewById(R.id.listView1);

        SQLiteDatabase db = openOrCreateDatabase("RCPL_DB", MODE_APPEND, null);
        Cursor cursor = db.rawQuery("select *,(count(amt),_id from EXPENSE_DETAILS group by _id) from EXPENSE_DETAILS", null);

        DatabaseView3Activity.MyCursorAdapter myCursorAdapter = new DatabaseView3Activity.MyCursorAdapter(this, cursor, 0);
        listView1.setAdapter(myCursorAdapter);
        listView1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);

        int tripid = cursor.getInt(cursor.getColumnIndex("_id"));
        int expid = cursor.getInt(cursor.getColumnIndex("_eid"));
        String loc1 = cursor.getString(cursor.getColumnIndex("loc1"));
        String loc2 = cursor.getString(cursor.getColumnIndex("loc2"));
        String doj = cursor.getString(cursor.getColumnIndex("date"));
        int total = cursor.getInt(cursor.getColumnIndex("count(amt)"));

        Toast.makeText(this, "Clicked :" + tripid + "-" +expid+"-" +  "(" + loc1 + "-" + loc2 + "-" + doj + "-" +total + ")", Toast.LENGTH_SHORT).show();
    }


    class MyCursorAdapter extends CursorAdapter
    {
        public MyCursorAdapter(Context context, Cursor c, int flags)
        {
            super(context, c, flags);
        }

        //these two functions will run for every row in the database
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View v = inflater.inflate(R.layout.row2, parent, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor)
        {
            TextView tv1 = (TextView) view.findViewById(R.id.textView1);
            TextView tv2 = (TextView) view.findViewById(R.id.textView2);
            TextView tv3 = (TextView) view.findViewById(R.id.textView3);
            TextView tv4 = (TextView) view.findViewById(R.id.textView4);
            TextView tv5 = (TextView) view.findViewById(R.id.textView5);
            TextView tv6 = (TextView) view.findViewById(R.id.textView6);

            int tripid = cursor.getInt(cursor.getColumnIndex("_id"));
            int expid = cursor.getInt(cursor.getColumnIndex("_eid"));
            int tot = cursor.getInt(cursor.getColumnIndex("count(amt)"));
            tid = "" + tripid;
            eid = "" + expid;
            loc1 = cursor.getString(cursor.getColumnIndex("loc1"));
            loc2 = cursor.getString(cursor.getColumnIndex("loc2"));
            date = cursor.getString(cursor.getColumnIndex("date"));
            total = ""+tot;

            tv1.append(tid);
            tv2.append(loc1);
            tv3.append(loc2);
            tv4.append(date);
            tv5.append(total);
            tv6.append(eid);
        }
    }
}

