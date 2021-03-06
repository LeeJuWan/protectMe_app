package andbook.example.protect_me_release;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

public class SplashActivity extends Activity {

    protected void onCreate(Bundle savedInstanceStat){
        super.onCreate(savedInstanceStat);
        try{
            Thread.sleep(2000); //대기초 설정

        }catch (InterruptedException e){
            System.err.println("SplashActivity InterruptedException error");
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED||
                ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)) {
            // 마시멜로우 이상 부터 권한 체크 진행
            startActivity(new Intent(getApplicationContext(), PermissionCheckAcitivty.class)); // 권한 미허용으로 인해 '권한 허용' 액티비티 연결
            finish();
        }
        else{
            startActivity(new Intent(getApplicationContext(),MainActivity.class)); // 모든 권한 허용으로 인해 MainActivity 연결
            finish(); // 해당 액티비티 종료
        }
    }
}
