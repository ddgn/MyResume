package myresume.diego.managerdnol.com.myresume.resource.util.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class CustomDialog {

    public static AlertDialog crearDialogo(String titulo, String texto, View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(texto);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return alertDialog;
    }
}
