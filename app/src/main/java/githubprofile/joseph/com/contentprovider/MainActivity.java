package githubprofile.joseph.com.contentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import githubprofile.joseph.com.contentprovider.model.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText tvName;
    public EditText tvEmail;
    public EditText tvPhone;
    public EditText tvAddress;
    private String TAG;
    Button btnInsert 	;
    Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvName = (EditText) findViewById(R.id.etName);
        tvEmail = (EditText) findViewById(R.id.etEmail);
        tvPhone = (EditText) findViewById(R.id.etPhone);
        tvAddress = (EditText) findViewById(R.id.etAddress);

        btnInsert 		= (Button) findViewById(R.id.btnInsert);
        btnDisplay 		= (Button) findViewById(R.id.btnDisplay);

        btnInsert.setOnClickListener(this);
        btnDisplay.setOnClickListener(this);

    }


    public void displayListView() {

        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);

    }


    private void insert() {

        String name = tvName.getText().toString();
        String email = tvEmail.getText().toString();
        String phone = tvPhone.getText().toString();
        String address = tvAddress.getText().toString();


        ContentValues initialValues = new ContentValues();
        initialValues.put(Constants.KEY_NAME, name);
        initialValues.put(Constants.KEY_EMAIL, email);
        initialValues.put(Constants.KEY_PHONE, phone);
        initialValues.put(Constants.KEY_ADDRESS, address);



        Uri uri = Constants.CONTENT_URI;
        Uri uriRowInserted = getContentResolver().insert(uri, initialValues);
        Toast.makeText(this, "Items  inserted at: " + uriRowInserted, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Items inserted at: " + uriRowInserted);

        tvName.setText("");
        tvEmail.setText("");
        tvPhone.setText("");
        tvAddress.setText("");

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnInsert:
                insert();
                break;

            case R.id.btnDisplay:
                displayListView();
                break;


        }
    }
}
