package myresume.diego.managerdnol.com.myresume.resource.util.ws;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class WebServiceCommunication {
    private static final String PROTOCOL_HTTP = "http";
    private static final String PROTOCOL_HTTPS = "https";
    private static final String REQUEST_PROPERY_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

    private static final int CONNECTION_TIMEOUT = 60000;
    private static final int READ_TIMEOUT = 60000;

    public static String getRequest(String urlAdress){
        String returnString = null;
        URL url = null;
        URLConnection urlConnection = null;

        try{
            url = new URL(urlAdress);
            urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(READ_TIMEOUT);

            String responseString = getResponseStringFromConnection(urlConnection);

                returnString = responseString;

        } catch (Exception e) {

            if (e instanceof SocketTimeoutException) {
                returnString = null;
            }
        } finally {
            if (url != null && urlConnection != null) {
                switch (url.getProtocol()) {
                    case PROTOCOL_HTTP:
                        ((HttpURLConnection) urlConnection).disconnect();
                        break;
                    case PROTOCOL_HTTPS:
                        ((HttpsURLConnection) urlConnection).disconnect();
                        break;
                }
            }
        }
        return returnString;
    }

    private static String getResponseStringFromConnection(final URLConnection urlConnection) {
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        String returnString = null;

        try {
            inputStream = urlConnection.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            StringBuilder stringBuilder = new StringBuilder();

            int bytesRead;
            byte[] bytes = new byte[1024];
            while ((bytesRead = bufferedInputStream.read(bytes)) > -1) {
                stringBuilder.append(new String(bytes, 0, bytesRead));
            }

            returnString = stringBuilder.toString();
        } catch (IOException e) {

            if (e instanceof SocketTimeoutException) {
                returnString = null;
            }
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }

        return returnString;
    }

    static public boolean isNetworkConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.getState().equals(NetworkInfo.State.CONNECTED)){
            return true;
        }else{
            return false;
        }
    }

}
