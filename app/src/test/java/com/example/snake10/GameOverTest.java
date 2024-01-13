package com.example.snake10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameOverTest {

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Mock
    Bundle mockBundle;

    @Mock
    Intent mockIntent;

    private GameOver gameOverActivity;

    @Before
    public void setUp() {
        // Создаем макеты и настраиваем их поведение
        when(mockSharedPreferences.getInt("scoreSP", 0)).thenReturn(10);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putInt(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockEditor);
        when(mockEditor.commit()).thenReturn(true);

        // Запускаем активность
        ActivityScenario<GameOver> scenario = ActivityScenario.launch(GameOver.class);
        scenario.onActivity(activity -> {
            gameOverActivity = activity;
            // Устанавливаем макеты
            gameOverActivity.getSharedPreferences("MyPref", 0);
        });
    }

    @Test
    public void testScoreUpdate() {
        // Устанавливаем счет в макете бандла
        when(mockBundle.getInt("score")).thenReturn(20);

        // Запускаем метод onCreate с макетом бандла
        gameOverActivity.onCreate(mockBundle);

        // Проверяем, что персональный рекорд обновлен
        assertEquals(20, mockSharedPreferences.getInt("scoreSP", 0));
    }

    @Test
    public void testRestart() {
        // Запускаем метод restart
        gameOverActivity.restart(null);

        // Проверяем, что активность MainActivity была запущена
        Intent actualIntent = ApplicationProvider.getApplicationContext().getPackageManager()
                .getLaunchIntentForPackage(ApplicationProvider.getApplicationContext().getPackageName());
        assertEquals(mockIntent, actualIntent);
    }

    @Test
    public void testExit() {
        // Запускаем метод exit
        gameOverActivity.exit(null);

        // Проверяем, что активность GameOver была закрыта
        assertEquals(true, gameOverActivity.isFinishing());
    }
}
