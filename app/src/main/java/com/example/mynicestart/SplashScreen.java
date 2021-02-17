package com.example.mynicestart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;


/**
 * Crea un Activity con una animación y arranca el login
 * @author Jorge Ballesteros
 * @see LoginActivity
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        openApp(true);

        TextView tv = findViewById(R.id.textView);
        ImageView imagen = findViewById(R.id.imageView4);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);

        tv.startAnimation(myanim);
        imagen.startAnimation(rotation);


        ImageView fondo = findViewById(R.id.imageView3);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1606122644544-35fee06ab48e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=656&q=80")
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                //.placeholder(new ColorDrawable(this.getResources()
                //.getColor(R.color.design_default_color_background)))
                //.circleCrop()
                //.getDiskCacheStrategy(DiskCacheStrategy.NONE)
                .into(fondo);
    }


    /**
     * Un método que inicia la aplicación, hecho para darle una duración al Splash Screen
     * @param locationPermission
     */
    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen
                        .this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);
    }
}