package com.mec.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuhuasoft.R;

/**
 * @author timpkins
 */
public class ItemPromptView extends RelativeLayout {
    private TextView tvPrompt;

    public ItemPromptView(Context context) {
        super(context);
    }

    public ItemPromptView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initItem(context, attrs);
    }

    public ItemPromptView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initItem(context, attrs);
    }

    public ItemPromptView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initItem(context, attrs);
    }

    private void initItem(Context context, AttributeSet attrs) {
        TypedArray tarray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ItemPromptView, 0, 0);
        String itemName = tarray.getString(R.styleable.ItemPromptView_itemName);
        float nameSize = tarray.getDimension(R.styleable.ItemPromptView_nameSize, 16);
        int nameColor = tarray.getColor(R.styleable.ItemPromptView_nameColor, getResources().getColor(R.color.textColor));
        String itemPrompt = tarray.getString(R.styleable.ItemPromptView_itemPrompt);
        float promptSize = tarray.getDimension(R.styleable.ItemPromptView_promptSize, 14);
        int promptColor = tarray.getColor(R.styleable.ItemPromptView_promptColor, getResources().getColor(R.color.tipsColor));
        tarray.recycle();

        TextView tvName = new TextView(context);
        tvName.setText(itemName);
        tvName.setTextSize(nameSize);
        tvName.setTextColor(nameColor);
        LayoutParams nameParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        nameParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        tvName.setLayoutParams(nameParams);

        tvPrompt = new TextView(context);
        tvPrompt.setText(itemPrompt);
        tvPrompt.setTextColor(promptColor);
        tvPrompt.setTextSize(promptSize);
        Drawable  textDrawable= getResources().getDrawable(R.mipmap.item_right);
        textDrawable.setBounds( 0, 0, textDrawable.getMinimumWidth(),textDrawable.getMinimumHeight());

        tvPrompt.setCompoundDrawables(null, null, textDrawable, null);
        tvPrompt.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.item_prompt_padding));
        LayoutParams promptParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        promptParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        tvPrompt.setLayoutParams(promptParams);

        addView(tvName);
        addView(tvPrompt);
    }

    public void setItemPrompt(String itemPrompt) {
        tvPrompt.setText(itemPrompt);
    }

    public void setPromptBaclground(@DrawableRes int resId){
        tvPrompt.setBackgroundResource(resId);
    }
}
