package a276.cmpt.mineseeker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

//class containing alert dialog when game is won
public class MessageFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.message_layout, null);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                getActivity().finish();
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Whoa!")
                .setView(v)
                .setPositiveButton(R.string.close_game, listener)
                .create();
    }
}
