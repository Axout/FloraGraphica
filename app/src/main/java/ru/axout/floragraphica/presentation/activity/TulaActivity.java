package ru.axout.floragraphica.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.data.TulaData;
import ru.axout.floragraphica.presentation.adapter.TulaRestAdapter;

import java.util.ArrayList;
import java.util.List;

public class TulaActivity extends AppCompatActivity {

    List<TulaData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    TulaRestAdapter tulaRestAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tula);

        // Присваивание переменным (Assign variables)
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tula);

        // Initialize BD
        database = RoomDB.getInstance(this);
        // Хранение данных БД в data list (Store database value in data list)
        dataList = database.tulaDao().getAll();

        // Инициализация менеджера линейного макета (Initialize linear layout manager)
        linearLayoutManager = new LinearLayoutManager(this);
        // Установка менеджера макета
        recyclerView.setLayoutManager(linearLayoutManager);
        // Инициализация adapter
        tulaRestAdapter = new TulaRestAdapter(TulaActivity.this, dataList);
        // Set adapter
        recyclerView.setAdapter(tulaRestAdapter);

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
    }

    // Создание меню экшн бара
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Наполнение меню. Добавление элементов в акшн бар (Inflate the menu. This adds items to the action bar if it is present.)
        getMenuInflater().inflate(R.menu.menu_tula, menu);
        return true;
    }

    // Обработка выпадающего меню в экшн баре
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // Обработка нажатия кнопок выпадающего меню
        switch (id) {
            case R.id.action_add_manually:
                Intent intent = new Intent(TulaActivity.this, AddTulaManuallyActivity.class);
                startActivity(intent);
                break;
            case R.id.action_send_manually:
                Toast.makeText(TulaActivity.this, "Ещё не работает", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete_manually:
                Toast.makeText(TulaActivity.this, "Ещё не работает", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_add_scan:
                Intent intent2 = new Intent(TulaActivity.this, AddTulaScanActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_send_scan:
                Toast.makeText(TulaActivity.this, "Ещё не работает", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_delete_scan:
                Toast.makeText(TulaActivity.this, "Задолбал! Только первая кнопка работает!!!", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
