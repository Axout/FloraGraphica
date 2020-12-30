package ru.axout.floragraphica.data;

import androidx.room.ColumnInfo;

public class CountPack {
    @ColumnInfo(name = "sortID")
    public int sortID;

    @ColumnInfo(name = "sort")
    public String sort;

    @ColumnInfo(name = "countPack")
    public int countPack;
}
