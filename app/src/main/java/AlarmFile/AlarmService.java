package AlarmFile;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import andbook.example.protect_me_release.PermissionCheckAcitivty;

public class AlarmService extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            AlarmChannels.sendNotification(context, (int) (System.currentTimeMillis() / 1000), AlarmChannels.Channel.MESSAGE_ID);
        else
            AlarmChannels.sendNotification_notOreo(context);


        //throw new UnsupportedOperationException("Not yet implemented");
    }

}
