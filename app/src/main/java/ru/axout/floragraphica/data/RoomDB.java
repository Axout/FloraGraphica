package ru.axout.floragraphica.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
Объявление БД
Аннотацией Database помечаем основной класс по работе с базой данных.
Этот класс должен быть абстрактным и наследовать RoomDatabase.
В параметрах аннотации Database указываем, какие Entity будут использоваться, и версию базы.
Для каждого Entity класса из списка entities будет создана таблица.
 */
@Database(entities = {MainData.class, TulaData.class, FoodData.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // Создаю экземпляр БД
    private static RoomDB database;
    // Имя БД
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context) {
        // проверка
        if (database == null) {
            // Если БД нет, то инициализируем её
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    // В Database классе описываем абстрактные методы для получения Dao объектов, которые будут использоваться.
    public abstract MainDao mainDao();
    public abstract TulaDao tulaDao();
    public abstract FoodDao foodDao();
}

