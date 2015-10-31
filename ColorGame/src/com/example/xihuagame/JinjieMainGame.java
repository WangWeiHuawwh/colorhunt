package com.example.xihuagame;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import model.CustomDialog;
import model.ExitApplication;
import model.MyData;
import model.My_role;
import model.Obb_Role;
import model.ShowThing;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotDialogListener;
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
import org.anddev.andengine.entity.modifier.DelayModifier;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.modifier.ParallelEntityModifier;
import org.anddev.andengine.entity.modifier.RotationModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.entity.util.ScreenCapture;
import org.anddev.andengine.entity.util.ScreenCapture.IScreenCaptureCallback;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
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
import com.ktplay.open.KTAccountManager;
import com.ktplay.open.KTLeaderboard;
import com.ktplay.open.KTLeaderboardPaginator;
import com.ktplay.open.KTPlay;
import com.ktplay.open.KTUser;
import com.ktplay.open.KTAccountManager.KTLoginListener;
import com.umeng.analytics.MobclickAgent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

public class JinjieMainGame extends BaseGameActivity {
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	private static final int OBB_WIDTH = 90;
	private static final int OBB_HEIGHT = 95;
	private static final int OBB_JULI = 90;
	private static final float PUSH_WIDE = (float) 130.00;
	private static final int WW = 300;
	private static final int MaxSp = 9;
	private static final int MSG_COUNTER_DOWN = 0;
	private int UPDATE_INTERVAL = 100;
	private static int TOTAL_Amount = 20;
	private static final int Maxsupertime = 250;
	private int superdoublemore = 1;
	private Camera mCamera;
	private SharedPreferences.Editor audioEditor;
	private SharedPreferences audioOPtions;
	private int clicknumber = MaxSp;
	private Boolean isSuper = false;
	private Boolean isSupercancel = false;
	private Boolean isbegin = false;
	private Timer timer;
	private TimerTask task;
	private Timer supertimer;
	private TimerTask supertask;
	String tmpstring;
	private My_role my_role = new My_role();
	protected Scene mMainScene;
	private Scene splashScene;
	//private Rectangle gameoverimg;
	private Texture mPush1Texture;
	private TextureRegion mPush1TextureRegion;
	private Texture mPush2Texture;
	private TextureRegion mPush2TextureRegion;
	private Texture mPush3Texture;
	private TextureRegion mPush3TextureRegion;
	private Texture mPush4Texture;
	private TextureRegion mPush4TextureRegion;

	private Texture MengwuTexture;
	private TiledTextureRegion MengwuTextureRegion;
	AnimatedSprite Mengwu;
	// copy
	private boolean gameovercanclick = false;
	ChangeableText play[] = new ChangeableText[10];
	ChangeableText playscore[] = new ChangeableText[10];

	ChangeableText playhaoyou[] = new ChangeableText[10];
	ChangeableText playhaoyouscore[] = new ChangeableText[10];
	// (copy)
	private Font mpaihangFont;
	private Texture mpaihangFontTexture;
	private Font mpaihangredFont;
	private Texture mpaihangredFontTexture;
	// (copy)
	private Sprite paihang;
	// copy
	private Scene paihangScene;

	private Texture numsum;
	private TextureRegion number[];
	private TextureRegion errorTexTureRegion;
	private Texture againTexture;
	private TextureRegion againTextureRegion;
	private Texture homeTexture;
	private TextureRegion homeTextureRegion;
	private Texture shareTexture;
	private TextureRegion shareTextureRegion;
	private Sprite loadingback;
	private Text DoyoudownText;
	protected MenuScene mStaticMenuScene;
	//Text textCenter;
	Sprite push1;
	Sprite push2;
	Sprite push3;
	Sprite push4;
	Sprite tmp[];
	// 激光
	Line line1;
	Line line2;
	Line line3;
	Line line4;
	// 曲谱数组
	private int[] KISS_THE_RAIN;
	private Font mFont;
	private Texture mFontTexture;
	private Font mscoreFont;
	private Texture mscoreFontTexture;
	private Font mbigFont;
	private Texture mbigFontTexture;
	private Obb_Role obb_role;
	private int[] array;
	private int obb_number = 0;
	private int click = 0;
	protected Handler mHandler;
	
	//改
	AnimatedSprite score1;
	AnimatedSprite score2;
	AnimatedSprite score3;
	AnimatedSprite score4;
	AnimatedSprite score5;
	Sprite okvir;
	Sprite fennv;
	private Sprite healthbar;
	Sprite timeokvir;
	Sprite timetime;
	private Sprite timebar;
	private Texture okvirTexture;
	private TextureRegion okvirTextureRegion;
	private Texture fennvTexture;
	private TextureRegion fennvTextureRegionon;
	private TextureRegion fennvTextureRegionoff;
	private Texture healthbarTexture;
	private TextureRegion healthbarTextureRegionon;
	private Texture timeokvirTexture;
	private TextureRegion timeokvirTextureRegion;
	private Texture timetimeTexture;
	private TextureRegion timetimeTextureRegionon;
	private Texture timebarTexture;
	private TextureRegion timebarTextureRegionon;
	
	private Texture scoreTexture;
	private TiledTextureRegion score1TextureRegion;
	private TiledTextureRegion score2TextureRegion;
	private TiledTextureRegion score3TextureRegion;
	private TiledTextureRegion score4TextureRegion;
	private TiledTextureRegion score5TextureRegion;
	//private Sprite money;
	protected Texture moneyTexture;
	protected TextureRegion moneyTextureRegion;
	
	Sprite pause;
	private Texture pauseTexture;
	private TextureRegion pauseTextureRegionon;
	private Sprite continuesprite;
	private Sprite backmainsprite;
	protected Texture continueTexture;
	protected TextureRegion continueTextureRegion;
	protected TextureRegion backmainTextureRegion;
	
	private Sprite saysprite;
	protected Texture duihuaTexture;
	protected TextureRegion saycheckTextureRegion;
	protected TextureRegion sayerrorTextureRegion;
	protected TextureRegion saytimeoverTextureRegion;
	protected Texture loadTexture;
	protected TextureRegion loadTextureRegion;
	//Sprite say;
	private Texture sayTexture;
	private TextureRegion sayTextureRegion;
	// private Texture upTexture;
	// private TextureRegion upTextureRegion;
	// Sprite up;
	// 声音
	private Sound prefectsound;
	private Sound prefectsound2;
	private Sound prefectsound3;
	private Sound prefectsound4;
	private Sound prefectsound5;

	private Sprite backback1;
	private Sprite backback2;
	private Texture backbackTexture;
	private TextureRegion backbackTextureRegionon;
	
	private float red = 1;
	private float green = 1;
	private float blue = 1;
	private Rectangle supertimeout;
	//private ChangeableText scoreText;
	int score = 0;
	private ChangeableText timeText;
	// private ChangeableText mgameoverText;
	private ChangeableText perfect;
	private Sprite MoShiText;
	private Text youxiText;
	private ChangeableText shengjidou;
	private ChangeableText MaxscoreText;
	private ChangeableText myscoreText;
	//private ChangeableText duihuaText;

	private Rectangle uprectangle;
	private Rectangle downrectangle;
	private Sprite share;
	private Sprite again;
	private Sprite home;
	private Scene endScene;
	private Sound konckSound;
	private Font mtimeFont;
	private Texture mtimeFontTexture;

	private Sound push1Sound;
	private Sound push2Sound;
	private Sound push3Sound;
	private Sound push4Sound;
	private Sound waringSound;
	private Sound gameoversound;
	private Sound getinsuper;
	private Sound passsound;
	private int soundnumber = 21;
	private Sound[] myback = new Sound[soundnumber + 1];
	private int pass = 100;
	private int[] passcore = { 100, 250, 500, 1000, 1500, 2000, 2500 };
	private int passnumber = 0;
	private int time;
	private int supertime = 10;
	GestureDetector detector;
	private boolean isloaded = false;
	
	private Sprite gameoverback;
	protected Texture gameoverbackTexture;
	protected TextureRegion gameoverbackTextureRegion;
	private Sprite moshi;
	protected Texture moshiTexture;
	protected TextureRegion moshiTextureRegion;
	protected TextureRegion paihangTextureRegion;
	protected TextureRegion newrecordTextureRegion;
	private Sprite newrecord;

