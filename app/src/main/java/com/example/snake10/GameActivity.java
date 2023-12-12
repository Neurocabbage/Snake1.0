package com.example.snake10;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * GameActivity - класс, представляющий активность игрового экрана приложения "Snake10".
 * Этот класс управляет отображением игрового поля, запуском игрового потока и обработкой
 * пользовательского ввода.
 */
public class GameActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    GameThread gameThread;
    SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    MediaPlayer button_click;

    /**
     * Метод, вызывается при создании активности.
     * @param savedInstanceState Объект, содержащий данные о предыдущем состоянии активности,
     *                           если оно было.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Устанавливаем макет (layout) для этой активности из ресурсов (activity_game.xml).
        setContentView(R.layout.activity_game);

        // Инициализация звукового эффекта для кнопки.
        button_click = MediaPlayer.create(this, R.raw.button_click);

        // Получаем SurfaceView из макета и устанавливаем текущий объект класса
        // как обратный вызов (callback).
        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);
        surfaceView.setFocusable(true);

        // Инициализация констант приложения.
        AppConstants.initialization(GameActivity.this);
    }


    /**
     * Метод обратного вызова, вызывается при создании Surface.
     * @param surfaceHolder Объект, управляющий Surface.
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;

        // Создаем и запускаем игровой поток, если он не был создан или уже не запущен.
        gameThread = new GameThread(surfaceHolder);
        if (!gameThread.isRunning()){
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        } else{
            gameThread.start();
        }
    }


    /**
     * Метод обратного вызова, вызывается при изменении размеров Surface.
     * @param surfaceHolder Объект, управляющий Surface.
     * @param i Новый формат Surface.
     * @param i1 Новая ширина Surface.
     * @param i2 Новая высота Surface.
     */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        AppConstants.surfaceViewWidth = surfaceView.getWidth();
        AppConstants.surfaceViewHeight = surfaceView.getHeight();
    }

    /**
     * Метод обратного вызова, вызывается при уничтожении Surface.
     * @param surfaceHolder Объект, управляющий Surface.
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        // Останавливаем игровой поток и дожидаемся его завершения.
        gameThread.setIsRunning(false);
        boolean retry = true;
        while (retry){
            try {
                gameThread.join();
                retry = false;
            }catch (InterruptedException e){
                // Обработка исключения.
            }
        }
    }

    /**
     * Метод, вызываемый при нажатии на кнопку для изменения направления движения змейки.
     * @param view Представление кнопки, на которую было осуществлено нажатие.
     */
    public void changeSnakeDirection(View view) {
        // Воспроизведение звукового эффекта при нажатии на кнопку.
        if (button_click != null){
            button_click.start();
        }

        // Получение направления движения из тега кнопки
        // и установка нового направления в игровом движке.
        String movement = view.getTag().toString();
        switch (movement){
            case "top":
                if(!AppConstants.getGameEngine().movingPosition.equals("bottom")){
                    AppConstants.getGameEngine().movingPosition = "top";
                }
                break;
            case "bottom":
                if(!AppConstants.getGameEngine().movingPosition.equals("top")){
                    AppConstants.getGameEngine().movingPosition = "bottom";
                }
                break;
            case "left":
                if(!AppConstants.getGameEngine().movingPosition.equals("right")){
                    AppConstants.getGameEngine().movingPosition = "left";
                }
                break;
            case "right":
                if(!AppConstants.getGameEngine().movingPosition.equals("left")){
                    AppConstants.getGameEngine().movingPosition = "right";
                }
                break;
        }
    }
}
