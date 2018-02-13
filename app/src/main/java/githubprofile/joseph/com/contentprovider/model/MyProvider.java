package githubprofile.joseph.com.contentprovider.model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by Admin on 2/13/2018.
 */

public class MyProvider extends ContentProvider {
    private static final String TAG = "MyProvider" ;
    DBAdapter myDb;
    private static final int INFO = 1;				// For whole table
    private static final int INFO_ID = 2;			// For a specific row in a table identified by _ID
    private static final int INFO_NAME = 3;// For a specific row in a table identified by COUNTRY NAME


    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(Constants.CONTENT_AUTHORITY , Constants.DATABASE_TABLE,  INFO);
        uriMatcher.addURI(Constants.CONTENT_AUTHORITY , Constants.DATABASE_TABLE+ "/#",  INFO_ID );
        uriMatcher.addURI(Constants.CONTENT_AUTHORITY , Constants.DATABASE_TABLE+ "/*",  INFO_NAME);
    }
    @Override
    public boolean onCreate() {

        myDb = new DBAdapter(getContext());
        myDb.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


        Cursor cursor;

        switch (uriMatcher.match(uri)) {

            case INFO:
                cursor = myDb.getAllRows(Constants.DATABASE_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case INFO_ID:
//               selection = NationEntry._ID + " = ?";
//                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
//                cursor = myDb.getRow(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
//                break;

            default:
                throw new IllegalArgumentException(TAG + "Unknown URI: " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {

            case INFO:
                return insertRecord(uri, contentValues, Constants.DATABASE_TABLE);
            default:
                throw new IllegalArgumentException(TAG + "Unknown URI: " + uri);
        }
    }

    private Uri insertRecord(Uri uri, ContentValues values, String tableName) {


        long rowId = myDb.insertRow(tableName , values);

        if (rowId == -1) {
            Log.e(TAG, "Insert error for URI " + uri);
            return null;
        }

        return ContentUris.withAppendedId(uri, rowId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
