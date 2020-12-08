package ru.axout.floragraphica.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ru.axout.floragraphica.R;


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
