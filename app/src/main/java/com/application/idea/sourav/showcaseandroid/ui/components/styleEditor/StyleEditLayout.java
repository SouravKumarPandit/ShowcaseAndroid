package com.application.idea.sourav.showcaseandroid.ui.components.styleEditor;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.*;
import android.text.style.*;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

public class StyleEditLayout extends RelativeLayout implements View.OnClickListener, ColorSelectorView.OnColorSelected, SizeSelectorView.OnSizePicked{
    private static final int FORMAT_TYPE_ID_NOR = 20400;
    private static final int FORMAT_TYPE_ID_BOLD = 20500;
    private static final int FORMAT_TYPE_ID_ITALIC = 20600;
    private static final int FORMAT_TYPE_ID_UND = 20700;
    private static final int FORMAT_TYPE_ID_STRIK = 20800;
    private static final int FORMAT_TYPE_ID_BULLET = 20900;
//    private static final int FORMAT_TYPE_ID_QUOTE = 20100;
    private static final int FORMAT_TYPE_ID_COLOR = 21100;
    private static final int FORMAT_TYPE_ID_COLOR_BG = 21200;
    private static final int FORMAT_TYPE_ID_SIZE = 21300;
    private Context context;
    private boolean isBold = false, isItalic = false, isUnderLine = false, isStrike = false, isSized = false, isBullet = false, isColored = false, isBgColor = false;
    private int selectionColor = 0xffdedede;
    private int textColor = Color.BLACK;
    private int textBgColor = Color.TRANSPARENT;
    private float pickedSize = 16.0f;
    private boolean isSelectionMode;
    private int bgSelPosition=0;
    private int fgSelPosition=0;
    private int sizeSelPosition=3;
    private int[] textColorArray = new int[]{
            0xFF212121,
            0xFFf44336,
            0xFF2196f3,
            0xFF4caf50,
            0xFF30EA9D,
            0xFFffc107,
            0xFF7F36F4,
            0xFFF436C8,
            0xFFC88CFF,
            0xFF8F8F8F,
            0xFFF49B42
    };
    private int[] textBgColorArray = new int[]{
            0xFFe6e6e6,
            0xFFef9a9a,
            0xFF90caf9,
            0xFFa5d6a7,
            0xFFffe082,
            0xFFe0e0e0,
            0xFF9CE1C4,
            0xFFF5A0E2,
            0xFFEFE99A,
            0xFFDCDBDB,
            0xFFE9C49F
    };
    private float[] pickedSizeArray = new float[]{
            8f,
            10f,
            12f,
            16f,
            18f,
            20f,
            22f,
            24f,
            26f,
            28f,
            30f
    };

    public StyleEditLayout(Context context) {
        super(context);
        intit(context);

    }

