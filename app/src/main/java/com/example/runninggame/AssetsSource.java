package com.example.runninggame;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

public class AssetsSource {
    static public Bitmap getImageFromAssetsFile(Context context, String fileName){
        Bitmap image =null;
        AssetManager assetManager = context.getResources().getAssets();
        try{
            InputStream is = assetManager.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }catch(IOException e){e.printStackTrace();}

        return image;
    }

    static public Typeface getFontFace(Context context, String filename){
        AssetManager assetManager = context.getResources().getAssets();
        Typeface fontFace = Typeface.createFromAsset(assetManager, filename);
        return fontFace;
    }
}
