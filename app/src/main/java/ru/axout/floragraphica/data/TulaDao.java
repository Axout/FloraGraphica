package ru.axout.floragraphica.data;

import androidx.room.*;

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

    // Выдача всех запросов
    @Query("SELECT * FROM table_tula")
    List<TulaData> getAll();

    // Подсчёт упаковок каждого сорта
    @Query("SELECT sortID, sort, COUNT(*) AS countPack FROM table_tula GROUP BY sortID")
    List<CountPack> getCountPack();

    @Query("SELECT EXISTS(SELECT * FROM table_tula WHERE sort = :sort AND packageNumber = :packageNumber)")
    Boolean checkBySortAndPackNumber(String sort, int packageNumber);

    @Query("DELETE FROM table_tula WHERE sort = :sortID AND packageNumber = :packageNumber")
    void deleteBySortIDAndPackNum(int sortID, int packageNumber);

    @Query("SELECT * FROM table_tula WHERE ID = :sortID")
    TulaData getWhereSortID(int sortID);

    @Query("SELECT * FROM table_tula WHERE ID = :sortID AND packageNumber = :packageNumber")
    TulaData getWhereSortIDAndPackNum(int sortID, int packageNumber);

//    // Обновление запросов
//    @Query("UPDATE table_tula SET quantity = :sQuantity WHERE ID = :sID")
//    void update(int sID, String sQuantity);
}
