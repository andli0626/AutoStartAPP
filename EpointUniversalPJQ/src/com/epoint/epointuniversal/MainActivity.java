package com.epoint.epointuniversal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.epoint.epointuniversal.pjq.R;

public class MainActivity extends SuperPhonePage {

	TextView bottomTextView;
	MarqueeText marqueeTextView;
	ImageView welcomeImageView;
	int imgs[] = { R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,
			R.drawable.pic3, R.drawable.pic4, R.drawable.pic5 };
	int i = 0;

	boolean isshowpic = true;

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (i > 5) {
					i = 0;
				} else {
					Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
					alphaAnimation.setDuration(3000);
					welcomeImageView.startAnimation(alphaAnimation);
					welcomeImageView.setBackgroundResource(imgs[i]);
				}

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置全屏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 隐藏底部操作栏
		// getLlContent().setSystemUiVisibility(
		// View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

		addContentViewAndTitle(R.layout.activity_main, "主页");

		hideTopBar();

		// 1024*561
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Log.i("andli", "屏幕分辨率为:" + dm.widthPixels + " * " + dm.heightPixels);

		bottomTextView = (TextView) findViewById(R.id.bottom_txt);
		marqueeTextView = (MarqueeText) findViewById(R.id.marquee_txt);
		marqueeTextView.startScroll();

		welcomeImageView = (ImageView) findViewById(R.id.welcome_imageview);
		Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation.setDuration(3000);
		welcomeImageView.startAnimation(alphaAnimation);
		new Thread() {
			public void run() {
				Looper.prepare();

				while (isshowpic) {
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);

				}

			};
		}.start();

	}

	@Override
	protected void onDestroy() {
		isshowpic = false;
		super.onDestroy();
	}
}
