package com.rs.core.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    public String type;
    /**
     * Contact name
     */
    public String name;
    /**
     * Contact phone number
     */
    public String phone;

    public String sortLetters;

    public Contact() {
    }

    public Contact(String type, String name, String phone, String sortLetters) {
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.sortLetters = sortLetters;
        if (this.name.endsWith("/M")) this.name = this.name.substring(0, this.name.length() - 2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(sortLetters);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel parcel) {
            Contact contact = new Contact();
            contact.type = parcel.readString();
            contact.name = parcel.readString();
            contact.phone = parcel.readString();
            contact.sortLetters = parcel.readString();
            return contact;
        }

        @Override
        public Contact[] newArray(int i) {
            return new Contact[i];
        }
    };

    @Override
    public String toString() {
        return "Contact{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                '}';
    }
}
