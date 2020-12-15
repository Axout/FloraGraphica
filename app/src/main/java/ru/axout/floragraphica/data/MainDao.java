package ru.axout.floragraphica.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/*
Data Access Object
Здесь описаны методы для работы с БД
 */
@Dao
public interface MainDao {
    // Вставка запроса
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    // Удаление запроса
    @Delete
    void delete(MainData mainData);

    // Удаление всех запросов
    @Delete
    void reset(List<MainData> mainData);

//    // Обновление запросов
//    @Query("UPDATE table_main SET quantity = :sQuantity WHERE ID = :sID")
//    void update(int sID, String sQuantity);

    // Выдача всех запросов
    @Query("SELECT * FROM table_main")
    List<MainData> getAll();

    @Query("SELECT * FROM table_main WHERE ID = :id")
    MainData getWhereID(int id);

    @Query("SELECT * FROM table_main WHERE sort = :sort")
    MainData getWhereSort(String sort);

//    @Query("SELECT * FROM table_main WHERE ID IN (:listID)")
//    List<MainData> getWhereID(List<Integer> listID);
}
