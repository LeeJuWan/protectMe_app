package ModelFile;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;


public class Audio extends Service {

    private String fileName;
    private MediaRecorder recorder;
    private String SDcard;


    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId){
        //파일 경로 및 이름 지정
        SDcard= Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName=SDcard+"/"+System.currentTimeMillis()+".mp3";

        @SuppressLint("HandlerLeak") final Handler handler1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(), "녹음시작(나를 지켜줘)", Toast.LENGTH_SHORT).show();
                super.handleMessage(msg);
            }
        };

        @SuppressLint("HandlerLeak") final Handler handler2 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(getApplicationContext(), "녹음완료 및 저장완료(나를 지켜줘)", Toast.LENGTH_SHORT).show();
                super.handleMessage(msg);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//오디오 입력 셋팅
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //출력 파일 확장자 셋팅
                    recorder.setAudioSamplingRate(44100); //샘플링 비율 셋팅
                    recorder.setAudioEncodingBitRate(96000); //비트레이트 셋팅
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //인코딩 코덱 셋팅
                    recorder.setOutputFile(fileName); //오디오 파일 출력 셋팅
                    try{
                        recorder.prepare(); //레코더셋팅
                        recorder.start(); //레코더 시작
                        handler1.sendEmptyMessage(0);
                        Thread.sleep(5000); //5초 동안 녹음 (메인 기능 오디오는 5~10분 녹음
                        recorder.stop(); //레코더 중지
                        handler2.sendEmptyMessage(0);
                    } catch (IOException e) {
                        System.err.print("IOException error");
                    }
                }catch (InterruptedException e){
                    System.err.print("InterruptedException error");
                }
                if(recorder != null){
                    recorder.release(); //레코더 해제
                    recorder = null;
                }
            }
        });
        thread.start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
