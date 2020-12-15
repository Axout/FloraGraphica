package ru.axout.floragraphica.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

import java.io.Serializable;

@Entity(tableName = "table_tula",
        foreignKeys = @ForeignKey(entity = MainData.class, parentColumns = "ID", childColumns = "sort_ID"))
public class TulaData implements Serializable {
    // Создание первичного ключа, автоматическое
     // @PrimaryKey(autoGenerate = true)
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "sort_ID")
    private int sort_ID;

    @ColumnInfo(name = "packageNumber")
    private int packageNumber;

    @ColumnInfo(name = "dateAdded")
    private String dateAdded;

//    @ColumnInfo(name = "color")
//    private String color;
//
//    @ColumnInfo(name = "quantity")
//    private int quantity;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSort_ID() {
        return sort_ID;
    }

    public void setSort_ID(int sort_ID) {
        this.sort_ID = sort_ID;
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
