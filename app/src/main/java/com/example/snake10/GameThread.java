package com.example.snake10;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;


/**
 * GameThread - класс, представляющий поток для управления игровой логикой и отображением.
 * Этот поток регулирует обновление игрового состояния и визуализацию на экране.
 */
public class GameThread extends Thread{
    SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 200; // Задержка между итерациями игрового цикла.
    boolean startGame = false; // Флаг для определения начала игры.

    /**
     * Конструктор класса.
     * @param surfaceHolder Объект, управляющий Surface для отображения графики.
     */
    public GameThread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }


    /**
     * Метод, выполняемый в потоке при его запуске.
     */
    @Override
    public void run() {
        while (isRunning){
            startTime = SystemClock.uptimeMillis();

            // Захватываем Canvas для рисования.
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas != null){
                synchronized (surfaceHolder){
                    // Первичная инициализация игры.
                    if (!startGame){
                        AppConstants.getGameEngine().generateNewApple();
                        startGame = true;
                    }

                    // Выполнение шага игрового цикла (движение, рост и отрисовка змейки).
                    AppConstants.getGameEngine().moveGrowAndDrawSnake(canvas);

                    // Освобождаем Canvas после рисования.
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // Регулировка времени выполнения игрового цикла.
            loopTime = SystemClock.uptimeMillis() - startTime;
            if (loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch (InterruptedException e){
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }
    }


    /**
     * Метод для проверки состояния потока (запущен или остановлен).
     * @return true, если поток запущен, иначе false.
     */
    public boolean isRunning(){
        return isRunning;
    }


    /**
     * Метод для установки состояния потока (запущен или остановлен).
     * @param state Новое состояние потока.
     */
    public void setIsRunning(boolean state){
        isRunning = state;
    }

}
