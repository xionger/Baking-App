package com.xiongxh.baking_app.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.xiongxh.baking_app.R;

import java.util.Locale;

public class IngredientsFormatUtils {
    public static void setTextWithSpan(TextView textView,
                                       String fulltext,
                                       String styleText,
                                       StyleSpan style){
        SpannableStringBuilder sb = new SpannableStringBuilder(fulltext);
        int start = fulltext.indexOf(styleText);
        int end = start + styleText.length();
        sb.setSpan(style, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(sb);
    }

    public static String formatIngredient(Context context, String name, double quantity, String measure){
        String line = context.getResources().getString(R.string.details_ingredient_line);

        String quanStr = String.format(Locale.US, "%s", quantity);
        if (quantity == (long) quantity){
            quanStr = String.format(Locale.US, "%d", (long)quantity);
        }

        return String.format(Locale.US, line, name, quanStr, measure);
    }
}
