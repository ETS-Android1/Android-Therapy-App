package com.example.counselor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import javax.xml.namespace.NamespaceContext;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String USERNAME = "USERNAME";
    public static final String isPatient = "isPatient";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name, String a){
        editor.putBoolean(LOGIN, true);
        editor.putString(USERNAME, name);
        editor.putString(isPatient, a);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if(!this.isLoggin()){
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
            ((homeActivity) context).finish();
            ((settings) context).finish();
            ((UserList) context).finish();
            ((Conversations) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(isPatient, sharedPreferences.getString(isPatient, null));

        return user;

    }




    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
        ((settings) context).finish();
    }
    public void logout2(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
        ((profile) context).finish();
    }

}
