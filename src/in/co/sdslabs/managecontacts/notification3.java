package in.co.sdslabs.managecontacts;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class notification3 extends Activity {
	Button notify;
	String filename = "userInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new1);
		SavePreferences("set", "done");
		AlarmManager mgrAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
		String[] date = null;
		String[] month = null;
		database1 db = new database1(notification3.this);
		db.open();
		int p = db.getCount2();
		int j = 0;
		String[] dob = db.getDOB2();
		while (j < p) {
			String[] row = dob[j].split("/");
			date[j] = row[0];
			month[j] = row[1];
			j++;
		}
		for (int i = 0; i < p; i++) {
			Intent intent = new Intent(this, Time.class);
			// Loop counter `i` is used as a `requestCode`
			PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i,
					intent, 0);
			// Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
			Calendar timeOff9 = (Calendar) Calendar.getInstance();
			int year = timeOff9.getTime().getYear();
			timeOff9.set(year, Integer.parseInt(date[i]),
					Integer.parseInt(month[i]));
			timeOff9.set(Calendar.HOUR_OF_DAY, 0);
			timeOff9.set(Calendar.MINUTE, 0);
			timeOff9.set(Calendar.SECOND, 0);
			mgrAlarm.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(),
					pendingIntent);

			intentArray.add(pendingIntent);

		}
		Toast.makeText(getApplicationContext(), "Alarms Set..",
				Toast.LENGTH_SHORT).show();
		Intent i = new Intent(notification3.this, thirdsheet.class);
		startActivity(i);

	}

	private void SavePreferences(String key, String value) {
		SharedPreferences sharedPreferences = getSharedPreferences(filename, 0);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
}
