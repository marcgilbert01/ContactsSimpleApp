package marc.contactssimpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class ContactListActivity extends AppCompatActivity implements ContactsApi.ContactApiListener {

    private ContactsApi contactsApi;

    ListView listViewContacts;
    Contact[] contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact List");

        // GET LIST VIEW
        listViewContacts = (ListView) findViewById(R.id.listViewContacts);

        // SET API CALLBACK
        contactsApi = new ContactsApi(this, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        contactsApi.loadContacts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
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
    public void callBack(Contact[] contacts) {

        if( listViewContacts!=null && contacts!=null ){
            listViewContacts.setAdapter(new ContactListViewAdapter(this, contacts));
            listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Contact contact = (Contact) listViewContacts.getItemAtPosition(position);
                    openContact(contact);
                }
            });
        }

    }


    // START ACTIVITY TO SHOW CONTACT DETAILS
    private void openContact(Contact contact){

        if( contact!=null ) {
            Intent intent = new Intent(this, ContactDetailsActivity.class);
            intent.putExtra(ContactDetailsActivity.ARG_CONTACT_PARAM, contact);
            startActivity(intent);
        }
    }


}
