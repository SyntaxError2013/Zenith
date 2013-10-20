package in.co.sdslabs.managecontacts;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.ContactsContract;
import android.widget.Toast;

public class add2 extends Activity {

	@Override
	public void onBackPressed() {
		Intent i = new Intent(add2.this, Homepage.class);
		startActivity(i);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contacts);
		new RetreiveFeedTask().execute();
	}

	class RetreiveFeedTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected String doInBackground(String... user) {

			Looper.prepare();
			database1 db = new database1(add2.this);
			db.open();
			int p = db.getCount2();
			int j = 0;
			while (j < p) {

				String[] name = db.getName2();
				String[] Number = db.getContact2();
				String[] email = db.getEmail2();

				String DisplayName = name[j];
				String MobileNumber = Number[j];
				String emailID = email[j];
				ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

				ops.add(ContentProviderOperation
						.newInsert(ContactsContract.RawContacts.CONTENT_URI)
						.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,
								null)
						.withValue(ContactsContract.RawContacts.ACCOUNT_NAME,
								null).build());

				if (DisplayName != null) {
					ops.add(ContentProviderOperation
							.newInsert(ContactsContract.Data.CONTENT_URI)
							.withValueBackReference(
									ContactsContract.Data.RAW_CONTACT_ID, 0)
							.withValue(
									ContactsContract.Data.MIMETYPE,
									ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
							.withValue(
									ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
									DisplayName).build());
				}

				if (MobileNumber != null) {
					ops.add(ContentProviderOperation
							.newInsert(ContactsContract.Data.CONTENT_URI)
							.withValueBackReference(
									ContactsContract.Data.RAW_CONTACT_ID, 0)
							.withValue(
									ContactsContract.Data.MIMETYPE,
									ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
							.withValue(
									ContactsContract.CommonDataKinds.Phone.NUMBER,
									MobileNumber)
							.withValue(
									ContactsContract.CommonDataKinds.Phone.TYPE,
									ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
							.build());
				}

				if (emailID != null) {
					ops.add(ContentProviderOperation
							.newInsert(ContactsContract.Data.CONTENT_URI)
							.withValueBackReference(
									ContactsContract.Data.RAW_CONTACT_ID, 0)
							.withValue(
									ContactsContract.Data.MIMETYPE,
									ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
							.withValue(
									ContactsContract.CommonDataKinds.Email.DATA,
									emailID)
							.withValue(
									ContactsContract.CommonDataKinds.Email.TYPE,
									ContactsContract.CommonDataKinds.Email.TYPE_WORK)
							.build());
				}

				try {
					getContentResolver().applyBatch(ContactsContract.AUTHORITY,
							ops);
					Toast.makeText(getApplicationContext(),
							"Contact " + name[j] + " Created.",
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(),
							"Exception: " + e.getMessage(), Toast.LENGTH_SHORT)
							.show();
				}
				j++;
			}
			Intent i = new Intent(add2.this, secondsheet.class);
			startActivity(i);
			Looper.loop();
			return null;

		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}

		protected void onPostExecute() {
		}
	}
}
