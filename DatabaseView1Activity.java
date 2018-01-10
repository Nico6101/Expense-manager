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

public class DatabaseView1Activity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ListView listView1;
    String tid,tname,loc1,loc2,doj1,doj2,abudget,bbudget;
    String[] projection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view1);

        listView1 = (ListView) findViewById(R.id.listView1);

        Database1Activity.database1 d = new Database1Activity.database1(getBaseContext());
        SQLiteDatabase db = d.getReadableDatabase();
        projection = new String[]{Database1Activity.database1._ID,Database1Activity.database1.CN_TNAME,Database1Activity.database1.CN_LOC1,
                Database1Activity.database1.CN_LOC2,Database1Activity.database1.CN_DOJ1,Database1Activity.database1.CN_DOJ2,
                Database1Activity.database1.CN_ABUDGET,Database1Activity.database1.CN_AMT};

        Cursor cursor = db.query(Database1Activity.database1.TABLE_NAME,projection,null,null,null,null, null);

        MyCursorAdapter myCursorAdapter = new MyCursorAdapter(this, cursor, 0);
        cursor.moveToFirst();
        listView1.setAdapter(myCursorAdapter);
        listView1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);

        int tripid = cursor.getInt(cursor.getColumnIndex("_ID"));
        String tname = cursor.getString(cursor.getColumnIndex("tname"));
        String loc1 = cursor.getString(cursor.getColumnIndex("loc1"));
        String loc2 = cursor.getString(cursor.getColumnIndex("loc2"));
        String doj1 = cursor.getString(cursor.getColumnIndex("doj1"));
        String doj2 = cursor.getString(cursor.getColumnIndex("doj2"));
        int abudget = cursor.getInt(cursor.getColumnIndex("abudget"));

        Toast.makeText(this, "Clicked :" + tripid + "-" + tname + "(" + loc1 + "-" + loc2 + "-" + doj1 + "-" + doj2 + "-" + abudget + "-" + ")", Toast.LENGTH_SHORT).show();
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

            View v = inflater.inflate(R.layout.row, parent, false);
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
            TextView tv7 = (TextView) view.findViewById(R.id.textView7);
            TextView tv8 = (TextView) view.findViewById(R.id.textView8);

            int tripid = cursor.getInt(cursor.getColumnIndex(Database1Activity.database1._ID));
            int approvedbudget = cursor.getInt(cursor.getColumnIndex(Database1Activity.database1.CN_ABUDGET));
            tid = "" + tripid;
            tname = cursor.getString(cursor.getColumnIndex(Database1Activity.database1.TABLE_NAME));
            loc1 = cursor.getString(cursor.getColumnIndex(Database1Activity.database1.CN_LOC1));
            loc2 = cursor.getString(cursor.getColumnIndex(Database1Activity.database1.CN_LOC2));
            doj1 = cursor.getString(cursor.getColumnIndex(Database1Activity.database1.CN_DOJ1));
            doj2 = cursor.getString(cursor.getColumnIndex(Database1Activity.database1.CN_DOJ2));
            abudget = "" + approvedbudget;
            int tot = cursor.getInt(cursor.getColumnIndex(Database1Activity.database1.CN_AMT));
            bbudget = "" + tot;

            tv1.append(tid);
            tv2.append(tname);
            tv3.append(loc1);
            tv4.append(loc2);
            tv5.append(doj1);
            tv6.append(doj2);
            tv7.append(abudget);
            tv8.append(bbudget);
        }
    }


}