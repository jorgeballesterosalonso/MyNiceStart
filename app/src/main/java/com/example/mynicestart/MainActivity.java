package com.example.mynicestart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView fondo = findViewById(R.id.imageView2);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1606276969080-bf446209054a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80")
                .transition(DrawableTransitionOptions.withCrossFade(500))
                //.placeholder(new ColorDrawable(this.getResources()
                //.getColor(R.color.design_default_color_background)))
                .circleCrop()
                //.getDiskCacheStrategy(DiskCacheStrategy.NONE)
                .into(fondo);

        TextView tv = findViewById(R.id.tvMain);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}