package com.project.uwm.mydiabitiestracker.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import com.project.uwm.mydiabitiestracker.R;

public class ReminderService extends WakeReminderIntentService {

	public ReminderService() {
		super("ReminderService");
			}

	@Override
	void doReminderWork(Intent intent) {
		Log.d("ReminderService", "Doing work.");
		Long rowId = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);
		 
		NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
						
		Intent notificationIntent = new Intent(this, ReminderEditActivity.class); 
		notificationIntent.putExtra(RemindersDbAdapter.KEY_ROWID, rowId); 
		
		PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this);


        builder.setAutoCancel(false);
      /*  builder.setTicker("this is ticker text");*/
        builder.setContentTitle(getString(R.string.notify_new_task_title));
        builder.setContentText(getString(R.string.notify_new_task_message));
       /* builder.setSmallIcon(R.drawable.ic_launcher);*/
        builder.setContentIntent(pi);
        builder.setOngoing(true);
        builder.setSubText("This is subtext...");   //API level 16
        builder.setNumber(100);
        builder.build();
		/*Notification note=new Notification(android.R.drawable.stat_sys_warning,
                getString(R.string.notify_new_task_message),
                System.currentTimeMillis());
		note.setLatestEventInfo(this, getString(R.string.notify_new_task_title),
                getString(R.string.notify_new_task_message), pi);
		note.defaults |= Notification.DEFAULT_SOUND; 
		note.flags |= Notification.FLAG_AUTO_CANCEL; */
		
		// An issue could occur if user ever enters over 2,147,483,647 tasks. (Max int value). 
		// I highly doubt this will ever happen. But is good to note. 
		int id = (int)((long)rowId);
		mgr.notify(id, builder.getNotification());
		
		
	}
}
