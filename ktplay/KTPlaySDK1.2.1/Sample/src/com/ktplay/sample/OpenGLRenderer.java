package com.ktplay.sample;

import javax.microedition.khronos.egl.EGLConfig;

import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import com.ktplay.sample.R;

/**
 * 此类仅用于辅助演示KT在OpenGL中的截图功能,接入过程中请忽略。
 */
public class OpenGLRenderer implements Renderer {

	private Context mContext  ;
	private int mTexture;
 	 
	public OpenGLRenderer(Context context) {
		mContext = context;
	}
 	 public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                          GL10.GL_NICEST);
	
		Resources res = mContext.getResources();
		this.mTexture = GraphicUtil.loadTexture(gl, res, R.drawable.bg);

	}
	
	public void onDrawFrame(GL10 gl) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); 

		drawBackgroundImage(gl,mTexture);

	}
	
	public void drawBackgroundImage(GL10 gl, int textureId) {
		gl.glLoadIdentity();
		gl.glCullFace(GL10.GL_BACK);
		gl.glPushMatrix();

		gl.glTranslatef(0, 0,-20);
		gl.glScalef(4f, 4f, 1f);

		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);

		GraphicUtil.drawTexture(gl, 0.0f, 0.0f, 8.0f, 8.0f,
				textureId, 1.0f, 1.0f, 1.0f, 1.0f);
		gl.glPopMatrix();
		gl.glDisable(GL10.GL_DEPTH_TEST);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f,(float) width / (float) height,0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}