package ru.axout.floragraphica.data;

import androidx.room.ColumnInfo;

public class CountPack {
    @ColumnInfo(name = "sort_ID")
    public int sort_ID;

    @ColumnInfo(name = "sort")
    public String sort;

    @ColumnInfo(name = "countPack")
    public int countPack;
}