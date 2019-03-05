package com.example.runninggame;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        TextView tvWin = (TextView) findViewById(R.id.tv_lose);
        TextView tvText = (TextView) findViewById(R.id.tv_tex);
        tvWin.setTypeface(AssetsSource.getFontFace(this, "Fonts/04B_03.TTF"));
        tvText.setTypeface(AssetsSource.getFontFace(this, "Fonts/04B_03.TTF"));

        ImageView imgOver = (ImageView)findViewById(R.id.iv_over);
        imgOver.setImageBitmap(AssetsSource.getImageFromAssetsFile(this,"png/bee02.jpg"));

        Button btnAgain = (Button) findViewById(R.id.bt_again);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoseActivity.this.setContentView(new MySurfaceView(LoseActivity.this));
                //LoseActivity.this.finish();
            }
        });
    }
}
