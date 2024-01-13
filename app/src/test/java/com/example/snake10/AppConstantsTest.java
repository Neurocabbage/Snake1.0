package com.example.snake10;

import android.content.Context;
import android.graphics.Color;

import androidx.test.platform.app.InstrumentationRegistry; // Для использования InstrumentationRegistry
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppConstantsTest {

    private Context context;

    @Before
    public void setUp() {
        // Использование InstrumentationRegistry для получения реального контекста
        context = InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void testInitialization() {
        AppConstants.initialization(context);
        assertEquals(true, AppConstants.getGameEngine() != null);
        // Другие проверки инициализации по необходимости
    }

    @Test
    public void testSetGameConstants() {
        AppConstants.setGameConstants();
        assertEquals(24, AppConstants.pointSize);
        assertEquals(3, AppConstants.defaultTailPoints);
        assertEquals(Color.GREEN, AppConstants.snakeColor);
        assertEquals(Color.RED, AppConstants.appleColor);
    }

    @Test
    public void testSetScreenSize() {
        // Использование контекста для теста
        AppConstants.setScreenSize(context);
        // Другие проверки, если необходимо
    }
}
