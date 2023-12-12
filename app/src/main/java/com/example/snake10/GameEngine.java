package com.example.snake10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * GameEngine - класс, отвечающий за игровую логику и отрисовку в игре Змейка.
 * Здесь реализованы методы для управления змейкой, отрисовки элементов и обработки игровых событий.
 */
public class GameEngine {
    ArrayList<Snake> snakePointsList = new ArrayList<>();
    String movingPosition;
    int score;
    private int appleX, appleY;
    Paint snakePaint, applePaint, scorePaint;
    Random random;
    final int TEXT_SIZE = 100;
    int gameState = 0;
    MediaPlayer points;


    /**
     * Конструктор класса GameEngine.
     * Инициализирует параметры змейки, яблока, красок, случайных чисел и звука.
     */
    public GameEngine() {
        snakePaint = createPaintObject(AppConstants.snakeColor);
        applePaint = createPaintObject(AppConstants.appleColor);
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        random = new Random();
        points = MediaPlayer.create(AppConstants.gameActivityContext, R.raw.points);
        movingPosition = "right";
        init();
    }


    /**
     * Метод init - инициализация игровых параметров при старте игры.
     */
    private void init() {
        gameState = 1;
        snakePointsList.clear();
        score = 0;
        int snakeStartX = AppConstants.pointSize * AppConstants.defaultTailPoints;
        for (int i = 0; i < AppConstants.defaultTailPoints; i++){
            Snake snake = new Snake(snakeStartX, AppConstants.pointSize);
            snakePointsList.add(snake);
            snakeStartX = snakeStartX - (AppConstants.pointSize * 2);
        }
    }


