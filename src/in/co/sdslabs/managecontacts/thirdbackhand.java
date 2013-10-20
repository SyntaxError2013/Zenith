package in.co.sdslabs.managecontacts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

public class thirdbackhand extends Activity {

	@Override
	public void onBackPressed() {
	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		final EditText user = (EditText) findViewById(R.id.editText1);
		final EditText pass = (EditText) findViewById(R.id.editText2);
		final EditText title = (EditText) findViewById(R.id.editText3);
		final Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String user1 = user.getText().toString();
				String pass1 = pass.getText().toString();
				String title1 = title.getText().toString();
				database1 db = new database1(thirdbackhand.this);
				db.open();
				db.emptyTABLE3();
				new RetreiveFeedTask().execute(user1, pass1, title1);
				db.close();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menus3, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.homepage: {
			Intent i = new Intent(thirdbackhand.this, Homepage.class);
			startActivity(i);
			break;
		}
		default:
			return false;
		}
		return false;
	}


	class RetreiveFeedTask extends AsyncTask<String, String, String> {
		ProgressDialog mProgressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = ProgressDialog.show(thirdbackhand.this,
					"Update:", "Please wait while the app is updating.", true);
			mProgressDialog.setCancelable(true);
		}

		protected String doInBackground(String... user) {

			Looper.prepare();

			database1 db = new database1(thirdbackhand.this);
			db.open();
			String spreadsheetTitle = user[2];
			String USERNAME = user[0];
			String PASSWORD = user[1];
			// Google Authorization using clientLogin
			SpreadsheetService service = new SpreadsheetService(
					"MySpreadsheetIntegration-v1");
			try {

				service.setProtocolVersion(SpreadsheetService.Versions.V3);
				service.setUserCredentials(USERNAME, PASSWORD);
			} catch (com.google.gdata.util.AuthenticationException e) {
				// TODO Auto-generated catch block
				Toast.makeText(thirdbackhand.this, e.toString(),
						Toast.LENGTH_LONG).show();
			}

			SpreadsheetEntry spreadsheet = null;
			URL metafeedUrl;
			try {

				metafeedUrl = new URL(
						"https://spreadsheets.google.com/feeds/spreadsheets/private/full");
				SpreadsheetFeed spreadsheetFeed = service.getFeed(metafeedUrl,
						SpreadsheetFeed.class);

				List<SpreadsheetEntry> spreadsheets = spreadsheetFeed
						.getEntries();
				for (SpreadsheetEntry entry : spreadsheets) {
					if (entry.getTitle().getPlainText()
							.equals(spreadsheetTitle)) {
						spreadsheet = entry;
					}
				}

				if (spreadsheet == null) {
					throw new FileNotFoundException(
							"Cannot find the required spreadsheet '"
									+ spreadsheetTitle + "'");
				}
				WorksheetFeed worksheetFeed = service.getFeed(
						spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
				List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
				WorksheetEntry worksheet = worksheets.get(0);

				URL listFeedUrl = worksheet.getListFeedUrl();
				ListFeed feed = service.getFeed(listFeedUrl, ListFeed.class);
			
				for (ListEntry row : feed.getEntries()) {
					String[] rowname = new String[8];
					int j = 0;
					
					for (String tag : row.getCustomElements().getTags()) {
						rowname[j] = row.getCustomElements().getValue(tag);
						j++;
						System.out.print(row.getCustomElements().getValue(tag)
								+ "\t");
					}if(j==1){
						
					}
					else{
					db.createEntry3(rowname[3], rowname[5], rowname[4],
							rowname[2], rowname[1], rowname[0]);}
					System.out.println();
				}
				db.close();
				mProgressDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"SpreadSheet Updated.. :)", Toast.LENGTH_LONG).show();
				Intent i = new Intent(thirdbackhand.this, thirdsheet.class);
				startActivity(i);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				Toast.makeText(thirdbackhand.this, e.toString(),
						Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(thirdbackhand.this, e.toString(),
						Toast.LENGTH_LONG).show();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				Toast.makeText(thirdbackhand.this, e.toString(),
						Toast.LENGTH_LONG).show();
			}
			Looper.loop();
			return null;

		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		protected void onPostExecute() {
			mProgressDialog.dismiss();
		}
	}
}
