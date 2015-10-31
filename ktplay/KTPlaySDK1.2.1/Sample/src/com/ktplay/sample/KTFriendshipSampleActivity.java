package com.ktplay.sample;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ktplay.open.KTAccountManager;
import com.ktplay.open.KTAccountManager.KTLoginListener;
import com.ktplay.open.KTError;
import com.ktplay.open.KTFriendship;
import com.ktplay.open.KTFriendship.OnAddFriendsListener;
import com.ktplay.open.KTFriendship.OnGetFriendsListener;
import com.ktplay.open.KTPlay;
import com.ktplay.open.KTUser;

/**
 * 展示KTPlay好友相关功能
 */
public class KTFriendshipSampleActivity extends BaseListActivity{

	private final static int ITEM_LOGIN = 0;
	private final static int ITEM_SHOW_ADDFRIENDS_VIEW = 1;
	private final static int ITEM_ADDFRIENDS = 2;
	private final static int ITEM_GET_FRIENDS = 3;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public String[] items() {
		return new String[]{
				this.getString(R.string.login),
				this.getString(R.string.open_addfriends_view),
				this.getString(R.string.add_friends),
				this.getString(R.string.get_friends)
		};
	}
	
	protected void onListItemClick (ListView l, View v, int position, long id) {
		switch(position){
			case ITEM_LOGIN:{
				//显示登录窗口（使用好友功能前需要登录）
				KTAccountManager.showLoginView(true, null);
			}
				break;
				
			case ITEM_SHOW_ADDFRIENDS_VIEW:{
				//显示添加好友窗口
				KTFriendship.showFriendRequestsView();
			}
			break;
			
			case ITEM_ADDFRIENDS:{
				//登录后才能使用好友功能
				if(KTAccountManager.isLoggedIn()){
					showSendFriendReqeustDialog();
				}else{
					KTAccountManager.showLoginView(true, new KTLoginListener(){
						@Override
						public void onLoginResult(boolean isSuccess,
								KTUser user, KTError error) {
							if(isSuccess){
								showSendFriendReqeustDialog();
							}else{
								
							}
						}});
				}
			}
			break;
			
			case ITEM_GET_FRIENDS:{
				if(KTAccountManager.isLoggedIn()){
					getFriends();
				}else{
					KTAccountManager.showLoginView(true, new KTLoginListener(){
						@Override
						public void onLoginResult(boolean isSuccess,
								KTUser user, KTError error) {
							if(isSuccess){
								getFriends();
							}
						}});
				}
			}
			break;
			
		}
	}
	
	/**
	 * 获取好友列表
	 */
	private void getFriends(){
		KTFriendship.friendList(new OnGetFriendsListener(){
			@Override
			public void onGetFriendsResult(boolean isSuccess,
					ArrayList<KTUser> users, int userArrayCount, KTError error) {
				if(isSuccess){
					int size = users.size();
					if(size == 0){
						//没有好友
						Context context = KTFriendshipSampleActivity.this;
						Toast.makeText(context, context.getString(R.string.no_friends), 
								Toast.LENGTH_SHORT).show();
					}else{
						//有好友
						String[] a = new String[size];
						for(int i = 0;i < size;i++){
							a[i] = users.get(i).toString();
						}
						Intent intent = new Intent(KTFriendshipSampleActivity.this,
								KTItemsActivity.class);
						intent.putExtra("list", a);
						KTFriendshipSampleActivity.this.startActivity(intent);
					}
				}else{
					Context context = KTFriendshipSampleActivity.this;
					Toast.makeText(context, error.description, Toast.LENGTH_SHORT).show();
				}
			}});
	}
	
	
	/**
	 * 发送好友请求
	 */
	private void showSendFriendReqeustDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = this.getLayoutInflater().inflate(R.layout.friend_request, null);
		builder.setView(v);
		builder.setNegativeButton(R.string.cancel, null);
		builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String s = ((EditText)v.findViewById(R.id.friend_reqeust_ids)).getText().toString();
				if(TextUtils.isEmpty(s)){
					Context context = KTFriendshipSampleActivity.this;
					Toast.makeText(context, context.getString(R.string.please_input), 
							Toast.LENGTH_SHORT).show();
					return;
				}
				String[] a = s.split(","); //好友ID用逗号分隔
				ArrayList<String> list = new ArrayList<String>();
				for(int i = 0; i < a.length ;i ++){
					list.add(a[i]);
				}
				
				//发送好友请求
				KTFriendship.sendFriendRequests(a.length, list, new OnAddFriendsListener(){
					@Override
					public void onAddFriendResult(boolean isSuccess,
							int successUserIdsCount, KTError error) {
						if(isSuccess){
							Context context = KTFriendshipSampleActivity.this;
							Toast.makeText(context, context.getString(R.string.success), 
									Toast.LENGTH_SHORT).show();
						}else{
							Context context = KTFriendshipSampleActivity.this;
							Toast.makeText(context, error.description, 
									Toast.LENGTH_SHORT).show();
						}
					}});
			}
		});
		builder.show();
		
	}
	
	
	public void onPause(){
		KTPlay.onPause(this); //必须调用
		super.onPause();
	}
	
	public void onResume(){
		super.onResume();
		KTPlay.onResume(this);//必须调用
	}
}
