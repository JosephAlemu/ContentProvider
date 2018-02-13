package githubprofile.joseph.com.contentprovider;

import android.app.usage.ConfigurationStats;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import githubprofile.joseph.com.contentprovider.adapter.Info_Adapter;
import githubprofile.joseph.com.contentprovider.model.Constants;
import githubprofile.joseph.com.contentprovider.model.DBAdapter;
import githubprofile.joseph.com.contentprovider.model.Info;

public class DisplayActivity extends AppCompatActivity {

    private List<Info> InfoList;
    private RecyclerView recyclerView;
    private Info_Adapter mAdapter;

    DBAdapter myDb;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        InfoList = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        openDB();
      //  populateMyDB();
        Cursor cursor = queryAndDisplayAll();

        getRecordSet(cursor);

        mAdapter = new Info_Adapter(this,InfoList );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    private void  getRecordSet(Cursor cursor) {


        if (cursor != null) {

            String str = "";
            if (cursor.moveToFirst()) {
                do {
           	// Cursor iterates through all rows




                int id = cursor.getInt(Constants.COL_ROWID);
                String name = cursor.getString(Constants.COL_NAME);
                String email = cursor.getString(Constants.COL_EMAIL);
                String phone = cursor.getString(Constants.COL_PHONE);
                String address = cursor.getString(Constants.COL_ADDRESS);


                Info info = new Info(id, name, email, phone, address);

                InfoList.add(info);



                } while (cursor.moveToNext());
            }

            // Close the cursor to avoid a resource leak.
            cursor.close();



            cursor.close();
            Log.i(TAG, str);
        }

    }


    private Cursor queryAndDisplayAll() {

        String[] projection =  Constants.ALL_KEYS;

        // Filter results. Make these null if you want to query all rows
        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;	// Ascending or Descending ...

        Uri uri = Constants.CONTENT_URI;			
        Log.i(TAG, ""+uri);
        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);


        return cursor;
    }

    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }


    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    private void closeDB() {
        myDb.close();
    }
}
