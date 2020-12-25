package ru.axout.floragraphica.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ru.axout.floragraphica.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imTula = findViewById(R.id.im_Tula);
        ImageView imFood = findViewById(R.id.im_Food);
        ImageView imVarsh = findViewById(R.id.im_Varsh);

        imTula.setOnClickListener(this);
        imFood.setOnClickListener(this);
        imVarsh.setOnClickListener(this);
    }

    // Создание меню экшн бара
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Наполнение меню. Добавление элементов в акшн бар (Inflate the menu. This adds items to the action bar if it is present.)
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_Tula:
                Intent intent = new Intent(HomeActivity.this, TulaActivity.class);
                startActivity(intent);
                break;
            case R.id.im_Food:
                Intent intent1 = new Intent(HomeActivity.this, FoodActivity.class);
                startActivity(intent1);
                break;
            case R.id.im_Varsh:
                Intent intent2 = new Intent(HomeActivity.this, VarshActivity.class);
                startActivity(intent2);
                break;
        }
    }
}