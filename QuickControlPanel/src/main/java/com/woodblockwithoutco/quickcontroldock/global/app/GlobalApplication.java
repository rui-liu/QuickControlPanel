/*******************************************************************************
 * Copyright 2014 Alexander Leontyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.woodblockwithoutco.quickcontroldock.global.app;



import org.acra.*;
import org.acra.sender.HttpSender;
import org.acra.annotation.*;

import com.woodblockwithoutco.quickcontroldock.global.holder.ConstantHolder;
import com.woodblockwithoutco.quickcontroldock.prefs.Keys;
import com.woodblockwithoutco.quickcontroldock.prefs.resolvers.LaunchResolver;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

public class GlobalApplication extends Application {

	private static Context sContext;
	private static final String KEY_VERSION_NEW = "jajsfhasgh!wwwsa875s";

	@Override
	public void onCreate() {
		super.onCreate();
		if(!ConstantHolder.getIsDebug()) ACRA.init(this);
		sContext = getApplicationContext();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(sContext);
		clearLegacyVersion(prefs);
		applyArtworkSizeBugFix(prefs);
		initEntryPointsState();
		
	}

	public static Context getGlobalContext() {
		return sContext;
	}
	
	private void clearLegacyVersion(SharedPreferences prefs) {
		if(!prefs.contains(KEY_VERSION_NEW)) {
			prefs.
			edit().
			clear().
			commit();

			prefs.
			edit().
			putBoolean(KEY_VERSION_NEW, true).
			commit();
		}
	}
	
	private void initEntryPointsState() {
		PackageManager pm  = getApplicationContext().getPackageManager();
		ComponentName compName = new ComponentName(getApplicationContext(), AssistantLauncherActivity.class);
		int state = 0;
		if(LaunchResolver.isGoogleNowGestureEnabled(getApplicationContext()) && LaunchResolver.isServiceEnabled(getApplicationContext())) {
			state = PackageManager.COMPONENT_ENABLED_STATE_DEFAULT;
		} else {
			state = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
		}
		pm.setComponentEnabledSetting(compName, state, PackageManager.DONT_KILL_APP);
		
		if(LaunchResolver.isServiceEnabled(getApplicationContext()) && LaunchResolver.isShortcutLaunchEnabled(getApplicationContext())) {
			state = PackageManager.COMPONENT_ENABLED_STATE_DEFAULT; 
		} else {
			state = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
		}
		compName = new ComponentName(getApplicationContext(), LauncherActivity.class);
		pm.setComponentEnabledSetting(compName, state, PackageManager.DONT_KILL_APP);
	}
	
	private void applyArtworkSizeBugFix(SharedPreferences prefs) {
		if(!prefs.contains("fix_artwork_size_bug")) {
			prefs.edit().putBoolean("fix_artwork_size_bug", true).commit();
			prefs.edit().putInt(Keys.Music.ARTWORK_SIZE, 120).commit();
		}
	}



}
