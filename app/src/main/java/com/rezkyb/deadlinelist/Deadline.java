package com.rezkyb.deadlinelist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deadline")
public class Deadline {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "deadline_name")
    private String mName;
    @ColumnInfo(name = "deadline_details")
    private String mDetails;
    @ColumnInfo(name = "deadline_date")
    private String mDate;

    public Deadline(String name, String details, String date) {
        this.mName = name;
        this.mDetails = details;
        this.mDate = date;
    }

    public String getName() {
        return mName;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getDate() {
        return mDate;
    }
}

