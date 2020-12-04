package ru.axout.floragraphica;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    ImageView imTula, imFood, imVarsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imTula = findViewById(R.id.im_Tula);
        imFood = findViewById(R.id.im_Food);
        imVarsh = findViewById(R.id.im_Varsh);

        imTula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TulaActivity.class);
                startActivity(intent);
            }
        });

        imFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        imVarsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, VarshActivity.class);
                startActivity(intent);
            }
        });
    }

    // Создание меню экшн бара
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Наполнение меню. Добавление элементов в акшн бар (Inflate the menu. This adds items to the action bar if it is present.)
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Обработка выпадающего меню в экшн баре
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // Обработка нажатия "Добавить сорт"
        if (id == R.id.action_settings) {
            Intent intent = new Intent(HomeActivity.this, AddDataActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}