package in.co.sdslabs.managecontacts;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class massmessage2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		final EditText edt = (EditText) findViewById(R.id.editText1);
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String message = edt.getText().toString();
				int j = 0, p = 0;
				Toast.makeText(getApplicationContext(),
						"Please Wait.. The process will take time.. :)",
						Toast.LENGTH_LONG).show();
				database1 db = new database1(massmessage2.this);
				db.open();
				p = db.getCount2();
				String[] mobilenumber = db.getContact2();
				SmsManager sms = SmsManager.getDefault();
				while (j < p) {

					String SENT = "SMS_SENT";
					String DELIVERED = "SMS_DELIVERED";

					PendingIntent sentPI = PendingIntent.getBroadcast(
							getApplicationContext(), 0, new Intent(SENT), 0);

					PendingIntent deliveredPI = PendingIntent.getBroadcast(
							getApplicationContext(), 0, new Intent(DELIVERED),
							0);

					// ---when the SMS has been sent---
					registerReceiver(new BroadcastReceiver() {
						@Override
						public void onReceive(Context arg0, Intent arg1) {
							switch (getResultCode()) {
							case Activity.RESULT_OK:
								// Toast.makeText(getBaseContext(), "SMS sent",
								// Toast.LENGTH_LONG).show();
								break;
							case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
								Toast.makeText(getBaseContext(),
										"Generic failure", Toast.LENGTH_LONG)
										.show();
								break;
							case SmsManager.RESULT_ERROR_NO_SERVICE:
								Toast.makeText(getBaseContext(), "No service",
										Toast.LENGTH_LONG).show();
								break;
							case SmsManager.RESULT_ERROR_NULL_PDU:
								Toast.makeText(getBaseContext(), "Null PDU",
										Toast.LENGTH_LONG).show();
								break;
							case SmsManager.RESULT_ERROR_RADIO_OFF:
								Toast.makeText(getBaseContext(), "Radio off",
										Toast.LENGTH_LONG).show();
								break;
							}
						}
					}, new IntentFilter(SENT));

					// ---when the SMS has been delivered---
					registerReceiver(new BroadcastReceiver() {
						@Override
						public void onReceive(Context arg0, Intent arg1) {
							switch (getResultCode()) {
							case Activity.RESULT_OK:
								Toast.makeText(getBaseContext(),
										"SMS delivered", Toast.LENGTH_LONG)
										.show();
								break;
							case Activity.RESULT_CANCELED:
								Toast.makeText(getBaseContext(),
										"SMS not delivered", Toast.LENGTH_LONG)
										.show();
								break;
							}
						}
					}, new IntentFilter(DELIVERED));

					sms.sendTextMessage(mobilenumber[j], null, message, sentPI,
							deliveredPI);

					j++;
				}
				db.close();
				Toast.makeText(getApplicationContext(),
						"SMS sending to all people.", Toast.LENGTH_LONG).show();
				
			}
		});
		Intent i = new Intent(massmessage2.this, secondsheet.class);
		startActivity(i);
	}

}
