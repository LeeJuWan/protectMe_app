package ModuleService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import andbook.example.protect_me_release.R;

public class scrum_noControllService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this, R.raw.scrum); //비명데이터 셋팅
        mediaPlayer.setLooping(true); //무한반복 셋팅
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    mediaPlayer.start(); // 미디어 시작
                    Thread.sleep(5000); // 2초 슬립하여 2초후에 비명 소리 정지
                    mediaPlayer.stop(); // 미디어 종료
                } catch (InterruptedException e) {
                    System.err.println("scrum_noCotrollService InterruptedException error");
                }finally {
                    if(mediaPlayer != null){
                        mediaPlayer.release(); // 미디어 해제
                        mediaPlayer = null;
                    }
                    stopSelf();
                }
            }
        }).start();

        return super.onStartCommand(intent,flags,startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
