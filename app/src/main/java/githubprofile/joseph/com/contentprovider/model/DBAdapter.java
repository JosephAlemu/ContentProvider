package githubprofile.joseph.com.contentprovider.model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 2/13/2018.
 */
public class DBAdapter {

    // Context of application who uses us.
    private final Context context;
    private DBAdapter.DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    // Add a new set of values to the database.
    public long insertRow(String tableName, ContentValues values) {

        // Insert it into the database.
        return db.insert(tableName, null, values);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = Constants.KEY_ROWID + "=" + rowId;
        return db.delete(Constants.DATABASE_TABLE, where, null) != 0;
    }

    public void deleteAll(String DATABASE_TABLE) {
        Cursor c = getAllRows(DATABASE_TABLE,null,null,null,null,null,null);
        long rowId = c.getColumnIndexOrThrow(Constants.KEY_ROWID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }



    public Cursor getAllRows(String DATABASE_TABLE ,String[] projection,  String selection,
                             String[] selectionArgs,String groupBy,String having, String sortOrder) {
        String where = null;
        Cursor c = 	db.query(true,  DATABASE_TABLE, projection,
                where, selectionArgs, null, null, sortOrder, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = Constants.KEY_ROWID + "=" + rowId;
        Cursor c = 	db.query(true, Constants.DATABASE_TABLE, Constants.ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, String email, int phone, int address) {
        String where = Constants.KEY_ROWID + "=" + rowId;


        ContentValues newValues = new ContentValues();
        newValues.put(Constants.KEY_NAME, name);
        newValues.put(Constants.KEY_EMAIL, email);
        newValues.put(Constants.KEY_PHONE, phone);
        newValues.put(Constants.KEY_ADDRESS, address);

        // Insert it into the database.
        return db.update(Constants.DATABASE_TABLE, newValues, where, null) != 0;
    }


    /////////////////////////////////////////////////////////////////////
    //	Private Helper Classes:
    /////////////////////////////////////////////////////////////////////

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL( Constants.DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + Constants.DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }
}