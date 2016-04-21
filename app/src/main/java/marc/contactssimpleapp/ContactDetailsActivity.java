package marc.contactssimpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetailsActivity extends AppCompatActivity {

    public static final String ARG_CONTACT_PARAM = "contact";

    TextView textViewFirstName;
    TextView textViewSurname;
    TextView textViewAddress;
    TextView textViewPhoneNumber;
    TextView textViewEmail;
    TextView textViewCreatedAt;
    TextView textViewUpdatedAt;
    ImageView imageView;

    Contact contact;
    PhotosApi photosApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Contact Details");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // RETRIEVE CONTACT DETAILS
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        contact = bundle.getParcelable(ARG_CONTACT_PARAM);

        // GET VIEWS
        textViewFirstName = (TextView) findViewById(R.id.textViewFirstNameDetails);
        textViewSurname   = (TextView) findViewById(R.id.textViewSurnameDetails);
        textViewAddress   = (TextView) findViewById(R.id.textViewAddressDetails);
        textViewPhoneNumber = (TextView) findViewById(R.id.textViewPhoneNumberDetails);
        textViewEmail     = (TextView) findViewById(R.id.textViewEmailDetails);
        textViewCreatedAt = (TextView) findViewById(R.id.textViewCreatedAtDetails);
        textViewUpdatedAt = (TextView) findViewById(R.id.textViewUpdatedAtDetails);
        imageView = (ImageView) findViewById(R.id.imageViewContactPhoto);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if( contact!=null ){

            textViewFirstName.setText( contact.getFirstName() );
            textViewSurname.setText( contact.getSurname() );
            textViewAddress.setText( contact.getAddress() );
            textViewPhoneNumber.setText( contact.getPhoneNumber() );
            textViewEmail.setText( contact.getEmail() );
            textViewCreatedAt.setText( "Created  " + contact.getCreatedAt() );
            textViewUpdatedAt.setText( "Modified " + contact.getUpdatedAt() );
            photosApi = new PhotosApi(this, contact);
            photosApi.loadImage(imageView);
        }

    }

}
