package ru.axout.floragraphica.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.MainData;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.data.TulaData;
import ru.axout.floragraphica.presentation.adapter.TulaAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddTulaManuallyActivity extends AppCompatActivity {

    List<TulaData> tulaDataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    TulaAdapter tulaAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tula_manually);

        // Присваивание переменным (Assign variables)
        final Spinner spinnerID = findViewById(R.id.spID);
        final EditText editTextQuantity = findViewById(R.id.editTextQuantity);
        Button btAdd = findViewById(R.id.bt_addTula);
        Button btReset = findViewById(R.id.bt_resetTula);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tula);

        // Initialize BD
        database = RoomDB.getInstance(this);

        // Считаем из БД список кодов сортов
        List<Integer> listID = getListID();

        // Создание выпадающего списка "Код сорта тюльпана"
        createSpinner(listID);

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

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение данных из выпадающего списка (spinner)
                int idFromSpinner = Integer.parseInt(spinnerID.getSelectedItem().toString());
                // Получение строки из table_main по ID
                MainData mainData = database.mainDao().getWhereID(idFromSpinner);
                // Получение данных (количество тюльпанов) из editText
                String sQuantity = editTextQuantity.getText().toString().trim();

                // Запись данных в table_tula
                // Проверка пустой строки
                if (!sQuantity.equals("")) { // Если строка не пустая
                    // Initialize  tulaData
                    TulaData tulaData = new TulaData();
                    // Передача данных в tulaData
                    tulaData.setTulip_ID(mainData.getID());
                    tulaData.setType(mainData.getType());
                    tulaData.setColor(mainData.getColor());
                    tulaData.setSort(mainData.getSort());
                    tulaData.setQuantity(Integer.parseInt(sQuantity));
                    // Вставка данных (картежа) в БД (Insert text in database)
                    database.tulaDao().insert(tulaData);
                    // Очитка edittext
                    editTextQuantity.setText("");
//                    // Очистка списка данных
//                    dataList.clear();
//                    // Заново данные из БД добавлются в список
//                    dataList.addAll(database.mainDao().getAll());
//                    // Уведомление после вставки данных (Notify when data is inserted)
//                    adapter.notifyDataSetChanged();
                    Toast.makeText(AddTulaManuallyActivity.this, "Добавлено", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Считаем из БД список кодов сортов
    private List<Integer> getListID() {
        List<MainData> mainDataList = database.mainDao().getAll();
        List<Integer> listID = new ArrayList<>();
        for (MainData id : mainDataList) {
            listID.add(id.getID());
        }
        return listID;
    }

    // Создание выпадающего списка "Код сорта тюльпана"
    private void createSpinner(List<Integer> listID) {
        final Spinner spID = findViewById(R.id.spID);
        ArrayAdapter<Integer> idAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listID);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spID.setAdapter(idAdapter);
        spID.setPrompt("Код сорта тюльпана");
    }
}
