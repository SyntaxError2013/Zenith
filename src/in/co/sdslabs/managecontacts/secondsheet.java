package in.co.sdslabs.managecontacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class secondsheet extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(),Homepage.class);
		startActivity(i);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		database1 db = new database1(secondsheet.this);
		db.open();
		boolean val = db.checkEmptyTable2();
		if (val == true) {
			Toast.makeText(getApplicationContext(),
					"Table Empty..So Shifting to UpDate Column..",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(secondsheet.this, secondbackhand.class);
			startActivity(i);
		}

		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		expListView.setAdapter(listAdapter);

		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
			}
		});

		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
			}
		});

		expListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					final int groupPosition, final int childPosition, long id) {
				// TODO Auto-generated method stub
				if (childPosition == 1) {
					String total;
					total = ((listDataChild.get(listDataHeader
							.get(groupPosition)).get(childPosition)));
					StringBuilder str = new StringBuilder(total);
					final String number1 = str.substring(6, str.length());
					Intent massemail = new Intent(Intent.ACTION_SEND);

					massemail.putExtra(Intent.EXTRA_EMAIL,
							new String[] { number1 });
					massemail.setType("message/rfc822");
					startActivity(Intent.createChooser(massemail,
							"Choose an Email client :"));

				}
				if (childPosition == 0) {
					AlertDialog.Builder alrt = new AlertDialog.Builder(
							secondsheet.this);
					alrt.setTitle(listDataHeader.get(groupPosition));
					alrt.setMessage(listDataChild.get(
							listDataHeader.get(groupPosition)).get(
							childPosition));
					String total;
					total = ((listDataChild.get(listDataHeader
							.get(groupPosition)).get(childPosition)));
					StringBuilder str = new StringBuilder(total);
					final String number1 = str.substring(8, 18);
					final long number = Long.parseLong(number1);
					alrt.setPositiveButton("Call",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									Intent callIntent = new Intent(
											Intent.ACTION_CALL);
									callIntent.setData(Uri.parse("tel:"
											+ number));
									startActivity(callIntent);
								}
							});
					alrt.setNegativeButton("Message",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									try {
										Intent sendIntent = new Intent(
												Intent.ACTION_VIEW);
										sendIntent.putExtra("sms_body", "");
										sendIntent.putExtra("address", number1);
										sendIntent
												.setType("vnd.android-dir/mms-sms");
										startActivity(sendIntent);

									} catch (Exception e) {
										Toast.makeText(
												getApplicationContext(),
												"SMS faild, please try again later!",
												Toast.LENGTH_LONG).show();
										e.printStackTrace();
									}
								}
							});
					alrt.show();
				}
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menus, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		database1 db = new database1(secondsheet.this);
		db.open();
		String[] email = db.getEmail2();
		switch (item.getItemId()) {
		case R.id.homepage: {
			Intent i = new Intent(secondsheet.this, Homepage.class);
			startActivity(i);
			break;
		}
		case R.id.settings: {
			Intent i = new Intent(secondsheet.this, secondbackhand.class);
			startActivity(i);
			break;
		}
		case R.id.massemail: {
			Intent massemail = new Intent(Intent.ACTION_SEND);
			massemail.putExtra(Intent.EXTRA_EMAIL, email);
			massemail.setType("message/rfc822");
			startActivity(Intent.createChooser(massemail,
					"Choose an Email client :"));
			break;
		}
		case R.id.masssms: {
			Intent i = new Intent(secondsheet.this, massmessage2.class);
			startActivity(i);
			break;
		}
		case R.id.contactimport: {
			AlertDialog.Builder alrt = new AlertDialog.Builder(secondsheet.this);
			alrt.setTitle("Conformation::");
			alrt.setMessage("Are you sure to import all Contacts to your phone..");
			alrt.setPositiveButton("Import",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent i = new Intent(secondsheet.this, add2.class);
							startActivity(i);
						}
					});
			alrt.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent i = new Intent(secondsheet.this,
									secondsheet.class);
							startActivity(i);
						}
					});
			alrt.show();
		}

		default:
			return false;
		}
		return false;
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		database1 db = new database1(secondsheet.this);
		db.open();
		int p = db.getCount2();

		String[] name = db.getName2();
		String[] email = db.getEmail2();
		String[] mobilenumber = db.getContact2();
		String[] year=db.getYear2();
		String[] branch=db.getBranch2();
		String[] dob = db.getDOB2();
		int i = 0;
		while (i < p) {
			listDataHeader.add(name[i]);
			List<String> details = new ArrayList<String>();
			details.add("Contact:" + mobilenumber[i]);
			details.add("Email:" + email[i]);
			details.add("DOB:" + dob[i]);
			details.add("Column5:"+ year[i]);
			details.add("Column6:"+ branch[i]);
			listDataChild.put(listDataHeader.get(i), details);
			i++;
		}
	}
}