package in.co.sdslabs.managecontacts;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;
import com.samsung.chord.IChordChannelListener;
import com.samsung.chord.IChordManagerListener;

public class GroupChat extends Activity implements OnClickListener{

	Button send/*,leave*/;
	EditText msg;
	LogView logView;
	ChordManager mChordManager;
	public static IChordChannelListener mChordChannelListener;
	public static IChordManagerListener mManagerListener;
    IChordChannel channel ;
	String userName;
	private static final String CHORD_HELLO_TEST_CHANNEL = "in.co.sdslabs.managecontacts.HELLOTESTCHANNEL";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_chat);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		send=(Button) findViewById(R.id.bSend);
		/*leave=(Button) findViewById(R.id.bLeave);*/
		msg=(EditText) findViewById(R.id.etMsg);
		logView=(LogView) findViewById(R.id.lvChat);
		send.setOnClickListener(this);
		/*leave.setOnClickListener(this);*/
		Intent fromOptions = getIntent();
		userName = fromOptions.getStringExtra("username"); 
		
		 mManagerListener=new IChordManagerListener() {

			@Override
			public void onError(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNetworkDisconnected() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStarted(String arg0, int arg1) {
				// TODO Auto-generated method stub
				JoinChannel();
				//Toast.makeText(GroupChat.this, "in on started method", Toast.LENGTH_SHORT).show(); 
				
			}
			/*@Override
			public void onStopped(int reason) {
			
			// Stopped
			mChordManager.close();
		
			}	*/
		 };
			
			 mChordChannelListener=new IChordChannelListener(){

				@Override
				public void onDataReceived(String arg0, String arg1,
						String arg2, byte[][] arg3) {
					// TODO Auto-generated method stub
					String receivedData = new String(arg3[0]);
					logView.appendLog(receivedData);
				}

				@Override
				public void onFileChunkReceived(String arg0, String arg1,
						String arg2, String arg3, String arg4, String arg5,
						long arg6, long arg7) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFileChunkSent(String arg0, String arg1,
						String arg2, String arg3, String arg4, String arg5,
						long arg6, long arg7, long arg8) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFileFailed(String arg0, String arg1, String arg2,
						String arg3, String arg4, int arg5) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFileReceived(String arg0, String arg1,
						String arg2, String arg3, String arg4, String arg5,
						long arg6, String arg7) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFileSent(String arg0, String arg1, String arg2,
						String arg3, String arg4, String arg5) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFileWillReceive(String arg0, String arg1,
						String arg2, String arg3, String arg4, String arg5,
						long arg6) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onNodeJoined(String arg0, String arg1) {
					// TODO Auto-generated method stub
					Handler mHandler = new Handler(Looper.getMainLooper())
					{
				        @Override
				        public void handleMessage(Message inputMessage) {
				            // Gets the image task from the incoming Message object.
				        	Toast.makeText(GroupChat.this,"New friend Joined ",Toast.LENGTH_SHORT).show();
				    		
				    		
				        }
						
					};
					mHandler.sendEmptyMessage(0);
					
				}

				@Override
				public void onNodeLeft(String arg0, String arg1) {
					// TODO Auto-generated method stub
					Handler mHandler = new Handler(Looper.getMainLooper())
					{
				        @Override
				        public void handleMessage(Message inputMessage) {
				            // Gets the image task from the incoming Message object.
				        	Toast.makeText(GroupChat.this,"One friend left the conversation ",Toast.LENGTH_SHORT).show();
				    		
				    		
				        }
						
					};
					mHandler.sendEmptyMessage(0);
				}};
		
		//chordManager instance and check network
		mChordManager = ChordManager.getInstance(GroupChat.this);
		mChordManager.setHandleEventLooper(getMainLooper());
		
		List<Integer> interfaceList = mChordManager
				.getAvailableInterfaceTypes();
		if (interfaceList.isEmpty()) {
			// There is no connection.
			 Toast.makeText(this, "No connection available", Toast.LENGTH_SHORT).show(); 
			return;
		}
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show(); 
		int nError=  mChordManager.start(interfaceList.get(0)/*.intValue()*/,
					mManagerListener);
		/*if (ChordManager.ERROR_INVALID_STATE == nError) {
			Toast.makeText(this,nError +"    Invalid state!", Toast.LENGTH_SHORT).show();
        } else if (ChordManager.ERROR_INVALID_INTERFACE == nError) {
        	Toast.makeText(this,nError +"    Invalid connection!", Toast.LENGTH_SHORT).show(); 
        } else if (ChordManager.ERROR_INVALID_PARAM == nError) {
        	Toast.makeText(this,nError +"    Invalid argument!", Toast.LENGTH_SHORT).show(); 
        } else if (ChordManager.ERROR_FAILED == nError) {
        	Toast.makeText(this,nError +"    fail to start internal error!", Toast.LENGTH_SHORT).show(); 
        }*/
			//JoinChannel();
	
		//Toast.makeText(GroupChat.this, "in on create", Toast.LENGTH_SHORT).show(); 
		
		//if(channel==null){Toast.makeText(this,nError +"", Toast.LENGTH_SHORT).show(); }
		
	}

	private void JoinChannel() {
		// TODO Auto-generated method stub
		 channel = null;
		channel =  mChordManager.joinChannel(CHORD_HELLO_TEST_CHANNEL,
				  mChordChannelListener);
		
		/*******************************************************************************/
		String toSend=userName+" joined the conversation";
		byte[][] payload = new byte[1][];
		payload[0] = toSend.getBytes();
		int i=channel.getJoinedNodeList().size();
		 //Toast.makeText(this, "Number of nodes connected: "+i, Toast.LENGTH_SHORT).show();
		if(channel.getJoinedNodeList().size()>0)			
			{
			channel.sendDataToAll("String", payload);
			
			}
		/*******************************************************************************/
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*if(v.getId()==R.id.bLeave)
		{
			  if (mChordManager != null) {
		        	mChordManager.leaveChannel("");
		        	mChordManager.stop();
		            mChordManager = null;
		            mChordChannelListener=null;
		        	mManagerListener=null;
		            channel =null;
		            Toast.makeText(this, "Group Left", Toast.LENGTH_SHORT).show();
		        }
		}*/
		if(v.getId()==R.id.bSend)
		{
			if(channel!=null)
				{
				String toSend=userName+" says..."+msg.getText().toString();
				byte[][] payload = new byte[1][];
				payload[0] = toSend.getBytes();
				int i=channel.getJoinedNodeList().size();
				 Toast.makeText(this, "Number of nodes connected: "+i, Toast.LENGTH_SHORT).show();
				if(channel.getJoinedNodeList().size()>0)			
					{
					channel.sendDataToAll("String", payload);
					logView.appendLog(toSend);
					msg.setText("");
					}
			    }
			
				 
		}
	}
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.settings,  menu);
			return true;
		}
	 public boolean onOptionsItemSelected(MenuItem item) {

			switch(item.getItemId()){
			case R.id.leaveGroup:
				 if (mChordManager != null) {
			        	mChordManager.leaveChannel("");
			        	mChordManager.stop();
			            mChordManager = null;
			            mChordChannelListener=null;
			        	mManagerListener=null;
			            channel =null;
			            Toast.makeText(this, "Group Left", Toast.LENGTH_SHORT).show();
			        }
			}
			
				return true;
			}

	 @Override
	    public void onDestroy() {
	        /**
	         * [D] Release Chord!
	         */
	        if (mChordManager != null) {
	        	mChordManager.leaveChannel("");
	            mChordManager = null;
	        }

	        super.onDestroy();
	    }
}
