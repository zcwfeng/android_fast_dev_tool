package com.zcwfeng.fastdev.intent_ref;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.zcwfeng.componentlibs.ui.basic.BaseActivity_deprecated;
import com.zcwfeng.fastdev.R;

import java.util.List;

/**
 * Created by David.zhang on 16/3/9.
 * Description：
 */
public class IntentReferenceActivity extends BaseActivity_deprecated {
    public static void launch(Context from) {
        Intent intent = new Intent(from, IntentReferenceActivity.class);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_reference);

        if (isIntentAvailiable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
            Toast.makeText(this, "有权限", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "没权限", Toast.LENGTH_LONG).show();
        }


    }

    public boolean isIntentAvailiable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfos.size() > 0;
    }
}
