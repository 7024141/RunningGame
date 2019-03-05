package com.example.runninggame;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = "png/bg_land.png";
        ImageView bgTextView = (ImageView)findViewById(R.id.bground);
        bgTextView.setImageBitmap(AssetsSource.getImageFromAssetsFile(this,fileName));

        Button btStart = (Button)findViewById(R.id.start);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(new MySurfaceView(MainActivity.this));
            }
        });
    }
}
