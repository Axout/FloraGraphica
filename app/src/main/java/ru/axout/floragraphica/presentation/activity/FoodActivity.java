package ru.axout.floragraphica.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.CountPack;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.presentation.adapter.TulaRestAdapter;

import java.util.List;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tula);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Initialize BD
        RoomDB database = RoomDB.getInstance(this);
        // Запрос в БД, полученные данные записывает в List<CountPack>
        List<CountPack> countPackList = database.foodDao().getCountPack();
        // Присваивание переменным (Assign variables)
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tula);
        // Инициализация менеджера линейного макета (Initialize linear layout manager)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Установка менеджера макета
        recyclerView.setLayoutManager(linearLayoutManager);
        // Инициализация adapter
        TulaRestAdapter tulaRestAdapter = new TulaRestAdapter(countPackList);
        // Set adapter
        recyclerView.setAdapter(tulaRestAdapter);
    }

    // Создание меню экшн бара
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Наполнение меню. Добавление элементов в акшн бар (Inflate the menu. This adds items to the action bar if it is present.)
        getMenuInflater().inflate(R.menu.menu_place, menu);
        return true;
    }

    // Обработка выпадающего меню в экшн баре
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // Обработка нажатия кнопок выпадающего меню
        switch (id) {
//            case R.id.action_sendToFood:
//                Intent intent1 = new Intent(FoodActivity.this, SendToFoodActivity.class);
//                startActivity(intent1);
//                break;
//            case R.id.action_sendToVarsh:
//                Intent intent2 = new Intent(FoodActivity.this, SendToVarshActivity.class);
//                startActivity(intent2);
//                break;
            case R.id.action_sold:
                Intent intent3 = new Intent(FoodActivity.this, SalesFoodActivity.class);
                startActivity(intent3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
