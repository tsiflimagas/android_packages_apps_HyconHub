/*
 * Copyright (C) 2020 The exTHmUI Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hycon.settings.fragments;

import com.android.internal.logging.nano.MetricsProto;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.SwitchPreference;
import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import java.util.ArrayList;

import com.hycon.settings.preferences.PackageListPreference;

import lineageos.hardware.LineageHardwareManager;

public class GamingModeSettings extends SettingsPreferenceFragment {

    private SwitchPreference mHardwareKeysDisable;
    private PackageListPreference mGamingPrefList;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.gaming);
        
        final PreferenceScreen prefScreen = getPreferenceScreen();

        mHardwareKeysDisable = findPreference(Settings.System.GAMING_MODE_DISABLE_HW_KEYS);
        LineageHardwareManager mLineageHardware = LineageHardwareManager.getInstance(getActivity());
        if (!mLineageHardware.isSupported(LineageHardwareManager.FEATURE_KEY_DISABLE)) {
            prefScreen.removePreference(mHardwareKeysDisable);
        }

        mGamingPrefList = (PackageListPreference) findPreference("gaming_mode_app_list");
        mGamingPrefList.setRemovedListKey(Settings.System.GAMING_MODE_REMOVED_APP_LIST);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CUSTOM_SETTINGS;
    }
}
