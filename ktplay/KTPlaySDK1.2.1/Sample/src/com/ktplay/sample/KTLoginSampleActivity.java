package com.ktplay.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ktplay.open.KTAccountManager;
import com.ktplay.open.KTAccountManager.KTLoginListener;
import com.ktplay.open.KTAccountManager.OnGetUserInfoListener;
import com.ktplay.open.KTError;
import com.ktplay.open.KTPlay;
import com.ktplay.open.KTUser;

public class KTLoginSampleActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		

		this.setContentView(R.layout.login);
		this.updateView();
		
		//监听登录状态变更（可选，需要的时候再设置）
		KTAccountManager.setLoginStatusChangedListener(new KTAccountManager.OnLoginStatusChangedListener() {
			@Override
			public void onLoginStatusChanged(boolean isLoggedIn, KTUser user) {
				String s = String.format(KTLoginSampleActivity.this.getString(R.string.login_status_changed),
						KTLoginSampleActivity.this.getString(isLoggedIn ?
								R.string.login_status_loggedin:
								R.string.login_status_not_login
								));
				Toast.makeText(KTLoginSampleActivity.this, s, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void onClick(View v){
		switch(v.getId()){
			case R.id.btn_showlogin:{
				//显示独立登录窗口(不可关闭)
				KTAccountManager.showLoginView(false, this.getListener());
			}
			break;
			
			case R.id.btn_showlogin_with_close:{
				//显示独立登录窗口(可关闭)
				KTAccountManager.showLoginView(true, this.getListener());
			}
			break;
			
			case R.id.btn_userinfo:{
				//用户信息
				this.getUserProfile();
			}
				break;
		}
	}
	
	public void onPause(){
		KTPlay.onPause(this); //必须调用
		super.onPause();
	}
	
	public void onResume(){
		super.onResume(); //必须调用
		KTPlay.onResume(this);
	}
	
	public void onDestroy(){
		//清空登录状态回调
		KTAccountManager.setLoginStatusChangedListener(null);
		super.onDestroy();
	}
	
	private void getUserProfile(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View v = this.getLayoutInflater().inflate(R.layout.user_info, null);
		builder.setView(v);
		final EditText edit = (EditText) v.findViewById(R.id.user_info_id);
		builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String userId = edit.getText().toString();
				if(TextUtils.isEmpty(userId)){
					Context context = KTLoginSampleActivity.this;
					Toast.makeText(context, context.getString(R.string.please_input), 
							Toast.LENGTH_SHORT).show();
					return;
				}
				KTAccountManager.userProfile(userId, new OnGetUserInfoListener(){
					@Override
					public void onGetUserInfoResult(boolean isSuccess,String userId,
							KTUser user, KTError error) {
						Context context = KTLoginSampleActivity.this;
						if(isSuccess){
							Toast.makeText(context, "success:userId=" + userId , 
									Toast.LENGTH_SHORT).show();
							showUserInfo(user);
						}else{
							
							Toast.makeText(context, "failed:userId=" + userId + " errorCode=" + error.code + " errorMessage="+ error.description, 
									Toast.LENGTH_SHORT).show();
						}
					}});
			}
		});
		builder.setNegativeButton(R.string.cancel, null);
		builder.show();
	}
	
	private void showUserInfo(KTUser user){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.user_info);
		builder.setMessage(user.toString());
		builder.setNegativeButton(R.string.confirm, null);
		builder.show();
	}
	
	private void updateView(){
		TextView loginStatus = (TextView) this.findViewById(R.id.label_login_status);
		
		//检查登录状态
		loginStatus.setText(this.getString(KTAccountManager.isLoggedIn() ? 
				R.string.login_status_loggedin : R.string.login_status_not_login));
		
		//显示当前登录用户信息
		KTUser user = KTAccountManager.currentAccount();
		String userInfo = user == null ? "" : user.toString();
		TextView userInfoView = (TextView) this.findViewById(R.id.login_user_info);
		userInfoView.setText(userInfo);
	}
	
	private KTLoginListener mListener;
	public KTLoginListener getListener(){
		if(mListener == null){
			mListener = new KTLoginListener(){
				@Override
				public void onLoginResult(boolean isSuccess, KTUser user,
						KTError error) {
					if(isSuccess){
						Toast.makeText(KTLoginSampleActivity.this, 
								KTLoginSampleActivity.this.getString(R.string.login_success), 
								Toast.LENGTH_SHORT).show();
						updateView();
					}else{
						Toast.makeText(KTLoginSampleActivity.this, 
								error.description, 
								Toast.LENGTH_SHORT).show();
						updateView();
					}
				}};
		}
		return mListener;
	}
}
