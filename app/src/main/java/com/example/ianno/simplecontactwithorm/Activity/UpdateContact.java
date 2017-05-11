package com.example.ianno.simplecontactwithorm.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ianno.simplecontactwithorm.Entity.Contact;
import com.example.ianno.simplecontactwithorm.R;

public class UpdateContact extends AppCompatActivity {

    TextView textViewUpdateFullname;
    TextView textViewUpdatePhone;
    TextView textViewUpdateEmail;
    String contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        Bundle bundle = getIntent().getExtras();
        contactId = bundle.getString("contactId");

        Contact contact = Contact.findById(Contact.class, Integer.parseInt(contactId));

        textViewUpdateFullname    = (TextView)findViewById(R.id.updateFullname);
        textViewUpdatePhone       = (TextView)findViewById(R.id.updatePhone);
        textViewUpdateEmail       = (TextView)findViewById(R.id.updateEmail);

        textViewUpdateFullname.setText(contact.getContactName());
        textViewUpdatePhone.setText(contact.getContactPhone());
        textViewUpdateEmail.setText(contact.getContactEmail());
    }

    public void updateContact(View view)
    {
        Contact contact = Contact.findById(Contact.class, Integer.parseInt(contactId));

        textViewUpdateFullname    = (TextView)findViewById(R.id.updateFullname);
        textViewUpdatePhone       = (TextView)findViewById(R.id.updatePhone);
        textViewUpdateEmail       = (TextView)findViewById(R.id.updateEmail);

        String fullname = textViewUpdateFullname.getText().toString();
        String phone    = textViewUpdatePhone.getText().toString();
        String email    = textViewUpdateEmail.getText().toString();

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
