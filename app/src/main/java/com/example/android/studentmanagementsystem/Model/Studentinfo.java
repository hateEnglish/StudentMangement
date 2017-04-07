package com.example.android.studentmanagementsystem.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mark63 on 5/4/17.
 */

public class Studentinfo implements Parcelable {

    public static final Creator<Studentinfo> CREATOR = new Creator<Studentinfo>() {
        @Override
        public Studentinfo createFromParcel(final Parcel in) {
            return new Studentinfo(in);
        }

        @Override
        public Studentinfo[] newArray(final int size) {
            return new Studentinfo[size];
        }
    };
    private String mname;
    private String mschool;
    private String mrollno;
    private String mEmail;

    /**
     *
     * @param name name of student
     * @param school school name
     * @param rollno  roll no
     * @param email   email
     */
    public Studentinfo(final String name, final String school, final String rollno, final String email) {
        this.mname = name;
        this.mschool = school;
        this.mrollno = rollno;
        this.mEmail = email;
    }

    /**
     *
     * @param in help in reading
     */
    protected Studentinfo(final Parcel in) {
        mname = in.readString();
        mschool = in.readString();
        mrollno = in.readString();
        mEmail = in.readString();
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return mname;
    }

    /**
     *
     * @return schoolname
     */
    public String getSchool() {
        return mschool;
    }

    /**
     *
     * @return rollno
     */

    public String getRollno() {
        return mrollno;
    }

    /**
     *
     * @return email
     */

    public String getEmail() {
        return mEmail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mname);
        dest.writeString(mschool);
        dest.writeString(mrollno);
        dest.writeString(mEmail);
    }


}
