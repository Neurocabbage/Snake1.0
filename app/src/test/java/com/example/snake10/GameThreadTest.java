package com.example.snake10;

import android.view.SurfaceHolder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class GameThreadTest {

    @Mock
    SurfaceHolder mockSurfaceHolder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRun() {
        // Создаем объект GameThread с мок SurfaceHolder
        GameThread gameThread = new GameThread(mockSurfaceHolder);

        // Запускаем поток
        gameThread.start();

        // Ждем некоторое время (например, 1 секунду) для выполнения нескольких итераций
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Останавливаем поток
        gameThread.setIsRunning(false);

        // Проверяем, что поток был запущен и работал (isRunning был true)
        assertTrue(gameThread.isRunning());

        // Проверяем, что методы SurfaceHolder использовались хотя бы один раз
        verify(mockSurfaceHolder, atLeastOnce()).lockCanvas(null);
        verify(mockSurfaceHolder, atLeastOnce()).unlockCanvasAndPost(any());

        // Проверяем, что поток был остановлен после вызова setIsRunning(false)
        assertFalse(gameThread.isRunning());
    }
}
