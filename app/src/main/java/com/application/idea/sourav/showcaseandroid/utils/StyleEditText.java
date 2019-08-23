package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.*;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;

/**
 * Created by Sourav on 2018
 */

public class StyleEditText extends android.support.v7.widget.AppCompatEditText
{
    int INPUT_STYLE_TYPE;
    CharSequence sbBeforeSpan;
    SpannableStringBuilder sbSpanChar;
    TextWatcher textWatcher;
    int iBeforeLength = 0;
    int iAfterLength = 0;
    int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

    public StyleEditText(Context context)
    {
        super(context);
        init(context);
    }


    public StyleEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);

    }

    public StyleEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(final Context context)
    {
        textWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
//                Toast.makeText(context, "! - " + charSequence, 500).show();
                sbBeforeSpan = charSequence;
                iBeforeLength = charSequence.length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                iAfterLength = charSequence.length();
                if (iBeforeLength < iAfterLength)
                {
                    sbSpanChar = (SpannableStringBuilder) charSequence.subSequence(iBeforeLength, iAfterLength);
                    if (INPUT_STYLE_TYPE == 205)
                    {
                        sbSpanChar.setSpan(new StyleSpan(Typeface.BOLD), 0, sbSpanChar.length(), flag);
                    } else if (INPUT_STYLE_TYPE == 206)
                    {
                        sbSpanChar.setSpan(new StyleSpan(Typeface.ITALIC), 0, sbSpanChar.length(), flag);
                    } else if (INPUT_STYLE_TYPE == 207)
                    {
                        sbSpanChar.setSpan(new UnderlineSpan(), 0, sbSpanChar.length(), flag);
                    } else if (INPUT_STYLE_TYPE == 208)
                    {
                        sbSpanChar.setSpan(new ForegroundColorSpan(Color.RED), 0, sbSpanChar.length(), flag);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                removeTextChangedListener(this);
                if (iBeforeLength < iAfterLength)
                    if (INPUT_STYLE_TYPE > 204)
                    {
                        if (editable.length() > 0&&sbSpanChar!=null)
                        {
                            if (sbBeforeSpan.length() > 0)
                                setText(TextUtils.concat(sbBeforeSpan, sbSpanChar));
                            else
                                setText(TextUtils.concat("", sbSpanChar));
                            setSelection(editable.length());
                        }
                    }

                addTextChangedListener(this);

            }
        };
        this.addTextChangedListener(textWatcher);
    }


    public int getInputStyleType()
    {
        return INPUT_STYLE_TYPE;
    }

    public void setInputStyleType(int input_TextType)
    {
        this.INPUT_STYLE_TYPE = input_TextType;
    }


}
