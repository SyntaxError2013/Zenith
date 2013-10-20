package in.co.sdslabs.managecontacts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver2 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
//we double check here for only boot complete event
if(intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED))
  {
    //here we start the service             
    Intent serviceIntent = new Intent(context, BootService2.class);
    context.startService(serviceIntent);
  }
}
}

