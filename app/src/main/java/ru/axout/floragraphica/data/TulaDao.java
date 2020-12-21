package ru.axout.floragraphica.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TulaDao {
    // Вставка запроса
    @Insert(onConflict = REPLACE)
    void insert(TulaData tulaData);

    // Удаление запроса
    @Delete
    void delete(TulaData tulaData);

//    // Обновление запросов
//    @Query("UPDATE table_tula SET quantity = :sQuantity WHERE ID = :sID")
//    void update(int sID, String sQuantity);

    // Выдача всех запросов
    @Query("SELECT * FROM table_tula")
    List<TulaData> getAll();

    // Подсчёт упаковок каждого сорта
    @Query("SELECT sortID, sort, COUNT(*) AS countPack FROM table_tula GROUP BY sortID")
    List<CountPack> getCountPack();

    @Query("SELECT EXISTS(SELECT * FROM table_tula WHERE sort = :sort AND packageNumber = :packageNumber)")
    Boolean checkBySortAndPackNumber(String sort, int packageNumber);
}
