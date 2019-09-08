package AlarmFile;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class BootReceiver extends BroadcastReceiver {

    private AlarmManager alarmManager;
    private Intent Alarm_set;
    private Calendar calendar;
    private PendingIntent pendingIntent;


    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Alarm_set = new Intent(context, AlarmReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(context, 0, Alarm_set, 0);
            calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 45, 0);
            //알람 예약
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                Toast.makeText(context, "예방알람 시간 셋팅 완료", Toast.LENGTH_SHORT).show();
            }

        }

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
