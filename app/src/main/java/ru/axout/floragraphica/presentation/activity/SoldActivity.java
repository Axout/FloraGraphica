package ru.axout.floragraphica.presentation.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.axout.floragraphica.data.CountPack;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.presentation.adapter.TulaRestAdapter;

import java.util.List;

public class SoldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tula);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Initialize BD
        RoomDB database = RoomDB.getInstance(this);
        // Запрос в БД, полученные данные записывает в List<CountPack>
        List<CountPack> countPackList = database.salesDao().getCountPack();
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
}
