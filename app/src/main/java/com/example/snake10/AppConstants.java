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
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int surfaceViewWidth, surfaceViewHeight;
    static Context gameActivityContext;
    static int pointSize;
    static int defaultTailPoints;
    static int snakeColor = Color.GREEN;
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
