package com.epoint.epointuniversal;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epoint.androidmobile.core.control.ToastUtil;
import com.epoint.androidmobile.core.string.StringHelp;
import com.epoint.androidmobile.core.superview.EpointPhoneActivity;
import com.epoint.epointuniversal.pjq.R;

/**
 * @author liyc
 * @time 2013-4-15 下午3:52:54
 * @annomation
 */
public class SuperPhonePage extends EpointPhoneActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.epointsuperlayout);
		setLlContent((ViewGroup) findViewById(R.id.llContent));
		setScContent((ViewGroup)findViewById(R.id.scContent));
		setTvTitle((TextView) findViewById(R.id.tvTitle));
		setIvLeft((ImageButton) findViewById(R.id.ivLeft));
		setIvRight((ImageButton) findViewById(R.id.ivRight));
		setInflater(LayoutInflater.from(this));
		getIvLeft().setOnClickListener(this);
		getIvRight().setOnClickListener(this);

		setRlTopBar((RelativeLayout)findViewById(R.id.rlTopBar));
		setPbGress((ProgressBar)findViewById(R.id.pbGress));
		setBtRight((Button)findViewById(R.id.btRight));
		getBtRight().setOnClickListener(this);
		getLlContent().setBackgroundResource(R.drawable.bg8);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	public String getIntentViewTitle()
	{
		String passTitle = getIntent().getStringExtra("viewtitle");
		return passTitle==null?"":passTitle;
	}

	public boolean validateXML(Object bs)
	{
		if(bs==null){
			ToastUtil.toastWorning(this, "网络超时");
			return false;
		}
		else{
			String xml = bs.toString();
			String ReturnInfo = StringHelp.getXMLAtt(xml, "ReturnInfo");
			String Status = StringHelp.getXMLAtt(ReturnInfo, "Status");
			if(Status.equalsIgnoreCase("True"))
			{
				return true;
			}
			else{
				ToastUtil.toastWorning(this, StringHelp.getXMLAtt(xml, "Description"));
				return false;
			}
		}
	}
	
	public Map<String, Object> getTaskParams()
	{
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("context", this);
		return params;
	}
 
}
