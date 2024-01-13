package com.example.snake10;

import org.junit.Test;
import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void testGetSnakeX() {
        // Создаем объект Snake с определенными координатами
        Snake snake = new Snake(10, 20);

        // Проверяем, что метод getSnakeX возвращает правильное значение
        assertEquals(10, snake.getSnakeX());
    }

    @Test
    public void testSetSnakeX() {
        // Создаем объект Snake
        Snake snake = new Snake(0, 0);

        // Устанавливаем новое значение координаты X
        snake.setSnakeX(15);

        // Проверяем, что новое значение установлено
        assertEquals(15, snake.getSnakeX());
    }

    @Test
    public void testGetSnakeY() {
        // Создаем объект Snake с определенными координатами
        Snake snake = new Snake(30, 40);

        // Проверяем, что метод getSnakeY возвращает правильное значение
        assertEquals(40, snake.getSnakeY());
    }

    @Test
    public void testSetSnakeY() {
        // Создаем объект Snake
        Snake snake = new Snake(0, 0);

        // Устанавливаем новое значение координаты Y
        snake.setSnakeY(25);

        // Проверяем, что новое значение установлено
        assertEquals(25, snake.getSnakeY());
    }
}

