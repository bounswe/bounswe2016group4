package com.knowwhatwoueat.kwue.Activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.knowwhatwoueat.kwue.R;

import java.util.List;

/**
 * TODO:User preferences must be set. And the preferences probably be hold at database
 * set the connection.
 *
 *
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onBuildHeaders(List<Header> target){
        loadHeadersFromResource(R.xml.preference_headers,target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SystemPref.class.getName().equals(fragmentName) || UserPref.class.getName().equals(fragmentName);
    }

    public static class SystemPref extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.system_preferences);

        }
    }

    public static class UserPref extends  PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.user_preferences);
        }
    }

}
