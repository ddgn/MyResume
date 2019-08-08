package myresume.diego.managerdnol.com.myresume.resource.util.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import myresume.diego.managerdnol.com.myresume.resource.util.listener.ParseLoadListener;
import myresume.diego.managerdnol.com.myresume.resource.util.constants.Constants;
import myresume.diego.managerdnol.com.myresume.resource.util.parser.JsonParser;
import myresume.diego.managerdnol.com.myresume.resource.util.ws.WebServiceCommunication;

public class InfoLoadingTask extends AsyncTask<Integer, String, String> {
    ProgressDialog dialog;
    Context context;
    int param;
    public ParseLoadListener parseLoadListener;

    public InfoLoadingTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Obteniendo información");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(Integer... ints) {
         param = ints[0];
        switch (param) {
            case 1:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.PERFIL_URL);
            case 2:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.EDUCACION_URL);
            case 3:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.LABORAL_URL);
            case 4:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.SKILLS_URL);
            case 5:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.CERTIFICADOS_URL);
            case 6:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.LENGUAJES_URL);
            case 7:
                return WebServiceCommunication.getRequest(Constants.BASE_URL + Constants.HOBBIES_URL);
            default:
                return "";
        }

    }

    @Override
    protected void onPostExecute(String response) {
        dialog.dismiss();
        try {
            parseLoadListener.processFinish(JsonParser.parseObject(response, param));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

