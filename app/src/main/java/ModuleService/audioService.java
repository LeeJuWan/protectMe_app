package ModuleService;

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


public class audioService extends Service {

    private String fileName;
    private MediaRecorder recorder;
    private String SDcard;


    @Override
    public void onCreate(){
        super.onCreate();

        fileName = null;
        recorder = new MediaRecorder();
        SDcard = null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId){
        // 파일 경로 및 이름 지정
        SDcard= Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName =SDcard+"/"+System.currentTimeMillis()+"나를지켜줘.mp3";

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

        new Thread (new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 오디오 입력 셋팅
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // 출력 파일 확장자 셋팅
                    recorder.setAudioSamplingRate(44100); // 샘플링 비율 셋팅
                    recorder.setAudioEncodingBitRate(96000); // 비트레이트 셋팅
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // 인코딩 코덱 셋팅
                    recorder.setOutputFile(String.valueOf(fileName)); // 오디오 파일 출력 셋팅


                    recorder.prepare(); // 레코더셋팅
                    recorder.start(); // 레코더 시작
                    handler1.sendEmptyMessage(0);
                    Thread.sleep(5000); // 5 초 동안 녹음 (메인 기능 오디오는 5~10분 녹음)
                    recorder.stop(); // 레코더 중지
                    handler2.sendEmptyMessage(0);
                }catch (InterruptedException e){
                    System.err.print("audioService InterruptedException error");
                } catch (IOException e) {
                    System.err.print("audioService IOException error");
                } finally {
                    if(recorder != null){
                        recorder.release(); // 레코더 해제
                        recorder = null;
                    }
                    stopSelf(); // 서비스 내부에서 서비스 종료
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
