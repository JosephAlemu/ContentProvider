package githubprofile.joseph.com.contentprovider.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Admin on 2/13/2018.
 */

public class Constants {



    public static  final  String CONTENT_AUTHORITY = "githubprofile.joseph.com.contentprovider.model.MyProvider";

    public static  final Uri BASE_CONTENT_URI =  Uri.parse("content://"+CONTENT_AUTHORITY);



    public static final String DATABASE_NAME = "my_Db";
    public static final String DATABASE_TABLE = "myTable";

    public static  final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,DATABASE_TABLE);

    private static final String TAG = "DBAdapter";

    // DB Fields
    public static final String KEY_ROWID = "_id";





    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";

    public static final int COL_ROWID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_EMAIL = 2;
    public static final int COL_PHONE= 3;
    public static final int COL_ADDRESS = 4;


    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_ADDRESS};

    // DB info: it's name, and the table we are using (just one).

    // Track DB version if a new version of your app changes the format.
    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_CREATE_SQL =
            "create table " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_NAME + " text not null, "
                    + KEY_EMAIL + " string not null, "
                    + KEY_PHONE + " integer not null, "
                    + KEY_ADDRESS + " integer"
                    + ");";




}
