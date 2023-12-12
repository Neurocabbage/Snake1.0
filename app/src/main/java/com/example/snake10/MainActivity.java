package com.example.snake10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity - класс, представляющий главный экран приложения "Snake10".
 * Этот класс является точкой входа в приложение и отображает основной интерфейс с кнопкой "Play".
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Метод, вызывается при создании активности.
     * @param savedInstanceState Объект, содержащий данные о предыдущем состоянии активности,
     *                           если оно было.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Метод, вызывается при нажатии на кнопку "Play".
     * Инициирует переход к активности GameActivity, запуская новый экран игры.
     * @param view Представление кнопки, на которую было осуществлено нажатие.
     */
    public void play(View view) {
        // Создание нового Intent для перехода к GameActivity.м
        Intent intent = new Intent(MainActivity.this, GameActivity.class);

        // Запуск активности GameActivity.
        startActivity(intent);

        // Завершение текущей активности,
        // чтобы предотвратить возврат к MainActivity через кнопку "назад".
        finish();
    }

}
