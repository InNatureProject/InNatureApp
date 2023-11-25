package joao.nicolly.daianny.elisa.util;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Classe de suporte as configurações da app
 */
public class Config {

    // endereço base do servidor web
    public static String INNATURE_URL = "https://busy-ruby-shrimp-yoke.cyclic.app/";

    /**
     * Salva o login no espaço reservado da app
     * @param context contexto da app
     * @param login o login
     */
    public static void setEmail(Context context, String login) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("login", login).commit();
    }

    /**
     * Obtem o login
     * @param context
     * @return
     */
    public static String getEmail(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("login", "");
    }

    /**
     * @param context
     * @param password
     */
    public static void setPassword(Context context, String password) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("password", password).commit();
    }

    /**
     * @param context
     * @return
     */
    public static String getPassword(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("password", "");
    }
    public static void setName(Context context, String name){
        SharedPreferences mPrefs = context.getSharedPreferences("configs",0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("name", name).commit();
    }
    public static String getName(Context context){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("name","");
    }

    public static void setTolken(Context context,String tolken){
        SharedPreferences mPrefs = context.getSharedPreferences("configs",0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("tolken",tolken).commit();
    }
    public  static String getTolken(Context context){
        SharedPreferences mPrefs = context.getSharedPreferences("configs",0);
        return  mPrefs.getString("tolken","");
    }
}
