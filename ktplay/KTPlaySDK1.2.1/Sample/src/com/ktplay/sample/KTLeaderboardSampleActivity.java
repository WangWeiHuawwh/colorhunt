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
import com.ktplay.open.KTLeaderboard;
import com.ktplay.open.KTLeaderboard.OnGetFriendsLeaderboardListener;
import com.ktplay.open.KTLeaderboard.OnGetGameLeaderboardListener;
import com.ktplay.open.KTLeaderboard.OnReportScoreListener;
import com.ktplay.open.KTLeaderboardPaginator;
import com.ktplay.open.KTPlay;
import com.ktplay.open.KTUser;

public class KTLeaderboardSampleActivity extends BaseListActivity{

	private final static int ITEM_LOGIN = 0;
	private final static int ITEM_FRIEND_LEADERBOARD = 1;
	private final static int ITEM_GLOBAL_LEADERBOARD = 2;
	private final static int ITEM_UPLOAD_SCORE = 3;
	
	
	public void onCreate(Bundle savedInstanceState){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public String[] items() {
		return new String[]{
				this.getString(R.string.login),
				this.getString(R.string.friend_leaderboard),
				this.getString(R.string.global_leaderboard),
				this.getString(R.string.uoload_score)
		};
	}
	
	protected void onListItemClick (ListView l, View v, int position, long id) {
		switch(position){
			case ITEM_LOGIN:{
				//使用上传高分、好友排行功能时需要登录
				KTAccountManager.showLoginView(true, null);
			}
				break;
				
			case ITEM_GLOBAL_LEADERBOARD:{
				showGetGlobalLeaderboard();
			}
			break;
			
			case ITEM_FRIEND_LEADERBOARD:{
				if(KTAccountManager.isLoggedIn()){
					showGetFriendLeaderboard();
				}else{
					KTAccountManager.showLoginView(true, new KTLoginListener(){
						@Override
						public void onLoginResult(boolean isSuccess,
								KTUser user, KTError error) {
							if(isSuccess){
								showGetFriendLeaderboard();
							}else{
								
							}
						}});
				}
			}
			break;
			
			case ITEM_UPLOAD_SCORE:{
				if(KTAccountManager.isLoggedIn()){
					showUploadScoreDialog();
				}else{
					KTAccountManager.showLoginView(true, new KTLoginListener(){
						@Override
						public void onLoginResult(boolean isSuccess,
								KTUser user, KTError error) {
							if(isSuccess){
								showUploadScoreDialog();
							}
						}});
				}
			}
			break;
			
		}
	}
	
	/**
	 * 上传分数
	 */
	private void showUploadScoreDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = this.getLayoutInflater().inflate(R.layout.upload_score, null);
		builder.setView(v);
		builder.setNegativeButton(R.string.cancel, null);
		builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String score = ((EditText)v.findViewById(R.id.uploadscore_score)).getText().toString();
				String leaderboardId = ((EditText)v.findViewById(R.id.uploadscore_leaderboard_id)).getText().toString();
				if(TextUtils.isEmpty(score) || TextUtils.isEmpty(leaderboardId)){
					Context context = KTLeaderboardSampleActivity.this;
					Toast.makeText(context, context.getString(R.string.please_input), 
							Toast.LENGTH_SHORT).show();
					return;
				}
				long lscore = 0;
				try{
					lscore = Long.parseLong(score);
				}catch(Exception e){
					Context context = KTLeaderboardSampleActivity.this;
					Toast.makeText(context, context.getString(R.string.invalid_number_format), 
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				KTLeaderboard.reportScore(lscore, leaderboardId, 
						new OnReportScoreListener(){
							@Override
							public void onReportScoreResult(boolean isSuccess,
									String leaderboardId, long score,
									KTError error) {
								if(isSuccess){
									Context context = KTLeaderboardSampleActivity.this;
									Toast.makeText(context,context.getString(R.string.success) + " leaderboardId= " + leaderboardId + " score=" + score, 
											Toast.LENGTH_SHORT).show();
								}else{
									Context context = KTLeaderboardSampleActivity.this;
									Toast.makeText(context, "failed: leaderboardId=" + leaderboardId + " score=" + score + " errorCode="+ error.code + " errorMessage=" + error.description, 
											Toast.LENGTH_SHORT).show();
								}
							}});
			}
		});
		builder.show();
	}
	
	/**
	 * 获取好友排行榜
	 */
	private void showGetFriendLeaderboard(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = this.getLayoutInflater().inflate(R.layout.leaderboard, null);
		builder.setView(v);
		builder.setNegativeButton(R.string.cancel, null);
		builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String leaderboardId = ((EditText)v.findViewById(R.id.leaderboard_id)).getText().toString();
				String startIndex = ((EditText)v.findViewById(R.id.leaderboard_startindex)).getText().toString();
				String count = ((EditText)v.findViewById(R.id.leaderboard_count)).getText().toString();
				if(TextUtils.isEmpty(leaderboardId) || 
						TextUtils.isEmpty(startIndex) ||
						TextUtils.isEmpty(count)
						){
					Context context = KTLeaderboardSampleActivity.this;
					Toast.makeText(context, context.getString(R.string.please_input), 
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				KTLeaderboard.friendsLeaderboard(leaderboardId, 
						Integer.parseInt(startIndex), 
						Integer.parseInt(count), new OnGetFriendsLeaderboardListener(){
							@Override
							public void onGetFriendsLeaderboardResult(
									boolean isSuccess,
									String leaderboardId,
									KTLeaderboardPaginator leaderboard,
									KTError error) {
								if(isSuccess){
									Context context = KTLeaderboardSampleActivity.this;
									Toast.makeText(context, "success: leaderboardId= " + leaderboardId, 
											Toast.LENGTH_SHORT).show();
									ArrayList<KTUser> users = leaderboard.getUsers();
									int size = users.size();
									if(size == 0){
										Toast.makeText(context, context.getString(R.string.no_leaderboard), 
												Toast.LENGTH_SHORT).show();
									}else{
										size++;
										String[] a = new String[size];
										a[0] = leaderboardInfo(leaderboard);
										for(int i = 1;i < size;i++){
											a[i] = users.get(i-1).toString();
										}
										Intent intent = new Intent(KTLeaderboardSampleActivity.this,
												KTItemsActivity.class);
										intent.putExtra("list", a);
										KTLeaderboardSampleActivity.this.startActivity(intent);
									}
								
								}else{
									Context context = KTLeaderboardSampleActivity.this;
									Toast.makeText(context, "failed: leaderboardId= " + leaderboardId + " errorCode=" + error.code +  " errorMessage=" + error.description, 
											Toast.LENGTH_SHORT).show();
								}
							}});
			}
		});
		builder.show();
	}
	
	/**
	 * 获取全局排行榜(所有游戏玩家)
	 */
	private void showGetGlobalLeaderboard(){

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = this.getLayoutInflater().inflate(R.layout.leaderboard, null);
		builder.setView(v);
		builder.setNegativeButton(R.string.cancel, null);
		builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String leaderboardId = ((EditText)v.findViewById(R.id.leaderboard_id)).getText().toString();
				String startIndex = ((EditText)v.findViewById(R.id.leaderboard_startindex)).getText().toString();
				String count = ((EditText)v.findViewById(R.id.leaderboard_count)).getText().toString();
				if(TextUtils.isEmpty(leaderboardId) || 
						TextUtils.isEmpty(startIndex) ||
						TextUtils.isEmpty(count)
						){
					Context context = KTLeaderboardSampleActivity.this;
					Toast.makeText(context, context.getString(R.string.please_input), 
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				//获取全局排行榜数据
				KTLeaderboard.gameLeaderboard(leaderboardId, 
						Integer.parseInt(startIndex), 
						Integer.parseInt(count), new OnGetGameLeaderboardListener(){
							@Override
							public void onGetGameLeaderboardResult(
									boolean isSuccess,
									String leaderboardId,
									KTLeaderboardPaginator leaderboard,
									KTError error) {
								if(isSuccess){
									Context context = KTLeaderboardSampleActivity.this;
									Toast.makeText(context, "success: leaderboardId= " + leaderboardId, 
											Toast.LENGTH_SHORT).show();
									ArrayList<KTUser> users = leaderboard.getUsers();
									int size = users.size();
									if(size == 0){
										Toast.makeText(context, context.getString(R.string.no_leaderboard), 
												Toast.LENGTH_SHORT).show();
									}else{
										size++;
										String[] a = new String[size];
										a[0] = leaderboardInfo(leaderboard);
										for(int i = 1;i < size;i++){
											a[i] = users.get(i-1).toString();
										}
										Intent intent = new Intent(KTLeaderboardSampleActivity.this,
												KTItemsActivity.class);
										intent.putExtra("list", a);
										KTLeaderboardSampleActivity.this.startActivity(intent);
								}
									}else{
									Context context = KTLeaderboardSampleActivity.this;
									Toast.makeText(context, "failed: leaderboardId= " + leaderboardId + " errorCode=" + error.code + " errorMessage=" + error.description, 
											Toast.LENGTH_SHORT).show();
								}
							}});
			}
		});
		builder.show();
	}
	
	/**
	 * 转换排行榜数据为普通文本
	 * @param paginator 排行榜数据
	 * @return 排行榜描述
	 */
	private String leaderboardInfo(KTLeaderboardPaginator paginator){
		StringBuffer buf = new StringBuffer();
		buf.append("leaderboardId:");
		buf.append(paginator.getLeaderboardId());
		buf.append('\n');
		
		buf.append("leaderboardName:");
		buf.append(paginator.getLeaderboardName());
		buf.append('\n');
		
		buf.append("leaderboardIcon:");
		buf.append(paginator.getLeaderboardIcon());
		buf.append('\n');
		
		buf.append("itemCount:");
		buf.append(paginator.getItemCount());
		buf.append('\n');
		return buf.toString();
	}
	
	public void onPause(){
		KTPlay.onPause(this); //必须调用
		super.onPause();
	}
	
	public void onResume(){
		super.onResume(); //必须调用
		KTPlay.onResume(this);
	}
}
