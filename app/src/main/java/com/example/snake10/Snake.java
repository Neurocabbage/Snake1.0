package com.example.snake10;


/**
 * Snake - класс, представляющий объект "змейка" в игре.
 * Этот класс содержит координаты головы змейки.
 */
public class Snake {
    private int snakeX, snakeY;


    /**
     * Конструктор класса Snake - инициализация координат змейки.
     * @param snakeX Координата X головы змейки.
     * @param snakeY Координата Y головы змейки.
     */
    public Snake(int snakeX, int snakeY){
        this.snakeX = snakeX;
        this.snakeY = snakeY;
    }


    /**
     * Метод getSnakeX - получение координаты X головы змейки.
     * @return Координата X головы змейки.
     */
    public int getSnakeX() {
        return snakeX;
    }


    /**
     * Метод setSnakeX - установка новой координаты X головы змейки.
     * @param snakeX Новая координата X головы змейки.
     */
    public void setSnakeX(int snakeX) {
        this.snakeX = snakeX;
    }


    /**
     * Метод getSnakeY - получение координаты Y головы змейки.
     * @return Координата Y головы змейки.
     */
    public int getSnakeY() {
        return snakeY;
    }


    /**
     * Метод setSnakeY - установка новой координаты Y головы змейки.
     * @param snakeY Новая координата Y головы змейки.
     */
    public void setSnakeY(int snakeY) {
        this.snakeY = snakeY;
    }
}
