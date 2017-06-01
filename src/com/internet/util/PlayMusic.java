package com.internet.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class PlayMusic implements OnCompletionListener {
	private final float VOLUME = (float) 0.8;
	private volatile boolean isPlaying = false;

	private Context mContext;
	private MediaPlayer mediaPlayer;
	private Play mPlay;

	private static PlayMusic init;

	public static PlayMusic getInstance(Context context) {
		if (init != null)
			return init;
		init = new PlayMusic(context);
		return init;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.release();
		isPlaying = false;
	}

	private PlayMusic(Context context) {
		mContext = context;
	}

	public synchronized void play(int resId) {
		if (isPlaying)
			return;
		if (mPlay == null)
			mPlay = new Play();
		mPlay.setResId(resId);
		new Thread(mPlay).start();
		isPlaying = true;
	}

	private class Play implements Runnable {
		int resId;

		public Play() {
			// TODO Auto-generated constructor stub
		}

		public void setResId(int resId) {
			this.resId = resId;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				mediaPlayer = MediaPlayer.create(mContext, resId);
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setVolume(VOLUME, VOLUME);
				mediaPlayer.setOnCompletionListener(PlayMusic.this);
				if (mediaPlayer != null)
					mediaPlayer.stop();
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (Exception e) {
				e.printStackTrace();
				isPlaying = false;
			}
		}

	}
}
