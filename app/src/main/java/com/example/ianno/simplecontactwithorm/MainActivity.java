package com.example.ianno.simplecontactwithorm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ianno.simplecontactwithorm.Activity.AddContact;
import com.example.ianno.simplecontactwithorm.Activity.DetailContact;
import com.example.ianno.simplecontactwithorm.Activity.UpdateContact;
import com.example.ianno.simplecontactwithorm.Adapter.ContactAdapter;
import com.example.ianno.simplecontactwithorm.Entity.Contact;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewContact;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), AddContact.class), 1);
            }
        });
        listViewContact = (ListView)findViewById(R.id.listViewContact);
        registerForContextMenu(listViewContact);
        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewContactId = (TextView) view.findViewById(R.id.itemContactId);
                String contactId = textViewContactId.getText().toString();
                Intent intent = new Intent(view.getContext(), DetailContact.class);
                intent.putExtra("contactId", contactId);
                startActivityForResult(intent, 1);
            }
        });
        inflateListViewContact();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
                inflateListViewContact();
            }
            if(resultCode == Activity.RESULT_CANCELED)
            {
                if(!data.getStringExtra("result").equalsIgnoreCase(""))
                {
                    Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select action");
        menu.add(0, 1, 0, R.string.edit);
        menu.add(0, 2, 1, R.string.delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        View view;
        TextView textViewContact;
        String contactId;
        switch (item.getItemId()) {
            case 1:
                view = adapterContextMenuInfo.targetView;
                textViewContact= (TextView)view.findViewById(R.id.itemContactId);
                contactId = textViewContact.getText().toString();

                Intent intent = new Intent(this, UpdateContact.class);
                intent.putExtra("contactId", contactId);
                startActivityForResult(intent, 1);

                return true;
            case 2:
                view = adapterContextMenuInfo.targetView;
                textViewContact = (TextView)view.findViewById(R.id.itemContactId);
                contactId = textViewContact.getText().toString();

                Contact contact = Contact.findById(Contact.class, Integer.parseInt(contactId));
                contact.delete();

                inflateListViewContact();

                return true;
            default:

        }
        return super.onContextItemSelected(item);
    }

    public void inflateListViewContact()
    {
        long totalContact = Contact.count(Contact.class, "",null);
        if(totalContact > 0)
        {
            List<Contact> contacts = Contact.listAll(Contact.class);
            contactAdapter = new ContactAdapter(this, contacts);
            listViewContact.setAdapter(contactAdapter);
        }
        else
        {
            Toast.makeText(this, "No contact found", Toast.LENGTH_SHORT).show();
        }
    }
}
