package in.co.sdslabs.managecontacts;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends FragmentActivity implements OnClickListener {
	 Button join ;
    String filename = "userInfo";
	// static SharedPreferences info;
	 static SharedPreferences getInfo;
	 String user;
	static String name;
	
	 public static class FireMissilesDialogFragment extends DialogFragment {
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				 final EditText input = new EditText(getActivity());
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(" Enter user's name ");
				builder.setView(input);
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								
								 SharedPreferences.Editor editor = getInfo.edit();
								name=input.getText().toString(); 
								editor.putString("name", name);
								editor.commit();
								
							}
						});
				return builder.create();
			}
		}
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity);
		join =(Button) findViewById(R.id.bJoin);
		join.setOnClickListener(this);
		getInfo=getSharedPreferences(filename, 0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bJoin){
			name = getInfo.getString("name", null);
		if(name==null)
		{	DialogFragment newFragment = new FireMissilesDialogFragment();
		newFragment.show(getSupportFragmentManager(),
				"missiles");
		
		}	
		else
		{
			name = getInfo.getString("name", null);
			Intent i= new Intent(MainActivity.this,GroupChat.class);
			Bundle basket = new Bundle();
			basket.putString("username", name);
			i.putExtras(basket);
			startActivity(i);
		}
		}
	}

}