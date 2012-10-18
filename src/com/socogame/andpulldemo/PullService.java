package com.socogame.andpulldemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class PullService extends Service {
	private NotificationManager notificationMgr;
	private boolean isStop = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("MSH", "====> service create");

		notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		displayNotificationMessage("启动后台服务进程！");
		Thread thread = new Thread(null, new ServiceWorker(),
				"BackgroundService");
		thread.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("MSH", "====> service destroy");

		displayNotificationMessage("结束后台服务进程！");
		isStop = true;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("MSH", "====> service start");
	}

	private void displayNotificationMessage(String message) {
		Notification notification = new Notification(R.drawable.ic_launcher,
				message, System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, AndPullActivity.class), 0);
		notification.setLatestEventInfo(this, "点击进入演示程序", message,
				contentIntent);
		// notificationMgr.notify(R.id.app_notification_id, notification);
		notificationMgr.notify(0, notification);
	}

	class ServiceWorker implements Runnable {
		public Handler mHandler;
		private int count = 0;

		public void run() {
			while (true) {
				try {
					if (isStop) {
						break;
					}
					syncData();
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Log.i("MSH", "====> safe out");
		}

		private void syncData() {
			displayNotificationMessage("第" + count + "次同步数据");
			count++;
		}
	}

}
