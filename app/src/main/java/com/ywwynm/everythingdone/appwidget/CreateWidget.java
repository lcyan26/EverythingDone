package com.ywwynm.everythingdone.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.ywwynm.everythingdone.App;
import com.ywwynm.everythingdone.R;
import com.ywwynm.everythingdone.activities.DetailActivity;
import com.ywwynm.everythingdone.utils.DisplayUtil;

/**
 * Created by ywwynm on 2016/7/27.
 * App Widget for creating new thing
 */
public class CreateWidget extends AppWidgetProvider {

    public static final String TAG = "CreateWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_create);

            int color = DisplayUtil.getRandomColor(context);
            while (color == App.newThingColor) {
                color = DisplayUtil.getRandomColor(context);
            }
            App.newThingColor = color;

            Intent contentIntent = DetailActivity.getOpenIntentForCreate(context, TAG, color);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    appWidgetId, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.iv_widget_create_content, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
