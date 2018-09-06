package app.task.com.softTsk.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.task.com.softTsk.R;

/**
 * Created by Rao.
 */
public class Util {

    public static final String PREFRENCES_NAME = "Softvison_pref";

    public static final String TAG = "Softvison";

    /**
     * Description: To hideKeyboard when onTouch of view.
     */
    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Description: To hideKeyboard when onTouch of view.
     */
    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * format float value into two decimal places
     *
     * @param inValue
     * @return
     */
    public static String decFormat(float inValue) {
        String shortString = "";
        DecimalFormat twoDec = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        shortString = (twoDec.format(inValue));
        return shortString;
    }

    /**
     * Description : This method formats the String data empty, null into Hyphen char.
     *
     * @param data
     * @return string data with formated.
     */
    public static String formatN2H(String data) {
        if (data == null || data.trim().equals("") || data.equals("null")) {
            data = "";
        }
        return data;
    }

    /**
     * Description : Converts text password to "SHA256" Format.
     *
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String hashingSHA256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();

        byte[] byteData = digest.digest(input.getBytes("UTF-8"));
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     * Formatting the date
     *
     * @param value
     * @return
     */
    public static String appendZero(int value) {
        String val = value + "";
        if (val.length() == 1) {
            val = "00" + val;
        }
        return val;
    }

    /**
     * Split the url
     *
     * @param urlHost
     * @return
     */
    public static String[] stringSeparator(String urlHost) {
        String[] separated = urlHost.split("/");
        String myFile = separated[separated.length - 1];
        String[] splitMe = myFile.split("\\.");

        return splitMe;
    }

    /**
     * @param rootView
     * @return
     */
    public static boolean isKeyboardShown(View rootView) {
        /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
        final int SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;

        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
        int heightDiff = rootView.getBottom() - r.bottom;
        /* Threshold size: dp to pixels, multiply with display density */
        boolean isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density;

        //Log.d("TAG", "isKeyboardShown ? " + isKeyboardShown + ", heightDiff:" + heightDiff + ", density:" + dm.density
        //	+ "root view height:" + rootView.getHeight() + ", rect:" + r);

        return isKeyboardShown;
    }

    /**
     * @param inputFormat
     * @param outputFormat
     * @param inputDate
     * @return
     */
    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (java.text.ParseException e) {
            Log.e("TAG", "ParseException - dateFormat");
        }

        return outputDate;

    }

    public static String firstLetterToUpperCase(String convertText) {

        StringBuilder rackingSystemSb = new StringBuilder(convertText.toLowerCase());
        rackingSystemSb.setCharAt(0, Character.toUpperCase(rackingSystemSb.charAt(0)));
        convertText = rackingSystemSb.toString();

        return convertText;
    }



    /**
     * Description : Progress Dialog
     *
     * @param activity
     */
    public ProgressDialog waitingMessage(Activity activity) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage(activity.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        return pd;
    }

    public static String keyHashgenrate(Activity activity) {
        String keyHash = null;
        try {
            Log.i("keyHashgenrate", activity.getPackageName());
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return keyHash;
    }


    //Storing vals to shared preferences for future usage
    public static void storeToPrefrencesBoolean(Context context, String type, Boolean vid) {

        try {
            SharedPreferences preferences = context.getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(type, vid);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Gets stored data from prefrences
    public static Boolean getFromPrefrencesBoolean(Context context, String type) {
        SharedPreferences preferences = context.getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(type, false);
    }


    //Storing vals to shared preferences for future usage
    public static void storeToPrefrences(Context context, String type, String vid) {

        try {
            SharedPreferences preferences = context.getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(type, vid);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Gets stored data from prefrences
    public static String getFromPrefrences(Context context, String type) {
        SharedPreferences preferences = context.getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getString(type, "");
    }

    public static Spannable setActionBarText(String actionBarText) {
        Spannable text = new SpannableString(actionBarText);
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return text;
    }












}
