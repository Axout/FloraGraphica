package ru.axout.floragraphica.presentation.activity;

import android.os.Bundle;
import android.view.Gravity;
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
import ru.axout.floragraphica.presentation.adapter.TulaRestAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTulaManuallyActivity extends AppCompatActivity {

    List<TulaData> tulaDataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    TulaAdapter tulaAdapter;

    private Toast toast;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tula_manually);

        // Присваивание переменным (Assign variables)
        final Spinner spinnerSort = findViewById(R.id.spSorts_tula);
        final EditText etPackNumber = findViewById(R.id.etPackNumber_tula);
        Button btAdd = findViewById(R.id.bt_add_tula);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tula_manually);

        // Initialize BD
        database = RoomDB.getInstance(this);
        // Считаем из БД список сортов тюльпанов
        List<String> listSorts = getListOfSorts();
        // Создание выпадающего списка "Код сорта тюльпана"
        createSpinner(listSorts);


        // Вывод данных пользователю:
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


        // Обработка нажатия кнопки "Добавить"
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение данных из выпадающего списка (spinner)
                String sortFromSpinner = spinnerSort.getSelectedItem().toString();
                // Получение строки из table_main по ID
                MainData mainData = database.mainDao().getWhereSort(sortFromSpinner);
                // Получение данных (номер упаковки) из editText
                String sPackNumber = etPackNumber.getText().toString().trim();

                // Запись данных в table_tula
                // Проверка пустой строки
                if (!sPackNumber.equals("")) { // Если строка не пустая
                    // Initialize  tulaData
                    TulaData tulaData = new TulaData();
                    // Передача данных в tulaData
                    tulaData.setSort_ID(mainData.getID());
                    tulaData.setPackageNumber(Integer.parseInt(sPackNumber));
                    tulaData.setDateAdded(getFormatDate());
                    // Вставка данных (картежа) в БД (Insert text in database)
                    database.tulaDao().insert(tulaData);
                    // Очитка edittext
                    etPackNumber.setText("");


                    // Для мгновенного обновления списка добавленных позиций:
                    // Очистка списка данных
                    tulaDataList.clear();
                    // Заново данные из БД добавлются в список
                    tulaDataList.addAll(database.tulaDao().getAll());
                    // Уведомление после вставки данных (Notify when data is inserted)
                    tulaAdapter.notifyDataSetChanged();

                    toast = Toast.makeText(AddTulaManuallyActivity.this, "Добавлено", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    toast = Toast.makeText(AddTulaManuallyActivity.this, "Не введён номер упаковки", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

    }

    // Форматирование даты под следующий вид: "dd.MM.yyyy"
    // Фиксация даты добавления упаковки
    private String getFormatDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDateNow.format(dateNow);
    }

    // Считаем из БД список сортов тюльпанов
    private List<String> getListOfSorts() {
        List<MainData> mainDataList = database.mainDao().getAll();
        List<String> listOfSorts = new ArrayList<>();
        for (MainData id : mainDataList) {
            listOfSorts.add(id.getSort());
        }
        return listOfSorts;
    }

    // Создание выпадающего списка "Cорта тюльпанов"
    private void createSpinner(List<String> listOfSorts) {
        final Spinner spID = findViewById(R.id.spSorts_tula);
        ArrayAdapter<String> idAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listOfSorts);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spID.setAdapter(idAdapter);
        spID.setPrompt("Cорт тюльпана");
    }
}
