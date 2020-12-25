package ru.axout.floragraphica.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SalesDao {
    // Вставка запроса
    @Insert(onConflict = REPLACE)
    void insert(SalesData salesData);

    // Удаление запроса
    @Delete
    void delete(SalesData salesData);

    // Выдача всех запросов
    @Query("SELECT * FROM table_sales")
    List<SalesData> getAll();

    // Подсчёт упаковок каждого сорта
    @Query("SELECT sortID, sort, COUNT(*) AS countPack FROM table_sales GROUP BY sortID")
    List<CountPack> getCountPack();

    @Query("SELECT * FROM table_sales WHERE ID = :sortID")
    TulaData getWhereSortID(int sortID);
}
