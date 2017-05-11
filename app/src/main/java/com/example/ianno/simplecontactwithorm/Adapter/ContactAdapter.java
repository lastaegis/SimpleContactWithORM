package com.example.ianno.simplecontactwithorm.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ianno.simplecontactwithorm.Entity.Contact;
import com.example.ianno.simplecontactwithorm.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private TextView textViewFullname;
    private TextView textViewIdContact;
    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact =  getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_design, parent, false);
        }
        textViewFullname = (TextView)convertView.findViewById(R.id.itemFullname);
        textViewIdContact= (TextView)convertView.findViewById(R.id.itemContactId);

        textViewFullname.setText(contact.getContactName());
        textViewIdContact.setText(String.valueOf(contact.getId()));
        return convertView;
    }
}
