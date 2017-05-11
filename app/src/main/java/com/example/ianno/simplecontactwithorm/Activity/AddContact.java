package com.example.ianno.simplecontactwithorm.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ianno.simplecontactwithorm.Entity.Contact;
import com.example.ianno.simplecontactwithorm.R;

public class AddContact extends AppCompatActivity {

    TextView textViewFullName;
    TextView textViewPhone;
    TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void insertContact(View view)
    {
        textViewFullName    = (TextView)findViewById(R.id.fullname);
        textViewPhone       = (TextView)findViewById(R.id.phone);
        textViewEmail       = (TextView)findViewById(R.id.email);

        String fullname = textViewFullName.getText().toString();
        String phone    = textViewPhone.getText().toString();
        String email    = textViewEmail.getText().toString();

        Contact contact = new Contact();
        contact.setContactName(fullname);
        contact.setContactPhone(phone);
        contact.setContactEmail(email);
        contact.save();

        Intent intent = new Intent();
        intent.putExtra("result", "Success");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void backToMainMenu(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("result", "Cancel by user");
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "Cancel by user");
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
