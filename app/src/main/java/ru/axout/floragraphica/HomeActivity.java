package ru.axout.floragraphica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
}