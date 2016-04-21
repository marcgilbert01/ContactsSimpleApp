package marc.contactssimpleapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

/**
 * Created by gilbertm on 04/02/2016.
 */
public class DaoContacts extends SQLiteOpenHelper{


    static private final String CONTACTS_DB_NAME = "contacts.db";
    static private final String CONTACTS_TABLE_NAME = "contactList";

    Context context;

    public DaoContacts(Context context) {
        super(context, CONTACTS_DB_NAME, null, 1 );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // CREATE TABLE
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + CONTACTS_TABLE_NAME + " ( " +
                "id INTEGER PRIMARY KEY," +
                "firstName          TEXT," +
                "surname            TEXT," +
                "address            TEXT," +
                "phoneNumber        TEXT," +
                "email              TEXT," +
                "createdAt          TEXT," +
                "updatedAt          TEXT" +
                ")");
    }

    public void insertOrUpdateContacts(Contact[] contacts){

        SQLiteDatabase db = getWritableDatabase();

        try {
            for (Contact contact : contacts) {
                db.execSQL("INSERT OR REPLACE INTO " + CONTACTS_TABLE_NAME + " VALUES(" +
                        contact.getId() + "," +
                        "'" + contact.getFirstName() + "'," +
                        "'" + contact.getSurname() + "'," +
                        "'" + contact.getAddress() + "'," +
                        "'" + contact.getPhoneNumber() + "'," +
                        "'" + contact.getEmail() + "'," +
                        "'" + contact.getCreatedAt() + "'," +
                        "'" + contact.getUpdatedAt() + "')");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            db.close();
        }

    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Contact[] getAllContacts() {

        Contact[] contacts = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query( CONTACTS_TABLE_NAME , null, null, null, null, null, null);
        if( cursor.getCount()>0  ) {

            contacts = new Contact[cursor.getCount()];
            cursor.moveToFirst();
            for( int r=0 ; r<cursor.getCount() ; r++ ) {
                Contact contact = readContact(cursor);
                contacts[r] = contact;
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return contacts;
    }


    private Contact readContact(Cursor cursor){

        Contact contact = new Contact();

        contact.setId(cursor.getInt(0));
        contact.setFirstName(cursor.getString(1));
        contact.setSurname(cursor.getString(2));
        contact.setAddress(cursor.getString(3));
        contact.setPhoneNumber(cursor.getString(4));
        contact.setEmail(cursor.getString(5));
        contact.setCreatedAt(cursor.getString(6));
        contact.setUpdatedAt( cursor.getString(7) );

        return contact;
    }


}
