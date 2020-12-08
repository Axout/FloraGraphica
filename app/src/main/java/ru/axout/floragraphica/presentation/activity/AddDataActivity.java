package ru.axout.floragraphica.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.axout.floragraphica.data.MainData;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.presentation.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddDataActivity extends AppCompatActivity {

    // Инициализация переменных
    Spinner spinnerType, spinnerColor, spinnerSort;
    EditText editTextID;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    // Список с данными (картежи)
    // БД создаётся в MainData
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    // Массивы для выпадающих списков
    private final String[] types = {"Обычные", "Пёстрые", "Махровые", "Бахромчатые", "Попугайные"};
    private final String[] colors = {"Белый", "Кремовый", "Жёлтый", "Бордо", "Красный", "Светло-розовый",
            "Розовый", "Оранжевый", "Лилак", "Фиолетовый", "Красно-жёлтый", "Розово-белый", "Красно-белый",
            "Лилово-белый", "Тёмно-пурпурный"};
    private final String[] sorts = {"Darwisnow", "Cream Fraiche", "Coalition", "Lalibela", "Red Lable",
            "Super Model", "Barcelona Beauty", "Orange Ninja", "Barcelona", "Bullit", "Verandy", "Milkshake",
            "Roman Empire", "Synaeda Blue", "Double Twist", "Queensland", "Parrot Sweet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        // Присваивание переменным (Assign variables)
        editTextID = findViewById(R.id.editTextID);
        spinnerType = findViewById(R.id.spTypes);
        spinnerColor = findViewById(R.id.spColors);
        spinnerSort = findViewById(R.id.spSorts);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        // Выпадающий список "Типы"
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spTypes = findViewById(R.id.spTypes);
        spTypes.setAdapter(typesAdapter);
        spTypes.setPrompt("Тип тюльпана");
        // Выпадающий список "Цвета"
        ArrayAdapter<String> colorsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colors);
        colorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spColors = findViewById(R.id.spColors);
        spColors.setAdapter(colorsAdapter);
        spColors.setPrompt("Цвет тюльпана");
        // Выпадающий список "Сорта"
        ArrayAdapter<String> sortsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sorts);
        sortsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spSorts = findViewById(R.id.spSorts);
        spSorts.setAdapter(sortsAdapter);
        spSorts.setPrompt("Сорт тюльпана");

        // Initialize BD
        database = RoomDB.getInstance(this);
        // Хранение данных БД в data list (Store database value in data list)
        dataList = database.mainDao().getAll();

        // Инициализация менеджера линейного макета (Initialize linear layout manager)
        linearLayoutManager = new LinearLayoutManager(this);
        // Установка менеджера макета
        recyclerView.setLayoutManager(linearLayoutManager);
        // Инициализация adapter
        adapter = new MainAdapter(AddDataActivity.this, dataList);
        // Set adapter
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение данных
                String sID = editTextID.getText().toString().trim();
                String sType = spTypes.getSelectedItem().toString();
                String sColor = spColors.getSelectedItem().toString();
                String sSort = spSorts.getSelectedItem().toString();

                // Проверка пустой строки
                // !!! Добавить проверу второй строки ID
                if (!sID.equals("")) { // Если строка не пустая
                    // Initialize main data
                    MainData data = new MainData();
                    // Передача данных в MainData
                    data.setID(Integer.parseInt(sID));
                    data.setType(sType);
                    data.setColor(sColor);
                    data.setSort(sSort);
                    // Вставка данных (картежа) в БД (Insert text in database)
                    database.mainDao().insert(data);
                    // Очитка edittext
                    editTextID.setText("");
                    // Очистка списка данных, что выводится пользователю
                    dataList.clear();
                    // Заново данные из БД добавлются в список
                    dataList.addAll(database.mainDao().getAll());
                    // Уведомление после вставки данных (Notify when data is inserted)
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Удаление всех данных из БД (Delete all data from database)
                database.mainDao().reset(dataList);
                // Очистка списка данных, что выводится пользователю
                dataList.clear();
                // Заново данные из БД добавлются в список
                dataList.addAll(database.mainDao().getAll());
                // Уведомление после удаления данных
                adapter.notifyDataSetChanged();
            }
        });
    }
}
