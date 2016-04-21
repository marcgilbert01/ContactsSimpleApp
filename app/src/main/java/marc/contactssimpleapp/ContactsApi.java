package marc.contactssimpleapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * Created by gilbertm on 04/02/2016.
 */
public class ContactsApi {

    private final String URL = "http://fast-gorge.herokuapp.com/contacts";

    Context context;
    ContactApiListener contactApiListener;

    public ContactsApi(Context context, ContactApiListener contactApiListener) {
        this.context = context;
        this.contactApiListener = contactApiListener;
    }

    public void loadContacts(){

        if( context!=null ) {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Contact[] contacts = new Gson().fromJson(response, Contact[].class);
                        // CALL BACK UI
                        if( contactApiListener!=null ){
                            contactApiListener.callBack(contacts);
                        }
                        // SAVE TO DB
                        if( contacts!=null && contacts.length>0 ){
                           saveContactsToDb(contacts);
                        }
                    }
                    },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // IF ERROR TRY TO GET CONTACT FROM DB
                        Contact[] contacts = getContactsFromDb();
                        if( contacts!=null ){
                            contactApiListener.callBack(contacts);
                        }
                        else {
                            error.printStackTrace();
                        }
                }
            });
            queue.add(stringRequest);
        }
    }


    private Contact[] getContactsFromDb() {

        Contact[] contacts = null;

        DaoContacts daoContacts = new DaoContacts(context);
        contacts  = daoContacts.getAllContacts();

        return contacts;
    }


    private void saveContactsToDb(final Contact[] contacts) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                DaoContacts daoContacts = new DaoContacts(context);
                daoContacts.insertOrUpdateContacts(contacts);
            }
        }.start();

    }


    public interface ContactApiListener {

        void callBack(Contact[] contacts);

    }



}
