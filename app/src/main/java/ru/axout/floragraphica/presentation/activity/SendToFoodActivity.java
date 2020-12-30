package ru.axout.floragraphica.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import ru.axout.floragraphica.CaptureAct;
import ru.axout.floragraphica.R;
import ru.axout.floragraphica.data.*;
import ru.axout.floragraphica.presentation.adapter.SendFoodAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendToFoodActivity extends AppCompatActivity {

    List<FoodData> foodDataList = new ArrayList<>();
    RoomDB database;
    Toast toast;
    SendFoodAdapter sendFoodAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_food);

        // Initialize BD
        database = RoomDB.getInstance(this);
        Button btScan = findViewById(R.id.bt_scan_to_food);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tula_manually);

        // Вывод данных пользователю:
        // Хранение данных БД в data list (Store database value in data list)
        foodDataList = database.foodDao().getAll();
        // Инициализация менеджера линейного макета (Initialize linear layout manager)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Установка менеджера макета
        recyclerView.setLayoutManager(linearLayoutManager);
        // Инициализация adapter
        sendFoodAdapter = new SendFoodAdapter(SendToFoodActivity.this, foodDataList);
        // Set adapter
        recyclerView.setAdapter(sendFoodAdapter);

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Для мгновенного обновления списка добавленных позиций:
        // Очистка списка данных
        foodDataList.clear();
        // Заново данные из БД добавлются в список
        foodDataList.addAll(database.foodDao().getAll());
        // Уведомление после вставки данных (Notify when data is inserted)
        sendFoodAdapter.notifyDataSetChanged();
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
                try {
                    addScanDataToDB(scanResult);
                } catch (Exception e) {
                    showMessageCenter("ОТКАЗ");
                }
                scanCode();
            }
            else {
                showMessage("Нет результата");
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
        TulaData tulaData;
        String sort;

        // Получение строки из table_tula по sortID
        tulaData = database.tulaDao().getWhereSortIDAndPackNum(sortID, packNumber);
        // Получение сорта из полученной строки
        sort = tulaData.getSort();

//        if (!database.foodDao().checkBySortAndPackNumber(sortID, packNumber)) {
        FoodData foodData = new FoodData();
        foodData.setSortID(sortID);
        foodData.setSort(sort);
        foodData.setPackageNumber(packNumber);
        foodData.setDateAdded(getFormatDate());
        // Вставка данных (картежа) в БД (Insert text in database)
        database.foodDao().insert(foodData);

        // Удаляем из table_tula отправленную на ФудСити упаковку
        database.tulaDao().delete(tulaData);
//            database.tulaDao().deleteBySortIDAndPackNum(sortID, packNumber);

        showMessage("Отправлено");
    }

    // Форматирование даты под следующий вид: "dd.MM.yyyy"
    // Фиксация даты добавления упаковки
    private String getFormatDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDateNow.format(dateNow);
    }

    private void showMessage(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(SendToFoodActivity.this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showMessageCenter(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(SendToFoodActivity.this, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
