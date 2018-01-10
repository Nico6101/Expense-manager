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

public class DatabaseView2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    ListView listView1;
    String eid,tid,loc1,loc2,category,amt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view2);

        listView1 = (ListView) findViewById(R.id.listView1);

        SQLiteDatabase db = openOrCreateDatabase("RCPL_DB", MODE_APPEND, null);
        Cursor cursor = db.rawQuery("select * from EXPENSE_DETAILS", null);

        DatabaseView2Activity.MyCursorAdapter myCursorAdapter = new DatabaseView2Activity.MyCursorAdapter(this, cursor, 0);
        listView1.setAdapter(myCursorAdapter);
        listView1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);

        int eid = cursor.getInt(cursor.getColumnIndex("_ID"));
        int tripid = cursor.getInt(cursor.getColumnIndex("_id"));
        int amount = cursor.getInt(cursor.getColumnIndex("amt"));
        String loc1 = cursor.getString(cursor.getColumnIndex("loc1"));
        String loc2 = cursor.getString(cursor.getColumnIndex("loc2"));
        String category = cursor.getString(cursor.getColumnIndex("category"));

        Toast.makeText(this, "Clicked :"+eid+"-" + tripid + "-" +  "(" + loc1 + "-" + loc2 + "-" + category + "-" + amount + "-" + ")", Toast.LENGTH_SHORT).show();
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

            View v = inflater.inflate(R.layout.row1, parent, false);
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
            int amount = cursor.getInt(cursor.getColumnIndex("amt"));
            tid = "" + tripid;
            eid = "" + expid;
            loc1 = cursor.getString(cursor.getColumnIndex("loc1"));
            loc2 = cursor.getString(cursor.getColumnIndex("loc2"));
            amt = "" + amount;
            category = cursor.getString(cursor.getColumnIndex("category"));

            tv1.append(tid);
            tv2.append(loc1);
            tv3.append(loc2);
            tv4.append(amt);
            tv5.append(category);
            tv6.append(eid);
        }
    }
}
