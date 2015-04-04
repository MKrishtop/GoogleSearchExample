package com.mikhailkrishtop.googlesearchexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mikhailkrishtop.googlesearchexample.data.internal.StringPreference;
import com.mikhailkrishtop.googlesearchexample.ui.MainActivity;
import com.mikhailkrishtop.googlesearchexample.ui.SearchOptionsItemSelectedStrategy;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by mykhailo on 4/4/15.
 */
public class DebugSearchOptionsItemSelectedStrategy implements SearchOptionsItemSelectedStrategy {

    @InjectView(R.id.mock_mode_cb) CheckBox mockModeCb;
    @InjectView(R.id.engine_id_et) EditText engineIdEt;
    @InjectView(R.id.api_key_et) EditText apiKeyEt;

    private final StringPreference dataSource;
    private final StringPreference engineId;
    private final StringPreference apiKey;

    @Inject public DebugSearchOptionsItemSelectedStrategy(
            @Named("dataSource") StringPreference dataSource,
            @Named("engineId")StringPreference engineId,
            @Named("apiKey") StringPreference apiKey) {

        this.apiKey = apiKey;
        this.engineId = engineId;
        this.dataSource = dataSource;
    }

    @Override
    public boolean onItemClick(int id, Activity activity) {
        if (id == R.id.action_debug_prefs) {
            @SuppressLint("InflateParams")
            View view = activity.getLayoutInflater().inflate(R.layout.dialog_debug_preferences, null, false);
            ButterKnife.inject(this, view);

            if (dataSource.get().equals("mock")) {
                mockModeCb.setChecked(true);
            }
            engineIdEt.setText(engineId.get());
            apiKeyEt.setText(apiKey.get());

            AlertDialog dialog = new AlertDialog.Builder(activity)
                    .setTitle("Debug Preferences")
                    .setView(view)
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataSource.set(mockModeCb.isChecked() ? "mock" : "live");
                            engineId.set(engineIdEt.getText().toString());
                            apiKey.set(apiKeyEt.getText().toString());

                            restartApp();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            dialog.show();

            return true;
        } else {
            return false;
        }
    }

    private void restartApp() {
        Intent restartIntent = new Intent(App.inst, MainActivity.class);
        restartIntent.setFlags(FLAG_ACTIVITY_NEW_TASK
                | (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                    ? FLAG_ACTIVITY_CLEAR_TASK : 0));
        App.inst.startActivity(restartIntent);
        App.inst.buildObjectGraph();
    }

}
