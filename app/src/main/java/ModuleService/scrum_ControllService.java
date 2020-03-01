package ModuleService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import andbook.example.protect_me_release.R;

public class scrum_ControllService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this, R.raw.scrum); // 비명데이터 셋팅
        mediaPlayer.setLooping(true); // 무한반복 셋팅
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                mediaPlayer.start(); // 미디어 시작
            }
        }).start();

        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        if(mediaPlayer != null){
            mediaPlayer.release(); // 미디어 해제
            mediaPlayer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}