package com.mec.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.shuhuasoft.R;

/**
 * @author timpkins
 */
public class InputEditText extends AppCompatEditText {

    public InputEditText(Context context) {
        super(context);
        init();
    }

    public InputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(null);
        Drawable textDrawable = getResources().getDrawable(R.drawable.input_edittext_bg);
        textDrawable.setBounds(0, 0, textDrawable.getMinimumWidth(), textDrawable.getMinimumHeight());
        setCompoundDrawables(null, null, null, textDrawable);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        setTextColor(Color.parseColor("#333333"));
        setHintTextColor(Color.parseColor("#CCCCCC"));
        int padding = getResources().getDimensionPixelOffset(R.dimen.item_prompt_padding);
        setPadding(0, padding, 0, padding);
        setCompoundDrawablePadding(padding);
    }

    public void inputError(){
        setTextColor(Color.parseColor("#FF444A"));
        Drawable textDrawable = getResources().getDrawable(R.drawable.input_edittext_error);
        textDrawable.setBounds(0, 0, textDrawable.getMinimumWidth(), textDrawable.getMinimumHeight());
        setCompoundDrawables(null, null, null, textDrawable);
    }

    public void inputNormal(){
        setTextColor(Color.parseColor("#333333"));
        Drawable textDrawable = getResources().getDrawable(R.drawable.input_edittext_bg);
        textDrawable.setBounds(0, 0, textDrawable.getMinimumWidth(), textDrawable.getMinimumHeight());
        setCompoundDrawables(null, null, null, textDrawable);
    }
}
