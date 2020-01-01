package com.appstreettaskapplication.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


public class TextViewUtils {


    public static void setSpannable(TextView tView, String str1, String str2, int color2) {
        SpannableString wordtoSpan = new SpannableString(str1 + str2);
        int l1 = str1.length();
        int l2 = str2.length();
        if (color2 != -1)
            wordtoSpan.setSpan(new ForegroundColorSpan(color2), l1, l1 + l2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tView.setText(wordtoSpan);

    }

}
