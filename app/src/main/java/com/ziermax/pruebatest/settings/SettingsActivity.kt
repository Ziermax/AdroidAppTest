package com.ziermax.pruebatest.settings

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ziermax.pruebatest.R
import com.ziermax.pruebatest.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

	companion object {
		const val KEY_VOLUME_LVL = "volumeLvl"
		const val KEY_BLUETOOTH = "bluetooth"
		const val KEY_VIBRATION = "vibration"
		const val KEY_DARK_MODE = "darkMode"
	}

	private lateinit var binding: ActivitySettingsBinding
	private var firstTimeSettings:Boolean = true
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
//		enableEdgeToEdge()
		binding = ActivitySettingsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		CoroutineScope(Dispatchers.IO).launch {
			getSettings().filter { firstTimeSettings }.collect{ settingsModel ->
				if (settingsModel != null) {
					runOnUiThread {
						binding.switchVibration.isChecked = settingsModel.vibration
						binding.switchBluetooth.isChecked = settingsModel.bluetooth
						binding.switchDarkMode.isChecked = settingsModel.darkMode
						binding.rsVolume.setValues(settingsModel.volume.toFloat())
						firstTimeSettings = !firstTimeSettings
					}
				}
			}
		}

		initUi()
	}

	private fun initUi() {

		binding.rsVolume.addOnChangeListener { _, value, _ ->
			CoroutineScope(Dispatchers.IO).launch {
				saveVolume(value.toInt())
			}
		}

		binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
			CoroutineScope(Dispatchers.IO).launch {
				saveOptions(KEY_BLUETOOTH, value)
			}
		}

		binding.switchVibration.setOnCheckedChangeListener { _, value ->
			CoroutineScope(Dispatchers.IO).launch {
				saveOptions(KEY_VIBRATION, value)
			}
		}

		binding.switchDarkMode.setOnCheckedChangeListener { _, value ->
			if (value == true){
				enableDarkMode()
			} else {
				disableDarkMode()
			}
			CoroutineScope(Dispatchers.IO).launch {
				saveOptions(KEY_DARK_MODE, value)
			}
		}

	}

	private fun getSettings(): Flow<SettingsModel> {
		val flowSettings: Flow<SettingsModel> = dataStore.data.map { preferences ->
			SettingsModel(
				volume = preferences[intPreferencesKey(KEY_VOLUME_LVL)] ?: 50,
				vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true,
				darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
				bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false
			)
		}
		return (flowSettings)
	}

	private suspend fun saveVolume(level: Int) {
		dataStore.edit { preferences ->
			preferences[intPreferencesKey(KEY_VOLUME_LVL)] = level
		}
	}

	private suspend fun saveOptions(key: String, value: Boolean) {
		dataStore.edit { preferences ->
			preferences[booleanPreferencesKey(key)] = value
		}
	}

	private fun enableDarkMode() {
		AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
		delegate.applyDayNight()
	}

	private fun disableDarkMode() {
		AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
		delegate.applyDayNight()
	}

}
/*		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}*/