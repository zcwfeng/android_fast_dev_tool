package com.zcwfeng.fastdev.ui.fragment.image.glide;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.zcwfeng.fastdev.R;

public class FSAppWidgetProvider extends AppWidgetProvider {

    private AppWidgetTarget appWidgetTarget;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.custom_view_futurestudio);

        appWidgetTarget = new AppWidgetTarget( context, rv, R.id.custom_view_image, appWidgetIds );

        Glide
                .with( context.getApplicationContext() ) // safer!
                .load(context.getResources().getString(R.string.glide_target_url_3) )
                .asBitmap()
                .into( appWidgetTarget );

        pushWidgetUpdate(context, rv);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews rv) {
        ComponentName myWidget = new ComponentName(context, FSAppWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, rv);
    }
}