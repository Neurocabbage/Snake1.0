package com.example.snake10;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;

import org.junit.Before;
import org.junit.Test;

public class GameActivityTest {
    private GameActivity gameActivity;

    @Before
    public void setUp() {
        gameActivity = new GameActivity();
    }

    @Test
    public void testChangeSnakeDirection() {
        // Создаем заглушку для объекта View
        View mockedView = mock(View.class);

        // Устанавливаем тег для заглушки
        when(mockedView.getTag()).thenReturn("top");

        // Вызываем метод, который мы хотим протестировать
        gameActivity.changeSnakeDirection(mockedView);

        // Проверяем, что установлено правильное направление движения в игровом движке
        assert(AppConstants.getGameEngine().movingPosition.equals("top"));
    }
}