    /**
     * Метод moveGrowAndDrawSnake - обработка движения, роста и отрисовка змейки.
     * @param canvas Объект Canvas для отрисовки.
     */
    public void moveGrowAndDrawSnake(Canvas canvas) {
        if (gameState == 1) {
            int headX = snakePointsList.get(0).getSnakeX();
            int headY = snakePointsList.get(0).getSnakeY();

            // Если голова змейки совпадает с координатами яблока, то змейка растет
            // и генерируется новое яблоко.
            if (headX == appleX && headY == appleY) {
                growSnake();
                generateNewApple();
            }

            // Обработка движения в зависимости от текущего направления.
            switch(movingPosition){
                case "right":
                    // Обновление координат головы змейки вправо.
                    snakePointsList.get(0).setSnakeX(headX + (AppConstants.pointSize * 2));
                    snakePointsList.get(0).setSnakeY(headY);

                    // Если голова выходит за пределы экрана, она появляется с другой стороны.
                    if (snakePointsList.get(0).getSnakeX() >
                            AppConstants.surfaceViewWidth - AppConstants.pointSize){
                        snakePointsList.get(0).setSnakeX(AppConstants.pointSize);
                    }
                    break;
                case "left":
                    // Аналогичные действия для движения влево.
                    snakePointsList.get(0).setSnakeX(headX - (AppConstants.pointSize * 2));
                    snakePointsList.get(0).setSnakeY(headY);
                    if (snakePointsList.get(0).getSnakeX() < -AppConstants.pointSize){
                        int surfaceWidth =
                                AppConstants.surfaceViewWidth - (AppConstants.pointSize * 2);
                        int validX = surfaceWidth / AppConstants.pointSize;
                        if (validX % 2 != 0){
                            validX++;
                        }
                        int newHeadX = validX * AppConstants.pointSize + AppConstants.pointSize;
                        snakePointsList.get(0).setSnakeX(newHeadX);
                    }
                    break;
                case "top":
                    // Аналогичные действия для движения вверх.
                    snakePointsList.get(0).setSnakeX(headX);
                    snakePointsList.get(0).setSnakeY(headY - AppConstants.pointSize * 2);
                    if (snakePointsList.get(0).getSnakeY() < 0){
                        int surfaceHeight =
                                AppConstants.surfaceViewHeight - (AppConstants.pointSize * 2);
                        int validY = surfaceHeight / AppConstants.pointSize;
                        if (validY % 2 != 0){
                            validY++;
                        }
                        int newHeadY = validY * AppConstants.pointSize + AppConstants.pointSize;
                        snakePointsList.get(0).setSnakeY(newHeadY);
                    }
                    break;
                case "bottom":
                    // Аналогичные действия для движения вниз.
                    snakePointsList.get(0).setSnakeX(headX);
                    snakePointsList.get(0).setSnakeY(headY + AppConstants.pointSize * 2);
                    if (snakePointsList.get(0).getSnakeY() >
                            AppConstants.surfaceViewHeight - AppConstants.pointSize){
                        snakePointsList.get(0).setSnakeY(AppConstants.pointSize);
                    }
                    break;
            }

            // Если проверка на завершение игры возвращает true, игра завершается,
            // и переходит на экран GameOver с отображением результата.
            if (checkGameOver(headX, headY)){
                gameState = 2;
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else{
                // Очистка экрана и отрисовка элементов (головы, тела, яблока, счета).
                canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
                canvas.drawRect(snakePointsList.get(0).getSnakeX() - AppConstants.pointSize,
                        snakePointsList.get(0).getSnakeY() - AppConstants.pointSize,
                        snakePointsList.get(0).getSnakeX() + AppConstants.pointSize,
                        snakePointsList.get(0).getSnakeY() + AppConstants.pointSize,
                        snakePaint);
                canvas.drawRect(appleX - AppConstants.pointSize,
                        appleY - AppConstants.pointSize,
                        appleX + AppConstants.pointSize,
                        appleY + AppConstants.pointSize, applePaint);
                for (int i = 1; i < snakePointsList.size(); i++){
                    int getTempX = snakePointsList.get(i).getSnakeX();
                    int getTempY = snakePointsList.get(i).getSnakeY();
                    snakePointsList.get(i).setSnakeX(headX);
                    snakePointsList.get(i).setSnakeY(headY);
                    canvas.drawRect(snakePointsList.get(i).getSnakeX() - AppConstants.pointSize,
                            snakePointsList.get(i).getSnakeY() - AppConstants.pointSize,
                            snakePointsList.get(i).getSnakeX() + AppConstants.pointSize,
                            snakePointsList.get(i).getSnakeY() + AppConstants.pointSize,
                            snakePaint);
                    headX = getTempX;
                    headY = getTempY;
                }
            }

            // Отображение текущего счета в верхней части экрана.
            canvas.drawText(String.valueOf(score),
                    AppConstants.surfaceViewWidth / 2 - 50,
                    TEXT_SIZE,
                    scorePaint);
        }
    }


    /**
     * Метод checkGameOver - проверка на окончание игры (столкновение с собой).
     * @param headX Координата X головы змейки.
     * @param headY Координата Y головы змейки.
     * @return true, если игра окончена, в противном случае false.
     */
    private boolean checkGameOver(int headX, int headY) {
        boolean gameOver = false;
        for (int i = 1; i < snakePointsList.size(); i++){
            if (headX == snakePointsList.get(i).getSnakeX() &&
                    headY == snakePointsList.get(i).getSnakeY()){
                gameOver = true;
                break;
            }
        }
        return gameOver;
    }


    /**
     * Метод generateNewApple - генерация нового яблока на поле.
     */
    public void generateNewApple() {
        int surfaceWidth = AppConstants.surfaceViewWidth - (AppConstants.pointSize * 2);
        int surfaceHeight = AppConstants.surfaceViewHeight - (AppConstants.pointSize * 2);
        boolean valid = true;
        while(valid){
            valid = false;
            int randomX = random.nextInt(surfaceWidth / AppConstants.pointSize);
            int randomY = random.nextInt(surfaceHeight / AppConstants.pointSize);
            if (randomX % 2 != 0){
                randomX++;
            }
            if (randomY % 2 != 0){
                randomY++;
            }
            appleX = randomX * AppConstants.pointSize + AppConstants.pointSize;
            appleY = randomY * AppConstants.pointSize + AppConstants.pointSize;
            for (int i = 0; i < snakePointsList.size(); i++){
                if(appleX == snakePointsList.get(i).getSnakeX()
                        && appleY == snakePointsList.get(i).getSnakeY()){
                    valid = true;
                    break;
                }
            }
        }
    }


    /**
     * Метод growSnake - увеличение размера змейки при поедании яблока.
     */
    private void growSnake() {
        if (points != null){
            points.start();
        }
        Snake snakePoint = new Snake(0, 0);
        snakePointsList.add(snakePoint);
        score++;
    }

    /**
     * Метод createPaintObject - создание объекта Paint с заданным цветом.
     * @param color Цвет объекта Paint.
     * @return Объект Paint с указанным цветом.
     */
    private Paint createPaintObject(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }
}
