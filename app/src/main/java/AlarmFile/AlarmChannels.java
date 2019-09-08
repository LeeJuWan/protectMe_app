package AlarmFile;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import andbook.example.protect_me_release.R;

public class AlarmChannels {

    private static Notification.Builder builder; //푸시알람 빌더 생성
    private static NotificationManager notificationManager; //푸시 알람 매니저 생성
    private static Notification.BigTextStyle bigTextStyle; //푸시 알람 핀치줌을 위한 텍스트 스타일 생성

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ //알람채널을 위한 ID 및 상세 메시지 생성
            Channel.GROUP_ID,
            Channel.GROUP_NAME,
            Channel.MESSAGE_ID,
            Channel.MESSAGE_NAME,
            Channel.MESSAGE_DESC})
    public @interface  Channel{
        //채널 그룹
        String GROUP_ID = "Protect_Me",
                GROUP_NAME = "나를 지켜줘";
        //메시지 채널
        String MESSAGE_ID ="message",
                MESSAGE_NAME = "예방 메시지",
                MESSAGE_DESC = "매 시간마다 예방 메시지를 받을 수 있습니다.";
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static void createChannel(Context context) {

        //채널 그룹 생성
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannelGroup group = new NotificationChannelGroup(
                Channel.GROUP_ID, Channel.GROUP_ID);
        notificationManager.createNotificationChannelGroup(group);

        //채널 생성: 메시지
        NotificationChannel channelMessage = new NotificationChannel(
                Channel.MESSAGE_ID, Channel.MESSAGE_NAME,
                android.app.NotificationManager.IMPORTANCE_DEFAULT);
        channelMessage.setDescription(Channel.MESSAGE_DESC); //채널 설명
        channelMessage.setGroup(Channel.GROUP_ID); //속해있는 채널 그룹 ID
        channelMessage.enableLights(true); // LED 점멸 기능
        channelMessage.setLightColor(Color.RED); // LED 색상(적색)
        channelMessage.enableVibration(true); //진동 가능
        notificationManager.createNotificationChannel(channelMessage);
    }



    /*
     * 알림 채널을 삭제한다.
     *
     * */
    @TargetApi(Build.VERSION_CODES.O)
    public static void deleteChannel(Context context, @Channel String id){
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.deleteNotificationChannel(id);
    }

   
    //오레오 이상 버전
    @TargetApi(Build.VERSION_CODES.O)
    public static  void sendNotification(Context context, int id, @Channel String channel){
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder((Context) context,channel)
                .setTicker("현재 시간은 성범죄 발생빈도가 높은 시간대 입니다." +
                        "넓은 도로 및 밝은 도로로 통하여 안전 귀가하세요. 감사합니다.")
                .setSmallIcon(R.drawable.icon48)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon96))
                .setWhen(System.currentTimeMillis())
                .setContentTitle("나를 지켜줘")
                .setContentText("현재 시간은 성범죄 발생빈도가 높은 시간대 입니다." +
                        "넓은 도로 및 밝은 도로로 통하여 안전 귀가하세요. 감사합니다.")
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        bigTextStyle= new Notification.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle("나를 지켜줘 예방 알람");
        bigTextStyle.bigText("현재 시간은 성범죄 발생빈도가 높은 시간대 입니다." +
                "넓은 도로 및 밝은 도로로 통하여 안전 귀가하세요. 감사합니다.");
        notificationManager.notify(id,builder.build());
    }

    //오레오 미만 버전
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void sendNotification_notOreo(Context context){
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context)
                .setTicker("현재 시간은 성범죄 발생빈도가 높은 시간대 입니다." +
                        "넓은 도로 및 밝은 도로로 통하여 안전 귀가하세요. 감사합니다.")
                .setSmallIcon(R.drawable.icon48)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon96))
                .setWhen(System.currentTimeMillis())
                .setContentTitle("나를 지켜줘")
                .setContentText("현재 시간은 성범죄 발생빈도가 높은 시간대 입니다." +
                        "넓은 도로 및 밝은 도로로 통하여 안전 귀가하세요. 감사합니다.")
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        bigTextStyle= new Notification.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle("나를 지켜줘 예방 알람");
        bigTextStyle.bigText("현재 시간은 성범죄 발생빈도가 높은 시간대 입니다." +
                "넓은 도로 및 밝은 도로로 통하여 안전 귀가하세요. 감사합니다.");
        notificationManager.notify((int) (System.currentTimeMillis() / 1000),builder.build());
    }
}
