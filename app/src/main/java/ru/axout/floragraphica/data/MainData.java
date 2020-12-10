package ru.axout.floragraphica.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/*
Аннотацией Entity помечаем объект (таблицу), который хранится в БД
 */
@Entity(tableName = "table_Assortment")
public class MainData implements Serializable {
    // Создание первичного ключа, автоматическое
    @PrimaryKey // @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "sort")
    private String sort;

    @ColumnInfo(name = "color")
    private String color;

    // Генерируем геттеры и сеттеры

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}

