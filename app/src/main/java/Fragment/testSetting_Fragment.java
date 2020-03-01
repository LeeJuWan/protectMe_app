package Fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import Alarm_Utill.AlarmChannels;
import andbook.example.protect_me_release.TestAll_SOSActivity;
import andbook.example.protect_me_release.TestCallActivity;
import andbook.example.protect_me_release.R;


public class testSetting_Fragment extends Fragment {

    private ImageButton
            alarmTest_btn,
            systemSetting_btn,
            test_Call_btn,
            test_SOS_btn;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testsetting, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstatanceState) {
        super.onActivityCreated(savedInstatanceState);

        Initialize();

        // 알람 시스템 셋팅
        systemSetting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    goToNotificationSetting();
                }else{
                    AlarmChannels.sendNotification_notOreo(getActivity());
                }
            }
        });

        alarmTest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    AlarmChannels.sendNotification(getActivity(), (int) (System.currentTimeMillis() / 1000), AlarmChannels.Channel.MESSAGE_ID);
                } else {
                    AlarmChannels.sendNotification_notOreo(getActivity());
                }
            }
        });

        // 테스트 신고전화
        test_Call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test_call = new Intent(getActivity(), TestCallActivity.class);
                startActivity(Test_call);
            }
        });

        test_SOS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Test_sos = new Intent(getActivity(), TestAll_SOSActivity.class);
                startActivity(Test_sos);
            }
        });

    }

    // 시스템 셋팅으로 이동하는 함수
    @TargetApi(Build.VERSION_CODES.O)
    private void goToNotificationSetting(){
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE,getActivity().getPackageName());
        startActivity(intent);
    }

    private void Initialize(){
        alarmTest_btn = getView().findViewById(R.id.AlarmTest);
        systemSetting_btn = getView().findViewById(R.id.SystemSetting);
        test_Call_btn = getView().findViewById(R.id.Test_Call_112);
        test_SOS_btn = getView().findViewById(R.id.TestSOS);
    }
}