    public StyleEditLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intit(context);

    }

    public StyleEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intit(context);
    }

    public void intit(Context context) {
        int i3dp = Utils.dpToPx(3);
        int i8dp = Utils.dpToPx(8);
        this.context = context;
        setGravity(Gravity.TOP);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setMinimumHeight(Utils.dpToPx(100));

        StyleEditText styleEditText = new StyleEditText(context);
        LayoutParams editTextParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        styleEditText.setLayoutParams(editTextParam);
//        styleEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        /* very important line for single word spanning*/
        styleEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        styleEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS| InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        styleEditText.setPadding(i8dp, i3dp, i8dp, Utils.dpToPx(50));
        styleEditText.setMinLines(5);
        styleEditText.setSingleLine(false);
        styleEditText.setGravity(Gravity.TOP);
        styleEditText.setId(R.id.custom_edit);
        styleEditText.setHint("Compose email");
        styleEditText.setHintTextColor(0xffe5e5e6);
        styleEditText.setBackground(null);
        addView(styleEditText);
        addView(getStyleHolder());
        addView(getColorSelector());
        addView(getTextSizeSelector());
//        addView(getAttachmentButton());

    }


    private View getColorSelector() {
        HorizontalScrollView colorScrollView = new HorizontalScrollView(context);
        colorScrollView.setBackground(getResources().getDrawable(R.drawable.item_select));
        colorScrollView.setLayoutTransition(new LayoutTransition());
        LayoutParams relativeParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        relativeParam.addRule(RelativeLayout.ABOVE, R.id.style_holder);
        relativeParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeParam.setMargins(0, 0, 0, Utils.dpToPx(50));
        colorScrollView.setLayoutParams(relativeParam);
        colorScrollView.setFillViewport(true);
        colorScrollView.setVisibility(GONE);
        colorScrollView.setHorizontalScrollBarEnabled(false);
        ColorSelectorView colorSelectorLayout = new ColorSelectorView(context);
        colorSelectorLayout.setOnColorSelected(this);
        colorScrollView.addView(colorSelectorLayout);
        return colorScrollView;
    }

    private View getTextSizeSelector() {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setBackground(getResources().getDrawable(R.drawable.item_select));
        horizontalScrollView.setLayoutTransition(new LayoutTransition());
        LayoutParams relativeParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        relativeParam.addRule(RelativeLayout.ABOVE, R.id.style_holder);
        relativeParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeParam.setMargins(0, 0, 0, Utils.dpToPx(50));
        horizontalScrollView.setLayoutParams(relativeParam);
        horizontalScrollView.setFillViewport(true);
        horizontalScrollView.setVisibility(GONE);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        SizeSelectorView sizeSelectorView = new SizeSelectorView(context);
        sizeSelectorView.setOnSizePicked(this);
        horizontalScrollView.addView(sizeSelectorView);
        return horizontalScrollView;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor);
        hideSoftInput();
    }

    public void hideSoftInput() {
        clearFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private HorizontalScrollView getStyleHolder() {

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);

        LayoutParams relativeParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        horizontalScrollView.setBackground(getResources().getDrawable(R.drawable.item_select));
        horizontalScrollView.setFillViewport(true);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setId(R.id.style_holder);
        horizontalScrollView.setLayoutParams(relativeParam);
        horizontalScrollView.setLayoutTransition(new LayoutTransition());

        LinearLayout styleHolder = new LinearLayout(context);
        styleHolder.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        styleHolder.setClipToPadding(false);
        styleHolder.setOrientation(LinearLayout.HORIZONTAL);
        styleHolder.setGravity(Gravity.CENTER_VERTICAL);
//〶₮Ⓣ〶₮Ⓣ⒯⊙ɴ̅
        styleHolder.addView(checkableText("ɴ̅", FORMAT_TYPE_ID_NOR));
        styleHolder.addView(checkableText("B", FORMAT_TYPE_ID_BOLD));
        styleHolder.addView(checkableText("I", FORMAT_TYPE_ID_ITALIC));
        styleHolder.addView(checkableText("U", FORMAT_TYPE_ID_UND));
        styleHolder.addView(checkableText("₩", FORMAT_TYPE_ID_STRIK));
        styleHolder.addView(checkableText("ᴀA", FORMAT_TYPE_ID_SIZE));
        styleHolder.addView(checkableText("•ᴘ", FORMAT_TYPE_ID_BULLET));//todo : can add other feature in here
        styleHolder.addView(checkableText("", FORMAT_TYPE_ID_COLOR));
        styleHolder.addView(checkableText("", FORMAT_TYPE_ID_COLOR_BG));
        horizontalScrollView.addView(styleHolder);
        return horizontalScrollView;
    }

    private CheckedTextView checkableText(String sLable, int iRadioId) {
        int i5dp = Utils.dpToPx(5);
        int i35dp = Utils.dpToPx(35);
        int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
        CheckedTextView checkText = new CheckedTextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i35dp, i35dp);

        layoutParams.setMargins(i5dp, 0, 0, 0);
        checkText.setLayoutParams(layoutParams);
        checkText.setGravity(Gravity.CENTER);
        checkText.setOnClickListener(StyleEditLayout.this);
        checkText.setId(iRadioId);
        if (iRadioId == FORMAT_TYPE_ID_COLOR_BG) {
            checkText.setBackground(getColorSelectorDrawable(selectionColor, Color.WHITE));
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                checkText.setVisibility(VISIBLE);
            } else {
                checkText.setVisibility(GONE);
            }*/
            return checkText;
        } else if (iRadioId == FORMAT_TYPE_ID_COLOR) {
            checkText.setBackground(getColorSelectorDrawable(textColor, selectionColor));
            return checkText;
        } else {
            if (FORMAT_TYPE_ID_BULLET == iRadioId)
                checkText.setVisibility(GONE);
            checkText.setBackground(getSelectorDrawable(selectionColor, Utils.dpToPx(12)));
            checkText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            checkText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            checkText.setTextColor(Color.BLACK);
            checkText.setTypeface(Typeface.DEFAULT_BOLD);
            checkText.setText(sLable);
            SpannableStringBuilder sbSpanChar = new SpannableStringBuilder(sLable);
            if (iRadioId == FORMAT_TYPE_ID_BOLD)
                sbSpanChar.setSpan(new StyleSpan(Typeface.BOLD), 0, sbSpanChar.length(), flag);
            else if (iRadioId == FORMAT_TYPE_ID_ITALIC)
                sbSpanChar.setSpan(new StyleSpan(Typeface.ITALIC), 0, sbSpanChar.length(), flag);
            else if (iRadioId == FORMAT_TYPE_ID_UND)
                sbSpanChar.setSpan(new CustomUnderlineSpan(), 0, sbSpanChar.length(), flag);
            checkText.setText(sbSpanChar);
        }
        if (iRadioId == FORMAT_TYPE_ID_NOR)
            checkText.setChecked(true);
        return checkText;
    }


    public StateListDrawable getSelectorDrawable(int prssedColor, int cornerRadius) {
        StateListDrawable res = new StateListDrawable();
        GradientDrawable roundGradiant = new GradientDrawable();
        roundGradiant.setCornerRadius(cornerRadius);
        roundGradiant.setColor(prssedColor);
        res.addState(new int[]{android.R.attr.state_pressed}, roundGradiant);
        res.addState(new int[]{android.R.attr.state_selected}, roundGradiant);
        res.addState(new int[]{android.R.attr.state_checked}, roundGradiant);
        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }


    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        if (v instanceof CheckedTextView) {
            CheckedTextView checkedTextView = (CheckedTextView) v;
            checkedTextView.toggle();
            if (getStyleEditView() != null)
                if (v.getId() == FORMAT_TYPE_ID_BOLD) {
                    if (isSelectionMode) {
                        bold(checkedTextView.isChecked());
                    }
                    isBold = checkedTextView.isChecked();
                } else if (v.getId() == FORMAT_TYPE_ID_ITALIC) {
                    if (isSelectionMode) {
                        italic(checkedTextView.isChecked());
                    }
                    isItalic = checkedTextView.isChecked();
                } else if (v.getId() == FORMAT_TYPE_ID_UND) {
                    if (isSelectionMode) {
                        underline(checkedTextView.isChecked());
                    }
                    isUnderLine = checkedTextView.isChecked();
                } else if (v.getId() == FORMAT_TYPE_ID_STRIK) {
                    if (isSelectionMode) {
                        strikeThrough(checkedTextView.isChecked());
                    }
                    isStrike = checkedTextView.isChecked();
                } else if (v.getId() == FORMAT_TYPE_ID_SIZE) {
                    SizeSelectorView sizeSelectorView = getSizeSelectorView();
                    sizeSelectorView.setRequestId(FORMAT_TYPE_ID_SIZE);
                    sizeSelectorView.setSizeSelectionArray(pickedSizeArray,true);
                    sizeSelectorView.setiSelectedPosition(sizeSelPosition);
                    if (isSelectionMode) {
                        sizedText(checkedTextView.isChecked());
                    }
                    getChildAt(3).setScaleX(0);
                    getChildAt(3).animate().scaleX(1).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    isSized = checkedTextView.isChecked();
                    resetColorFilter(false);
                    resetSizeFilter(isSized);
                } else if (v.getId() == FORMAT_TYPE_ID_BULLET) {
                    /*if (isSelectionMode) {
                        bullet(checkedTextView.isChecked());
                    }
                    isBullet = checkedTextView.isChecked();*/
                } else if (v.getId() == FORMAT_TYPE_ID_COLOR) {
                    ColorSelectorView colorSelectorView = getColorSelectorView();
                    colorSelectorView.setRequestId(FORMAT_TYPE_ID_COLOR);
                    colorSelectorView.setColorArray(textColorArray);
                    colorSelectorView.setiSelectedPosition(fgSelPosition);
                    if (isSelectionMode) {
                        foregroundColored(checkedTextView.isChecked());
                    }
                    getChildAt(2).setScaleX(0);
                    getChildAt(2).animate().scaleX(1).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    isColored = checkedTextView.isChecked();
                    resetSizeFilter(false);
                    resetColorFilter(isColored);

                } else if (v.getId() == FORMAT_TYPE_ID_COLOR_BG) {
                    ColorSelectorView colorSelectorView = getColorSelectorView();
                    colorSelectorView.setRequestId(FORMAT_TYPE_ID_COLOR_BG);
                    colorSelectorView.setColorArray(textBgColorArray);
                    colorSelectorView.setiSelectedPosition(bgSelPosition);
                    if (isSelectionMode) {
                        backgroundColored(checkedTextView.isChecked());
                    }
                    isBgColor = checkedTextView.isChecked();
                    getChildAt(2).setScaleX(0);
                    getChildAt(2).animate().scaleX(1).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    resetSizeFilter(false);
                    resetColorFilter(isBgColor);

                } else {
                    if (isSelectionMode) {
                        normal();
                    }
                    isBold = false;
                    isItalic = false;
                    isUnderLine = false;
                    isStrike = false;
                    isSized = false;
//                    isBullet = false;
                    isColored = false;
                    isBgColor = false;
                }
       
            resetInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor);

        }

    }


    private void resetColorFilter(boolean reqColor) {
        if (getChildAt(2) != null)
            if (reqColor) {
                getChildAt(2).setVisibility(VISIBLE);
            } else {
                getChildAt(2).setVisibility(GONE);
            }
//        isColored=reqColor;

    }

    private void resetSizeFilter(boolean reqColor) {
        if (getChildAt(3) != null)
            if (reqColor) {
                getChildAt(3).setVisibility(VISIBLE);
            } else {
                getChildAt(3).setVisibility(GONE);
            }
//        isColored=reqColor;

    }

    @Override
    public void onColorSelectionChange(int selectedColor, int selectionPosition, int requestId) {
        if (requestId == FORMAT_TYPE_ID_COLOR) {
            textColor = selectedColor;
            fgSelPosition=selectionPosition;
            if (isSelectionMode) {
                foregroundColoredValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
            }
            ((CheckedTextView) getEditStyleHolder().getChildAt(7)).setBackground(getColorSelectorDrawable(selectedColor, selectionColor));
            resetColorFilter(false);
        } else if (requestId == FORMAT_TYPE_ID_COLOR_BG) {

            textBgColor = selectedColor;
            bgSelPosition=selectionPosition;
            if (isSelectionMode) {
                backgroudColoredValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
            }
            ((CheckedTextView) getEditStyleHolder().getChildAt(8)).setBackground(getColorSelectorDrawable(selectionColor, textBgColor));
            resetColorFilter(false);

        }
        resetInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor);

    }

    @Override
    public void onValueSelectionChange(float selectedValue, int iSelectedPosition, int requestId) {
//        Toast.makeText(context, "Picked - " + selectedValue, Toast.LENGTH_SHORT).show();
        pickedSize = selectedValue;
        resetSizeFilter(false);
        if (pickedSize == 1.0f)
            isSized = false;
        sizeSelPosition=iSelectedPosition;
        resetInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor);

    }

    private StateListDrawable getColorSelectorDrawable(int pickedColor, int iSelectionColor) {
        StateListDrawable res = new StateListDrawable();
        int iWidthVal = Utils.dpToPx(8);
        GradientDrawable roundGradiant = new GradientDrawable();
        roundGradiant.setCornerRadius(iWidthVal);
        roundGradiant.setColor(pickedColor);
        roundGradiant.setStroke(iWidthVal, iSelectionColor);

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(pickedColor);
        normalDrawable.setCornerRadius(iWidthVal);
        normalDrawable.setStroke(iWidthVal, Color.WHITE);
        res.addState(new int[]{android.R.attr.state_pressed}, roundGradiant);
        res.addState(new int[]{android.R.attr.state_selected}, roundGradiant);
        res.addState(new int[]{android.R.attr.state_checked}, roundGradiant);
        res.addState(new int[]{}, normalDrawable);
        return res;
    }


    protected boolean containBullet(int index) {
        String[] lines = TextUtils.split(getEditableText().toString(), "\n");
        if (index < 0 || index >= lines.length) {
            return false;
        }

        int start = 0;
        for (int i = 0; i < index; i++) {
            start = start + lines[i].length() + 1;
        }

        int end = start + lines[index].length();
        if (start >= end) {
            return false;
        }

        BulletSpan[] spans = getEditableText().getSpans(start, end, BulletSpan.class);
        return spans.length > 0;
    }

    private void resetInputFilter(boolean isBold, boolean isItalic, boolean isUnderLine, boolean isStrike, boolean isSized, boolean isBullet, boolean isColored, boolean isBgColor) {
        if (getEditStyleHolder() != null) {
            ((CheckedTextView) getEditStyleHolder().getChildAt(1)).setChecked(isBold);
            ((CheckedTextView) getEditStyleHolder().getChildAt(2)).setChecked(isItalic);
            ((CheckedTextView) getEditStyleHolder().getChildAt(3)).setChecked(isUnderLine);
            ((CheckedTextView) getEditStyleHolder().getChildAt(4)).setChecked(isStrike);
            ((CheckedTextView) getEditStyleHolder().getChildAt(5)).setChecked(isSized);
            ((CheckedTextView) getEditStyleHolder().getChildAt(6)).setChecked(isBullet);
            ((CheckedTextView) getEditStyleHolder().getChildAt(7)).setChecked(isColored);
            ((CheckedTextView) getEditStyleHolder().getChildAt(8)).setChecked(isBgColor);
            if (!isBold && !isItalic && !isUnderLine && !isStrike && !isSized && !isBullet && !isColored && !isBgColor) {
                ((CheckedTextView) getEditStyleHolder().getChildAt(0)).setChecked(true);
                resetColorFilter(false);
            } else {
                ((CheckedTextView) getEditStyleHolder().getChildAt(0)).setChecked(false);
            }

            getStyleEditView().setFilters(new InputFilter[]{new StyleInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor, textColor, textBgColor, pickedSize)});
        }

    }


   /* public String getEditorText() {
        return Html.toHtml(new SpannableString(getStyleEditView().getText()));
    }*/

    public void setPickedSizeArray(float[] pickedSizeArray)
    {
        this.pickedSizeArray = pickedSizeArray;
    }

    public void setTextColorArray(int[] textColorArray)
    {
        this.textColorArray = textColorArray;
    }

    public void setTextBgColorArray(int[] textBgColorArray)
    {
        this.textBgColorArray = textBgColorArray;
    }

    public String getEditorHtmlText() {
        return StyleEditorParser.toHtml(getStyleEditView().getText());
    }
    public LinearLayout getEditStyleHolder() {
        return (LinearLayout) ((HorizontalScrollView) getChildAt(1)).getChildAt(0);
    }

    public EditText getStyleEditView() {
        return (EditText) getChildAt(0);
    }

    public Editable getEditableText() {
        return getStyleEditView().getText();
    }

    public ColorSelectorView getColorSelectorView() {
        return (ColorSelectorView) ((HorizontalScrollView) getChildAt(2)).getChildAt(0);
    }

    public SizeSelectorView getSizeSelectorView() {
        return (SizeSelectorView) ((HorizontalScrollView) getChildAt(3)).getChildAt(0);
    }

    public void bold(boolean valid) {
        if (valid) {
            styleValid(Typeface.BOLD, getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            styleInvalid(Typeface.BOLD, getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }

    public void italic(boolean valid) {
        if (valid) {
            styleValid(Typeface.ITALIC, getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            styleInvalid(Typeface.ITALIC, getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }

    public void underline(boolean valid) {
        if (valid) {
            underlineValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            invalidateUnderLineStyle(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }

    public void strikeThrough(boolean valid) {
        if (valid) {
            strikeThroughValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            strikeThroughInvalid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }

    public void sizedText(boolean valid) {
        if (valid) {
            bigTextValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            bigTextInValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }



    private void foregroundColored(boolean valid) {
        if (valid) {
            foregroundColoredValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            invalidateForegroundColor(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }

    private void backgroundColored(boolean valid) {
        if (valid) {
            backgroudColoredValid(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        } else {
            invalidateBackgroudColor(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
        }
    }


    private void normal() {
        invalidateAllStyle(Typeface.NORMAL, getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
//        invalidateUnderLineStyle(getStyleEditView().getSelectionStart(), getStyleEditView().getSelectionEnd());
    }

    private void invalidateAllStyle(int normal, int start, int end) {
        CharacterStyle[] spans = getEditableText().getSpans(start, end, CharacterStyle.class);
        for (CharacterStyle span : spans) {
            getEditableText().removeSpan(span);
        }
    }

    private void invalidateUnderLineStyle(int start, int end) {
        CustomUnderlineSpan[] spans = getEditableText().getSpans(start, end, CustomUnderlineSpan.class);
        for (CustomUnderlineSpan span : spans) {
            getEditableText().removeSpan(span);
        }
    }

    protected void styleInvalid(int style, int start, int end) {
        switch (style) {
            case Typeface.NORMAL:
            case Typeface.BOLD:
            case Typeface.ITALIC:
            case Typeface.BOLD_ITALIC:
                break;
            default:
                return;
        }

        StyleSpan[] spans = getEditableText().getSpans(start, end, StyleSpan.class);
        for (StyleSpan span : spans) {
            if (span.getStyle() == style) {
                getEditableText().removeSpan(span);
            }
        }
    }


    protected void underlineValid(int start, int end) {

        getEditableText().setSpan(new CustomUnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    protected void strikeThroughValid(int start, int end) {
        getEditableText().setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    protected void strikeThroughInvalid(int start, int end) {
        StrikethroughSpan[] spans = getEditableText().getSpans(start, end, StrikethroughSpan.class);
        for (StrikethroughSpan span : spans) {
            getEditableText().removeSpan(span);
        }
    }

    protected void bigTextValid(int start, int end) {
        getEditableText().setSpan(new AbsoluteSizeSpan((int) pickedSize,true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    protected void bigTextInValid(int start, int end) {
        AbsoluteSizeSpan[] spans = getEditableText().getSpans(start, end, AbsoluteSizeSpan.class);
        for (AbsoluteSizeSpan span : spans) {
            getEditableText().removeSpan(span);
        }
    }


   /* protected void bulletTextInValid(int start, int end) {
        CLBulletSpan[] spans = getEditableText().getSpans(start, end, CLBulletSpan.class);
        for (CLBulletSpan span : spans) {
            getEditableText().removeSpan(span);
        }
    }
*/
    private void foregroundColoredValid(int start, int end) {
        getEditableText().setSpan(new ForegroundColorSpan(textColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void invalidateForegroundColor(int start, int end) {
        ForegroundColorSpan[] spans = getEditableText().getSpans(start, end, ForegroundColorSpan.class);
        for (ForegroundColorSpan span : spans) {
            getEditableText().removeSpan(span);
        }

    }

    private void backgroudColoredValid(int start, int end) {
        getEditableText().setSpan(new BackgroundColorSpan(textBgColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void invalidateBackgroudColor(int start, int end) {
        BackgroundColorSpan[] spans = getEditableText().getSpans(start, end, BackgroundColorSpan.class);
        for (BackgroundColorSpan span : spans) {
            getEditableText().removeSpan(span);
        }

    }


    protected void styleValid(int style, int start, int end) {
        switch (style) {
            case Typeface.NORMAL:
            case Typeface.BOLD:
            case Typeface.ITALIC:
            case Typeface.BOLD_ITALIC:
                break;
            default:
                return;
        }
        getEditableText().setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    protected boolean containStyle(int style, int start, int end) {
        switch (style) {
            case Typeface.NORMAL:
            case Typeface.BOLD:
            case Typeface.ITALIC:
            case Typeface.BOLD_ITALIC:
                break;
            default:
                return false;
        }


        StringBuilder builder = new StringBuilder();

        // Make sure no duplicate characters be added
        for (int i = start; i < end; i++) {
            StyleSpan[] spans = getEditableText().getSpans(i, i + 1, StyleSpan.class);
            for (StyleSpan span : spans) {
                if (span.getStyle() == style) {
                    builder.append(getEditableText().subSequence(i, i + 1).toString());
                    break;
                }
            }
        }

        return getEditableText().subSequence(start, end).toString().equals(builder.toString());

    }

    protected boolean containUnderline(int start, int end) {

        CustomUnderlineSpan[] spans = getEditableText().getSpans(start, end, CustomUnderlineSpan.class);
        return spans.length > 0;
    }

    protected boolean containColored(int start, int end) {
        ForegroundColorSpan[] spans = getEditableText().getSpans(start, end, ForegroundColorSpan.class);
        if (spans.length > 0) {
            textColor = spans[0].getForegroundColor();
            ((CheckedTextView) getEditStyleHolder().getChildAt(7)).setBackground(getColorSelectorDrawable(textColor, selectionColor));
        }
        return spans.length > 0;
    }

    protected boolean containBackground(int start, int end) {
        BackgroundColorSpan[] spans = getEditableText().getSpans(start, end, BackgroundColorSpan.class);
        if (spans.length > 0) {
            textBgColor = spans[0].getBackgroundColor();
            ((CheckedTextView) getEditStyleHolder().getChildAt(8)).setBackground(getColorSelectorDrawable(selectionColor, textBgColor));
        }

        return spans.length > 0;
    }

    protected boolean containStrikethrough(int start, int end) {
        StrikethroughSpan[] spans = getEditableText().getSpans(start, end, StrikethroughSpan.class);
        return spans.length > 0;

    }

    protected boolean containBigText(int start, int end) {
        AbsoluteSizeSpan[] spans = getEditableText().getSpans(start, end, AbsoluteSizeSpan.class);
        return spans.length > 0;
    }

    public void setHtmlString(String htmlString) {
//        getStyleEditView().setText(new SpannableString(htmlString));
        getStyleEditView().setText(Html.fromHtml(htmlString));
    }


    private class StyleEditText extends AppCompatEditText
    {

        public StyleEditText(Context context) {
            super(context);
        }

        @Override
        protected void onSelectionChanged(int selStart, int selEnd) {
            super.onSelectionChanged(selStart, selEnd);
            if (getText()!=null&&getText().length()>0&&!(getText().toString().substring(selStart,selEnd).isEmpty())){
                setEditTextFocusChangeListener(selStart, selEnd);
            }
            resetColorFilter(false);
            resetSizeFilter(false);

        }

        private void setEditTextFocusChangeListener(int selStart, int selEnd) {
            if (getStyleEditView() != null && getStyleEditView().hasSelection()) {
                isBold = containStyle(Typeface.BOLD, selStart, selEnd);
                isItalic = containStyle(Typeface.ITALIC, selStart, selEnd);
                isUnderLine = containUnderline(selStart, selEnd);
                isColored = containColored(selStart, selEnd);
                isStrike = containStrikethrough(selStart, selEnd);
                isSized = containBigText(selStart, selEnd);
//                isBullet = containBulletText(selStart, selEnd);
                isBgColor = containBackground(selStart, selEnd);
                isSelectionMode = true;
                resetInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor);
            } else if (isSelectionMode) {
                isSelectionMode = false;
                resetInputFilter(isBold, isItalic, isUnderLine, isStrike, isSized, isBullet, isColored, isBgColor);
            }
        }


    }
}
