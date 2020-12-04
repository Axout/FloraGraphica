package ru.axout.floragraphica;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_tula",
        foreignKeys = @ForeignKey(entity = MainData.class, parentColumns = "ID", childColumns = "tulip_ID"))
public class TulaData implements Serializable {
    // Создание первичного ключа, автоматическое
     // @PrimaryKey(autoGenerate = true)
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "tulip_ID")
    private int tulip_ID;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "sort")
    private String sort;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "quantity")
    private int quantity;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTulip_ID() {
        return tulip_ID;
    }

    public void setTulip_ID(int tulip_ID) {
        this.tulip_ID = tulip_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
