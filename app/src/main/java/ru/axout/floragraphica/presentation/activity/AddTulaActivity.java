package ru.axout.floragraphica.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import ru.axout.floragraphica.CaptureAct;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.MainData;
import ru.axout.floragraphica.data.RoomDB;
import ru.axout.floragraphica.data.TulaData;
import ru.axout.floragraphica.presentation.adapter.TulaAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTulaActivity extends AppCompatActivity implements View.OnClickListener{

    List<TulaData> tulaDataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    TulaAdapter tulaAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tula);

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
        tulaAdapter = new TulaAdapter(AddTulaActivity.this, tulaDataList);
        // Set adapter
        recyclerView.setAdapter(tulaAdapter);


        final Button btAdd = findViewById(R.id.bt_add_tula);
        final Button btScan = findViewById(R.id.bt_scan_tula);
        // устанавливаем один обработчик для всех кнопок
        btAdd.setOnClickListener(this);
        btScan.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Для мгновенного обновления списка добавленных позиций:
        // Очистка списка данных
        tulaDataList.clear();
        // Заново данные из БД добавлются в список
        tulaDataList.addAll(database.tulaDao().getAll());
        // Уведомление после вставки данных (Notify when data is inserted)
        tulaAdapter.notifyDataSetChanged();
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

    // анализируем, какая кнопка была нажата. Один метод для всех кнопок
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_tula:
                addManuallyToDB();
                break;
            case R.id.bt_scan_tula:
                scanCode();
                break;
        }
    }

    private void addManuallyToDB() {

        // Присваивание переменным (Assign variables)
        final Spinner spinnerSort = findViewById(R.id.spSorts_tula);
        final EditText etPackNumber = findViewById(R.id.etPackNumber_tula);

        // Получение данных из выпадающего списка (spinner)
        String sortFromSpinner = spinnerSort.getSelectedItem().toString();
        // Получение строки из table_main по ID
        MainData mainData = database.mainDao().getWhereSort(sortFromSpinner);
        // Получение данных (номер упаковки) из editText
        String sPackNumber = etPackNumber.getText().toString().trim();
        int packNumber = Integer.parseInt(sPackNumber);

        // Запись данных в table_tula
        // Проверяем, чтобы не добавить в БД ещё раз одни и те же данные
        Toast toast;
        if (!database.tulaDao().checkBySortAndPackNumber(sortFromSpinner, packNumber)) {
            // Проверка пустой строки
            if (!sPackNumber.equals("")) { // Если строка не пустая
                TulaData tulaData = new TulaData();
                // Передача данных в tulaData
                tulaData.setSortID(mainData.getID());
                tulaData.setSort(mainData.getSort());
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

                toast = Toast.makeText(AddTulaActivity.this, "Добавлено", Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(AddTulaActivity.this, "Не введён номер упаковки", Toast.LENGTH_SHORT);
            }
        } else {
            toast = Toast.makeText(AddTulaActivity.this, "УЖЕ ДОБАВЛЕНО", Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Сканирование...");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            final String scanResult = result.getContents();
            if (scanResult != null) {
                addScanDataToDB(scanResult);
                scanCode();
            }
            else {
                Toast.makeText(this, "Нет результата", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void addScanDataToDB(String scanResult) {
        int cod = Integer.parseInt(scanResult);
        int sortID = cod / 100000;
        int packNumber = cod % 100000;

        // Получение строки из table_main по ID
        MainData mainData = database.mainDao().getWhereID(sortID);
//        Toast.makeText(this, mainData.getSort(), Toast.LENGTH_SHORT).show();
        String sort = mainData.getSort();

        if (!database.tulaDao().checkBySortAndPackNumber(sort, packNumber)) {
            TulaData tulaData = new TulaData();
            // Передача данных в tulaData
            tulaData.setSortID(sortID);
            tulaData.setSort(sort);
            tulaData.setPackageNumber(packNumber);
            tulaData.setDateAdded(getFormatDate());
            // Вставка данных (картежа) в БД (Insert text in database)
            database.tulaDao().insert(tulaData);

            Toast.makeText(AddTulaActivity.this, "Добавлено", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddTulaActivity.this, "УЖЕ ДОБАВЛЕНО", Toast.LENGTH_SHORT).show();
        }
    }
}
