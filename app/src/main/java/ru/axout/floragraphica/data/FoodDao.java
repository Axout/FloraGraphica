package ru.axout.floragraphica.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FoodDao {
    // Вставка запроса
    @Insert(onConflict = REPLACE)
    void insert(FoodData foodData);

    // Удаление запроса
    @Delete
    void delete(FoodData foodData);

    // Выдача всех запросов
    @Query("SELECT * FROM table_food")
    List<FoodData> getAll();

    // Подсчёт упаковок каждого сорта
    @Query("SELECT sortID, sort, COUNT(*) AS countPack FROM table_food GROUP BY sortID")
    List<CountPack> getCountPack();

    @Query("SELECT EXISTS(SELECT * FROM table_food WHERE sort = :sort AND packageNumber = :packageNumber)")
    Boolean checkBySortAndPackNumber(int sort, int packageNumber);

    @Query("SELECT * FROM table_food WHERE sortID = :sortID")
    FoodData getWhereSortID(int sortID);

    @Query("SELECT * FROM table_food WHERE sortID = :sortID AND packageNumber = :packageNumber")
    FoodData getWhereSortIDAndPackNum(int sortID, int packageNumber);
}
