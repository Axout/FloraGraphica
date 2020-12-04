package ru.axout.floragraphica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TulaActivity extends AppCompatActivity {

//    // Инициализация переменных
//    RecyclerView recyclerView;
//
//    // Список с данными (картежи)
//    // БД создаётся в MainData
//    List<TulaData> dataList = new ArrayList<>();
//    LinearLayoutManager linearLayoutManager;
//    RoomDB database;
//    TulaAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tula);

//        // Присваивание переменным (Assign variables)
//        recyclerView = findViewById(R.id.recycler_view);
//
//        // Initialize BD
//        database = RoomDB.getInstance(this);
//        // Хранение данных БД в data list (Store database value in data list)
//        dataList = database.tulaDao().getAll();
//
//        // Инициализация менеджера линейного макета (Initialize linear layout manager)
//        linearLayoutManager = new LinearLayoutManager(this);
//        // Установка менеджера макета
//        recyclerView.setLayoutManager(linearLayoutManager);
//        // Инициализация adapter
//        adapter = new TulaAdapter(TulaActivity.this, dataList);
//        // Set adapter
//        recyclerView.setAdapter(adapter);

//        // Получение данных
//        String sID = editTextID.getText().toString().trim();
//        String sType = spTypes.getSelectedItem().toString();
//        String sColor = spColors.getSelectedItem().toString();
//        String sSort = spSorts.getSelectedItem().toString();
//
//        // Initialize main data
//        MainData data = new MainData();
//        // Передача данных в MainData
//        data.setID(Integer.parseInt(sID));
//        data.setType(sType);
//        data.setColor(sColor);
//        data.setSort(sSort);
//        // Вставка данных (картежа) в БД (Insert text in database)
//        database.mainDao().insert(data);
//        // Очистка списка данных, что выводится пользователю
//        dataList.clear();
//        // Заново данные из БД добавлются в список
//        dataList.addAll(database.mainDao().getAll());
//        // Уведомление после вставки данных (Notify when data is inserted)
//        adapter.notifyDataSetChanged();
    }
}
