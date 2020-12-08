package ru.axout.floragraphica.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.os.Bundle;
import ru.axout.floragraphica.R;

/* MainActivity
Служит для отображения заставки
 */
public class MainActivity extends Activity {

    private final int SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Отключение заголовка активити (верхней плашки)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        // Handler позволяет реализовать отложенный по времени код
        // Runnable - запускает новый поток. Метод finish() закрывает этот поток.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}