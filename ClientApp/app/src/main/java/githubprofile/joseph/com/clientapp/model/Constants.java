package githubprofile.joseph.com.clientapp.model;

import android.net.Uri;

/**
 * Created by Admin on 2/13/2018.
 */

public class Constants {



    public static  final  String CONTENT_AUTHORITY = "githubprofile.joseph.com.contentprovider.model.MyProvider";

    public static  final Uri BASE_CONTENT_URI =  Uri.parse("content://"+CONTENT_AUTHORITY);


    public static final String DATABASE_TABLE = "myTable";

    public static  final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,DATABASE_TABLE);


    // DB Fields
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_ADDRESS};




    public static final int COL_ROWID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_EMAIL = 2;
    public static final int COL_PHONE= 3;
    public static final int COL_ADDRESS = 4;






}
