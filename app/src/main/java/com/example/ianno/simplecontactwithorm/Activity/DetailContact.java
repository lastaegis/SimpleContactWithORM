package com.example.ianno.simplecontactwithorm.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ianno.simplecontactwithorm.Entity.Contact;
import com.example.ianno.simplecontactwithorm.R;

public class DetailContact extends AppCompatActivity {

    private TextView textViewDetailFullname;
    private TextView textViewDetailPhone;
    private TextView textViewDetailEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        textViewDetailFullname  = (TextView)findViewById(R.id.detailFullname);
        textViewDetailPhone     = (TextView)findViewById(R.id.detailPhone);
        textViewDetailEmail     = (TextView)findViewById(R.id.detailEmail);

        Bundle bundle = getIntent().getExtras();
        String contactId = bundle.getString("contactId");

        Contact contact = Contact.findById(Contact.class, Integer.parseInt(contactId));

        String contactName = contact.getContactName();
        String contactPhone= contact.getContactPhone();
        String contactEmail= contact.getContactEmail();

        textViewDetailFullname.setText(contactName);
        textViewDetailPhone.setText(contactPhone);
        textViewDetailEmail.setText(contactEmail);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "");
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
