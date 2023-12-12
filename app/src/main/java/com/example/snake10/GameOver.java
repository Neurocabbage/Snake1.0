package com.example.snake10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Класс GameOver обрабатывает завершение игры, отображает результат и персональный рекорд.
 */
public class GameOver extends AppCompatActivity {
    TextView tvScore, tvPersonalBest;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        // Получаем счет и персональный рекорд из предыдущей активности
        int score = getIntent().getExtras().getInt("score");
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int scoreSP = pref.getInt("scoreSP", 0);

        // Обновляем персональный рекорд, если текущий счет больше
        SharedPreferences.Editor editor = pref.edit();
        if (score > scoreSP){
            scoreSP = score;
            editor.putInt("scoreSP", scoreSP);
            editor.commit();
        }

        // Находим и привязываем компоненты TextView
        tvScore = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        // Устанавливаем текст для TextView
        tvScore.setText("" + score);
        tvPersonalBest.setText("" + scoreSP);
    }


    /**
     * Обработчик нажатия на кнопку "Restart".
     * Запускает MainActivity и закрывает текущую активность.
     */
    public void restart(View view){
        // Запускаем MainActivity и закрываем текущую активность
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * Обработчик нажатия на кнопку "Exit".
     * Закрывает текущую активность.
     */
    public void exit(View view) {
        finish();
    }
}
