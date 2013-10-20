package in.co.sdslabs.managecontacts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class LogView extends ScrollView{
  
	TextView textView;
	public LogView(Context context,AttributeSet attr) {
		super(context,attr);
		// TODO Auto-generated constructor stub
		textView =new  TextView(context);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        addView(textView);
		
	}
	public void appendLog(String msg)
	{
		textView.append(msg+"\n");
		 post(new Runnable() {
	            @Override
	            public void run() {
	                fullScroll(View.FOCUS_DOWN);
	            }
	        });
	}

}
