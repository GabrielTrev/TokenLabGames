package com.example.gabriel.TokenLabGames;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;


public class LoadImgURLTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public LoadImgURLTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String path = urls[0];
        Bitmap bitmap = null;

        try {
            InputStream inputStream = new java.net.URL(path).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.imageView.setImageBitmap(bitmap);
    }

}
