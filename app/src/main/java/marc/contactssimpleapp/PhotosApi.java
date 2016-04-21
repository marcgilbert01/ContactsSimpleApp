package marc.contactssimpleapp;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by gilbertm on 04/02/2016.
 */
public class PhotosApi {


    static private final String AVATAR_URL = "http://api.adorable.io/avatars/100/";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Context context;
    Contact contact;

    public PhotosApi(Context context, Contact contact) {
        this.context = context;
        this.contact = contact;
    }


    public void loadImage(ImageView imageView ){

        // IF URL FOUND IN CONTACT LOAD


        // ELSE GET AVATAR FROM CONTACT EMAIL
        if( contact.getEmail()!=null && contact.getEmail().matches( EMAIL_PATTERN ) ) {

            Picasso.with(context).load( AVATAR_URL + contact.getEmail() ).into(imageView) ;
        }
        else{

            Picasso.with(context).load( AVATAR_URL + "noimage" ).into(imageView) ;
        }

    }


}
