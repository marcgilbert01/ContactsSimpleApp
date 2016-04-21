package marc.contactssimpleapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gilbertm on 04/02/2016.
 */
public class Contact implements Parcelable {

    private Integer id;
    private String first_name;
    private String surname;
    private String address;
    private String phone_number;
    private String email;
    private String createdAt;
    private String updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }



    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(first_name);
        dest.writeString(surname);
        dest.writeString(address);
        dest.writeString(phone_number);
        dest.writeString(email);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);

    }

    public Contact(){

    }

    public Contact(Parcel p) {

        id = p.readInt();
        first_name = p.readString();
        surname = p.readString();
        address = p.readString();
        phone_number = p.readString();
        email = p.readString();
        createdAt = p.readString();
        updatedAt = p.readString();

    }


    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


}
