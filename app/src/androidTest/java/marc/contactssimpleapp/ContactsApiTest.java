package marc.contactssimpleapp;

import android.test.AndroidTestCase;

/**
 * Created by gilbertm on 04/02/2016.
 */
public class ContactsApiTest extends AndroidTestCase {

    ContactsApi contactsApi;


    public void testLoadContacts(){

        contactsApi = new ContactsApi(getContext(), new ContactsApi.ContactApiListener() {

            @Override
            public void callBack(Contact[] contacts) {

                assertNotNull(contacts);
                assertTrue(contacts.length > 1);

                Contact contact = contacts[0];

                assertNotNull(contact);
                assertNotNull(contact.getId());

            }
        });

        contactsApi.loadContacts();


    }






}
