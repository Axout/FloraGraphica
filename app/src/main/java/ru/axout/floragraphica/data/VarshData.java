package ru.axout.floragraphica.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "table_varsh",
        foreignKeys = @ForeignKey(entity = MainData.class, parentColumns = "ID", childColumns = "sortID",
                onDelete = CASCADE))
public class VarshData implements Serializable {
    // Создание первичного ключа, автоматическое
    // @PrimaryKey(autoGenerate = true)
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "sortID")
    private int sortID;

    @ColumnInfo(name = "sort")
    private String sort;

    @ColumnInfo(name = "packageNumber")
    private int packageNumber;

    @ColumnInfo(name = "dateAdded")
    private String dateAdded;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
