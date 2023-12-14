package com.example.snake10;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


/**
 * AppConstants - класс, содержащий константы и методы для инициализации игровых параметров.
 * Здесь определены основные константы, такие как размеры экрана, цвета, а также объект GameEngine.
 */
public class AppConstants {
    // Экземпляр класса GameEngine
    static GameEngine gameEngine;

    // Ширина и высота экрана устройства
    static int SCREEN_WIDTH, SCREEN_HEIGHT;

    // Ширина и высота поверхности отображения игры
    static int surfaceViewWidth, surfaceViewHeight;

    // Контекст активности игры
    static Context gameActivityContext;

    // Размер точки на экране
    static int pointSize;

    // Количество точек хвоста по умолчанию
    static int defaultTailPoints;

    // Цвет змейки (в данном случае зеленый)
    static int snakeColor = Color.GREEN;

    // Цвет яблока
    static int appleColor;


    /**
     * Метод initialization - инициализация игровых параметров.
     * @param context Контекст приложения.
     */
    public static void initialization(Context context) {
        setScreenSize(context);
        AppConstants.gameActivityContext = context;
        setGameConstants();
        gameEngine = new GameEngine();
    }

    /**
     * Метод setGameConstants - установка констант игры.
     */
    public static void setGameConstants() {
        AppConstants.pointSize = 24;
        AppConstants.defaultTailPoints = 3;
        AppConstants.snakeColor = Color.GREEN;
        AppConstants.appleColor = Color.RED;
    }

    /**
     * Метод getGameEngine - получение объекта GameEngine.
     * @return Объект GameEngine.
     */
    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    /**
     * Метод setScreenSize - установка размеров экрана устройства.
     * @param context Контекст приложения.
     */
    public static void setScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }

}
