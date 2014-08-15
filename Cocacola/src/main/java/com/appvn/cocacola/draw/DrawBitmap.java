package com.appvn.cocacola.draw;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import com.appvn.cocacola.R;

/**
 * Created by truongtvd on 7/18/14.
 */
public class DrawBitmap {


    public static Bitmap buildBitmap(Context context, int share) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("content", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("content", context.getString(R.string.myname));
        int size = sharedPreferences.getInt("size", 60);
        String fonts = sharedPreferences.getString("fonts", "Coke.ttf");
        int template = sharedPreferences.getInt("template", 1);

        Bitmap background = null;
        switch (template) {
            case 1:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca1);

                break;
            case 2:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca2);
                break;
            case 3:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca3);
                break;
            case 4:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca4);
                break;
            case 5:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca5);
                break;
            case 6:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca6);
                break;
            case 7:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca7);
                break;
            case 8:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca8);
                break;
            case 9:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca9);
                break;
            case 10:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca10);
                break;
            case 11:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca11);
                break;
            case 12:
                background = BitmapFactory.decodeResource(context.getResources(), R.drawable.coca12);
                break;


        }


        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        Bitmap myBitmap = Bitmap.createBitmap(width, 500, Bitmap.Config.ARGB_8888);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/" + fonts);
        Typeface font_link = Typeface.createFromAsset(context.getAssets(), "fonts/Coke.ttf");
        Canvas myCanvas = new Canvas(myBitmap);
        Canvas sign = new Canvas(myBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(font);
        paint.setColor(Color.WHITE);
        paint.setTextSize(size);


        Paint signPaint = new Paint();
        signPaint.setColor(Color.RED);
        signPaint.setTextAlign(Paint.Align.LEFT);
        signPaint.setTextSize(20);
       // signPaint.setStyle(Paint.Style.STROKE);
       // signPaint.setStrokeWidth(2);
        signPaint.setTypeface(font_link);

        paint.setTextAlign(Paint.Align.CENTER);
        int xPos = (myCanvas.getWidth() / 2);
        int yPos = (int) ((myCanvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));

        int x = sharedPreferences.getInt("x", xPos);
        int y = sharedPreferences.getInt("y", yPos);
        int rotate = sharedPreferences.getInt("rotate", 0);
        int centreX = (myCanvas.getWidth() - background.getWidth()) / 2;

        int centreY = (myCanvas.getHeight() - background.getHeight()) / 2;

        if (share == 0) {
            myCanvas.drawColor(Color.TRANSPARENT);
            myCanvas.drawBitmap(background, centreX, centreY, null);
            myCanvas.save();
            myCanvas.rotate(rotate, x, y);

            myCanvas.drawText(name, x, y, paint);
            myCanvas.restore();
        } else {
            myCanvas.drawColor(Color.WHITE);
            myCanvas.drawBitmap(background, centreX, centreY, null);
            myCanvas.save();
            myCanvas.rotate(rotate, x, y);

            myCanvas.drawText(name, x, y, paint);
            myCanvas.restore();
            sign.drawText("http://goo.gl/Ub2KSo",10,20,signPaint);
        }


        return myBitmap;
    }


}
