package com.example.gabriel.ajedrezcocos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    CCGLSurfaceView vistaPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vistaPrincipal = new CCGLSurfaceView(this);
        setContentView(vistaPrincipal);
    }

    protected void onStart(){
        super.onStart();
        clsJuego mijuego = new clsJuego(vistaPrincipal);
        mijuego.ComenzarJuego();
    }
}
