package in.co.sdslabs.managecontacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Homepage extends Activity {
	Button btn1, btn2, btn3, btn4,btn5;
	SharedPreferences storeInfo, getInfo;
	static String fileName = "loginInfo";

	@Override
	public void onBackPressed() {
		AlertDialog.Builder alrt = new AlertDialog.Builder(Homepage.this);
		alrt.setTitle("Exit::");
		alrt.setMessage("Are you sure you want to exit the application:");
		alrt.setPositiveButton("Exit", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		alrt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent i = new Intent(Homepage.this, Homepage.class);
				startActivity(i);
			}
		});
		alrt.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
		btn5 = (Button) findViewById(R.id.button5);
		storeInfo = getSharedPreferences(fileName, 0);
		getInfo = getSharedPreferences(fileName, 0);
		String name1 = getInfo.getString("name1", "null");
		String name2 = getInfo.getString("name2", "null");
		String name3 = getInfo.getString("name3", "null");
		if (name1 != "null") {
			btn1.setText(name1);
		}
		if (name2 != "null") {
			btn2.setText(name2);
		}
		if (name3 != "null") {
			btn3.setText(name3);
		}
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Homepage.this, firstsheet.class);
				startActivity(i);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i1 = new Intent(Homepage.this, secondsheet.class);
				startActivity(i1);
			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i2 = new Intent(Homepage.this, thirdsheet.class);
				startActivity(i2);
			}
		});
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i2 = new Intent(Homepage.this, MainActivity.class);
				startActivity(i2);
			}
		});
btn5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i2 = new Intent(Homepage.this, About_us.class);
				startActivity(i2);
			}
		});
		final SharedPreferences.Editor editor = storeInfo.edit();
		btn1.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				final View layout = View.inflate(Homepage.this,
						R.layout.edittext, null);

				final EditText savedText = ((EditText) layout
						.findViewById(R.id.myEditText));

				AlertDialog.Builder builder = new AlertDialog.Builder(
						Homepage.this);
				builder.setTitle("Change SpreadSheet Name:");
				builder.setIcon(0);

				builder.setPositiveButton("Save", new Dialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String myTextString = savedText.getText().toString();
						editor.putString("name1", myTextString);
						editor.commit();
						Toast.makeText(Homepage.this, "Name Changed",
								Toast.LENGTH_LONG).show();
						btn1.setText(myTextString);
					}
				});
				builder.setView(layout);
				builder.create();
				builder.show();
				return true;

			}
		});
		btn2.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				final View layout = View.inflate(Homepage.this,
						R.layout.edittext, null);

				final EditText savedText = ((EditText) layout
						.findViewById(R.id.myEditText));

				AlertDialog.Builder builder = new AlertDialog.Builder(
						Homepage.this);
				builder.setTitle("Change SpreadSheet Name:");
				builder.setIcon(0);

				builder.setPositiveButton("Save", new Dialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String myTextString = savedText.getText().toString();
						editor.putString("name2", myTextString);
						editor.commit();
						Toast.makeText(Homepage.this, "Name Changed",
								Toast.LENGTH_LONG).show();
						btn2.setText(myTextString);
					}
				});
				builder.setView(layout);
				builder.create();
				builder.show();
				return true;

			}
		});
		btn3.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				final View layout = View.inflate(Homepage.this,
						R.layout.edittext, null);

				final EditText savedText = ((EditText) layout
						.findViewById(R.id.myEditText));

				AlertDialog.Builder builder = new AlertDialog.Builder(
						Homepage.this);
				builder.setTitle("Change SpreadSheet Name:");
				builder.setIcon(0);

				builder.setPositiveButton("Save", new Dialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String myTextString = savedText.getText().toString();
						editor.putString("name3", myTextString);
						editor.commit();
						Toast.makeText(Homepage.this, "Name Changed",
								Toast.LENGTH_LONG).show();
						btn3.setText(myTextString);
					}
				});
				builder.setView(layout);
				builder.create();
				builder.show();
				return true;

			}
		});

	}
}