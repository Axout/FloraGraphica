package ru.axout.floragraphica.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface VarshDao {
    // Вставка запроса
    @Insert(onConflict = REPLACE)
    void insert(VarshData varshData);

    // Удаление запроса
    @Delete
    void delete(VarshData varshData);

    // Выдача всех запросов
    @Query("SELECT * FROM table_varsh")
    List<VarshData> getAll();

    // Подсчёт упаковок каждого сорта
    @Query("SELECT sortID, sort, COUNT(*) AS countPack FROM table_varsh GROUP BY sortID")
    List<CountPack> getCountPack();

    @Query("SELECT EXISTS(SELECT * FROM table_varsh WHERE sort = :sort AND packageNumber = :packageNumber)")
    Boolean checkBySortAndPackNumber(String sort, int packageNumber);

    @Query("SELECT * FROM table_varsh WHERE ID = :sortID")
    VarshData getWhereSortID(int sortID);
}
