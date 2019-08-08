package myresume.diego.managerdnol.com.myresume.resource.util.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import myresume.diego.managerdnol.com.myresume.resource.util.constants.Constants;
import myresume.diego.managerdnol.com.myresume.resource.util.img.BitmapManager;

public class SavePreferences {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    public static void saveProfilePicture(Context context, Bitmap bitmap){
        sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Constants.PREFS_KEY, BitmapManager.encodeTobase64(bitmap));
        editor.commit();
    }

    public static String getSavedProfilePicture (Context context){
        sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Constants.PREFS_KEY, null);
    }

}
