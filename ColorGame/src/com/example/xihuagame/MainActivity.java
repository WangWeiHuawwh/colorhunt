package com.example.xihuagame;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.CustomDialog;
import model.ExitApplication;
import model.MyData;
import model.Obb_Role;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveXModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.modifier.ParallelEntityModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseBounceOut;

import com.bodong.dianjinweb.DianJinPlatform;
import com.bodong.dianjinweb.listener.AppActiveListener;
import com.bodong.dianjinweb.listener.ConsumeListener;
import com.ktplay.open.KTPlay;
import com.ktplay.open.KTPlay.OnAppearListener;
import com.ktplay.open.KTPlay.OnDisappearListener;
import com.ktplay.open.KTPlay.OnDispatchRewardsListener;
import com.ktplay.open.KTRewardItem;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends BaseGameActivity {
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	private Camera mCamera;
	private Handler mHandler;
	private SharedPreferences audioOptions;
	private SharedPreferences.Editor audioEditor;
	protected Scene mMenuScene;
	protected Scene ShezhiScene;
	protected Scene guanyuScene;
	private Sprite backimg1;
	/*
	private Text text1;
	private Text text2;
	private ChangeableText text3;
	private Text text4;
	private Text text5;
	private Text text6;
	private Text Besttext;
	private Text lefttext;
	private Text righttext;
	*/
	private Text free;
	private Text change;
	private ChangeableText jifendouText;
	protected Scene mStaticMenuScene;
	private Font mmFont;
	protected Texture mmFontTexture;
	private Font mfont;
	protected Texture mFontTexture;
	private Font mredfont;
	protected Texture mredFontTexture;
	private Font mbigfont;
	protected Texture mbigFontTexture;
	protected Texture pushTexture;
	protected TextureRegion push1TextureRegion;
	protected TextureRegion push2TextureRegion;
	protected TextureRegion push3TextureRegion;
	protected TextureRegion push4TextureRegion;
	protected Texture push5Texture;
	protected TextureRegion push5TextureRegion;
	protected Texture newTexture;
	protected TextureRegion newTextureRegion;
	protected Texture moneyTexture;
	protected TextureRegion moneyTextureRegion;

	protected Texture zuoTexture;
	protected TextureRegion zuoTextureRegion;
	protected Texture youTexture;
	protected TextureRegion youTextureRegion;
	
	protected Texture loadTexture;
	protected TextureRegion loadTextureRegion;
	
	protected Texture main;
	protected Texture main2;
	protected TextureRegion miao15TextureRegion;
	protected TextureRegion jinjieTextureRegion;
	protected TextureRegion zhuangqiangTextureRegion;
	protected TextureRegion biantaiTextureRegion;
	protected TextureRegion jingzhunTextureRegion;
	protected TextureRegion shangdianTextureRegion;
	protected TextureRegion shequTextureRegion;
	protected TextureRegion shezhiTextureRegion;
	protected TextureRegion tuichuTextureRegion;
	private Texture nanduTexture;
	private TextureRegion nandu2TextureRegion;
	private TextureRegion nandu4TextureRegion;
	
	protected Texture shezhimain;
	protected TextureRegion guanyuTextureRegion;
	protected TextureRegion fankuiTextureRegion;
	protected TextureRegion fanhuiTextureRegion;
	protected TextureRegion yinxiaokaiTextureRegion;
	protected TextureRegion yinxiaoguanTextureRegion;
	
	protected Texture backimageTexture;
	protected TextureRegion backimageTextureRegion;
	Sprite backSprite;
	
	protected Texture gaunyubackTexture;
	protected TextureRegion guanyubackTextureRegion;
	protected Texture xiaobackTexture;
	protected TextureRegion xiaobackTextureRegion;
	
	
	private ChangeableText base15;
	private ChangeableText jinjie;
	private ChangeableText overtime;
	private ChangeableText isadv;
	// private ChangeableText perfect;
	private Scene splashScene;
	private Sound pushSound;
	private Sound konckSound;
	// 返回主界面按钮
	private Sprite backturn;
	private final static String ALBUM_PATH = Environment
			.getExternalStorageDirectory() + "/colorhunt_img/";
	protected Scene mOptionsMenuScene;
	ChangeableText nandutext;

	Sprite nandu_s;
	private Texture MengwuTexture;
	AnimatedSprite chongwu1;
	AnimatedSprite chongwu2;

	Sprite zuo;
	Sprite you;
	Sprite yinxiao;
	//Sprite jijiangkaifang;
	
	private TiledTextureRegion MengwuTextureRegion;
	AnimatedSprite Mengwu;
	// 当前显示宠物
	int dangqian = 1;
	String mykey = "no"; // key
	String mykey2 = "yes"; // key
	String mykey4 = "pause"; // key
	String mykey3 = "time"; // key
	String jiangetime = "200";

	@Override
	public Engine onLoadEngine() {
		mHandler = new Handler();
		ExitApplication.getInstance().addActivity(this);
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		audioOptions = getSharedPreferences("audio", MODE_PRIVATE);
		audioEditor = audioOptions.edit();
		// 是否有音效
		if (!audioOptions.contains("effectsOn")) {
			audioEditor.putBoolean("effectsOn", true);
			audioEditor.commit();
		}
		// 15秒模式最高分
		if (!audioOptions.contains("Maxtimescore")) {
			audioEditor.putInt("Maxtimescore", 0);
			audioEditor.commit();
		}
		// 进阶模式最高分
		
		if (!audioOptions.contains("Maxscore")) {
			audioEditor.putInt("Maxscore", 0);
			audioEditor.commit();
		}
		// 撞墙模式最高分
		if (!audioOptions.contains("zhuangqiangscore")) {
			audioEditor.putInt("zhuangqiangscore", 0);
			audioEditor.commit();
		}
		// 变态模式最高分
		if (!audioOptions.contains("btscore")) {
			audioEditor.putInt("btscore", 0);
			audioEditor.commit();
		}
		// 精准模式最高分
		if (!audioOptions.contains("bestscore")) {
			audioEditor.putInt("bestscore", 0);
			audioEditor.commit();
		}
		// 15秒模式初始
		if (!audioOptions.contains("base15")) {
			audioEditor.putInt("base15", 15);
			audioEditor.commit();
		}
		// 进阶模式初始
		if (!audioOptions.contains("jinjie")) {
			audioEditor.putInt("jinjie", 2);
			audioEditor.commit();
		}
		// 错误次数
		if (!audioOptions.contains("overtime")) {
			audioEditor.putInt("overtime", 1);
			audioEditor.commit();
		}
		// 玩家金币
		if (!audioOptions.contains("jifen")) {
			audioEditor.putInt("jifen", 0);
			audioEditor.commit();
		}
		// 是否去除广告1有广告0无广告
		if (!audioOptions.contains("isadv")) {
			audioEditor.putInt("isadv", 1);
			audioEditor.commit();
		}
		// 几键
		if (!audioOptions.contains("nandu")) {
			audioEditor.putInt("nandu", 0);
			audioEditor.commit();
		}
		// 选择宠物几号
		if (!audioOptions.contains("choice")) {
			audioEditor.putInt("choice", 0);
			audioEditor.commit();
		}
		// 是否获得宠物,1为未获得2为获得
		if (!audioOptions.contains("quanxian")) {
			audioEditor.putInt("quanxian", 111);
			audioEditor.commit();
		}
		try {
			// ktplay
			KTPlay.startWithAppKey(this, "mX2Xgz",
					"2659c7df5594d18723410db17a7eb16868c9a01d");
			KTPlay.setOnAppearListener(new OnAppearListener() {
				@Override
				public void onAppear() {
					mEngine.stop();
				}
			});
			KTPlay.setOnDisappearListener(new OnDisappearListener() {
				@Override
				public void onDisappear() {
					mEngine.start();
				}
			});
			KTPlay.update();
			KTPlay.setOnDispatchRewardsListener(new OnDispatchRewardsListener() {
				public void onDispatchRewards(ArrayList<KTRewardItem> rewards) {
					for (KTRewardItem reward : rewards) {
						long jiangli = reward.getValue();
						audioEditor.putInt("jifen",
								audioOptions.getInt("jifen", 0) + (int) jiangli);
						audioEditor.commit();
						// 游戏接收奖励
					}
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 友盟
		MobclickAgent.updateOnlineConfig(this);
		// 有米
		AdManager.getInstance(MainActivity.this).init("7cf3e1038bcf8cf2",
				"298754d19f6e30cf", false);
		// 载入广告
		if (audioOptions.getInt("isadv", 1) != 0) {
			SpotManager.getInstance(MainActivity.this).loadSpotAds();
			SpotManager.getInstance(MainActivity.this).setSpotTimeout(5000);

		}
		SpotManager.getInstance(MainActivity.this).setSpotOrientation(
				SpotManager.ORIENTATION_LANDSCAPE);
		// 91点金
		DianJinPlatform.initialize(this, 52432,
				"6c8219b974134daac3d10b927ed1929e", 1001);
		DianJinPlatform.setAppActivedListener(new AppActiveListener() {
			@Override
			public void onSuccess(long reward) {
				Toast.makeText(getApplicationContext(), "奖励金额:" + reward,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(int errorCode, String errorMessage) {
				switch (errorCode) {
				case DianJinPlatform.DIANJIN_NET_ERROR: // 网络不稳定
					Toast.makeText(MainActivity.this, errorMessage,
							Toast.LENGTH_SHORT).show();
					break;
				case DianJinPlatform.DIANJIN_DUPLICATE_ACTIVATION: // 重复激活
					Toast.makeText(MainActivity.this, errorMessage,
							Toast.LENGTH_SHORT).show();
					break;
				case DianJinPlatform.DIANJIN_ADVERTSING_EXPIRED: // 应用已下架
					Toast.makeText(MainActivity.this, errorMessage,
							Toast.LENGTH_SHORT).show();
					break;
				case DianJinPlatform.DIANJIN_ACTIVATION_FAILURE: // 激活失败
					Toast.makeText(MainActivity.this, errorMessage,
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});
		// 隐藏悬浮
		DianJinPlatform.hideFloatView(MainActivity.this);
		/*
		 * BitmapDrawable bitmapDrawable =
		 * (BitmapDrawable)getResources().getDrawable(R.drawable.share); try {
		 * saveFile(bitmapDrawable.getBitmap(), "share"); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// 获取在线参数
		if (audioOptions.getInt("isadv", 1) != 0) {
			AdManager.getInstance(this).asyncGetOnlineConfig(mykey,
					new net.youmi.android.onlineconfig.OnlineConfigCallBack() {
						public void onGetOnlineConfigSuccessful(String key,
								String value) {
							// TODO Auto-generated method stub
							// 获取在线参数成功
							MyData.value = AdManager.getInstance(
									MainActivity.this).syncGetOnlineConfig(key,
									"1");
						}

						public void onGetOnlineConfigFailed(String key) {
							// TODO Auto-generated method stub
							// 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
						}
					});
			AdManager.getInstance(this).asyncGetOnlineConfig(mykey2,
					new net.youmi.android.onlineconfig.OnlineConfigCallBack() {
						@Override
						public void onGetOnlineConfigSuccessful(String key,
								String value) {
							// TODO Auto-generated method stub
							// 获取在线参数成功
							MyData.value2 = AdManager.getInstance(
									MainActivity.this).syncGetOnlineConfig(key,
									"0");
						}

						@Override
						public void onGetOnlineConfigFailed(String key) {
							// TODO Auto-generated method stub
							// 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
						}
					});
			AdManager.getInstance(this).asyncGetOnlineConfig(mykey3,
					new net.youmi.android.onlineconfig.OnlineConfigCallBack() {
						@Override
						public void onGetOnlineConfigSuccessful(String key,
								String value) {
							// TODO Auto-generated method stub
							// 获取在线参数成功
							jiangetime = AdManager.getInstance(
									MainActivity.this).syncGetOnlineConfig(key,
									"200");
						}

						@Override
						public void onGetOnlineConfigFailed(String key) {
							// TODO Auto-generated method stub
							// 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
						}
					});
			AdManager.getInstance(this).asyncGetOnlineConfig(mykey4,
					new net.youmi.android.onlineconfig.OnlineConfigCallBack() {
						@Override
						public void onGetOnlineConfigSuccessful(String key,
								String value) {
							// TODO Auto-generated method stub
							// 获取在线参数成功
							// 获取在线参数成功
							MyData.value3 = AdManager.getInstance(
									MainActivity.this).syncGetOnlineConfig(key,
									"0");
						}

						@Override
						public void onGetOnlineConfigFailed(String key) {
							// TODO Auto-generated method stub
							// 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
						}
					});

		}
		// TODO Auto-generated method stub
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new FillResolutionPolicy(), this.mCamera).setNeedsSound(true));
	}

	public void saveFile(Bitmap bm, String fileName) throws IOException {
		File dirFile = new File(ALBUM_PATH);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		File myCaptureFile = new File(ALBUM_PATH + fileName);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.PNG, 80, bos);
		bos.flush();
		bos.close();
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		this.mmFontTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mmFont = FontFactory.createFromAsset(mmFontTexture, this,
				"fonts/pod.ttf", 40, true, Color.rgb(254, 67, 101));
		this.mEngine.getTextureManager().loadTexture(mmFontTexture);
		this.mEngine.getFontManager().loadFont(mmFont);
		
		this.loadTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.loadTextureRegion = TextureRegionFactory.createFromAsset(
				loadTexture, this, "main/loadmain.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(loadTexture);
	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		this.mMenuScene = new Scene(1);
		initSplashScene();
		mEngine.registerUpdateHandler(new TimerHandler(1f,
				new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						// TODO Auto-generated method stub
						loadResources();
						loadScenes();
						MainActivity.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								splashScene.detachSelf();
							}
						});
						mMenuScene.setChildScene(mStaticMenuScene);
						mEngine.setScene(mMenuScene);
					}
				}));
		return mMenuScene;
	}

	protected void loadScenes() {
		// TODO Auto-generated method stub
		this.mEngine.registerUpdateHandler(new FPSLogger());
		// this.createStaticMenuScene();
		mStaticMenuScene = new Scene(2);
		Obb_Role obb_role = new Obb_Role();
		obb_role.refreshnumber();
		//backimg1 = new Rectangle(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		//backimg1.setColor(1f, 1f, 1f);
		backimg1=new Sprite(0, 0,CAMERA_WIDTH, CAMERA_HEIGHT, backimageTextureRegion);
		mStaticMenuScene.getLastChild().attachChild(backimg1);
		// 主界面背景颜色
		// mStaticMenuScene.getLastChild().attachChild(backimg2);
		Obb_Role ob = new Obb_Role();
		/*
		Line line1 = new Line(0, CAMERA_HEIGHT / 3, CAMERA_WIDTH,
				CAMERA_HEIGHT / 3);
		Line line2 = new Line(0, CAMERA_HEIGHT * 2 / 3, CAMERA_WIDTH,
				CAMERA_HEIGHT * 2 / 3);
		Line line3 = new Line(CAMERA_WIDTH / 4, 0, CAMERA_WIDTH / 4,
				CAMERA_HEIGHT);
		// Line line4=new Line(CAMERA_WIDTH*2/4, 0, CAMERA_WIDTH*2/4,
		// CAMERA_HEIGHT);
		Line line5 = new Line(CAMERA_WIDTH * 3 / 4, 0, CAMERA_WIDTH * 3 / 4,
				CAMERA_HEIGHT);
		line1.setLineWidth(2);
		line2.setLineWidth(2);
		line3.setLineWidth(2);
		// line4.setLineWidth(2);
		line5.setLineWidth(2);
		 */
		Sprite miao15_s=new Sprite(16, 15,180,140, miao15TextureRegion){
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(pushSound);
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									// mMenuScene.registerEntityModifier(new
									// ScaleModifier(0.3f, 1.0f, 0.0f));
									mHandler.postDelayed(mTimeGameLaunch, 100);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.2f, 1.0f, 0.0f), new AlphaModifier(0.2f,
									0.0f, 1.0f))));
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite jinjie_s=new Sprite(212, 15,180,140, jinjieTextureRegion) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(pushSound);
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									// mMenuScene.registerEntityModifier(new
									// ScaleModifier(0.3f, 1.0f, 0.0f));
									mHandler.postDelayed(mzhuangqingGameLaunch,
											100);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.2f, 1.0f, 0.0f), new AlphaModifier(0.2f,
									0.0f, 1.0f))));
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite zhuangqiang_s=new Sprite(408, 15,180,140, zhuangqiangTextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(pushSound);
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									// mMenuScene.registerEntityModifier(new
									// ScaleModifier(0.3f, 1.0f, 0.0f));
									mHandler.postDelayed(mMainGameLaunch, 100);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.2f, 1.0f, 0.0f), new AlphaModifier(0.2f,
									0.0f, 1.0f))));

					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite biantai_s=new Sprite(604, 15, 180,140,biantaiTextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(pushSound);
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									// mMenuScene.registerEntityModifier(new
									// ScaleModifier(0.3f, 1.0f, 0.0f));
									mHandler.postDelayed(mBTGameLaunch, 300);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.2f, 1.0f, 0.0f), new AlphaModifier(0.2f,
									0.0f, 1.0f))));
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite jingzhun_s=new Sprite(16, 170,180,140, jingzhunTextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									mSoundplay(pushSound);
									// mMenuScene.registerEntityModifier(new
									// ScaleModifier(0.3f, 1.0f, 0.0f));
									mHandler.postDelayed(mBestGameLaunch, 100);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.2f, 1.0f, 0.0f), new AlphaModifier(0.2f,
									0.0f, 1.0f))));

					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite shangdian_s=new Sprite(212, 170,180,140, shangdianTextureRegion){

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									mMenuScene
											.registerEntityModifier(new ScaleModifier(
													0.3f, 1.0f, 0.0f));
									creatOptionsMenuScene();
									mMenuScene.clearChildScene();
									mMenuScene.setChildScene(mOptionsMenuScene);
									mEngine.setScene(mMenuScene);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f), new AlphaModifier(0.1f,
									0.0f, 1.0f))));
					mSoundplay(konckSound);
					/*
					 * SpotManager.getInstance(MainActivity.this).showSpotAds(
					 * MainActivity.this, new SpotDialogListener() { public void
					 * onShowSuccess() { } public void onShowFailed() { } });
					 */
					/*
					 * SpotManager.getInstance(MainActivity.this)
					 * .setAutoCloseSpot(true);// 设置自动关闭插屏开关
					 * SpotManager.getInstance(MainActivity.this)
					 * .setCloseTime(5000); // 设置关闭插屏时间
					 */
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite shequ_s=new Sprite(408, 170,367,140, shequTextureRegion){
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub

								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f), new AlphaModifier(0.1f,
									0.0f, 1.0f))));
					ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					// TelephonyManager mTelephony=
					// (TelephonyManager)this.getSystemService(TELEPHONY_SERVICE);
					// 检查网络连接，如果无网络可用，就不需要进行连网操作等
					NetworkInfo info = mConnectivity.getActiveNetworkInfo();
					if (info == null
							|| !mConnectivity.getBackgroundDataSetting()) {
						Toast.makeText(MainActivity.this, "请检查网络连接",
								Toast.LENGTH_SHORT).show();
					} else {
						try {
							// 社区
							KTPlay.show();
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
					mSoundplay(konckSound);
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite shezhi_s=new Sprite(408, 325,180,140, shezhiTextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(konckSound);
					/*
					if (audioOptions.getBoolean("effectsOn", true)) {
						audioEditor.putBoolean("effectsOn", false);
						//text3.setText("音效:关");
					} else {
						audioEditor.putBoolean("effectsOn", true);
						//text3.setText("音效:开");
					}
					audioEditor.commit();
					*/
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									mMenuScene.registerEntityModifier(new ScaleModifier(
													0.3f, 1.0f, 0.0f));
									creatShezhiScene();
									mMenuScene.clearChildScene();
									mMenuScene.setChildScene(ShezhiScene);
									mEngine.setScene(mMenuScene);
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f), new AlphaModifier(0.1f,
									0.0f, 1.0f))));

					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite tuichu_s=new Sprite(604, 325,180,140, tuichuTextureRegion) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub

								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f), new AlphaModifier(0.1f,
									0.0f, 1.0f))));
					
					mSoundplay(konckSound);
					showCheckDialog("你要离开我吗？");
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		nandu_s=new Sprite(16, 325,367,140,nandu2TextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									if (audioOptions.getInt("nandu", 0) == 1) {
										audioEditor.putInt("nandu", 0);
										//nandutext.setText("难度:二键");
										nandu2TextureRegion.setTexturePosition(10, 10);
										
									} else {
										audioEditor.putInt("nandu", 1);
										//nandutext.setText("难度:四键");
										nandu2TextureRegion.setTexturePosition(10, 300);
									}
									audioEditor.commit();
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f), new AlphaModifier(0.1f,
									0.0f, 1.0f))));
					mSoundplay(konckSound);
					break;

				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		if (audioOptions.getInt("nandu", 0) == 1) {
			nandu2TextureRegion.setTexturePosition(10, 300);
		}
		mStaticMenuScene.registerTouchArea(miao15_s);
		mStaticMenuScene.registerTouchArea(jinjie_s);
		mStaticMenuScene.registerTouchArea(zhuangqiang_s);
		mStaticMenuScene.registerTouchArea(jingzhun_s);
		mStaticMenuScene.registerTouchArea(shangdian_s);
		//mStaticMenuScene.registerTouchArea(pushfankui);
		mStaticMenuScene.registerTouchArea(shequ_s);
		mStaticMenuScene.registerTouchArea(biantai_s);
		mStaticMenuScene.registerTouchArea(shezhi_s);
		mStaticMenuScene.registerTouchArea(tuichu_s);
		mStaticMenuScene.registerTouchArea(nandu_s);

		mStaticMenuScene.getLastChild().attachChild(miao15_s);
		mStaticMenuScene.getLastChild().attachChild(jinjie_s);
		mStaticMenuScene.getLastChild().attachChild(zhuangqiang_s);
		mStaticMenuScene.getLastChild().attachChild(jingzhun_s);
		mStaticMenuScene.getLastChild().attachChild(shangdian_s);
		mStaticMenuScene.getLastChild().attachChild(shequ_s);
		mStaticMenuScene.getLastChild().attachChild(biantai_s);
		mStaticMenuScene.getLastChild().attachChild(shezhi_s);
		mStaticMenuScene.getLastChild().attachChild(tuichu_s);
		mStaticMenuScene.getLastChild().attachChild(nandu_s);
		/*
		 * backimg3=new Rectangle(0, CAMERA_HEIGHT/2, CAMERA_WIDTH/2,
		 * CAMERA_HEIGHT/2); backimg3.setColor(0f,137/255f, 85/255f);
		 * mStaticMenuScene.getLastChild().attachChild(backimg3); backimg4=new
		 * Rectangle(CAMERA_WIDTH/2, CAMERA_HEIGHT/2, CAMERA_WIDTH/2,
		 * CAMERA_HEIGHT/2); backimg4.setColor(234/255f, 234/255f, 234/255f);
		 * mStaticMenuScene.getLastChild().attachChild(backimg4);
		 */
		/*
		text1 = new Text(0, 0, mfont, "15秒模式");
		text1.setPosition((CAMERA_WIDTH / 4 - text1.getWidth()) / 2,
				(CAMERA_HEIGHT / 3 - text1.getHeight()) / 2);
		text2 = new Text(0, 0, mfont, "进阶模式");
		text2.setPosition(
				CAMERA_WIDTH / 4 + (CAMERA_WIDTH / 4 - text2.getWidth()) / 2,
				(CAMERA_HEIGHT / 3 - text2.getHeight()) / 2);
		text3 = new ChangeableText(0, 0, mfont, "音效:关", 4);
		if (audioOptions.getBoolean("effectsOn", false)) {
			text3.setText("音效:开");
		}
		text3.setPosition((CAMERA_WIDTH / 4 - text3.getWidth()) / 2,
				CAMERA_HEIGHT * 2 / 3 + (CAMERA_HEIGHT / 3 - text3.getHeight())
						/ 2);
		text4 = new Text(0, 0, mfont, "退出");
		text4.setPosition(
				CAMERA_WIDTH * 3 / 4 + (CAMERA_WIDTH / 4 - text4.getWidth())
						/ 2,
				CAMERA_HEIGHT * 2 / 3 + (CAMERA_HEIGHT / 3 - text4.getHeight())
						/ 2);

		text5 = new Text(0, 0, mfont, "撞墙模式");
		text5.setPosition(
				CAMERA_WIDTH * 2 / 4 + (CAMERA_WIDTH / 4 - text5.getWidth())
						/ 2, (CAMERA_HEIGHT / 3 - text5.getHeight()) / 2);

		text6 = new Text(0, 0, mfont, "升级");
		text6.setPosition(
				CAMERA_WIDTH / 4 + (CAMERA_WIDTH / 4 - text6.getWidth()) / 2,
				CAMERA_HEIGHT * 2 / 3 + (CAMERA_HEIGHT / 3 - text6.getHeight())
						/ 2);

		Besttext = new Text(0, 0, mfont, "精准模式");
		Besttext.setPosition((CAMERA_WIDTH / 4 - Besttext.getWidth()) / 2,
				CAMERA_HEIGHT / 3 + (CAMERA_HEIGHT / 3 - Besttext.getHeight())
						/ 2);

		lefttext = new Text(0, 0, mfont, "与我对话");
		lefttext.setPosition(CAMERA_WIDTH * 2 / 4
				+ (CAMERA_WIDTH / 4 - lefttext.getWidth()) / 2, CAMERA_HEIGHT
				* 2 / 3 + (CAMERA_HEIGHT / 3 - lefttext.getHeight()) / 2);

		righttext = new Text(0, 0, mfont, "变态模式");
		righttext.setPosition(CAMERA_WIDTH * 3 / 4
				+ (CAMERA_WIDTH / 4 - righttext.getWidth()) / 2,
				(CAMERA_HEIGHT / 3 - righttext.getHeight()) / 2);

		Text shequtext = new Text(0, 0, mfont, "社区");
		shequtext.setPosition(CAMERA_WIDTH * 3 / 4
				+ (CAMERA_WIDTH / 4 - shequtext.getWidth()) / 2, CAMERA_HEIGHT
				/ 3 + (CAMERA_HEIGHT / 3 - shequtext.getHeight()) / 2);

		nandutext = new ChangeableText(0, 0, mfont, "难度:二键", 5);
		if (audioOptions.getInt("nandu", 0) == 1) {
			nandutext.setText("难度:四键");
		}
		nandutext.setPosition((CAMERA_WIDTH - nandutext.getWidth()) / 2,
				CAMERA_HEIGHT / 3 + (CAMERA_HEIGHT / 3 - shequtext.getHeight())
						/ 2);
		*/
		/*
		 * biaoyu=new Text(0, 0, mfont, "捕获颜色");
		 * biaoyu.setPosition((CAMERA_WIDTH
		 * -biaoyu.getWidth())/2,(CAMERA_HEIGHT-biaoyu.getHeight())/2);
		 * biaoyu.setAlpha(0f); biaoyu.registerEntityModifier(new
		 * SequenceEntityModifier( new IEntityModifierListener() {
		 * 
		 * @Override public void onModifierFinished(IModifier<IEntity>
		 * pModifier, IEntity pItem) { // TODO Auto-generated method stub
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * biaoyu.setVisible(false); } }); } },new DelayModifier(0.5f), new
		 * ParallelEntityModifier(new ScaleModifier(1f, 0, 1.2f),new
		 * AlphaModifier(3f, 0f, 1f)) ));
		 */
		/*
		mStaticMenuScene.getLastChild().attachChild(text1);
		mStaticMenuScene.getLastChild().attachChild(text2);
		mStaticMenuScene.getLastChild().attachChild(text3);
		mStaticMenuScene.getLastChild().attachChild(text4);
		mStaticMenuScene.getLastChild().attachChild(text5);
		mStaticMenuScene.getLastChild().attachChild(text6);
		mStaticMenuScene.getLastChild().attachChild(Besttext);
		mStaticMenuScene.getLastChild().attachChild(lefttext);
		mStaticMenuScene.getLastChild().attachChild(righttext);
		mStaticMenuScene.getLastChild().attachChild(shequtext);
		mStaticMenuScene.getLastChild().attachChild(nandutext);
		*/
		// mStaticMenuScene.getLastChild().attachChild(perfect);
		// perfect.setVisible(false);
		// perfect.setRotation(30);
	}
	private void creatShezhiScene(){
		ShezhiScene=new Scene(2);
		Sprite backimg4 = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,backimageTextureRegion);
		//backimg4.setColor(1f, 1f, 1f);
		Sprite guanyu=new Sprite(16, 95, 180, 140,guanyuTextureRegion){
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									ShezhiScene.registerEntityModifier(new ScaleModifier(
													0.3f, 1.0f, 0.0f));
									creatguanyuScene();
									mMenuScene.clearChildScene();
									mMenuScene.setChildScene(guanyuScene);
									MainActivity.this.runOnUpdateThread(new Runnable() {
										@Override
										public void run() {
											ShezhiScene.detachSelf();
										}
									});
									mEngine.setScene(mMenuScene);
								}
								private void creatguanyuScene() {
									// TODO Auto-generated method stub
									guanyuScene=new Scene(3);
									Sprite guanyuimg = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,guanyubackTextureRegion);
									guanyuimg.setColor(1f, 1f, 1f);
									Sprite exit=new Sprite(600,300 , 200, 200,xiaobackTextureRegion){
										@SuppressLint("NewApi")
										public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
												float pTouchAreaLocalX, float pTouchAreaLocalY) {
											// TODO Auto-generated method stub
											switch (pSceneTouchEvent.getAction()) {
											case TouchEvent.ACTION_DOWN:
												mSoundplay(konckSound);
												creatShezhiScene();
												mMenuScene.clearChildScene();
												mMenuScene.setChildScene(ShezhiScene);
												MainActivity.this.runOnUpdateThread(new Runnable() {
													@Override
													public void run() {
														guanyuScene.detachSelf();
													}
												});
												mEngine.setScene(mMenuScene);
												break;
											case TouchEvent.ACTION_UP:
												break;
											}
											return true;
										}
									};
									guanyuScene.registerTouchArea(exit);
									guanyuScene.getLastChild().attachChild(guanyuimg);
									guanyuScene.getLastChild().attachChild(exit);
									
									
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f), new AlphaModifier(0.1f,
									0.0f, 1.0f))));
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		yinxiao=new Sprite(212, 245, 180, 140,yinxiaokaiTextureRegion){
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new ParallelEntityModifier(new AlphaModifier(0.1f,
									1.0f, 0.0f), new AlphaModifier(0.1f, 0.0f,
									1.0f))));
					mSoundplay(konckSound);
					if (audioOptions.getBoolean("effectsOn", true)) {
						audioEditor.putBoolean("effectsOn", false);
						yinxiaokaiTextureRegion.setTexturePosition(16, 560);
					} else {
						audioEditor.putBoolean("effectsOn", true);
						yinxiaokaiTextureRegion.setTexturePosition(424, 280);
					}
					audioEditor.commit();
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		if (audioOptions.getBoolean("effectsOn", false)) {
			yinxiaokaiTextureRegion.setTexturePosition(424, 280);
		}
		Sprite fankui=new Sprite(408, 95, 180, 140,fankuiTextureRegion){
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.registerEntityModifier(new SequenceEntityModifier(
							new ParallelEntityModifier(new AlphaModifier(0.1f,
									1.0f, 0.0f), new AlphaModifier(0.1f, 0.0f,
									1.0f))));
					mSoundplay(konckSound);
					this.registerEntityModifier(new SequenceEntityModifier(
							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									FeedbackAgent agent = new FeedbackAgent(
											MainActivity.this);
									agent.startFeedbackActivity();
								}
							}, new ParallelEntityModifier(new AlphaModifier(
									0.1f, 1.0f, 0.0f)), new AlphaModifier(0.1f,
									0.0f, 1.0f)));
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Sprite fanhui=new Sprite(604, 245, 180, 140,fanhuiTextureRegion){
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(konckSound);
					mMenuScene.clearChildScene();
					mMenuScene.setChildScene(mStaticMenuScene);
					MainActivity.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							ShezhiScene.detachSelf();
						}
					});
					mEngine.setScene(mMenuScene);
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		ShezhiScene.registerTouchArea(guanyu);
		ShezhiScene.registerTouchArea(yinxiao);
		ShezhiScene.registerTouchArea(fankui);
		ShezhiScene.registerTouchArea(fanhui);
		ShezhiScene.getLastChild().attachChild(backimg4);
		ShezhiScene.getLastChild().attachChild(guanyu);
		ShezhiScene.getLastChild().attachChild(yinxiao);
		ShezhiScene.getLastChild().attachChild(fankui);
		ShezhiScene.getLastChild().attachChild(fanhui);
	}
	private void creatOptionsMenuScene() {
		// TODO Auto-generated method stub
		mOptionsMenuScene = new Scene(2);
		Sprite backimg3 = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,backimageTextureRegion);
		//backimg3.setColor(1f, 1f, 1f);
		jifendouText = new ChangeableText(0, 0, mredfont, 
				" "+audioOptions.getInt("jifen", 0), 10);
		Sprite money = new Sprite(0, 10, 50, 50, moneyTextureRegion);
		jifendouText.setPosition(money.getWidth(), 10);

		//jijiangkaifang=new Sprite(300,50,200,30,jijiangkaifangTextureRegion);
		
		Text jijiangkaifang=new Text(230,50, mmFont, "更多宠物即将开放...");
		jijiangkaifang.setColor(1, 0, 0);
		chongwu1 = new AnimatedSprite(0, 0, 200, 400, MengwuTextureRegion);
		chongwu2 = new AnimatedSprite(0, 0, 200, 400, MengwuTextureRegion);
		final long[] durations = new long[3];
		durations[0] = 500;
		durations[1] = 500;
		chongwu1.animate(durations, 0, 2, true);
		chongwu2.animate(durations, 0, 2, true);
		zuo = new Sprite(0, 0, 100, 100, zuoTextureRegion) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mOptionsMenuScene.unregisterTouchArea(zuo);
					mOptionsMenuScene.unregisterTouchArea(you);
					switch (dangqian) {
					case 1:
						chongwu2.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										mOptionsMenuScene
												.registerTouchArea(zuo);
										mOptionsMenuScene
												.registerTouchArea(you);
									}
								}, new ParallelEntityModifier(
										new MoveXModifier(1, CAMERA_WIDTH,
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												EaseBounceOut.getInstance()))));
						chongwu1.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
									}
								}, new ParallelEntityModifier(
										new MoveXModifier(0.5f,
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												-chongwu2.getWidth()))));
						if (--dangqian <= 0) {
							dangqian = 2;
						}
						break;
					case 2:
						chongwu1.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										mOptionsMenuScene
												.registerTouchArea(zuo);
										mOptionsMenuScene
												.registerTouchArea(you);
									}
								}, new ParallelEntityModifier(
										new MoveXModifier(1, CAMERA_WIDTH,
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												EaseBounceOut.getInstance()))));
						chongwu2.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub

									}
								}, new ParallelEntityModifier(
										new MoveXModifier(0.5f,
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												-chongwu2.getWidth()))));
						if (--dangqian <= 0) {
							dangqian = 2;
						}
						break;
					default:
						break;
					}
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		you = new Sprite(0, 0, 100, 100, youTextureRegion) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mOptionsMenuScene.unregisterTouchArea(zuo);
					mOptionsMenuScene.unregisterTouchArea(you);
					switch (dangqian) {
					case 1:
						chongwu1.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub

									}
								}, new ParallelEntityModifier(
										new MoveXModifier(0.5f,
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												CAMERA_WIDTH))));
						chongwu2.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										mOptionsMenuScene
												.registerTouchArea(zuo);
										mOptionsMenuScene
												.registerTouchArea(you);
									}
								}, new ParallelEntityModifier(
										new MoveXModifier(1, -chongwu2
												.getWidth(),
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												EaseBounceOut.getInstance()))));
						if (++dangqian >= 2) {
							dangqian = 1;
						}
						break;
					case 2:
						chongwu1.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub
										mOptionsMenuScene
												.registerTouchArea(zuo);
										mOptionsMenuScene
												.registerTouchArea(you);
									}
								}, new ParallelEntityModifier(
										new MoveXModifier(1, -chongwu2
												.getWidth(),
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												EaseBounceOut.getInstance()))));
						chongwu2.registerEntityModifier(new SequenceEntityModifier(
								new IEntityModifierListener() {
									@Override
									public void onModifierFinished(
											IModifier<IEntity> pModifier,
											IEntity pItem) {
										// TODO Auto-generated method stub

									}
								}, new ParallelEntityModifier(
										new MoveXModifier(0.5f,
												(CAMERA_WIDTH - chongwu2
														.getWidth()) / 2,
												CAMERA_WIDTH))));
						if (++dangqian >= 2) {
							dangqian = 1;
						}
						break;
					default:
						break;
					}
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		chongwu1.setPosition((CAMERA_WIDTH - chongwu1.getWidth()) / 2,
				(CAMERA_HEIGHT - chongwu1.getWidth()) / 2);
		chongwu2.setPosition(-chongwu2.getWidth(),
				(CAMERA_HEIGHT - chongwu2.getWidth()) / 2);
		zuo.setPosition((CAMERA_WIDTH - zuo.getWidth()) / 2 -50,
				CAMERA_HEIGHT - 100);
		you.setPosition((CAMERA_WIDTH - you.getWidth()) / 2 + 50,
				CAMERA_HEIGHT - 100);

		backturn = new Sprite(0, 0,200 ,200 , xiaobackTextureRegion) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(konckSound);
					mMenuScene.clearChildScene();
					mMenuScene.setChildScene(mStaticMenuScene);
					MainActivity.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							mOptionsMenuScene.detachSelf();
						}
					});
					mEngine.setScene(mMenuScene);
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		backturn.setPosition(CAMERA_WIDTH - backturn.getWidth() - 20,
				300);
		mOptionsMenuScene.registerTouchArea(zuo);
		mOptionsMenuScene.registerTouchArea(you);
		mOptionsMenuScene.registerTouchArea(backturn);
		mOptionsMenuScene.getLastChild().attachChild(backimg3);
		mOptionsMenuScene.getLastChild().attachChild(money);
		mOptionsMenuScene.getLastChild().attachChild(jifendouText);
		mOptionsMenuScene.getLastChild().attachChild(chongwu1);
		mOptionsMenuScene.getLastChild().attachChild(chongwu2);
		mOptionsMenuScene.getLastChild().attachChild(zuo);
		mOptionsMenuScene.getLastChild().attachChild(you);
		mOptionsMenuScene.getLastChild().attachChild(backturn);
		mOptionsMenuScene.getLastChild().attachChild(jijiangkaifang);

		/*
		 * Obb_Role obb_role=new Obb_Role(); obb_role.refreshnumber();
		 * backimg3=new Rectangle(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		 * //backimg3.setColor(obb_role.getRed(), obb_role.getGreen(),
		 * obb_role.getBlue()); backimg3.setColor(1f,1f,1f); jifendouText=new
		 * ChangeableText(0, 0, mredfont, "X"+audioOptions.getInt("jifen",
		 * 0),10); Sprite money=new Sprite(0, 0,50,50,moneyTextureRegion);
		 * jifendouText.setPosition(CAMERA_WIDTH-jifendouText.getWidth()-40,
		 * 10); money.setPosition(jifendouText.getX()-money.getWidth(),
		 * jifendouText.getY()); base15=new ChangeableText(0, 0, mredfont,
		 * "15秒模式加1秒(200升级豆)",20) {
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * if(audioOptions.getInt("jifen", 0)>=200) { CustomDialog dialog;
		 * CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("确认？") .setMessage("消耗200升级豆\n15秒模式：+1秒")
		 * .setPositiveButton("是的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * audioEditor.putInt("base15",audioOptions.getInt("base15", 15)+1);
		 * audioEditor.commit();
		 * //base15.setText("15秒:"+audioOptions.getInt("base15",
		 * 15)+"秒(200升级豆)");
		 * audioEditor.putInt("jifen",audioOptions.getInt("jifen", 0)-200);
		 * audioEditor.commit();
		 * jifendouText.setText("X"+audioOptions.getInt("jifen", 0));
		 * dialog.dismiss(); final Text addscore=new Text(0, 0, mredfont,
		 * "-200",HorizontalAlign.CENTER);
		 * addscore.setPosition((CAMERA_WIDTH-addscore.getWidth())/2,
		 * (CAMERA_HEIGHT-addscore.getHeight())/2);
		 * addscore.registerEntityModifier(new SequenceEntityModifier( new
		 * IEntityModifierListener() {
		 * 
		 * @Override public void onModifierFinished(IModifier<IEntity>
		 * pModifier, IEntity pItem) { // TODO Auto-generated method stub
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * addscore.detachSelf();
		 * 
		 * } });
		 * 
		 * } }, new ParallelEntityModifier( new MoveModifier(1f,
		 * (CAMERA_WIDTH-addscore.getWidth()),
		 * (CAMERA_WIDTH-addscore.getWidth())/2, 0, 0), new AlphaModifier(1f, 1,
		 * 0)), new ScaleModifier(1f, 1f, 0)));
		 * mOptionsMenuScene.getLastChild().attachChild(addscore); } }).
		 * setNegativeButton("考虑", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } else { CustomDialog dialog; CustomDialog.Builder
		 * customBuilder = new CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("抱歉") .setMessage("升级豆不足").
		 * setNegativeButton("好的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } break; case TouchEvent.ACTION_UP: break; } return
		 * true; } }; base15.setPosition((CAMERA_WIDTH-base15.getWidth())/2,
		 * CAMERA_HEIGHT/5-25); jinjie=new ChangeableText(0, 0, mredfont,
		 * "进阶模式加1秒(1000升级豆)",20) {
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * if(audioOptions.getInt("jifen", 0)>=1000) { CustomDialog dialog;
		 * CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("确认？") .setMessage("消耗1000升级豆\n进阶模式：+1秒")
		 * .setPositiveButton("是的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * audioEditor.putInt("jinjie",audioOptions.getInt("jinjie", 2)+1);
		 * audioEditor.commit();
		 * //jinjie.setText("进阶:"+audioOptions.getInt("jinjie",
		 * 2)+"秒(1000升级豆)");
		 * audioEditor.putInt("jifen",audioOptions.getInt("jifen", 0)-1000);
		 * audioEditor.commit();
		 * jifendouText.setText("X"+audioOptions.getInt("jifen", 0));
		 * dialog.dismiss(); final Text addscore=new Text(0, 0, mredfont,
		 * "-1000",HorizontalAlign.CENTER);
		 * addscore.setPosition((CAMERA_WIDTH-addscore.getWidth())/2,
		 * (CAMERA_HEIGHT-addscore.getHeight())/2);
		 * addscore.registerEntityModifier(new SequenceEntityModifier( new
		 * IEntityModifierListener() {
		 * 
		 * @Override public void onModifierFinished(IModifier<IEntity>
		 * pModifier, IEntity pItem) { // TODO Auto-generated method stub
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * addscore.detachSelf();
		 * 
		 * } });
		 * 
		 * } }, new ParallelEntityModifier( new MoveModifier(1f,
		 * (CAMERA_WIDTH-addscore.getWidth()),
		 * (CAMERA_WIDTH-addscore.getWidth())/2, 0, 0), new AlphaModifier(1f, 1,
		 * 0)), new ScaleModifier(1f, 1f, 0)));
		 * mOptionsMenuScene.getLastChild().attachChild(addscore); } }).
		 * setNegativeButton("考虑", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } else { CustomDialog dialog; CustomDialog.Builder
		 * customBuilder = new CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("抱歉") .setMessage("升级豆不足").
		 * setNegativeButton("好的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } break; case TouchEvent.ACTION_UP: break; } return
		 * true; } }; jinjie.setPosition((CAMERA_WIDTH-jinjie.getWidth())/2,
		 * (CAMERA_HEIGHT)*2/5-25); overtime=new ChangeableText(0, 0, mredfont,
		 * "错误点击加1次(1000升级豆)",20) {
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * if(audioOptions.getInt("jifen", 0)>=1000) { CustomDialog dialog;
		 * CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("确认？") .setMessage("消耗1000升级豆\n允许错误：+1次")
		 * .setPositiveButton("是的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * audioEditor.putInt("overtime",audioOptions.getInt("overtime", 2)+1);
		 * audioEditor.commit();
		 * //overtime.setText("错误:"+(audioOptions.getInt("overtime",
		 * 1)-1)+"次(1000升级豆)");
		 * audioEditor.putInt("jifen",audioOptions.getInt("jifen", 0)-1000);
		 * audioEditor.commit();
		 * jifendouText.setText("X"+audioOptions.getInt("jifen", 0));
		 * dialog.dismiss(); final Text addscore=new Text(0, 0, mredfont,
		 * "-1000",HorizontalAlign.CENTER);
		 * addscore.setPosition((CAMERA_WIDTH-addscore.getWidth())/2,
		 * (CAMERA_HEIGHT-addscore.getHeight())/2);
		 * addscore.registerEntityModifier(new SequenceEntityModifier( new
		 * IEntityModifierListener() {
		 * 
		 * @Override public void onModifierFinished(IModifier<IEntity>
		 * pModifier, IEntity pItem) { // TODO Auto-generated method stub
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * addscore.detachSelf();
		 * 
		 * } });
		 * 
		 * } }, new ParallelEntityModifier( new MoveModifier(1f,
		 * (CAMERA_WIDTH-addscore.getWidth()),
		 * (CAMERA_WIDTH-addscore.getWidth())/2, 0, 0), new AlphaModifier(1f, 1,
		 * 0)), new ScaleModifier(1f, 1f, 0)));
		 * mOptionsMenuScene.getLastChild().attachChild(addscore); } }).
		 * setNegativeButton("考虑", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } else { CustomDialog dialog; CustomDialog.Builder
		 * customBuilder = new CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("抱歉") .setMessage("升级豆不足").
		 * setNegativeButton("好的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } break; case TouchEvent.ACTION_UP: break; } return
		 * true; } }; overtime.setPosition((CAMERA_WIDTH-overtime.getWidth())/2,
		 * (CAMERA_HEIGHT)*3/5-25); isadv=new ChangeableText(0, 0, mredfont,
		 * "永久性去除广告(500升级豆)",20) {
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * if(audioOptions.getInt("isadv", 1)==0) { CustomDialog dialog;
		 * CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this); customBuilder.setTitle("抱歉")
		 * .setMessage("您已去除广告"). setNegativeButton("好的", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int which) { dialog.dismiss(); } });
		 * dialog = customBuilder.create(); dialog.show(); } else {
		 * if(audioOptions.getInt("jifen", 0)>=500) { CustomDialog dialog;
		 * CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("确认？") .setMessage("消耗500升级豆\n永久去除广告")
		 * .setPositiveButton("是的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * audioEditor.putInt("isadv",0); audioEditor.commit();
		 * audioEditor.putInt("jifen",audioOptions.getInt("jifen", 0)-500);
		 * audioEditor.commit();
		 * jifendouText.setText("X"+audioOptions.getInt("jifen", 0));
		 * dialog.dismiss(); final Text addscore=new Text(0, 0, mredfont,
		 * "-500",HorizontalAlign.CENTER);
		 * addscore.setPosition((CAMERA_WIDTH-addscore.getWidth())/2,
		 * (CAMERA_HEIGHT-addscore.getHeight())/2);
		 * addscore.registerEntityModifier(new SequenceEntityModifier( new
		 * IEntityModifierListener() {
		 * 
		 * @Override public void onModifierFinished(IModifier<IEntity>
		 * pModifier, IEntity pItem) { // TODO Auto-generated method stub
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * addscore.detachSelf();
		 * 
		 * } });
		 * 
		 * } }, new ParallelEntityModifier( new MoveModifier(1f,
		 * (CAMERA_WIDTH-addscore.getWidth()),
		 * (CAMERA_WIDTH-addscore.getWidth())/2, 0, 0), new AlphaModifier(1f, 1,
		 * 0)), new ScaleModifier(1f, 1f, 0)));
		 * mOptionsMenuScene.getLastChild().attachChild(addscore); } }).
		 * setNegativeButton("考虑", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show();
		 * 
		 * } else { CustomDialog dialog; CustomDialog.Builder customBuilder =
		 * new CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("抱歉") .setMessage("升级豆不足").
		 * setNegativeButton("好的", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); } } break; case TouchEvent.ACTION_UP: break; } return
		 * true; } }; isadv.setPosition((CAMERA_WIDTH-overtime.getWidth())/2,
		 * (CAMERA_HEIGHT)*4/5-25); backturn=new ChangeableText(0, 0, mredfont,
		 * "返回",5) {
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * mSoundplay(konckSound); mMenuScene.clearChildScene();
		 * mMenuScene.setChildScene(mStaticMenuScene);
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { mOptionsMenuScene.detachSelf(); } });
		 * mEngine.setScene(mMenuScene); break; case TouchEvent.ACTION_UP:
		 * break; } return true; } };
		 * 
		 * backturn.setPosition(CAMERA_WIDTH-backturn.getWidth()-20,CAMERA_HEIGHT
		 * -backturn.getHeight()-20); free=new Text(10, 10, mredfont, "免费积分"){
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * //91点金积分墙 DianJinPlatform.showOfferWall(MainActivity.this);
		 * 
		 * break; case TouchEvent.ACTION_UP: break; } return true; } };
		 * change=new Text(0, 0, mredfont, "积分换豆"){
		 * 
		 * @SuppressLint("NewApi") public boolean onAreaTouched(TouchEvent
		 * pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		 * // TODO Auto-generated method stub
		 * switch(pSceneTouchEvent.getAction()){ case TouchEvent.ACTION_DOWN:
		 * CustomDialog dialog; final int
		 * num=(int)DianJinPlatform.getBalance(MainActivity.this);
		 * CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this);
		 * customBuilder.setTitle("确认？") .setMessage("已有积分"+num+"\n确认1积分兑换4升级豆")
		 * .setPositiveButton("是的", new DialogInterface.OnClickListener() {
		 * public void onClick(final DialogInterface dialog, int which) { //查询豆
		 * //int num=PointsManager.getInstance(MainActivity.this).queryPoints();
		 * DianJinPlatform.consume(MainActivity.this, num, new ConsumeListener()
		 * {
		 * 
		 * @Override public void onSuccess() { if(num>0) {
		 * Toast.makeText(MainActivity.this,"消费成功", Toast.LENGTH_SHORT).show();
		 * audioEditor.putInt("jifen",audioOptions.getInt("jifen", 0)+num*4);
		 * audioEditor.commit(); dialog.dismiss(); final Text addscore=new
		 * Text(0, 0, mfont, "+"+num*4,HorizontalAlign.CENTER);
		 * addscore.setPosition((CAMERA_WIDTH-addscore.getWidth())/2,
		 * (CAMERA_HEIGHT-addscore.getHeight())/2);
		 * addscore.registerEntityModifier(new SequenceEntityModifier( new
		 * IEntityModifierListener() {
		 * 
		 * @Override public void onModifierFinished(IModifier<IEntity>
		 * pModifier, IEntity pItem) { // TODO Auto-generated method stub
		 * MainActivity.this.runOnUpdateThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * jifendouText.setText("X"+audioOptions.getInt("jifen", 0));
		 * addscore.detachSelf(); } }); } }, new ParallelEntityModifier( new
		 * MoveModifier(1f,
		 * (CAMERA_WIDTH-addscore.getWidth())/2,(CAMERA_WIDTH-addscore
		 * .getWidth()), 0, 0), new AlphaModifier(1f, 1, 0)), new
		 * ScaleModifier(1f, 1f, 0)));
		 * mOptionsMenuScene.getLastChild().attachChild(addscore); } else {
		 * CustomDialog dialog2; CustomDialog.Builder customBuilder = new
		 * CustomDialog.Builder(MainActivity.this); customBuilder.setTitle("抱歉")
		 * .setMessage("无积分或无网络"). setNegativeButton("好的", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog2, int which) { dialog2.dismiss(); }
		 * }); dialog2 = customBuilder.create(); dialog2.show();
		 * dialog.dismiss(); } }
		 * 
		 * @Override public void onError(int errorCode,String errorMessage) {
		 * switch (errorCode) { case
		 * DianJinPlatform.DIANJIN_ERROR_ILLEGAL_CONSUNE://合法的消费金额
		 * Toast.makeText(MainActivity.this,
		 * errorMessage,Toast.LENGTH_SHORT).show(); break; case
		 * DianJinPlatform.DIANJIN_ERROR_BALANCE_NO_ENOUGH://余额不足
		 * Toast.makeText(MainActivity.this,
		 * errorMessage,Toast.LENGTH_SHORT).show(); default: break; } } }); }
		 * }). setNegativeButton("考虑", new DialogInterface.OnClickListener() {
		 * public void onClick(DialogInterface dialog, int which) {
		 * dialog.dismiss(); } }); dialog = customBuilder.create();
		 * dialog.show(); break; case TouchEvent.ACTION_UP: break; } return
		 * true; } };
		 * change.setPosition(10,CAMERA_HEIGHT-change.getHeight()-20);
		 * 
		 * mOptionsMenuScene.registerTouchArea(base15);
		 * mOptionsMenuScene.registerTouchArea(jinjie);
		 * mOptionsMenuScene.registerTouchArea(overtime);
		 * mOptionsMenuScene.registerTouchArea(isadv);
		 * mOptionsMenuScene.registerTouchArea(backturn);
		 * mOptionsMenuScene.registerTouchArea(free);
		 * mOptionsMenuScene.registerTouchArea(change);
		 * mOptionsMenuScene.getLastChild().attachChild(backimg3);
		 * mOptionsMenuScene.getLastChild().attachChild(jifendouText);
		 * mOptionsMenuScene.getLastChild().attachChild(money);
		 * mOptionsMenuScene.getLastChild().attachChild(base15);
		 * mOptionsMenuScene.getLastChild().attachChild(jinjie);
		 * mOptionsMenuScene.getLastChild().attachChild(overtime);
		 * mOptionsMenuScene.getLastChild().attachChild(isadv);
		 * mOptionsMenuScene.getLastChild().attachChild(backturn);
		 * mOptionsMenuScene.getLastChild().attachChild(free);
		 * mOptionsMenuScene.getLastChild().attachChild(change);
		 */
	}

	public void initSplashScene() {
		splashScene = new Scene(2);
		Sprite loadingback = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,loadTextureRegion);
		splashScene.getLastChild().attachChild(loadingback);
		mMenuScene.setChildScene(splashScene);

	}

	public void loadResources() {
		this.mFontTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mfont = FontFactory.createFromAsset(mFontTexture, this,
				"fonts/pod.ttf", 40, true, Color.WHITE);
		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
		this.mEngine.getFontManager().loadFont(this.mfont);

		this.mredFontTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mredfont = FontFactory.createFromAsset(mredFontTexture, this,
				"fonts/pod.ttf", 40, true, Color.RED);
		this.mEngine.getTextureManager().loadTexture(this.mredFontTexture);
		this.mEngine.getFontManager().loadFont(this.mredfont);

		this.mbigFontTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mbigfont = FontFactory.createFromAsset(mbigFontTexture, this,
				"fonts/pod.ttf", 100, true, Color.RED);
		this.mEngine.getTextureManager().loadTexture(this.mbigFontTexture);
		this.mEngine.getFontManager().loadFont(this.mbigfont);
		/*
		 * this.pushTexture = new Texture(512,
		 * 512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		 * this.push1TextureRegion =
		 * TextureRegionFactory.createFromAsset(pushTexture, this,
		 * "main/push1.png", 0, 0); this.push2TextureRegion =
		 * TextureRegionFactory.createFromAsset(pushTexture, this,
		 * "main/push2.png", 0, 256); this.push3TextureRegion =
		 * TextureRegionFactory.createFromAsset(pushTexture, this,
		 * "main/push3.png", 256, 0); this.push4TextureRegion =
		 * TextureRegionFactory.createFromAsset(pushTexture, this,
		 * "main/push4.png", 256, 256);
		 * this.mEngine.getTextureManager().loadTexture(pushTexture);
		 */
		this.moneyTexture = new Texture(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.moneyTextureRegion = TextureRegionFactory.createFromAsset(
				moneyTexture, this, "main/money.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(moneyTexture);

		this.zuoTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.zuoTextureRegion = TextureRegionFactory.createFromAsset(
				zuoTexture, this, "main/zuo.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(zuoTexture);

		this.youTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.youTextureRegion = TextureRegionFactory.createFromAsset(
				youTexture, this, "main/you.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(youTexture);
		
		main = new Texture(1024, 1024,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);
		miao15TextureRegion = TextureRegionFactory.createFromAsset(main, this,
				"main/miao15.png", 16, 0);
		jinjieTextureRegion = TextureRegionFactory.createFromAsset(main, this,
				"main/jinjie.png", 424, 0);
		zhuangqiangTextureRegion = TextureRegionFactory.createFromAsset(main, this,
				"main/zhuangqiang.png", 16, 280);
	    biantaiTextureRegion = TextureRegionFactory.createFromAsset(main, this,
				"main/biantai.png", 424, 280);
		jingzhunTextureRegion = TextureRegionFactory.createFromAsset(main, this,
				"main/jingzhun.png", 16, 560);
		shangdianTextureRegion = TextureRegionFactory.createFromAsset(main, this,
				"main/shangdian.png", 424, 560);
		
		main2 = new Texture(1024, 1024,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);
		shequTextureRegion = TextureRegionFactory.createFromAsset(main2, this,
				"main/shequ.png", 0, 0);
		shezhiTextureRegion = TextureRegionFactory.createFromAsset(main2, this,
				"main/shezhi.png", 0, 280);
		tuichuTextureRegion = TextureRegionFactory.createFromAsset(main2, this,
				"main/tuichu.png", 500, 280);
		this.mEngine.getTextureManager().loadTexture(main);
		this.mEngine.getTextureManager().loadTexture(main2);
		
		nanduTexture = new Texture(1024, 1024,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);
		nandu2TextureRegion = TextureRegionFactory.createFromAsset(nanduTexture, this,
				"main/nandu2.png", 10, 10);
		nandu4TextureRegion = TextureRegionFactory.createFromAsset(nanduTexture, this,
				"main/nandu4.png", 10, 300);
		this.mEngine.getTextureManager().loadTexture(nanduTexture);

		shezhimain=new Texture(1024, 1024);
		guanyuTextureRegion = TextureRegionFactory.createFromAsset(shezhimain, this,
				"main/guanyu.png", 16, 0);
		fankuiTextureRegion = TextureRegionFactory.createFromAsset(shezhimain, this,
				"main/fankui.png", 424, 0);
		fanhuiTextureRegion = TextureRegionFactory.createFromAsset(shezhimain, this,
				"main/fanhui.png", 16, 280);
		yinxiaokaiTextureRegion = TextureRegionFactory.createFromAsset(shezhimain, this,
				"main/yinxiaokai.png", 424, 280);
		yinxiaoguanTextureRegion = TextureRegionFactory.createFromAsset(shezhimain, this,
				"main/yinxiaoguan.png", 16, 560);
		this.mEngine.getTextureManager().loadTexture(shezhimain);
		
		backimageTexture=new Texture(1024, 512);
		backimageTextureRegion = TextureRegionFactory.createFromAsset(backimageTexture, this,
				"main/background.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(backimageTexture);
		
		this.MengwuTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.MengwuTextureRegion = TextureRegionFactory.createTiledFromAsset(
				MengwuTexture, this, "main/mengwu.png", 0, 0, 4, 2);
		this.mEngine.getTextureManager().loadTexture(MengwuTexture);
		
		gaunyubackTexture=new Texture(1024, 1024);
		guanyubackTextureRegion = TextureRegionFactory.createFromAsset(gaunyubackTexture, this,
				"main/guanyuback.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(gaunyubackTexture);
		
		xiaobackTexture=new Texture(256, 256);
		xiaobackTextureRegion = TextureRegionFactory.createFromAsset(xiaobackTexture, this,
				"main/xiaoback.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(xiaobackTexture);
		
		try {

			this.pushSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/getin.ogg");
			this.konckSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/konck.ogg");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

	private Runnable mTimeGameLaunch = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// Intent myIntent = new Intent(MainActivity.this,
			// TimeMainGame.class);
			Intent myIntent = new Intent(MainActivity.this, TimeMainGame.class);
			MainActivity.this.startActivity(myIntent);
		}
	};
	private Runnable mMainGameLaunch = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent myIntent = new Intent(MainActivity.this, NewMainGame.class);
			MainActivity.this.startActivity(myIntent);

		}
	};
	private Runnable mBestGameLaunch = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent myIntent = new Intent(MainActivity.this, BestMainGame.class);
			MainActivity.this.startActivity(myIntent);

		}
	};
	private Runnable mBTGameLaunch = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent myIntent = new Intent(MainActivity.this, BTMainGame.class);
			MainActivity.this.startActivity(myIntent);

		}
	};
	private Runnable mLaunchOptions = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
			MainActivity.this.startActivity(myIntent);
		}
	};
	private Runnable mzhuangqingGameLaunch = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent myIntent = new Intent(MainActivity.this,
					JinjieMainGame.class);
			MainActivity.this.startActivity(myIntent);
		}
		
	};

	@Override
	public void onPauseGame() {
		// TODO Auto-generated method stub
		super.onPauseGame();
	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			showCheckDialog("你要离开我吗?");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 退出提示函数
	public void showCheckDialog(String message) {
		CustomDialog dialog;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				MainActivity.this);
		customBuilder
				.setTitle("退出?")
				.setMessage(message)
				.setPositiveButton("是的", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// 有米
						// 注销广告
						// 注销91点金
						DianJinPlatform.destory(MainActivity.this);
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
						ExitApplication.getInstance().exit();
					}
				})
				.setNegativeButton("逗你玩",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
		dialog = customBuilder.create();
		dialog.show();
		/*
		 * new AlertDialog.Builder(this) .setTitle("你要离开我吗?")
		 * .setMessage(message) .setPositiveButton("是的", new
		 * DialogInterface.OnClickListener(){ public void
		 * onClick(DialogInterface dialog, int which) {
		 * setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 * ExitApplication.getInstance().exit(); } }) .setNegativeButton("逗你玩",
		 * new DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int which) { } }) .show();
		 */
	}

	@Override
	public void onResumeGame() {
		// TODO Auto-generated method stub
		super.onResumeGame();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			MobclickAgent.onPause(this);
			KTPlay.onPause(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try {
			MobclickAgent.onResume(this);
			KTPlay.onResume(this);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void mSoundplay(Sound mSound) {
		if (audioOptions.getBoolean("effectsOn", true)) {
			mSound.play();
		}

	}

	public void showShare() {
		/*
		 * //分享 Intent intent = new Intent(Intent.ACTION_SEND);
		 * intent.setType("text/plain"); // 纯文本 File f = new File( ALBUM_PATH +
		 * "share"); if (f != null && f.exists() && f.isFile()) {
		 * intent.setType("image/png"); Uri u = Uri.fromFile(f);
		 * intent.putExtra(Intent.EXTRA_STREAM, u); }
		 * intent.putExtra(Intent.EXTRA_TEXT,
		 * "发现一个有意思的游戏，《捕获颜色》,我觉得很好玩,大家一起来玩玩看，下载地址:http://app.mi.com/detail/61542"
		 * ); intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * startActivity(Intent.createChooser(intent, getTitle()));
		 */
		/*
		 * ShareSDK.initSDK(this);
		 * 
		 * OnekeyShare oks = new OnekeyShare(); //关闭sso授权
		 * oks.disableSSOWhenAuthorize(); // 分享时Notification的图标和文字
		 * oks.setNotification(R.drawable.icon, getString(R.string.app_name));
		 * // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用 oks.setTitle("分享游戏"); //
		 * titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 * //oks.setTitleUrl("http://app.mi.com/detail/61542"); //
		 * text是分享文本，所有平台都需要这个字段 oks.setText(
		 * "发现一个有意思的游戏《捕获颜色》,我觉得很好玩,大家一起来玩玩看，下载地址:http://app.mi.com/detail/61542"
		 * ); // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 * oks.setImagePath(ALBUM_PATH + "share"); // url仅在微信（包括好友和朋友圈）中使用
		 * //oks.setUrl("http://app.mi.com/detail/61542"); //
		 * comment是我对这条分享的评论，仅在人人网和QQ空间使用 oks.setComment("真棒!"); //
		 * site是分享此内容的网站名称，仅在QQ空间使用 oks.setSite("推荐游戏"); //
		 * siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 * oks.setSiteUrl("http://app.mi.com/detail/61542");
		 * 
		 * // 启动分享GUI oks.show(this);
		 */
	}

}