	private int overtime = 1;
	private int backnumber = 1;
	ShowThing st;
	private final static String ALBUM_PATH = Environment
			.getExternalStorageDirectory() + "/colorhunt_img/";
	Sound kacha;
	Sound lucy;
	private String shangchuan;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		detector.onTouchEvent(event);
		return true;
	}

	private Handler timehandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_COUNTER_DOWN:

				if (!isSuper && !isSupercancel) {
					time--;
					if (time < 0) {
						mSoundplay(gameoversound);
						gameover(1);
					} else {
						if (time == 30 || time == 20 || time == 10) {
							mSoundplay(waringSound);
						}
						tmpstring = String.valueOf(time);
						timeText.setText(time/10+"");
						if(time<=TOTAL_Amount)
						{
						timebar.setWidth(190*time/TOTAL_Amount);
						timebar.setPosition(500+(float)44*(TOTAL_Amount-time)/TOTAL_Amount, 15);
						}
						else{
						timebar.setWidth(190);
						timebar.setPosition(500, 15);
						}
					}
				}
				break;
			}
		}
	};
	private Handler supertimehandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_COUNTER_DOWN:

				supertime--;
				if (supertime == -1) {

					mHandler.removeCallbacks(refreshhandler);
					healthbar.setWidth(0);
					healthbar.setColor(1f, 204 / 255f, 51 / 255f);

					if (supertask != null) {
						supertask.cancel();
					}
					if (supertimer != null) {
						supertimer.purge();
						supertimer.cancel();
					}

					mMainScene.unregisterTouchArea(push1);
					mMainScene.unregisterTouchArea(push2);
					mMainScene.unregisterTouchArea(push3);
					mMainScene.unregisterTouchArea(push4);
					click = 0;
					isSuper = false;
					supertimeout.setVisible(true);
					backback1.setAlpha(0.5f);
					backback2.setColor(1f, 1f, 1f);
					// 挡板
					uprectangle.setVisible(true);
					downrectangle.setVisible(true);

					uprectangle
							.registerEntityModifier(new SequenceEntityModifier(
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											JinjieMainGame.this
													.runOnUpdateThread(new Runnable() {
														@Override
														public void run() {
															// TODO
															// Auto-generated
															// method stub
															uprectangle
																	.setVisible(false);
														}
													});
										}
									}, new MoveYModifier(0.5f,
											-CAMERA_HEIGHT / 2, 0),
									new MoveYModifier(0.5f, 0,
											-CAMERA_HEIGHT / 2)));
					downrectangle
							.registerEntityModifier(new SequenceEntityModifier(
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											JinjieMainGame.this
													.runOnUpdateThread(new Runnable() {
														@Override
														public void run() {
															// TODO
															// Auto-generated
															// method stub
															downrectangle
																	.setVisible(false);
														}
													});
										}
									}, new MoveYModifier(0.5f, CAMERA_HEIGHT,
											CAMERA_HEIGHT / 2),
									new MoveYModifier(0.5f, CAMERA_HEIGHT / 2,
											CAMERA_HEIGHT)));
					final Text outsupertext = new Text(0, 0, mscoreFont,
							"愤怒模式结束", HorizontalAlign.CENTER);
					outsupertext.setPosition(
							(CAMERA_WIDTH - outsupertext.getWidth()) / 2,
							(CAMERA_HEIGHT) / 2);
					outsupertext
							.registerEntityModifier(new SequenceEntityModifier(
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											JinjieMainGame.this
													.runOnUpdateThread(new Runnable() {
														@Override
														public void run() {
															// TODO
															// Auto-generated
															// method stub
															outsupertext
																	.setVisible(false);
															outsupertext
																	.detachSelf();
														}
													});
										}
									}, new DelayModifier(1f)));
					mMainScene.getLastChild().attachChild(outsupertext);
					isSupercancel = true;
					mHandler.postDelayed(refreshhandler, 1000);
					timeText.setText("恢复");
				} else {
					tmpstring = String.valueOf(supertime);
					timeText.setText(supertime/10+"");
					if(supertime<=Maxsupertime)
					{
					timebar.setWidth(190*supertime/Maxsupertime);
					timebar.setPosition(500+(float)44*(Maxsupertime-supertime)/Maxsupertime, 15);
					}
					else{
					timebar.setWidth(190);
					timebar.setPosition(500, 15);
					}
				}
				healthbar.setWidth(supertime * 196 / Maxsupertime);
				break;
			}
		}

	};

	public Engine onLoadEngine() {
		// TODO Auto-generated method stub
		mHandler = new Handler();
		ExitApplication.getInstance().addActivity(this);
		audioOPtions = getSharedPreferences("audio", MODE_PRIVATE);
		audioEditor = audioOPtions.edit();
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		obb_role = new Obb_Role();
		obb_role.setnumber(clicknumber);
		obb_role.refreshnumber();
		array = obb_role.getarray();
		obb_number = obb_role.getnumber();

		number = new TextureRegion[MaxSp];
		tmp = new Sprite[MaxSp];

		detector = new GestureDetector(JinjieMainGame.this,
				new ExampleGestureLisener());
		TOTAL_Amount = (audioOPtions.getInt("jinjie", 2) + 1) * 10;

		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new FillResolutionPolicy(), this.mCamera).setNeedsSound(true));
	}

	public void onLoadResources() {
		// TODO Auto-generated method stub

		try {
			if (MultiTouch.isSupported(this)) {
				mEngine.setTouchController(new MultiTouchController());
			}
		} catch (final MultiTouchException e) {

		}

		this.mFontTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFont = FontFactory.createFromAsset(mFontTexture, this,
				"fonts/pod.ttf", 40, true, Color.rgb(255, 98, 149));

		this.mEngine.getTextureManager().loadTexture(mFontTexture);
		this.mEngine.getFontManager().loadFont(mFont);
		this.mFont.prepareLettes("游戏结束".toCharArray());
		this.loadTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.loadTextureRegion = TextureRegionFactory.createFromAsset(
				loadTexture, this, "main/loadin.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(loadTexture);
	}

	public void loadResources() {
		this.mPush1Texture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mPush1TextureRegion = TextureRegionFactory.createFromAsset(
				mPush1Texture, this, "main/push1.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(mPush1Texture);

		this.mPush2Texture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mPush2TextureRegion = TextureRegionFactory.createFromAsset(
				mPush2Texture, this, "main/push2.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(mPush2Texture);

		this.mPush3Texture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mPush3TextureRegion = TextureRegionFactory.createFromAsset(
				mPush3Texture, this, "main/push3.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(mPush3Texture);

		this.mPush4Texture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mPush4TextureRegion = TextureRegionFactory.createFromAsset(
				mPush4Texture, this, "main/push4.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(mPush4Texture);


		this.sayTexture = new Texture(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.sayTextureRegion = TextureRegionFactory.createFromAsset(
				sayTexture, this, "main/say.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(sayTexture);

		numsum = new Texture(1024, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		number[0] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c1.png", 0, 0);
		number[1] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c2.png", 200, 0);
		number[2] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c3.png", 400, 0);
		number[3] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c4.png", 600, 0);
		number[4] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c5.png", 0, 200);
		number[5] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c6.png", 200, 200);
		number[6] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c7.png",400, 200);
		number[7] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c8.png", 600, 200);
		number[8] = TextureRegionFactory.createFromAsset(numsum, this,
				"main/c8.png", 600, 200);
		this.errorTexTureRegion = TextureRegionFactory.createFromAsset(numsum,
				this, "main/error.png", 800, 200);
		this.mEngine.getTextureManager().loadTexture(numsum);
		

		this.againTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.againTextureRegion = TextureRegionFactory.createFromAsset(
				againTexture, this, "main/again.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(againTexture);

		this.homeTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.homeTextureRegion = TextureRegionFactory.createFromAsset(
				homeTexture, this, "main/home.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(homeTexture);

		this.shareTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.shareTextureRegion = TextureRegionFactory.createFromAsset(
				shareTexture, this, "main/share.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(shareTexture);

		this.MengwuTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.MengwuTextureRegion = TextureRegionFactory.createTiledFromAsset(
				MengwuTexture, this, "main/mengwu.png", 0, 0, 4, 2);
		this.mEngine.getTextureManager().loadTexture(MengwuTexture);

		//改
		this.scoreTexture = new Texture(1024, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.score1TextureRegion = TextureRegionFactory.createTiledFromAsset(
				scoreTexture, this, "main/number.png", 0, 0, 10, 1);
		this.mEngine.getTextureManager().loadTexture(scoreTexture);
		this.score2TextureRegion = TextureRegionFactory.createTiledFromAsset(
				scoreTexture, this, "main/number.png", 512, 0, 10, 1);
		this.mEngine.getTextureManager().loadTexture(scoreTexture);
		this.score3TextureRegion = TextureRegionFactory.createTiledFromAsset(
				scoreTexture, this, "main/number.png", 0, 128, 10, 1);
		this.mEngine.getTextureManager().loadTexture(scoreTexture);
		this.score4TextureRegion = TextureRegionFactory.createTiledFromAsset(
				scoreTexture, this, "main/number.png", 512, 128, 10, 1);
		this.mEngine.getTextureManager().loadTexture(scoreTexture);
		this.score5TextureRegion = TextureRegionFactory.createTiledFromAsset(
				scoreTexture, this, "main/number.png", 0, 256, 10, 1);
		this.mEngine.getTextureManager().loadTexture(scoreTexture);
		
		this.okvirTexture = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.okvirTextureRegion = TextureRegionFactory.createFromAsset(
				okvirTexture, this, "main/okvir.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(okvirTexture);
		
		this.fennvTexture = new Texture(512, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fennvTextureRegionon = TextureRegionFactory.createFromAsset(
				fennvTexture, this, "main/fennvon.png", 0, 0);
		this.fennvTextureRegionoff = TextureRegionFactory.createFromAsset(
				fennvTexture, this, "main/fennvoff.png", 0, 128);
		this.mEngine.getTextureManager().loadTexture(fennvTexture);
		
		this.healthbarTexture = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.healthbarTextureRegionon = TextureRegionFactory.createFromAsset(
				healthbarTexture, this, "main/healbar.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(healthbarTexture);
		
		this.timeokvirTexture = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.timeokvirTextureRegion = TextureRegionFactory.createFromAsset(
				timeokvirTexture, this, "main/timeback.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(timeokvirTexture);
		
		this.timetimeTexture = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.timetimeTextureRegionon = TextureRegionFactory.createFromAsset(
				timetimeTexture, this, "main/time.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(timetimeTexture);
		
		this.timebarTexture = new Texture(512, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.timebarTextureRegionon = TextureRegionFactory.createFromAsset(
				timebarTexture, this, "main/timeheal.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(timebarTexture);
		
		this.backbackTexture = new Texture(1024, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.backbackTextureRegionon = TextureRegionFactory.createFromAsset(
				backbackTexture, this, "main/backback.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(backbackTexture);
		this.pauseTexture = new Texture(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.pauseTextureRegionon = TextureRegionFactory.createFromAsset(
				pauseTexture, this, "main/pause.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(pauseTexture);
		this.continueTexture = new Texture(512, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.continueTextureRegion = TextureRegionFactory.createFromAsset(
				continueTexture, this, "main/continue.png", 0, 0);
		this.backmainTextureRegion = TextureRegionFactory.createFromAsset(
				continueTexture, this, "main/backmain.png", 0, 512);
		this.mEngine.getTextureManager().loadTexture(continueTexture);
		
		this.gameoverbackTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.gameoverbackTextureRegion = TextureRegionFactory.createFromAsset(
				gameoverbackTexture, this, "main/gameoverback.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(gameoverbackTexture);
		this.moshiTexture = new Texture(512, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.moshiTextureRegion = TextureRegionFactory.createFromAsset(
				moshiTexture, this, "main/jinjiemoshi.png", 0, 0);
		this.paihangTextureRegion = TextureRegionFactory.createFromAsset(
				moshiTexture, this, "main/paihang.png", 0, 90);
		this.newrecordTextureRegion = TextureRegionFactory.createFromAsset(
				moshiTexture, this, "main/newrecord.png", 0, 150);
		this.mEngine.getTextureManager().loadTexture(moshiTexture);
		
		this.duihuaTexture = new Texture(1024, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.saycheckTextureRegion = TextureRegionFactory.createFromAsset(
				duihuaTexture, this, "main/saycheck.png", 0, 0);
		this.sayerrorTextureRegion = TextureRegionFactory.createFromAsset(
				duihuaTexture, this, "main/sayerror.png", 512, 0);
		this.saytimeoverTextureRegion = TextureRegionFactory.createFromAsset(
				duihuaTexture, this, "main/saytimeover.png", 0, 256);
		this.mEngine.getTextureManager().loadTexture(duihuaTexture);
		
		// copy
		this.mtimeFontTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mtimeFont = FontFactory.createFromAsset(mtimeFontTexture, this,
				"fonts/pod.ttf", 40, true, Color.rgb(255, 0, 0));
		this.mEngine.getTextureManager().loadTexture(mtimeFontTexture);
		this.mEngine.getFontManager().loadFont(mtimeFont);
		this.mtimeFont.prepareLettes("s01234567890".toCharArray());

		this.mscoreFontTexture = new Texture(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mscoreFont = FontFactory.createFromAsset(mscoreFontTexture, this,
				"fonts/pod.ttf", 30, true, Color.rgb(255, 204, 51));
		this.mEngine.getTextureManager().loadTexture(mscoreFontTexture);
		this.mEngine.getFontManager().loadFont(mscoreFont);
		this.mscoreFont.prepareLettes("score:1234567890".toCharArray());

		// (copy)
		this.mpaihangFontTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mpaihangFont = FontFactory.createFromAsset(mpaihangFontTexture,
				this, "fonts/pod.ttf", 20, true, Color.rgb(0, 0, 0));
		this.mEngine.getTextureManager().loadTexture(mpaihangFontTexture);
		this.mEngine.getFontManager().loadFont(mpaihangFont);
		this.mpaihangFont.prepareLettes("1234567890".toCharArray());
		this.mpaihangredFontTexture = new Texture(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mpaihangredFont = FontFactory.createFromAsset(
				mpaihangredFontTexture, this, "fonts/pod.ttf", 20, true,
				Color.rgb(255, 0, 0));
		this.mEngine.getTextureManager().loadTexture(mpaihangredFontTexture);
		this.mEngine.getFontManager().loadFont(mpaihangredFont);
		this.mpaihangredFont.prepareLettes("1234567890".toCharArray());

		this.mbigFontTexture = new Texture(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mbigFont = FontFactory.createFromAsset(mbigFontTexture, this,
				"fonts/pod.ttf", 100, true, Color.rgb(255, 0, 0));
		this.mEngine.getTextureManager().loadTexture(mbigFontTexture);
		this.mEngine.getFontManager().loadFont(mbigFont);
		this.mbigFont.prepareLettes("1234567890".toCharArray());

		this.moneyTexture = new Texture(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.moneyTextureRegion = TextureRegionFactory.createFromAsset(
				moneyTexture, this, "main/money.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(moneyTexture);
		try {
			this.prefectsound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/perfect.ogg");
			this.prefectsound2 = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/p2.ogg");
			this.prefectsound3 = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/p3.ogg");
			this.prefectsound4 = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/p4.ogg");
			this.prefectsound5 = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/p5.ogg");
			this.waringSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/waring.mp3");
			this.gameoversound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/gameover.mp3");
			this.getinsuper = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/getinsuper.mp3");
			this.passsound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/pass.mp3");
			this.kacha = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/kacha.ogg");
			this.lucy = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/lucy.ogg");
			this.konckSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), getApplicationContext(),
					"music/konck.ogg");
			for (int i = 1; i <= soundnumber; i++) {
				this.myback[i] = SoundFactory.createSoundFromAsset(
						this.mEngine.getSoundManager(),
						getApplicationContext(), "music/sound" + i + ".mp3");
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		this.mMainScene = new Scene(1);
		initSplashScene();
		mEngine.registerUpdateHandler(new TimerHandler(1f,
				new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						// TODO Auto-generated method stub
						loadResources();
						loadScene();
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								splashScene.detachSelf();
							}
						});
						Scene scene = new Scene();
						scene.setBackgroundEnabled(false);
						mMainScene.setChildScene(scene);
						mEngine.setScene(mMainScene);
					}
				}));
		return mMainScene;
	}

	private void initSplashScene() {
		// TODO Auto-generated method stub

		// 载入广告
		if (MyData.value2.equals("1")) {
			 
				SpotManager.getInstance(JinjieMainGame.this).showSpotAds(
						JinjieMainGame.this);
			

		}
		splashScene = new Scene(2);
		loadingback = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,loadTextureRegion);
		obb_role.refreshnumber();
		// loadingback.setColor(obb_role.getRed(),obb_role.getGreen(),obb_role.getBlue());
		 DoyoudownText=new Text(0, 0, mFont, "冷知识");
		 DoyoudownText.setPosition((CAMERA_WIDTH-DoyoudownText.getWidth())/2, 20);
		st = new ShowThing();
		st.random();
		Text telltext = new Text(0, 0, this.mFont, st.getcolorthing(),
				HorizontalAlign.CENTER);
		telltext.setPosition((CAMERA_WIDTH - telltext.getWidth()) / 2,
				(CAMERA_HEIGHT - telltext.getHeight()) / 2 - 10);
		if(st.colorwhat>=st.color.length)
		{
			telltext.setColor(72/255f,102/255f,216/255f);
			DoyoudownText.setColor(72/255f,102/255f,216/255f);
		}else
		{
			int[] a = st.getColor();
			telltext.setSize(30, 30);
			telltext.setColor(a[0] / 255f, a[1] / 255f, a[2] / 255f);
			DoyoudownText.setColor(a[0] / 255f, a[1] / 255f, a[2] / 255f);
		}
		splashScene.getLastChild().attachChild(loadingback);
		splashScene.getLastChild().attachChild(DoyoudownText);
		splashScene.getLastChild().attachChild(telltext);
		mMainScene.setChildScene(splashScene);
	}

	public void loadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		okvir = new Sprite(10, 10, 220, 50, okvirTextureRegion);
		fennv = new Sprite(10, 10, 220, 50, fennvTextureRegionoff);
		healthbar = new Sprite(22, 15, 0, 40,healthbarTextureRegionon);
		
		timeokvir = new Sprite(490, 10, 220, 50, timeokvirTextureRegion);
		timetime = new Sprite(490, 10, 220, 50, timetimeTextureRegionon);
		timebar = new Sprite(500, 15, 190, 40,timebarTextureRegionon);
		score1=new AnimatedSprite(0, 0,30,40,score1TextureRegion);
		score2=new AnimatedSprite(0, 0,30,40,score2TextureRegion);
		score3=new AnimatedSprite(0, 0,30,40,score3TextureRegion);
		score4=new AnimatedSprite(0, 0,30,40,score4TextureRegion);
		score5=new AnimatedSprite(0, 0,30,40,score5TextureRegion);
		
		
		//money = new Sprite(0, 0, 50, 50, moneyTextureRegion);
		uprectangle = new Rectangle(0, -CAMERA_HEIGHT / 2, CAMERA_WIDTH,
				CAMERA_HEIGHT / 2);
		downrectangle = new Rectangle(0, CAMERA_HEIGHT, CAMERA_WIDTH,
				CAMERA_HEIGHT / 2);

		backback1 = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,backbackTextureRegionon);
		backback2 = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,backbackTextureRegionon);
		perfect = new ChangeableText(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2,
				mbigFont, "perfect", 10);
		supertimeout = new Rectangle(0, 90, CAMERA_WIDTH, 120);

		
		pause=new Sprite(CAMERA_WIDTH-50, 20, 30,30,pauseTextureRegionon){// 为卡牌绑定监听
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					final MenuScene menuScene=new MenuScene(mCamera);   
					final int centerX=(CAMERA_WIDTH-400)/2;   
					final int centerY=(CAMERA_HEIGHT-240)/2;   
					SpriteBackground backgroundsprite=new SpriteBackground(centerX, centerY, pTouchAreaLocalY, backback1);   
					continuesprite=new Sprite(CAMERA_WIDTH/2-200, CAMERA_HEIGHT/2-100, 200,200,continueTextureRegion){// 为卡牌绑定监听
						@SuppressLint("NewApi")
						public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
								float pTouchAreaLocalX, float pTouchAreaLocalY) {
							// TODO Auto-generated method stub
							switch (pSceneTouchEvent.getAction()) {
							case TouchEvent.ACTION_DOWN:
								mMainScene.clearChildScene();
								timer = new Timer();
								task = new TimerTask() {
									@Override
									public void run() {
										timehandler.sendEmptyMessage(MSG_COUNTER_DOWN);
									}
								};
								timer.schedule(task, 0, UPDATE_INTERVAL);
								break;
							case TouchEvent.ACTION_UP:
								break;
							}
							return true;
					}};
					backmainsprite=new Sprite(CAMERA_WIDTH/2, CAMERA_HEIGHT/2-100, 200,200,backmainTextureRegion){// 为卡牌绑定监听
						@SuppressLint("NewApi")
						public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
								float pTouchAreaLocalX, float pTouchAreaLocalY) {
							// TODO Auto-generated method stub
							switch (pSceneTouchEvent.getAction()) {
							case TouchEvent.ACTION_DOWN:
								Intent intent = new Intent(JinjieMainGame.this,
										MainActivity.class);
								startActivity(intent);
								
								break;
							case TouchEvent.ACTION_UP:
								break;
							}
							return true;
					}};
					menuScene.setBackground(backgroundsprite);   
					menuScene.attachChild(continuesprite);   
					menuScene.registerTouchArea(continuesprite);  
					menuScene.attachChild(backmainsprite);   
					menuScene.registerTouchArea(backmainsprite);
					mMainScene.setChildScene(menuScene, true, true, true); 
					if (MyData.value3.equals("1")) {
						
							SpotManager.getInstance(JinjieMainGame.this).showSpotAds(
									JinjieMainGame.this);
						
					}
					if(timer!=null)
					{
						timer.cancel();
					}
					if(task!=null)
					{
						task.cancel();
					}
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
		}};
		
		timeText = new ChangeableText(0, 0, this.mtimeFont, "00", 2);
		timeText.setPosition(CAMERA_WIDTH - timeText.getWidth()-pause.getWidth()-10, 10);
		
		// 激光
		line1 = new Line(0, 0, 100, 100);
		line2 = new Line(0, 0, 100, 100);
		line3 = new Line(0, 0, 100, 100);
		line4 = new Line(0, 0, 100, 100);

		push1 = new Sprite(0, CAMERA_HEIGHT - 2 * PUSH_WIDE, PUSH_WIDE,
				PUSH_WIDE, mPush1TextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (isbegin == false) {
						begainpush();
					}
					push1();
					this.registerEntityModifier(new SequenceEntityModifier(
							new ScaleModifier(0.1f, 1f, 1.3f),
							new ParallelEntityModifier(new AlphaModifier(0.1f,
									1.0f, 0.0f), new ScaleModifier(0.1f, 1.2f,
									1f), new AlphaModifier(0.1f, 0.0f, 1.0f))));
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		push2 = new Sprite(CAMERA_WIDTH - PUSH_WIDE, CAMERA_HEIGHT - 2
				* PUSH_WIDE, PUSH_WIDE, PUSH_WIDE, mPush2TextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (isbegin == false) {
						begainpush();
					}
					this.registerEntityModifier(new SequenceEntityModifier(
							new ScaleModifier(0.1f, 1f, 1.3f),
							new ParallelEntityModifier(new AlphaModifier(0.1f,
									1.0f, 0.0f), new ScaleModifier(0.1f, 1.2f,
									1f), new AlphaModifier(0.1f, 0.0f, 1.0f))));
					push2();
					break;
				case TouchEvent.ACTION_UP:

					break;
				}
				return true;
			}
		};
		push3 = new Sprite(PUSH_WIDE - 10, CAMERA_HEIGHT - PUSH_WIDE,
				PUSH_WIDE, PUSH_WIDE, mPush3TextureRegion) {
			// 为卡牌绑定监听
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (isbegin == false) {
						begainpush();
					}
					this.registerEntityModifier(new SequenceEntityModifier(
							new ScaleModifier(0.1f, 1f, 1.3f),
							new ParallelEntityModifier(new AlphaModifier(0.1f,
									1.0f, 0.0f), new ScaleModifier(0.1f, 1.2f,
									1f), new AlphaModifier(0.1f, 0.0f, 1.0f))));
					push3();
					break;
				case TouchEvent.ACTION_UP:
					break;

				}
				return true;
			}
		};
		push4 = new Sprite(CAMERA_WIDTH - 2 * PUSH_WIDE + 10, CAMERA_HEIGHT
				- PUSH_WIDE, PUSH_WIDE, PUSH_WIDE, mPush4TextureRegion) {
			// 为卡牌绑定监听
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (isbegin == false) {
						begainpush();
					}
					this.registerEntityModifier(new SequenceEntityModifier(
							new ScaleModifier(0.1f, 1f, 1.3f),
							new ParallelEntityModifier(new AlphaModifier(0.1f,
									1.0f, 0.0f), new ScaleModifier(0.1f, 1.2f,
									1f), new AlphaModifier(0.1f, 0.0f, 1.0f))));
					push4();
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		Mengwu = new AnimatedSprite(0, 0, 150, 280, MengwuTextureRegion);
		saysprite = new Sprite(CAMERA_WIDTH / 2 - 115,CAMERA_HEIGHT / 2 - 35, 230, 100, saycheckTextureRegion);

		// gameover
		endScene = new Scene(2);
		gameoverback = new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,gameoverbackTextureRegion);
		// mgameoverText=new ChangeableText(0, 0,mFont, "再来一次?",12);
		MoShiText = new Sprite(0,0,200,50,moshiTextureRegion);
		shengjidou = new ChangeableText(0, 0, mFont, "升级豆:0", 10) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(konckSound);
					CustomDialog dialog;
					CustomDialog.Builder customBuilder = new CustomDialog.Builder(
							JinjieMainGame.this);
					customBuilder
							.setTitle("说明")
							.setMessage("300分=1升级豆\n用升级豆可以升级")
							.setNegativeButton("知道了",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
					dialog = customBuilder.create();
					dialog.show();
					break;
				case TouchEvent.ACTION_UP:
					// TODO Auto-generated method stub
					break;
				}
				return true;
			}
		};
		MaxscoreText = new ChangeableText(0, 0, mFont, "000000", 10);
		//myscoreText = new ChangeableText(0, 0, mbigFont, ""+ audioOPtions.getInt("Maxtimescore", 0), 10);
		share = new Sprite(0, 0, PUSH_WIDE * 1.2f, PUSH_WIDE * 1.2f,
				shareTextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (gameovercanclick == true) {
						mSoundplay(kacha);
						Display mDisplay = getWindowManager()
								.getDefaultDisplay();
						int W = mDisplay.getWidth();
						int H = mDisplay.getHeight();
						// 截屏
						final ScreenCapture screenCapture = new ScreenCapture();
						// 加入截屏功能。
						endScene.attachChild(screenCapture);
						// 截屏
						screenCapture.capture(0, 0, W, H,
								ALBUM_PATH + "compet",
								new IScreenCaptureCallback() {
									public void onScreenCaptured(
											final String pFilePath) {
										JinjieMainGame.this
												.runOnUiThread(new Runnable() {

													@Override
													public void run() {
														Toast.makeText(
																JinjieMainGame.this,
																"截屏成功",
																Toast.LENGTH_LONG)
																.show();

													}
												});
									}

									public void onScreenCaptureFailed(
											final String pFilePath,
											Exception pException) {
										JinjieMainGame.this
												.runOnUiThread(new Runnable() {

													@Override
													public void run() {
														Toast.makeText(
																JinjieMainGame.this,
																"截屏失败",
																Toast.LENGTH_LONG)
																.show();

													}
												});
									}
								});
						Intent intent = new Intent(Intent.ACTION_SEND);
						File f = new File(ALBUM_PATH + "compet");
						if (f != null && f.exists() && f.isFile()) {
							intent.setType("image/png");
							Uri u = Uri.fromFile(f);
							intent.putExtra(Intent.EXTRA_STREAM, u);
						} else {
							intent.setType("text/plain"); // 纯文本
						}
						intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
						intent.putExtra(
								Intent.EXTRA_TEXT,
								"我在'捕获颜色'进阶模式中得分为"
										+ score
										+ ",求赞，求超越!!下载地址:http://apk.hiapk.com/appinfo/com.example.ColorGame");
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(Intent.createChooser(intent, getTitle()));
						MobclickAgent.onEvent(JinjieMainGame.this, "share");
						this.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(
										0.1f, 1.0f, 0.0f)), new AlphaModifier(
										0.1f, 0.0f, 1.0f)));
					}
					break;
				case TouchEvent.ACTION_UP:
					// TODO Auto-generated method stub

					break;
				}
				return true;
			}
		};
		home = new Sprite(0, 0, PUSH_WIDE * 1.2f, PUSH_WIDE * 1.2f,
				homeTextureRegion) {
			// 为卡牌绑定监听
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					// (copy)
					if (gameovercanclick == true) {
						mSoundplay(konckSound);
						// 隐藏悬浮
						// DianJinPlatform.hideFloatView (JinjieMainGame.this);
						Intent intent = new Intent(JinjieMainGame.this,
								MainActivity.class);
						startActivity(intent);
						this.registerEntityModifier(new SequenceEntityModifier(
								new ParallelEntityModifier(new AlphaModifier(
										0.1f, 1.0f, 0.0f)), new AlphaModifier(
										0.1f, 0.0f, 1.0f)));
						// TimeMainGame.this.finish();
					}
					break;
				case TouchEvent.ACTION_UP:
					// TODO Auto-generated method stub
					break;
				}
				return true;
			}
		};
		again = new Sprite(0, 0, PUSH_WIDE * 1.2f, PUSH_WIDE * 1.2f,
				againTextureRegion) {

			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					// (copy)
					if (gameovercanclick == true) {
						mSoundplay(konckSound);
						// 隐藏悬浮
						// DianJinPlatform.hideFloatView (JinjieMainGame.this);
						gameInit();
						Scene scene = new Scene();
						scene.setBackgroundEnabled(false);
						mMainScene.setChildScene(scene);
						mEngine.setScene(mMainScene);
					}
					break;
				case TouchEvent.ACTION_UP:
					// TODO Auto-generated method stub

					break;
				}
				return true;
			}
		};
		// (copy)
		paihang = new Sprite(0, 0, 200,50, paihangTextureRegion) {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (gameovercanclick == true) {
						mSoundplay(konckSound);
						try {
							ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
							// TelephonyManager mTelephony=
							// (TelephonyManager)this.getSystemService(TELEPHONY_SERVICE);
							// 检查网络连接，如果无网络可用，就不需要进行连网操作等
							NetworkInfo info = mConnectivity
									.getActiveNetworkInfo();
							if (info == null
									|| !mConnectivity
											.getBackgroundDataSetting()) {
								Toast.makeText(JinjieMainGame.this, "请检查网络连接",
										Toast.LENGTH_SHORT).show();
							} else {
								// 上传分数回调
								final KTLeaderboard.OnReportScoreListener onReportScoreListener = new KTLeaderboard.OnReportScoreListener() {
									@Override
									public void onReportScoreResult(
											boolean isSuccess, String arg1,
											long arg2,
											com.ktplay.open.KTError error) {
										// TODO Auto-generated method stub
										if (isSuccess) {
											Toast.makeText(JinjieMainGame.this,
													"分数上传成功",
													Toast.LENGTH_SHORT).show();
										} else {
											Toast.makeText(JinjieMainGame.this,
													error.description,
													Toast.LENGTH_SHORT).show();
										}
									}
								};
								// 创建回调
								boolean isLoggedIn = KTAccountManager
										.isLoggedIn();
								if (isLoggedIn) {
									Toast.makeText(JinjieMainGame.this, "登陆成功",
											Toast.LENGTH_SHORT).show();
									// 上传分数
									KTLeaderboard.reportScore(score,
											shangchuan, onReportScoreListener);

									gameovercanclick = false;
									endScene.unregisterTouchArea(share);
									endScene.unregisterTouchArea(again);
									endScene.unregisterTouchArea(home);
									endScene.unregisterTouchArea(paihang);
									setpaihangScence();
								} else {
									// 未登录
									KTAccountManager.showLoginView(true,
											new KTLoginListener() {
												public void onLoginResult(
														boolean isSuccess,
														com.ktplay.open.KTUser user,
														com.ktplay.open.KTError error) {
													if (isSuccess) {
														Toast.makeText(
																JinjieMainGame.this,
																"登陆成功",
																Toast.LENGTH_SHORT)
																.show();
														// 上传分数
														KTLeaderboard
																.reportScore(
																		score,
																		shangchuan,
																		onReportScoreListener);

														endScene.unregisterTouchArea(share);
														endScene.unregisterTouchArea(again);
														endScene.unregisterTouchArea(home);
														endScene.unregisterTouchArea(paihang);
														setpaihangScence();
													} else {
														Toast.makeText(
																JinjieMainGame.this,
																error.description,
																Toast.LENGTH_SHORT)
																.show();
													}
												}
											});
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		mMainScene.getLastChild().attachChild(backback1);
		mMainScene.getLastChild().attachChild(backback2);
		for (int i = 0; i < MaxSp; i++) {
			tmp[i] = new Sprite(
					i * (OBB_WIDTH + 10) + (CAMERA_WIDTH - OBB_WIDTH) / 2
							- (OBB_WIDTH / 2 * (obb_number - 1))
							- (obb_number - 1) * 5, OBB_JULI, OBB_WIDTH,
					OBB_HEIGHT, number[i]);
			mMainScene.getLastChild().attachChild(tmp[i]);
		}


		mMainScene.getLastChild().attachChild(score1);
		mMainScene.getLastChild().attachChild(score2);
		mMainScene.getLastChild().attachChild(score3);
		mMainScene.getLastChild().attachChild(score4);
		mMainScene.getLastChild().attachChild(score5);
		mMainScene.getLastChild().attachChild(okvir);
		mMainScene.getLastChild().attachChild(fennv);
		mMainScene.getLastChild().attachChild(healthbar);
		mMainScene.getLastChild().attachChild(timeokvir);
		mMainScene.getLastChild().attachChild(timetime);
		mMainScene.getLastChild().attachChild(timebar);
		
		mMainScene.registerTouchArea(pause);
		mMainScene.getLastChild().attachChild(pause);
		
		mMainScene.getLastChild().attachChild(supertimeout);
		mMainScene.getLastChild().attachChild(timeText);
		mMainScene.getLastChild().attachChild(push1);
		mMainScene.getLastChild().attachChild(push2);
		mMainScene.getLastChild().attachChild(push3);
		mMainScene.getLastChild().attachChild(push4);

		mMainScene.getLastChild().attachChild(Mengwu);
		mMainScene.getLastChild().attachChild(saysprite);
		// 激光
		mMainScene.getLastChild().attachChild(line1);
		mMainScene.getLastChild().attachChild(line2);
		mMainScene.getLastChild().attachChild(line3);
		mMainScene.getLastChild().attachChild(line4);
		mMainScene.getLastChild().attachChild(perfect);
		mMainScene.getLastChild().attachChild(downrectangle);
		mMainScene.getLastChild().attachChild(uprectangle);
		gameInit();
		isloaded = true;
	}

	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

	private Runnable refreshhandler = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 开始时把萌物对话清空
			saysprite.setVisible(false);

			isSupercancel = false;
			if (isSuper == true) {
				clicknumber = MaxSp;
			} else {
				Random random = new Random();
				if (score >= 0 && score <= 20) {
					clicknumber = random.nextInt(2) + 4;
				} else if (20 < score && score <= 300) {
					clicknumber = random.nextInt(2) + 5;
				} else if (300 < score && score <= 500) {
					clicknumber = random.nextInt(3) + 5;
				} else if (500 < score && score <= 1000) {
					backback2.setAlpha(0.5f);
					clicknumber = random.nextInt(3) + 6;
				} else {
					clicknumber = 7;
				}
				time = TOTAL_Amount;
				timeText.setText(time/10+"");
				if(time<=TOTAL_Amount)
				{
				timebar.setWidth(190*time/TOTAL_Amount);
				timebar.setPosition(500+(float)44*(TOTAL_Amount-time)/TOTAL_Amount, 15);
				}
				else{
				timebar.setWidth(190);
				timebar.setPosition(500, 15);
				}
			}
			obb_role.setnumber(clicknumber);
			obb_role.refreshnumber();
			array = obb_role.getarray();
			obb_number = obb_role.getnumber();
			Random random = new Random();
			for (int i = 0; i < obb_number; i++) {
				tmp[i].setPosition(i * (OBB_WIDTH) + (CAMERA_WIDTH - OBB_WIDTH)
						/ 2 - (OBB_WIDTH / 2 * (obb_number - 1)), OBB_JULI);
				setnumberPosition(array);
				tmp[i].setVisible(true);
				tmp[i].clearEntityModifiers();
				tmp[i].setAlpha(1f);
				tmp[i].setRotation(0);
				tmp[i].setScale(1);
			}
			if (obb_number != MaxSp) {
				for (int i = obb_number; i < MaxSp; i++) {
					tmp[i].setVisible(false);
				}
			}
			
			if (isSuper == false && score >= 10) {
				int xiaoguo = random.nextInt(5);
				if (xiaoguo == 0) {
					// 特殊效果
					for (int i = obb_number - 1; i >= 0; i--) {
						// 大小
						tmp[i].registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(
								new ScaleModifier(0.5f, 1, 1.5f),
								new ScaleModifier(0.5f, 1.5f, 1))));
					}
				} else if (xiaoguo == 1) {
					// 特殊效果
					for (int i = obb_number - 1; i >= 0; i--) {
						// 旋转
						tmp[i].registerEntityModifier(new LoopEntityModifier(new RotationModifier(1f,
								0, 360)));
					}
				} else if (xiaoguo == 2) {
					// 特殊效果
					for (int i = obb_number - 1; i >= 0; i--) {
						// 透明
						tmp[i].registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(
								new AlphaModifier(0.5f, 1, 0.7f),
								new AlphaModifier(0.5f, 0.7f, 1))));
					}
				} else {

				}
			}
			supertimeout.setVisible(false);

			// add注册push点击监听
			if (obb_role.getWhatkey() == 2) {
				switch (obb_role.getadd1()) {
				case 1:
					mMainScene.registerTouchArea(push1);
					break;
				case 2:
					mMainScene.registerTouchArea(push2);
					break;
				case 3:
					mMainScene.registerTouchArea(push3);
					break;
				case 4:
					mMainScene.registerTouchArea(push4);
					break;
				default:
					break;
				}
				switch (obb_role.getadd2()) {
				case 1:
					mMainScene.registerTouchArea(push1);
					break;
				case 2:
					mMainScene.registerTouchArea(push2);
					break;
				case 3:
					mMainScene.registerTouchArea(push3);
					break;
				case 4:
					mMainScene.registerTouchArea(push4);
					break;
				default:
					break;
				}
			} else {
				mMainScene.registerTouchArea(push1);
				mMainScene.registerTouchArea(push2);
				mMainScene.registerTouchArea(push3);
				mMainScene.registerTouchArea(push4);
			}
		}
	};

	private void SetIntPush(int i) {
		switch (i) {
		case 2:
			push1.setVisible(false);
			push2.setVisible(false);
			push3.setVisible(false);
			push4.setVisible(false);
			mMainScene.unregisterTouchArea(push1);
			mMainScene.unregisterTouchArea(push2);
			mMainScene.unregisterTouchArea(push3);
			mMainScene.unregisterTouchArea(push4);
			switch (obb_role.getadd1()) {
			case 1:
				push1.setVisible(true);
				mMainScene.registerTouchArea(push1);
				push1.setPosition(20, CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push1.setWidth(PUSH_WIDE * 1.2f);
				push1.setHeight(PUSH_WIDE * 1.2f);
				break;
			case 2:
				push2.setVisible(true);
				mMainScene.registerTouchArea(push2);
				push2.setPosition(20, CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push2.setWidth(PUSH_WIDE * 1.2f);
				push2.setHeight(PUSH_WIDE * 1.2f);
				break;
			case 3:
				push3.setVisible(true);
				mMainScene.registerTouchArea(push3);
				push3.setPosition(20, CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push3.setWidth(PUSH_WIDE * 1.2f);
				push3.setHeight(PUSH_WIDE * 1.2f);
				break;
			case 4:
				push4.setVisible(true);
				mMainScene.registerTouchArea(push4);
				push4.setPosition(20, CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push4.setWidth(PUSH_WIDE * 1.2f);
				push4.setHeight(PUSH_WIDE * 1.2f);
				break;
			default:
				break;
			}
			switch (obb_role.getadd2()) {
			case 1:
				push1.setVisible(true);
				mMainScene.registerTouchArea(push1);
				push1.setPosition(CAMERA_WIDTH - PUSH_WIDE * 1.2f-20,
						CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push1.setWidth(PUSH_WIDE * 1.2f);
				push1.setHeight(PUSH_WIDE * 1.2f);
				break;
			case 2:
				push2.setVisible(true);
				mMainScene.registerTouchArea(push2);
				push2.setPosition(CAMERA_WIDTH - PUSH_WIDE * 1.2f-20,
						CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push2.setWidth(PUSH_WIDE * 1.2f);
				push2.setHeight(PUSH_WIDE * 1.2f);
				break;
			case 3:
				push3.setVisible(true);
				mMainScene.registerTouchArea(push3);
				push3.setPosition(CAMERA_WIDTH - PUSH_WIDE * 1.2f-20,
						CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push3.setWidth(PUSH_WIDE * 1.2f);
				push3.setHeight(PUSH_WIDE * 1.2f);
				break;
			case 4:
				push4.setVisible(true);
				mMainScene.registerTouchArea(push4);
				push4.setPosition(CAMERA_WIDTH - PUSH_WIDE * 1.2f-20,
						CAMERA_HEIGHT - PUSH_WIDE * 1.5f);
				push4.setWidth(PUSH_WIDE * 1.2f);
				push4.setHeight(PUSH_WIDE * 1.2f);
				break;
			default:
				break;
			}
			break;
		case 4:
			push1.setWidth(PUSH_WIDE);
			push1.setHeight(PUSH_WIDE);
			push2.setWidth(PUSH_WIDE);
			push2.setHeight(PUSH_WIDE);
			mMainScene.registerTouchArea(push1);
			mMainScene.registerTouchArea(push2);
			mMainScene.registerTouchArea(push3);
			mMainScene.registerTouchArea(push4);
			push1.setPosition(0, CAMERA_HEIGHT - 2 * PUSH_WIDE);
			push2.setPosition(CAMERA_WIDTH - PUSH_WIDE, CAMERA_HEIGHT - 2
					* PUSH_WIDE);
			push3.setPosition(PUSH_WIDE - 10, CAMERA_HEIGHT - PUSH_WIDE);
			push4.setPosition(CAMERA_WIDTH - 2 * PUSH_WIDE + 10, CAMERA_HEIGHT
					- PUSH_WIDE);
			push1.setVisible(true);
			push2.setVisible(true);
			push3.setVisible(true);
			push4.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void gameInit() {

		isSuper = false;
		// copy

		gameovercanclick = false;
		isSupercancel = false;

		isbegin = false;
		obb_number = 0;

		click = 0;

		score = 0;
		score1.setCurrentTileIndex(0);
		score2.setCurrentTileIndex(0);
		score3.setCurrentTileIndex(0);
		score4.setCurrentTileIndex(0);
		score5.setCurrentTileIndex(0);
		score1.setPosition(315, 10);
		score2.setPosition(345, 10);
		score3.setPosition(375, 10);
		score4.setPosition(405, 10);
		score5.setPosition(435, 10);
		score1.setSize(30, 40);
		score2.setSize(30, 40);
		score3.setSize(30, 40);
		score4.setSize(30, 40);
		score5.setSize(30, 40);
		clicknumber = MaxSp;
		supertime = 10;
		my_role.setMysupernumber(0);
		backback2.setAlpha(1f);

		obb_number = 1;

		passnumber = 0;
		pass = passcore[passnumber];

		time = TOTAL_Amount;
		tmpstring = String.valueOf(time);
		timeText.setText(time/10+"");
		timebar.setPosition(500, 15);


		// add改变按钮个数
		if (audioOPtions.getInt("nandu", 0) == 0) {
			obb_role.setwhatkey(2);
			shangchuan = "jinjie2";
		} else {
			obb_role.setwhatkey(4);
			shangchuan = "jinjie";
		}
		obb_role.setnumber(clicknumber);
		obb_role.setadd();
		obb_role.refreshnumber();
		array = obb_role.getarray();
		setnumberPosition(array);
		// 设置按钮个数
		SetIntPush(obb_role.getWhatkey());

		perfect.setVisible(false);
		perfect.setRotation(30);
		for (int i = 0; i < obb_number; i++) {
			tmp[i].setPosition(i * (OBB_WIDTH + 10)
					+ (CAMERA_WIDTH - OBB_WIDTH) / 2
					- (OBB_WIDTH / 2 * (obb_number - 1)), OBB_JULI);
			setnumberPosition(array);
			tmp[i].setVisible(true);
		}

		if (obb_number != MaxSp) {
			for (int i = obb_number; i < MaxSp; i++) {
				tmp[i].setVisible(false);
			}
		}
		KISS_THE_RAIN = st.getsound();

		supertimeout.setVisible(false);
		healthbar.setColor(1f, 204 / 255f, 51 / 255f);
		healthbar.setWidth(0);
		// backimg1.setColor(1f, 246/255f, 199/255f);
		// backimg2.setColor(1f, 246/255f, 199/255f);
		backback1.setColor(1f, 1f, 1f);
		backback2.setColor(1f, 1f, 1f);
		supertimeout.setColor(1f, 246 / 255f, 199 / 255f);
		final long[] durations = new long[9];
		durations[0] = 500;
		durations[1] = 500;
		durations[2] = 500;
		durations[3] = 500;
		durations[4] = 500;
		durations[5] = 500;
		durations[6] = 500;
		durations[7] = 500;
		Mengwu.animate(durations, 0, 8, true);
		Mengwu.setPosition((CAMERA_WIDTH - Mengwu.getWidth()) / 2,300);
		// up.setVisible(false);
		// up.setPosition((CAMERA_WIDTH-up.getWidth())/2,CAMERA_HEIGHT-up.getHeight());

		// gameover
		MaxscoreText.setVisible(true);
		saysprite.setVisible(true);
		saycheckTextureRegion.setTexturePosition(0, 0);
		backnumber = 1;
		// 生命重置
		overtime = audioOPtions.getInt("overtime", 1);
		// 激光
		line1.setVisible(false);
		line2.setVisible(false);
		line3.setVisible(false);
		line4.setVisible(false);
		line1.setLineWidth(3);
		line2.setLineWidth(3);
		line3.setLineWidth(3);
		line4.setLineWidth(3);
		// 挡板
		uprectangle.setPosition(0, -CAMERA_HEIGHT / 2);
		downrectangle.setPosition(0, CAMERA_HEIGHT);
		uprectangle.setVisible(false);
		downrectangle.setVisible(false);
		// 不能点击升级豆
		mMainScene.unregisterTouchArea(shengjidou);
		mMainScene.registerTouchArea(Mengwu);
	}

	private Runnable GameOverhandler = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub

			gameover(0);
		}
	};

	private void gameover(int i) {
		// 显示悬浮
		// DianJinPlatform.showFloatView(JinjieMainGame.this);
		mMainScene.unregisterTouchArea(push1);
		mMainScene.unregisterTouchArea(push2);
		mMainScene.unregisterTouchArea(push3);
		mMainScene.unregisterTouchArea(push4);
		mHandler.removeCallbacks(refreshhandler);
		my_role.setMysupernumber(0);
		healthbar.setWidth(0);
		if (supertask != null) {
			supertask.cancel();
		}
		if (supertimer != null) {
			supertimer.purge();
			supertimer.cancel();
		}

		timer.cancel();
		isSuper = false;

		if (i == 1) {
			saysprite.setVisible(true);
			saycheckTextureRegion.setTexturePosition(0, 256);
			final Text outtimetext = new Text(0, 0, mFont, "",
					HorizontalAlign.CENTER);
			outtimetext.setPosition(
					(CAMERA_WIDTH - outtimetext.getWidth()) / 2,
					(CAMERA_HEIGHT) / 2);
			outtimetext.registerEntityModifier(new SequenceEntityModifier(
					new IEntityModifierListener() {
						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							// TODO Auto-generated method stub
							saysprite.setVisible(false);
							int Maxscore = audioOPtions.getInt("Maxscore", 0);
							if (score > Maxscore) {

								setGameOverScence(true);
								audioEditor.putInt("Maxscore", score);
								audioEditor.commit();
							} else {
								setGameOverScence(false);
							}
							JinjieMainGame.this
									.runOnUpdateThread(new Runnable() {
										@Override
										public void run() {
											// TODO Auto-generated method stub
											outtimetext.setVisible(false);
											outtimetext.detachSelf();
										}
									});

						}
					},
					new ParallelEntityModifier(new AlphaModifier(1f, 0f, 1f))));
			Mengwu.stopAnimation();
			
			mMainScene.getChildScene().attachChild(outtimetext);
		} else {

			int Maxscore = audioOPtions.getInt("Maxscore", 0);
			if (score > Maxscore) {

				setGameOverScence(true);
				audioEditor.putInt("Maxscore", score);
				audioEditor.commit();
			} else {
				setGameOverScence(false);
			}
		}

	}

	private void setGameOverScence(boolean ismax) {
		// (copy)
		endScene.registerTouchArea(share);
		endScene.registerTouchArea(again);
		endScene.registerTouchArea(home);
		endScene.registerTouchArea(paihang);
		gameovercanclick = true;

		// 载入广告
		if (MyData.value.equals("1")) {
			
				SpotManager.getInstance(JinjieMainGame.this).showSpotAds(
						JinjieMainGame.this);
			
		}

		try {
			// 上传分数回调
			final KTLeaderboard.OnReportScoreListener onReportScoreListener = new KTLeaderboard.OnReportScoreListener() {
				@Override
				public void onReportScoreResult(boolean isSuccess, String arg1,
						long arg2, com.ktplay.open.KTError error) {
					// TODO Auto-generated method stub
					if (isSuccess) {
					} else {
					}
				}
			};
			// 创建回调
			boolean isLoggedIn = KTAccountManager.isLoggedIn();
			if (isLoggedIn) {
				// 上传分数
				KTLeaderboard.reportScore(score, shangchuan,
						onReportScoreListener);
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		obb_role.refreshnumber();
		//endScene.getLastChild().attachChild(gameoverimg);
		/*
		 * //显示game over if(score<=100) { mgameoverText.setText("挑战自我"); } else
		 * if(score<250) { mgameoverText.setText("还需努力"); } else if(score<=500)
		 * { mgameoverText.setText("渐行渐远"); } else if(score<=700) {
		 * mgameoverText.setText("突破自我"); } else if(score<=1000) {
		 * mgameoverText.setText("自我升华"); } else if(score<=1200) {
		 * mgameoverText.setText("回到自然"); } else if(score<=1500) {
		 * mgameoverText.setText("空即是色"); } else {
		 * mgameoverText.setText("遁入佛门"); }
		 */
		MaxscoreText.setText(" " + audioOPtions.getInt("Maxscore", 0));
		obb_role.refreshnumber();
		endScene.getLastChild().attachChild(gameoverback);
		/*
		 * //显示game over if(score<=100) { mgameoverText.setText("重度色盲患者"); }
		 * else if(score<250) { mgameoverText.setText("中度色盲患者"); } else
		 * if(score<=500) { mgameoverText.setText("轻度色盲患者"); } else
		 * if(score<=700) { mgameoverText.setText("渐入佳境"); } else
		 * if(score<=1000) { mgameoverText.setText("小有成就"); } else
		 * if(score<=1200) { mgameoverText.setText("初露锋芒"); } else
		 * if(score<=1500) { mgameoverText.setText("高手阶段"); } else
		 * if(score<=2000) { mgameoverText.setText("大神阶段"); } else {
		 * mgameoverText.setText("人类无法超越"); }
		 */
		//改变
		MaxscoreText.setPosition(440,215);
		// endScene.getLastChild().attachChild(mgameoverText);
		endScene.getLastChild().attachChild(MoShiText);
		//youxiText.setPosition(CAMERA_WIDTH - youxiText.getWidth() - 5, 5);
		//endScene.getLastChild().attachChild(youxiText);
		// 显示升级豆
		mSoundplay(lucy);
		//变化
		final Text add = new Text(0, 0, mbigFont, "+" + score / WW,
				HorizontalAlign.CENTER);
		add.setPosition((CAMERA_WIDTH - add.getWidth()) / 2,
				(CAMERA_HEIGHT - add.getHeight()) / 2);
		add.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								add.detachSelf();
								audioEditor.putInt("jifen",
										audioOPtions.getInt("jifen", 0) + score
												/ WW);
								audioEditor.commit();
								shengjidou.setText(" "
										+ audioOPtions.getInt("jifen", 0));
							}
						});
					}
				}, new ParallelEntityModifier(new MoveModifier(1f,
						(CAMERA_WIDTH - add.getWidth()) / 2,
						(CAMERA_WIDTH - add.getWidth()) / 2,
						(CAMERA_HEIGHT - add.getHeight()) / 2, 272), new AlphaModifier(1f,
						1, 0))));
		shengjidou.setText(" " + audioOPtions.getInt("jifen", 0));
		shengjidou.setPosition((CAMERA_WIDTH - shengjidou.getWidth() + 50) / 2,270);
		//money.setPosition(shengjidou.getX() - money.getWidth(),shengjidou.getY());
		mMainScene.registerTouchArea(shengjidou);
		score1.setPosition(250, 120);
		score2.setPosition(310, 120);
		score3.setPosition(370, 120);
		score4.setPosition(430, 120);
		score5.setPosition(490, 120);
		endScene.getLastChild().attachChild(MaxscoreText);
		//endScene.getLastChild().attachChild(myscoreText);
		score1.setSize(60, 80);
		score2.setSize(60, 80);
		score3.setSize(60, 80);
		score4.setSize(60, 80);
		score5.setSize(60, 80);
		endScene.getLastChild().attachChild(score1);
		endScene.getLastChild().attachChild(score2);
		endScene.getLastChild().attachChild(score3);
		endScene.getLastChild().attachChild(score4);
		endScene.getLastChild().attachChild(score5);
		if (ismax == true) {
			MaxscoreText.setText("本次");
			newrecord=new Sprite(0,0,100,50,newrecordTextureRegion);
			newrecord.setPosition(530, 90);
			endScene.getLastChild().attachChild(newrecord);
			SetScore(score);
		} else {
			if(newrecord!=null)
			{
				newrecord.setVisible(false);
			}
			SetScore(score);
		}
		//止
		again.setPosition(120, CAMERA_HEIGHT - share.getHeight() - 20);
		endScene.getLastChild().attachChild(share);
		endScene.registerTouchArea(share);

		home.setPosition(CAMERA_WIDTH - home.getWidth() - 120, CAMERA_HEIGHT
				- home.getHeight() - 20);
		endScene.getLastChild().attachChild(home);
		endScene.registerTouchArea(home);

		share.setPosition((CAMERA_WIDTH - home.getWidth()) / 2, CAMERA_HEIGHT
				- home.getHeight() - 20);
		endScene.getLastChild().attachChild(again);
		endScene.registerTouchArea(again);
		// 加入升级豆
		endScene.getLastChild().attachChild(shengjidou);
		endScene.getLastChild().attachChild(add);
		// (copy)
		paihang.setPosition(CAMERA_WIDTH - paihang.getWidth(), 0);
		// 排行榜界面
		endScene.registerTouchArea(paihang);
		endScene.getLastChild().attachChild(paihang);

		// (copy)
		// MaxscoreText.setPosition((CAMERA_WIDTH-MaxscoreText.getWidth())/2,paihang.getHeight()+10);
		//endScene.getLastChild().attachChild(money);
		mMainScene.setChildScene(endScene);
		// 回收资源
		System.gc();
	}

	// (copy)
	private void setpaihangScence() {
		paihangScene = new Scene(3);
		Rectangle paihangimg = new Rectangle(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		paihangimg.setColor(1, 1, 1);
		final Text take1 = new Text(0, 0, mtimeFont, "获取数据中...");
		take1.setPosition((CAMERA_WIDTH / 2 - take1.getWidth()) / 2,
				(CAMERA_HEIGHT - take1.getHeight()) / 2);
		take1.setVisible(true);

		final Text take2 = new Text(0, 0, mtimeFont, "获取数据中...");
		take2.setPosition(
				CAMERA_WIDTH / 2 + (CAMERA_WIDTH / 2 - take2.getWidth()) / 2,
				(CAMERA_HEIGHT - take2.getHeight()) / 2);
		take2.setVisible(true);

		Text moshi;
		if (obb_role.getWhatkey() == 2) {
			moshi = new Text(0, 0, mtimeFont, "进阶模式(2键)");
		} else {
			moshi = new Text(0, 0, mtimeFont, "进阶模式(4键)");
		}
		moshi.setPosition((CAMERA_WIDTH - moshi.getWidth()) / 2, 0);

		Line line11 = new Line(0, moshi.getHeight() + 3, CAMERA_WIDTH,
				moshi.getHeight() + 3);
		Line line22 = new Line(CAMERA_WIDTH / 2, line11.getY(),
				CAMERA_WIDTH / 2, CAMERA_HEIGHT);
		line11.setColor(1, 0, 0);
		line22.setColor(1, 0, 0);
		line11.setLineWidth(5);
		line22.setLineWidth(5);
		final Text shijie = new Text(0, 0, mpaihangFont, "世界排行");
		shijie.setPosition(0, moshi.getHeight());
		shijie.setVisible(false);
		final Text haoyou = new Text(0, 0, mpaihangFont, "好友排行");
		haoyou.setPosition(CAMERA_WIDTH / 2, moshi.getHeight());
		haoyou.setVisible(false);

		for (int i = 0; i < play.length; i++) {
			play[i] = new ChangeableText(0, 0, mpaihangFont, "精准模式", 20);
			play[i].setPosition(10, CAMERA_HEIGHT * (i + 2) / 10);
			play[i].setVisible(false);

			playscore[i] = new ChangeableText(0, 0, mpaihangFont, "精准模式", 20);
			playscore[i].setPosition(CAMERA_WIDTH / 4, CAMERA_HEIGHT * (i + 2)
					/ 10);
			playscore[i].setVisible(false);

			playhaoyou[i] = new ChangeableText(0, 0, mpaihangFont, "精准模式", 20);
			playhaoyou[i].setPosition(CAMERA_WIDTH / 2 + 10, CAMERA_HEIGHT
					* (i + 2) / 10);
			playhaoyou[i].setVisible(false);

			playhaoyouscore[i] = new ChangeableText(0, 0, mpaihangFont, "精准模式",
					20);
			playhaoyouscore[i].setPosition(CAMERA_WIDTH * 3 / 4, CAMERA_HEIGHT
					* (i + 2) / 10);
			playhaoyouscore[i].setVisible(false);
		}

		Text myback = new Text(0, 0, mtimeFont, "返回") {
			@SuppressLint("NewApi")
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mSoundplay(konckSound);
					endScene.clearChildScene();
					endScene.registerTouchArea(share);
					endScene.registerTouchArea(again);
					endScene.registerTouchArea(home);
					endScene.registerTouchArea(paihang);
					gameovercanclick = true;
					// mMenuScene.setChildScene(mStaticMenuScene);
					JinjieMainGame.this.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							paihangScene.detachSelf();
						}
					});
					// mEngine.setScene(mMenuScene);
					break;
				case TouchEvent.ACTION_UP:
					break;
				}
				return true;
			}
		};
		myback.setPosition(CAMERA_WIDTH - myback.getWidth() - 10, CAMERA_HEIGHT
				- myback.getHeight());
		try {
			// 创建游戏玩家排行榜回调
			final KTLeaderboard.OnGetGameLeaderboardListener onGetGameLeaderboardListener = new KTLeaderboard.OnGetGameLeaderboardListener() {
				@Override
				public void onGetGameLeaderboardResult(boolean isSuccess,
						String arg1, KTLeaderboardPaginator paginator,
						com.ktplay.open.KTError arg3) {
					// TODO Auto-generated method stub
					if (isSuccess) {
						KTUser curLoggedInUser = KTAccountManager
								.currentAccount();
						for (int i = 0; i < paginator.getItemCount(); i++) {
							if (paginator
									.getUsers()
									.get(i)
									.getUserId()
									.toString()
									.equals(curLoggedInUser.getUserId()
											.toString())) {
								if (paginator.getUsers().get(i).getNickname()
										.length() >= 8) {
									play[i].setText("★"
											+ (i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname()
													.substring(0, 7) + "..");
								} else {
									play[i].setText("★"
											+ (i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname());
								}
								play[i].setVisible(true);
								play[i].setColor(0, 0, 1);
								playscore[i].setText("最高分:"
										+ paginator.getUsers().get(i)
												.getScore());
								playscore[i].setVisible(true);
							} else {
								if (paginator.getUsers().get(i).getNickname()
										.length() >= 8) {
									play[i].setText((i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname()
													.substring(0, 7));
								} else {
									play[i].setText((i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname());
								}
								play[i].setVisible(true);
								playscore[i].setText("最高分:"
										+ paginator.getUsers().get(i)
												.getScore());
								playscore[i].setVisible(true);
							}

						}
						take1.setVisible(false);
						shijie.setVisible(true);
						haoyou.setVisible(true);
					} else {
						Toast.makeText(JinjieMainGame.this,
								arg3.description + "错误", Toast.LENGTH_SHORT)
								.show();
					}
				}
			};
			// 获取游戏玩家排行榜数据
			KTLeaderboard.gameLeaderboard(shangchuan, 0, 10,
					onGetGameLeaderboardListener);
			// 创建好友排行榜回调
			KTLeaderboard.OnGetFriendsLeaderboardListener onGetFriendsLeaderboardListener = new KTLeaderboard.OnGetFriendsLeaderboardListener() {
				public void onGetFriendsLeaderboardResult(boolean isSuccess,
						String leaderboardId, KTLeaderboardPaginator paginator,
						com.ktplay.open.KTError error) {
					if (isSuccess) {
						KTUser curLoggedInUser = KTAccountManager
								.currentAccount();
						for (int i = 0; i < paginator.getItemCount(); i++) {
							if (paginator
									.getUsers()
									.get(i)
									.getUserId()
									.toString()
									.equals(curLoggedInUser.getUserId()
											.toString())) {
								if (paginator.getUsers().get(i).getNickname()
										.length() >= 8) {
									playhaoyou[i].setText("★"
											+ (i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname()
													.substring(0, 7) + "..");
								} else {
									playhaoyou[i].setText("★"
											+ (i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname());
								}
								playhaoyou[i].setVisible(true);
								playhaoyou[i].setColor(0, 0, 1);
								playhaoyouscore[i].setText("最高分:"
										+ paginator.getUsers().get(i)
												.getScore());
								playhaoyouscore[i].setVisible(true);
							} else {
								if (paginator.getUsers().get(i).getNickname()
										.length() >= 8) {
									playhaoyou[i].setText((i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname()
													.substring(0, 7));
								} else {
									playhaoyou[i].setText((i + 1)
											+ "."
											+ paginator.getUsers().get(i)
													.getNickname());
								}
								playhaoyou[i].setVisible(true);
								playhaoyouscore[i].setText("最高分:"
										+ paginator.getUsers().get(i)
												.getScore());
								playhaoyouscore[i].setVisible(true);
							}

						}
						take2.setVisible(false);

					} else {
						Toast.makeText(JinjieMainGame.this, error.description,
								Toast.LENGTH_SHORT).show();
					}
				}
			};
			// 获取好友排行榜数据
			KTLeaderboard.friendsLeaderboard(shangchuan, 0, 10,
					onGetFriendsLeaderboardListener);

		} catch (Exception e) {
			// TODO: handle exception
		}
		paihangScene.registerTouchArea(myback);
		paihangScene.getLastChild().attachChild(paihangimg);
		paihangScene.getLastChild().attachChild(myback);
		paihangScene.getLastChild().attachChild(shijie);
		paihangScene.getLastChild().attachChild(haoyou);
		paihangScene.getLastChild().attachChild(take1);
		paihangScene.getLastChild().attachChild(take2);
		paihangScene.getLastChild().attachChild(moshi);
		paihangScene.getLastChild().attachChild(line11);
		paihangScene.getLastChild().attachChild(line22);
		for (int i = 0; i < play.length; i++) {
			paihangScene.getLastChild().attachChild(play[i]);
			paihangScene.getLastChild().attachChild(playscore[i]);
			paihangScene.getLastChild().attachChild(playhaoyou[i]);
			paihangScene.getLastChild().attachChild(playhaoyouscore[i]);
		}
		endScene.setChildScene(paihangScene);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			MobclickAgent.onPause(this);
			// (copy)
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
			// (copy)
			KTPlay.onResume(this);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void clickWhat() {
		//停止旋转动画
		tmp[click].clearEntityModifiers();
		if (click < obb_number - 1) {
			final int i = click;
			tmp[i].registerEntityModifier(new SequenceEntityModifier(
					new IEntityModifierListener() {
						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							// TODO Auto-generated method stub
							JinjieMainGame.this
									.runOnUpdateThread(new Runnable() {
										@Override
										public void run() {
											// TODO Auto-generated method stub
										}
									});
						}
					}, new ScaleModifier(0.1f, 1, 0.5f), new ScaleModifier(
							0.1f, 0.5f, 1)));
			AddScore(1);
			click++;
		} else {
			final int i = click;
			tmp[i].registerEntityModifier(new SequenceEntityModifier(
					new IEntityModifierListener() {
						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							// TODO Auto-generated method stub
							JinjieMainGame.this
									.runOnUpdateThread(new Runnable() {
										@Override
										public void run() {
											if (isSuper) {
												final Text addscore = new Text(
														0,
														0,
														mbigFont,
														"+"
																+ 5
																* (int) Math
																		.pow(2,
																				superdoublemore - 1),
														HorizontalAlign.CENTER);
												addscore.setPosition(
														(CAMERA_WIDTH - addscore
																.getWidth()) / 2,
														(CAMERA_HEIGHT - addscore
																.getHeight()) / 2);
												addscore.registerEntityModifier(new SequenceEntityModifier(
														new IEntityModifierListener() {
															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier,
																	IEntity pItem) {
																// TODO
																// Auto-generated
																// method stub

																JinjieMainGame.this
																		.runOnUpdateThread(new Runnable() {
																			@Override
																			public void run() {
																				// TODO
																				// Auto-generated
																				// method
																				// stub
																				addscore.detachSelf();

																			}
																		});

															}
														},
														new ParallelEntityModifier(
																new MoveModifier(
																		1f,
																		(CAMERA_WIDTH - addscore
																				.getWidth()) / 2,
																				(CAMERA_WIDTH - addscore
																						.getWidth()) / 2,
																		(CAMERA_HEIGHT - addscore
																				.getHeight()) / 2,
																		0),
																new AlphaModifier(
																		1f, 1,
																		0)),
														new ScaleModifier(1f,
																1f, 0)));
												mMainScene.getLastChild()
														.attachChild(addscore);
												score = score
														+ 5
														* (int) Math
																.pow(2,
																		superdoublemore - 1);
												SetScore(score);

												if (score > pass) {

													if (passnumber < 6) {
														mSoundplay(passsound);
														passnumber++;
														pass = passcore[passnumber];
													} else {
														passnumber = 6;
													}
												}
												if (superdoublemore == 1) {
													perfect.setText("perfect");
													mSoundplay(prefectsound);
												} else if (superdoublemore == 2) {
													perfect.setText("PX2");
													mSoundplay(prefectsound);
												} else if (superdoublemore == 3) {
													perfect.setText("PX3");
													mSoundplay(prefectsound);
												} else if (superdoublemore == 4) {
													perfect.setText("PX4");
													mSoundplay(prefectsound);
												} else {
													perfect.setText("PXX");
													mSoundplay(prefectsound);
												}
												superdoublemore++;
												perfect.setPosition(
														(CAMERA_WIDTH - perfect
																.getWidth()) / 2,
														(CAMERA_HEIGHT - perfect
																.getHeight()) / 2);
												perfect.setVisible(true);
												perfect.registerEntityModifier(new SequenceEntityModifier(
														new IEntityModifierListener() {
															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier,
																	IEntity pItem) {
																// TODO
																// Auto-generated
																// method stub
																JinjieMainGame.this
																		.runOnUpdateThread(new Runnable() {
																			@Override
																			public void run() {
																				perfect.setVisible(false);
																				perfect.setScale(1f);
																				perfect.setAlpha(1f);
																			}
																		});
															}
														},
														new ParallelEntityModifier(
																new ScaleModifier(
																		0.5f,
																		1f,
																		1.2f)),
														new AlphaModifier(0.2f,
																1f, 0.5f)));
											} else {
												AddScore(5);
												if (my_role.getMysupernumber() < my_role
														.getMyMaxsupernumber()) {
													my_role.setMysupernumber(my_role
															.getMysupernumber() + 10);
													healthbar.setWidth(190
															* my_role
																	.getMysupernumber()
															/ my_role
																	.getMyMaxsupernumber());

													if (my_role
															.getMysupernumber() >= my_role
															.getMyMaxsupernumber()) {
														healthbar.setWidth(190);

														mSoundplay(getinsuper);
														isSuper = true;
														mMainScene
																.unregisterTouchArea(push1);
														mMainScene
																.unregisterTouchArea(push2);
														mMainScene
																.unregisterTouchArea(push3);
														mMainScene
																.unregisterTouchArea(push4);
														// 挡板
														uprectangle
																.setVisible(true);
														downrectangle
																.setVisible(true);

														uprectangle
																.registerEntityModifier(new SequenceEntityModifier(
																		new IEntityModifierListener() {
																			@Override
																			public void onModifierFinished(
																					IModifier<IEntity> pModifier,
																					IEntity pItem) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub
																				JinjieMainGame.this
																						.runOnUpdateThread(new Runnable() {
																							@Override
																							public void run() {
																								// TODO
																								// Auto-generated
																								// method
																								// stub
																								uprectangle
																										.setVisible(false);
																							}
																						});
																			}
																		},
																		new MoveYModifier(
																				0.5f,
																				-CAMERA_HEIGHT / 2,
																				0),
																		new MoveYModifier(
																				0.5f,
																				0,
																				-CAMERA_HEIGHT / 2)));
														downrectangle
																.registerEntityModifier(new SequenceEntityModifier(
																		new IEntityModifierListener() {
																			@Override
																			public void onModifierFinished(
																					IModifier<IEntity> pModifier,
																					IEntity pItem) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub
																				JinjieMainGame.this
																						.runOnUpdateThread(new Runnable() {
																							@Override
																							public void run() {
																								// TODO
																								// Auto-generated
																								// method
																								// stub
																								downrectangle
																										.setVisible(false);
																							}
																						});
																			}
																		},
																		new MoveYModifier(
																				0.5f,
																				CAMERA_HEIGHT,
																				CAMERA_HEIGHT / 2),
																		new MoveYModifier(
																				0.5f,
																				CAMERA_HEIGHT / 2,
																				CAMERA_HEIGHT)));
														final Text insupertext = new Text(
																0,
																0,
																mFont,
																"自动进入愤怒模式",
																HorizontalAlign.CENTER);
														insupertext
																.setPosition(
																		(CAMERA_WIDTH - insupertext
																				.getWidth()) / 2,
																		(CAMERA_HEIGHT) / 2);
														insupertext
																.registerEntityModifier(new SequenceEntityModifier(
																		new IEntityModifierListener() {
																			@Override
																			public void onModifierFinished(
																					IModifier<IEntity> pModifier,
																					IEntity pItem) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub
																				JinjieMainGame.this
																						.runOnUpdateThread(new Runnable() {
																							@Override
																							public void run() {
																								// TODO
																								// Auto-generated
																								// method
																								// stub
																								insupertext
																										.setVisible(false);
																								insupertext
																										.detachSelf();

																								backback2.setAlpha(1f);
																								backback2.setColor(
																										0,
																										0,
																										0);

																								click = 0;

																								superdoublemore = 1;
																								mHandler.postDelayed(
																										refreshhandler,
																										100);

																								supertime = Maxsupertime;
																								tmpstring = String
																										.valueOf(supertime);
																								timeText.setText(supertime/10+"");
																								if(supertime<=Maxsupertime)
																								{
																								timebar.setWidth(190*supertime/Maxsupertime);
																								timebar.setPosition(500+(float)44*(Maxsupertime-supertime)/Maxsupertime, 15);
																								}
																								else{
																								timebar.setWidth(190);
																								timebar.setPosition(500, 15);
																								}
																								supertimer = new Timer();
																								supertask = new TimerTask() {
																									public void run() {
																										supertimehandler
																												.sendEmptyMessage(MSG_COUNTER_DOWN);
																									}
																								};
																								supertimer
																										.schedule(
																												supertask,
																												0,
																												UPDATE_INTERVAL);
																								my_role.setMysupernumber(0);
																								time = TOTAL_Amount;
																							}
																						});
																			}
																		},
																		new DelayModifier(
																				1f)));
														mMainScene
																.getLastChild()
																.attachChild(
																		insupertext);
														return;
														/*
														 * up.setVisible(true);
														 * up
														 * .registerEntityModifier
														 * (new
														 * SequenceEntityModifier
														 * ( new
														 * IEntityModifierListener
														 * () {
														 * 
														 * @Override public void
														 * onModifierFinished
														 * (IModifier<IEntity>
														 * pModifier, IEntity
														 * pItem) { // TODO
														 * Auto-generated method
														 * stub
														 * JinjieMainGame.this
														 * .runOnUpdateThread
														 * (new Runnable() {
														 * 
														 * @Override public void
														 * run() { // TODO
														 * Auto-generated method
														 * stub
														 * up.setVisible(false);
														 * up.setPosition((
														 * CAMERA_WIDTH
														 * -up.getWidth
														 * ())/2,CAMERA_HEIGHT
														 * -up.getHeight()-50);
														 * } }); } },new
														 * ParallelEntityModifier
														 * (new MoveModifier(1f,
														 * (
														 * CAMERA_WIDTH-up.getWidth
														 * ())/2,
														 * (CAMERA_WIDTH-up
														 * .getWidth())/2,
														 * CAMERA_HEIGHT
														 * -up.getHeight(), 50)
														 * ,new
														 * AlphaModifier(1f,
														 * 1.0f,0.0f))));
														 */

													}
												}
												/*
												 * final Text text=new Text(0,
												 * 0, mscoreFont,
												 * "+"+AddTime/10,
												 * HorizontalAlign.CENTER);
												 * text.
												 * setPosition((CAMERA_WIDTH
												 * -text.getWidth())/2,
												 * (CAMERA_HEIGHT
												 * -text.getHeight())/2);
												 * text.registerEntityModifier
												 * (new SequenceEntityModifier(
												 * new IEntityModifierListener()
												 * {
												 * 
												 * @Override public void
												 * onModifierFinished
												 * (IModifier<IEntity>
												 * pModifier, IEntity pItem) {
												 * // TODO Auto-generated method
												 * stub JinjieMainGame.this.
												 * runOnUpdateThread(new
												 * Runnable() {
												 * 
												 * @Override public void run() {
												 * // TODO Auto-generated method
												 * stub text.detachSelf(); } });
												 * } }, new
												 * ParallelEntityModifier( new
												 * MoveModifier(1f,
												 * (CAMERA_WIDTH
												 * -text.getWidth())/2,
												 * (CAMERA_WIDTH
												 * -timeText.getWidth())/2,
												 * (CAMERA_HEIGHT
												 * -text.getHeight())/2, 0), new
												 * AlphaModifier(1f, 1, 0)), new
												 * ScaleModifier(1f, 1f, 0)));
												 */
												/*
												 * if(time<1000) {
												 * time=time+AddTime; }
												 */
												tmpstring = String
														.valueOf(time);
												timeText.setText(time/10+"");
												if(time<=TOTAL_Amount)
												{
												timebar.setWidth(190*time/TOTAL_Amount);
												timebar.setPosition(500+(float)44*(TOTAL_Amount-time)/TOTAL_Amount, 15);
												}
												else{
												timebar.setWidth(190);
												timebar.setPosition(500, 15);
												}
												// mMainScene.getLastChild().attachChild(text);
												red = obb_role.getRed();
												green = obb_role.getGreen();
												blue = obb_role.getBlue();
												backback2.setColor(red, green,
														blue);
												backback2.registerEntityModifier(new SequenceEntityModifier(
														new IEntityModifierListener() {
															@Override
															public void onModifierFinished(
																	IModifier<IEntity> pModifier,
																	IEntity pItem) {
																// TODO
																// Auto-generated
																// method stub
																JinjieMainGame.this
																		.runOnUpdateThread(new Runnable() {
																			@Override
																			public void run() {
																				// TODO
																				// Auto-generated
																				// method
																				// stub

																				backback1.setColor(
																						red,
																						green,
																						blue);
																			}
																		});
															}
														}, new ScaleModifier(
																0.5f, 0.1f, 1f)));
											}

											mMainScene
													.unregisterTouchArea(push1);
											mMainScene
													.unregisterTouchArea(push2);
											mMainScene
													.unregisterTouchArea(push3);
											mMainScene
													.unregisterTouchArea(push4);
											click = 0;
											isSupercancel = true;
											mHandler.postDelayed(
													refreshhandler, 100);
										}
									});
						}
					}, new ScaleModifier(0.1f, 1, 0.5f), new ScaleModifier(
							0.1f, 0.5f, 1)));

		}

	}

	private void stopAll() {

		if (task != null) {
			task.cancel();
		}
		if (timer != null) {
			timer.purge();
			timer.cancel();
		}

		mMainScene.unregisterTouchArea(push1);
		mMainScene.unregisterTouchArea(push2);
		mMainScene.unregisterTouchArea(push3);
		mMainScene.unregisterTouchArea(push4);

	}

	private void AddScore(final int i) {

		final Text addscore = new Text(0, 0, mscoreFont, "+" + i,
				HorizontalAlign.CENTER);
		addscore.setPosition((CAMERA_WIDTH - addscore.getWidth()) / 2,
				(CAMERA_HEIGHT - addscore.getHeight()) / 2);
		addscore.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub

						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								addscore.detachSelf();

							}
						});

					}
				}, new ParallelEntityModifier(new MoveModifier(1f,
						(CAMERA_WIDTH - addscore.getWidth()) / 2, (CAMERA_WIDTH - addscore.getWidth()) / 2,
						(CAMERA_HEIGHT - addscore.getHeight()) / 2, 0),
						new AlphaModifier(1f, 1, 0)), new ScaleModifier(1f, 1f,
						0)));
		mMainScene.getLastChild().attachChild(addscore);
		score = score + i;
		SetScore(score);

		if (score > pass) {
			mSoundplay(passsound);
			if (passnumber < 6) {
				passnumber++;
				pass = passcore[passnumber];
			} else {
				passnumber = 6;
			}

		}

	}

	@Override
	public void onResumeGame() {
		// TODO Auto-generated method stub
		super.onResumeGame();
		if (mEngine != null) {
			mEngine.start();
		}
		if (isSupercancel != null) {
			isSupercancel = false;
		}
	}

	@Override
	public void onPauseGame() {
		// TODO Auto-generated method stub
		super.onPauseGame();
		if (mEngine != null) {
			mEngine.stop();
		}
		if (isSupercancel != null) {
			isSupercancel = true;
		}
	}

	// 播放打击音效
	private Runnable mPlaySoundhandler = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// mSoundplay();
		}
	};

	// 播放音效函数
	private void mSoundplay(Sound mSound) {
		if (audioOPtions.getBoolean("effectsOn", true)) {
			mSound.play();
		}
	}

	public void setnumberPosition(int[] array) {
		for (int i = 0; i < array.length; i++) {

			if (array[i] == 1) {
				number[i].setTexturePosition(0, 0);
			} else if (array[i] == 2) {
				number[i].setTexturePosition(200, 0);
			} else if (array[i] == 3) {
				number[i].setTexturePosition(400, 0);
			} else if (array[i] == 4) {
				number[i].setTexturePosition(600, 0);
			} else {
				number[i].setTexturePosition(800, 200);
			}
		}
	}

	public void push1() {

		// mSoundplay(push1Sound);
		mSoundplay(myback[KISS_THE_RAIN[backnumber]]);
		if (++backnumber >= KISS_THE_RAIN.length) {
			backnumber = 1;
		}
		// 激光
		line1.setPosition(push1.getX() + push1.getWidth() / 2, push1.getY()
				+ push1.getHeight() / 2,
				tmp[click].getX() + tmp[click].getWidth() / 2,
				tmp[click].getY() + tmp[click].getHeight() / 2);
		// line1.setColor(random.nextInt(255)/255f, random.nextInt(255)/255f,
		// random.nextInt(255)/255f);
		line1.setColor(1, 1, 0);
		line1.setVisible(true);
		line1.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								line1.setVisible(false);
							}
						});
					}
				}, new ParallelEntityModifier(new AlphaModifier(0.2f, 0f, 1f))));
		if (array[click] == 1) {
			number[click].setTexturePosition(0, 200);
			clickWhat();
		} else {
			if (isSuper) {

				mMainScene.unregisterTouchArea(push1);
				mMainScene.unregisterTouchArea(push2);
				mMainScene.unregisterTouchArea(push3);
				mMainScene.unregisterTouchArea(push4);
				click = 0;
				mHandler.postDelayed(refreshhandler, 100);
			} else {
				if (--overtime > 0) {
					number[click].setTexturePosition(800, 200);
					pushwrongdialog();
				} else {
					saysprite.setVisible(true);
					saycheckTextureRegion.setTexturePosition(512,0);
					// 播放音效
					mSoundplay(gameoversound);

					stopAll();
					mHandler.removeCallbacks(refreshhandler);
					//停止旋转动画
					tmp[click].clearEntityModifiers();
					tmp[click]
							.registerEntityModifier(new SequenceEntityModifier(
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											JinjieMainGame.this
													.runOnUpdateThread(new Runnable() {
														@Override
														public void run() {
															number[click]
																	.setTexturePosition(
																			800,200);
														}
													});
										}
									}, new ParallelEntityModifier(
											new ScaleModifier(1, 0f, 1f),
											new AlphaModifier(1, 0f, 1f))));
					Mengwu.stopAnimation();
					
					mHandler.postDelayed(GameOverhandler, 2000);
				}
			}
		}
	}

	public void push2() {

		// mSoundplay(push2Sound);
		mSoundplay(myback[KISS_THE_RAIN[backnumber]]);
		if (++backnumber >= KISS_THE_RAIN.length) {
			backnumber = 1;
		}
		line2.setPosition(push2.getX() + push2.getWidth() / 2, push2.getY()
				+ push2.getHeight() / 2,
				tmp[click].getX() + tmp[click].getWidth() / 2,
				tmp[click].getY() + tmp[click].getHeight() / 2);
		// line2.setColor(random.nextInt(255)/255f, random.nextInt(255)/255f,
		// random.nextInt(255)/255f);
		line2.setColor(0, 1, 0);
		line2.setVisible(true);
		line2.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								line2.setVisible(false);
							}
						});
					}
				}, new ParallelEntityModifier(new AlphaModifier(0.2f, 0f, 1f))));
		if (array[click] == 2) {
			// 点击的第click换图片
			number[click].setTexturePosition(200, 200);
			clickWhat();
		} else {
			if (isSuper) {

				mMainScene.unregisterTouchArea(push1);
				mMainScene.unregisterTouchArea(push2);
				mMainScene.unregisterTouchArea(push3);
				mMainScene.unregisterTouchArea(push4);
				click = 0;
				mHandler.postDelayed(refreshhandler, 100);

			} else {
				if (--overtime > 0) {
					number[click].setTexturePosition(800, 200);
					pushwrongdialog();
				} else {
					saysprite.setVisible(true);
					saycheckTextureRegion.setTexturePosition(512,0);
					mSoundplay(gameoversound);

					stopAll();
					mHandler.removeCallbacks(refreshhandler);
					//停止旋转动画
					tmp[click].clearEntityModifiers();
					tmp[click]
							.registerEntityModifier(new SequenceEntityModifier(
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											JinjieMainGame.this
													.runOnUpdateThread(new Runnable() {
														@Override
														public void run() {
															number[click]
																	.setTexturePosition(
																			800,200);
														}
													});
										}
									}, new ParallelEntityModifier(
											new ScaleModifier(1, 0f, 1f),
											new AlphaModifier(1, 0f, 1f))));
					Mengwu.stopAnimation();
					
					mHandler.postDelayed(GameOverhandler, 2000);
				}
			}
		}
	}

	public void push3() {
		// 播放声音
		// mSoundplay(push3Sound);
		mSoundplay(myback[KISS_THE_RAIN[backnumber]]);
		if (++backnumber >= KISS_THE_RAIN.length) {
			backnumber = 1;
		}
		line3.setPosition(push3.getX() + push3.getWidth() / 2, push3.getY()
				+ push3.getHeight() / 2,
				tmp[click].getX() + tmp[click].getWidth() / 2,
				tmp[click].getY() + tmp[click].getHeight() / 2);
		// line3.setColor(random.nextInt(255)/255f, random.nextInt(255)/255f,
		// random.nextInt(255)/255f);
		line3.setColor(1, 0, 0);
		line3.setVisible(true);
		line3.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								line3.setVisible(false);
							}
						});
					}
				}, new ParallelEntityModifier(new AlphaModifier(0.2f, 0f, 1f))));
		if (array[click] == 3) {

			number[click].setTexturePosition(400, 200);
			clickWhat();
		} else {
			if (isSuper) {

				mMainScene.unregisterTouchArea(push1);
				mMainScene.unregisterTouchArea(push2);
				mMainScene.unregisterTouchArea(push3);
				mMainScene.unregisterTouchArea(push4);
				click = 0;
				mHandler.postDelayed(refreshhandler, 100);

			} else {
				if (--overtime > 0) {
					number[click].setTexturePosition(800, 200);
					pushwrongdialog();
				} else {
					saysprite.setVisible(true);
					saycheckTextureRegion.setTexturePosition(512,0);
					// 播放音效
					mSoundplay(gameoversound);
					stopAll();
					mHandler.removeCallbacks(refreshhandler);
					//停止旋转动画
					tmp[click].clearEntityModifiers();
					tmp[click]
							.registerEntityModifier(new SequenceEntityModifier(

							new IEntityModifierListener() {
								@Override
								public void onModifierFinished(
										IModifier<IEntity> pModifier,
										IEntity pItem) {
									// TODO Auto-generated method stub
									JinjieMainGame.this
											.runOnUpdateThread(new Runnable() {
												@Override
												public void run() {
													number[click]
															.setTexturePosition(
																	800,200);
												}
											});
								}
							}, new ParallelEntityModifier(new ScaleModifier(1,
									0f, 1f), new AlphaModifier(1, 0f, 1f))));
					Mengwu.stopAnimation();
					
					mHandler.postDelayed(GameOverhandler, 2000);
				}
			}
		}
	}

	public void push4() {

		// mSoundplay(push4Sound);
		mSoundplay(myback[KISS_THE_RAIN[backnumber]]);
		if (++backnumber >= KISS_THE_RAIN.length) {
			backnumber = 1;
		}
		line4.setPosition(push4.getX() + push4.getWidth() / 2, push4.getY()
				+ push4.getHeight() / 2,
				tmp[click].getX() + tmp[click].getWidth() / 2,
				tmp[click].getY() + tmp[click].getHeight() / 2);
		// line4.setColor(random.nextInt(255)/255f, random.nextInt(255)/255f,
		// random.nextInt(255)/255f);
		line4.setColor(0, 0, 1);
		line4.setVisible(true);
		line4.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								line4.setVisible(false);
							}
						});
					}
				}, new ParallelEntityModifier(new AlphaModifier(0.2f, 0f, 1f))));
		if (array[click] == 4) {
			number[click].setTexturePosition(600, 200);
			clickWhat();
		} else {
			if (isSuper) {

				mMainScene.unregisterTouchArea(push1);
				mMainScene.unregisterTouchArea(push2);
				mMainScene.unregisterTouchArea(push3);
				mMainScene.unregisterTouchArea(push4);
				click = 0;
				mHandler.postDelayed(refreshhandler, 100);

			} else {
				if (--overtime > 0) {
					number[click].setTexturePosition(800, 200);
					pushwrongdialog();
				} else {
					saysprite.setVisible(true);
					saycheckTextureRegion.setTexturePosition(512,0);
					// 播放音效
					mSoundplay(gameoversound);

					stopAll();
					mHandler.removeCallbacks(refreshhandler);
					//停止旋转动画
					tmp[click].clearEntityModifiers();
					tmp[click]
							.registerEntityModifier(new SequenceEntityModifier(
									new IEntityModifierListener() {
										@Override
										public void onModifierFinished(
												IModifier<IEntity> pModifier,
												IEntity pItem) {
											// TODO Auto-generated method stub
											JinjieMainGame.this
													.runOnUpdateThread(new Runnable() {
														@Override
														public void run() {
															number[click]
																	.setTexturePosition(
																			800,200);
														}
													});
										}
									}, new ParallelEntityModifier(
											new ScaleModifier(1, 0f, 1f),
											new AlphaModifier(1, 0f, 1f))));
					Mengwu.stopAnimation();
					
					mHandler.postDelayed(GameOverhandler, 2000);
				}
			}
		}
	}

	private void begainpush() {
		saysprite.setVisible(false);
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				timehandler.sendEmptyMessage(MSG_COUNTER_DOWN);
			}
		};
		timer.schedule(task, 0, UPDATE_INTERVAL);
		isbegin = true;
	}

	class ExampleGestureLisener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if (isloaded = true) {
				// 表示能领槽满
				if (my_role.getMysupernumber() >= my_role.getMyMaxsupernumber()) {
					float vx = Math.abs(velocityX);
					float vy = Math.abs(velocityY);
					if (vx > vy) {
						if (velocityX < 0) {

						} else {

						}
					} else {
						if (velocityY < 0) {
							/*
							 * isSuper=true;
							 * 
							 * mSoundplay(getinsuper); backimg2.setAlpha(1f);
							 * backimg2.setColor(0, 0, 0);
							 * 
							 * 
							 * mMainScene.unregisterTouchArea(push1);
							 * mMainScene.unregisterTouchArea(push2);
							 * mMainScene.unregisterTouchArea(push3);
							 * mMainScene.unregisterTouchArea(push4); click=0;
							 * 
							 * superdoublemore=1;
							 * mHandler.postDelayed(refreshhandler, 100);
							 * 
							 * supertime = Maxsupertime;
							 * tmpstring=String.valueOf(supertime);
							 * timeText.setText(tmpstring.substring(1,
							 * tmpstring.
							 * length()-1)+"."+tmpstring.substring(tmpstring
							 * .length()-1, tmpstring.length())+"秒") ;
							 * supertimer = new Timer(); supertask = new
							 * TimerTask() { public void run() {
							 * supertimehandler
							 * .sendEmptyMessage(MSG_COUNTER_DOWN); } };
							 * supertimer.schedule(supertask, 0,
							 * UPDATE_INTERVAL); my_role.setMysupernumber(0);
							 * time=TOTAL_Amount;
							 */
						} else {

						}

					}
				}
			}
			return true;
		}
	}

	public void pushwrongdialog() {
		final Text error = new Text(0, 0, mscoreFont, "错误-1",
				HorizontalAlign.CENTER);
		error.setPosition((CAMERA_WIDTH - error.getWidth()) / 2,
				(CAMERA_HEIGHT - error.getHeight()) / 2);
		error.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						JinjieMainGame.this.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								error.detachSelf();
							}
						});
					}
				}, new ParallelEntityModifier(new MoveModifier(1f,
						(CAMERA_WIDTH - error.getWidth()) / 2,
						(CAMERA_WIDTH - error.getWidth()) / 2,
						(CAMERA_HEIGHT - error.getHeight()) / 2, 0),
						new AlphaModifier(1f, 1, 0))));
		mMainScene.getLastChild().attachChild(error);
		timeText.setText(time/10+"");
		if(time<=TOTAL_Amount)
		{
		timebar.setWidth(190*time/TOTAL_Amount);
		timebar.setPosition(500+(float)44*(TOTAL_Amount-time)/TOTAL_Amount, 15);
		}
		else{
		timebar.setWidth(190);
		timebar.setPosition(500, 15);
		}
		red = obb_role.getRed();
		green = obb_role.getGreen();
		blue = obb_role.getBlue();
		backback2.setColor(red, green, blue);
		backback2.registerEntityModifier(new SequenceEntityModifier(
				new IEntityModifierListener() {
					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						// TODO Auto-generated method stub
						backback1.setColor(red, green, blue);

					}
				}, new ScaleModifier(0.5f, 0.1f, 1f)));
		mMainScene.unregisterTouchArea(push1);
		mMainScene.unregisterTouchArea(push2);
		mMainScene.unregisterTouchArea(push3);
		mMainScene.unregisterTouchArea(push4);
		click = 0;
		isSupercancel = true;
		mHandler.postDelayed(refreshhandler, 500);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			showCheckDialog("返回主菜单?");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 退出提示函数
	public void showCheckDialog(String message) {
		CustomDialog dialog;
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(
				JinjieMainGame.this);
		customBuilder
				.setTitle("返回主菜单?")
				.setMessage(message)
				.setPositiveButton("是的", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(JinjieMainGame.this,
								MainActivity.class);
						startActivity(intent);
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
	private void SetScore(int number) {
		// TODO Auto-generated method stub
		score1.setCurrentTileIndex((number/10000));
		score2.setCurrentTileIndex((number%10000)/1000);
		score3.setCurrentTileIndex((number%1000)/100);
		score4.setCurrentTileIndex((number%100)/10);
		score5.setCurrentTileIndex((number%10));
	}
}
