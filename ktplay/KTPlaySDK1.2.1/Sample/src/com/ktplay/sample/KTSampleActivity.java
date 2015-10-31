package com.ktplay.sample;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.ktplay.open.KTPlay;
import com.ktplay.open.KTPlay.OnActivityStatusChangedListener;
import com.ktplay.open.KTPlay.OnAppearListener;
import com.ktplay.open.KTPlay.OnDisappearListener;
import com.ktplay.open.KTPlay.OnDispatchRewardsListener;
import com.ktplay.open.KTRewardItem;


/**
 * 展示KTPlay SDK使用方法
 */
public class KTSampleActivity extends Activity {
	private final static String TAG = "KTSampleActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		//初始化KTPlay SDK,此方法必须在游戏主SDK的onCreate方法中调用.
		KTPlay.startWithAppKey(this, IConfig.APP_KEY, IConfig.APP_SECRET);

		//设置各类回调
		setKTListeners();
		
		//模拟游戏运行
		startGameLoop();
	}
	
	public void onClick (View v) {
		switch(v.getId()){
			case R.id.btn_showkt:
				KTPlay.show(); //显示KT窗口
				break;
				
			//登录相关功能
			case R.id.btn_login_feature:{
				Intent intent = new Intent(this,KTLoginSampleActivity.class);
				this.startActivity(intent);
			}
			break;
			
			//好友相关功能
			case R.id.btn_friend_feature:{
				Intent intent = new Intent(this,KTFriendshipSampleActivity.class);
				this.startActivity(intent);
			}
			break;
			
			//排行榜相关功能
			case R.id.btn_leaderboard_feature:{
				Intent intent = new Intent(this,KTLeaderboardSampleActivity.class);
				this.startActivity(intent);
			}
			break;
		}
	}
	
	/**
	 * 显示新消息提示
	 */
	private void showNewMsgHint(){
		this.findViewById(R.id.img_new_msg).setVisibility(View.VISIBLE);
	}
	
	
	/**
	 * 隐藏新消息提示
	 */
	private void hideNewMsgHint(){
		this.findViewById(R.id.img_new_msg).setVisibility(View.GONE);
	}
	

	private void setKTListeners() {
		
		//KT展开时回调
		KTPlay.setOnAppearListener(new OnAppearListener() {
			@Override
			public void onAppear() {
				Log.i(TAG, "KTPlay Opened");
			}
		});
		
		//KT关闭时回调
		KTPlay.setOnDisappearListener(new OnDisappearListener() {
			@Override
			public void onDisappear() {
				Log.i(TAG, "KTPlay Closed");
			}
		});
		
		//KT有新消息/状态时回调
		KTPlay.setOnActivityStatusChangedListener(new OnActivityStatusChangedListener() {
			@Override
			public void onActivityChanged(boolean hasNewActivity) {
				if(hasNewActivity){
					showNewMsgHint(); //显示新消息提示
				}else{
					hideNewMsgHint(); //隐藏新消息提示
				}
			}
		});
		
		//KT触发领取奖励时回调
		KTPlay.setOnDispatchRewardsListener(new OnDispatchRewardsListener() {
			@Override
			public void onDispatchRewards(ArrayList<KTRewardItem> rewards) {
				Log.i(TAG, "KTPlay Dispatch Rewards");
				for (KTRewardItem reward:rewards) {
					//模拟游戏分发奖励流程
					dispatchReward(reward);
					String tip = String.format(getString(R.string.dispatch_reward), 
							reward.getName(),
							reward.getValue(),
							mRewards.get(reward.getTypeId()));
					Toast.makeText(KTSampleActivity.this, tip, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	
	private Hashtable<String,Long> mRewards = new Hashtable<String,Long>();
	
	/**
	 * 模拟游戏发奖励
	 * @param reward 奖励内容
	 */
	public void dispatchReward(KTRewardItem reward){
		String type = reward.getTypeId();
		Long obj = mRewards.get(type);
		if(obj != null){ //在已有数值上添加
			mRewards.put(type, obj + reward.getValue());
		}else{ //新增
			mRewards.put(type, reward.getValue());
		}
	}

	/**
	 * 模拟游戏更新
	 */
	public void gameUpdate() {
		KTPlay.update(); //KTPlay的update方法必须在游戏的主循环中被调用，否则可能导致截图功能不可用
	}

	/**
	 * 启动游戏循环(模拟)
	 */
	public void startGameLoop() {
		new Thread() {
			public void run() {
				while (true) {
					try {
						gameUpdate();
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}
			}
		}.start();
	}

	@Override
	public void onResume() {
		super.onResume();
		KTPlay.onResume(this); //必须调用
	}

	@Override
	public void onPause() {
		super.onPause();
		KTPlay.onPause(this); //必须调用
	}
}
