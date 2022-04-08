package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipecode implements Parcelable {


    String effect;
    int petType;


    public Recipecode(String effect, int petType){
        this.effect=effect;
        this.petType=petType;
    }

    public Recipecode(Parcel in){
        effect=in.readString();
        petType=in.readInt();
    }
    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getPetType() {
        return petType;
    }

    public void setPetType(int petType) {
        this.petType = petType;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(effect);
        parcel.writeInt(petType);
    }

    public static final Creator<Recipecode> CREATOR=new Creator<Recipecode>() {
        @Override
        public Recipecode createFromParcel(Parcel parcel) {
            return new Recipecode(parcel);
        }

        @Override
        public Recipecode[] newArray(int size) {
            return new Recipecode[size];
            }
        };

    @Override
    public int describeContents() {
        return 0;
    }

}
