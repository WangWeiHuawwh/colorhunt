package com.ktplay.sample;

import android.content.Context;
import android.opengl.GLSurfaceView;

import android.util.AttributeSet;

/**
 * 此类仅用于辅助演示KT在OpenGL中的截图功能,接入过程中请忽略。
 */
class BasicGLSurfaceView extends GLSurfaceView {
    public BasicGLSurfaceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        setRenderer(new OpenGLRenderer(context));
    }
}

