package com.appvn.cocacola;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.appvn.cocacola.draw.DrawBitmap;


/**
 * Implementation of App Widget functionality.
 */
public class WidgetCoca extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent.getAction().equals(MyActivity.ACTION_UPDATE_WIDET)){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_coca);

            views.setImageViewBitmap(R.id.imgWidget, DrawBitmap.buildBitmap(context,0));
            Intent i=new Intent(context, MyActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, i, 0);
            views.setOnClickPendingIntent(R.id.imgWidget, pendingIntent);
            pushWidGetupdate(context,views);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_coca);

        views.setImageViewBitmap(R.id.imgWidget, DrawBitmap.buildBitmap(context,0));
        Intent i=new Intent(context, MyActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, i, 0);
        views.setOnClickPendingIntent(R.id.imgWidget, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void pushWidGetupdate(Context context, RemoteViews remoteViews) {
        // TODO Auto-generated method stub
        ComponentName myWidget = new ComponentName(context, WidgetCoca.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}


