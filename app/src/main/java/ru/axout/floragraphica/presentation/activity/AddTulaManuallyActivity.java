package ru.axout.floragraphica.presentation.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.data.TulaData;
import ru.axout.floragraphica.presentation.adapter.TulaAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddTulaManuallyActivity extends AppCompatActivity {

    Spinner spinnerID;
    EditText editTextQuantity;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<TulaData> tulaDataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    TulaAdapter tulaAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tula_manually);

        // Присваивание переменным (Assign variables)
        spinnerID = findViewById(R.id.spID);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        btAdd = findViewById(R.id.bt_addTula);
        btReset = findViewById(R.id.bt_resetTula);
        recyclerView = findViewById(R.id.recycler_view_tula);

        Integer[] arrayID = {1,2,3,4,5,7};

        final Spinner spID = findViewById(R.id.spID);

        // Выпадающий список "Код сорта тюльпана"
        ArrayAdapter<Integer> idAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayID);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spID.setAdapter(idAdapter);
        spID.setPrompt("Код сорта тюльпана");

        // Initialize BD
        database = RoomDB.getInstance(this);
        // Хранение данных БД в data list (Store database value in data list)
        tulaDataList = database.tulaDao().getAll();

        // Инициализация менеджера линейного макета (Initialize linear layout manager)
        linearLayoutManager = new LinearLayoutManager(this);
        // Установка менеджера макета
        recyclerView.setLayoutManager(linearLayoutManager);
        // Инициализация adapter
        tulaAdapter = new TulaAdapter(AddTulaManuallyActivity.this, tulaDataList);
        // Set adapter
        recyclerView.setAdapter(tulaAdapter);
    }
}
