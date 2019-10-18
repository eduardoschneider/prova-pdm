package br.edu.iftm.pdm.prova.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import br.edu.iftm.pdm.prova.R;

public class ReportRemoveDialogFragment extends AppCompatDialogFragment {

    public interface OnContactRemoveListener {
        void onContactRemove();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.are_you_sure_remove)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(getActivity() instanceof OnContactRemoveListener) {
                            OnContactRemoveListener listener = (OnContactRemoveListener) getActivity();
                            listener.onContactRemove();
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
