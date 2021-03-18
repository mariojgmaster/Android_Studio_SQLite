/*
 * Class: Connection
 * Version: 1.1.1
 * Author: Mário Oliveira
 */

package com.exemple.products;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Utils {

    public void ShowToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void openScreen(Context context, Class screen, Boolean isGo) {
        Intent intent = new Intent(context, screen);
        Bundle bundleAnimationGo = ActivityOptions.makeCustomAnimation(context, R.anim.translate_from_go, R.anim.translate_to_go).toBundle();
        Bundle bundleAnimationBack = ActivityOptions.makeCustomAnimation(context, R.anim.translate_from_back, R.anim.translate_to_back).toBundle();
        Bundle bundleAnimation = isGo ? bundleAnimationGo : bundleAnimationBack;
        context.startActivity(intent, bundleAnimation);
    }

}
