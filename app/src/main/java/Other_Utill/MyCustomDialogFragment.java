package Other_Utill;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import andbook.example.protect_me_release.R;

public class MyCustomDialogFragment extends DialogFragment {

    public static final String DIALOG_MESSAGE = "dialogMessage";
    public static final String POSITIVE_BUTTON_APP_FINISH = "positiveButtonAppFinish";

    String dialogMessage = "not set";

    @Override
    public Dialog onCreateDialog(Bundle saveInstatnceState){

        Bundle bundle = getArguments();
        if(bundle != null){
            dialogMessage = bundle.getString(DIALOG_MESSAGE);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(dialogMessage)
                .setTitle("Title Test")
                .setIcon(R.drawable.icon96)
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

}