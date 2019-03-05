package com.example.runninggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        TextView tvScore = (TextView) findViewById(R.id.tv_score);
        String strScore = tvScore.getText() + String.valueOf(score);
        tvScore.setText( strScore);

        ImageView imgWin = (ImageView)findViewById(R.id.iv_win);
        imgWin.setImageBitmap(AssetsSource.getImageFromAssetsFile(this,"png/bee.jpg"));

        TextView tvWin = (TextView) findViewById(R.id.tv_win);
        TextView tvText = (TextView) findViewById(R.id.tv_text);
        tvWin.setTypeface(AssetsSource.getFontFace(this, "Fonts/04B_03.TTF"));
        tvText.setTypeface(AssetsSource.getFontFace(this, "Fonts/04B_03.TTF"));
        tvScore.setTypeface(AssetsSource.getFontFace(this, "Fonts/04B_03.TTF"));

        Button btn = (Button)findViewById(R.id.bt_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinActivity.this, MainActivity.class);
                WinActivity.this.startActivity(intent);
                WinActivity.this.finish();
            }
        });
    }
}
