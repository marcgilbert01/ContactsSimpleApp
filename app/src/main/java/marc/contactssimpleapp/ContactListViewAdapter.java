package marc.contactssimpleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gilbertm on 04/02/2016.
 */
public class ContactListViewAdapter extends ArrayAdapter<Contact>{

    private Context context;
    private Contact[] contacts;


    public ContactListViewAdapter(Context context, Contact[] contacts) {
        super(context, -1, contacts);
        this.contacts = contacts;
        this.context  = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View contactInListView = LayoutInflater.from(context).inflate(R.layout.contact_in_list, null );

        TextView textViewFirstName = (TextView) contactInListView.findViewById(R.id.textViewFirstName);
        textViewFirstName.setText(contacts[position].getFirstName());

        TextView textViewSurname   = (TextView) contactInListView.findViewById(R.id.textViewSurname);
        textViewSurname.setText(contacts[position].getSurname());

        ImageView imageView = (ImageView) contactInListView.findViewById(R.id.imageViewPhotoInList);
        PhotosApi photosApi = new PhotosApi(context, contacts[position]  );
        photosApi.loadImage(imageView);

        return contactInListView;

    }
}
