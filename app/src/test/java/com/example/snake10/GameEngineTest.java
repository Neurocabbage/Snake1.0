package com.example.snake10;

import android.graphics.Canvas;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class GameEngineTest {

    private GameEngine gameEngine;
    private Canvas canvas;

    @Before
    public void setUp() {
        // Создаем мок Canvas для использования в тестах
        canvas = mock(Canvas.class);
        // Создаем экземпляр GameEngine
        gameEngine = new GameEngine();
    }

    @Test
    public void testMoveGrowAndDrawSnake() {
        // Вызываем метод moveGrowAndDrawSnake с моком Canvas
        gameEngine.moveGrowAndDrawSnake(canvas);
        // Проверяем, что метод drawRect вызывается правильное количество раз
        verify(canvas, Mockito.times(gameEngine.snakePointsList.size())).drawRect(
                Mockito.anyFloat(), Mockito.anyFloat(),
                Mockito.anyFloat(), Mockito.anyFloat(), Mockito.any());
        // Добавьте другие проверки, если необходимо
    }

    @Test
    public void testCheckGameOver() throws Exception {
        // Создаем экземпляр GameEngine
        gameEngine = new GameEngine();

        // Получаем доступ к приватному методу
        Method checkGameOverMethod = GameEngine.class.getDeclaredMethod("checkGameOver", int.class, int.class);
        checkGameOverMethod.setAccessible(true);

        // Подготавливаем тестовые данные
        gameEngine.snakePointsList.add(new Snake(100, 100));
        gameEngine.snakePointsList.add(new Snake(120, 100));

        // Вызываем приватный метод
        boolean result = (boolean) checkGameOverMethod.invoke(gameEngine, 120, 100);

        // Проверяем, что метод возвращает true при столкновении
        assertTrue(result);

        // Подготавливаем тестовые данные
        gameEngine.snakePointsList.clear();
        gameEngine.snakePointsList.add(new Snake(100, 100));

        // Вызываем приватный метод
        result = (boolean) checkGameOverMethod.invoke(gameEngine, 200, 200);

        // Проверяем, что метод возвращает false при отсутствии столкновения
        assertTrue(!result);
    }

    @Test
    public void testGenerateNewApple() {
        GameEngine gameEngine = new GameEngine();

        // вызываем метод, который генерирует новое яблоко
        gameEngine.generateNewApple();

        // получаем значение appleX с помощью геттера и проверяем, что оно не равно нулю (или другому значению по умолчанию)
        assertNotEquals(0, gameEngine.getAppleX());
    }

    @Test
    public void testGrowSnake() {
        // Подготавливаем тестовые данные
        int initialSize = gameEngine.snakePointsList.size();
        // Вызываем метод growSnake
        gameEngine.growSnake();
        // Проверяем, что размер змейки увеличился на 1
        assert(gameEngine.snakePointsList.size() == initialSize + 1);
    }

    // Другие методы тестирования, если необходимо
}