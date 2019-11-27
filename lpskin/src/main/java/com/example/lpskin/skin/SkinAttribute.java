package com.example.lpskin.skin;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lpskin.skin.utils.SkinResources;
import com.example.lpskin.skin.utils.SkinUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Carl on 2018/3/17 017.
 */

public class SkinAttribute {
    private static final List<String> mAttributes = new ArrayList<>();//支持换肤的属性

    static {
        mAttributes.add("skinTypeface");
        mAttributes.add("background");
        mAttributes.add("src");
        mAttributes.add("textColor");
        mAttributes.add("drawableLeft");
        mAttributes.add("drawableTop");
        mAttributes.add("drawableRight");
        mAttributes.add("drawableBottom");
    }

    private Typeface typeface;

    private List<SkinView> skinViews = new ArrayList<>();

    public SkinAttribute(Typeface typeface) {
        this.typeface = typeface;
    }

    /**
     * 筛选符合属性的view,并且开始应用皮肤属性
     *
     * @param view
     * @param attrs
     */
    public void load(View view, AttributeSet attrs) {
        List<SkinPair> skinPairs = new ArrayList<>();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            //获得属性名
            String attributeName = attrs.getAttributeName(i);
            if (mAttributes.contains(attributeName)) {
                String attributeValue = attrs.getAttributeValue(i);
                //写死了 不管了
                if (attributeValue.startsWith("#")) {
                    continue;
                }
                //资源id
                int resId = 0;
                if (attributeValue.startsWith("?")) {//?开头,  "?colorAccess" 对应主题中的属性名称id
                    int attrId = Integer.parseInt(attributeValue.substring(1));//属性id
                    //获得主题style中对应attr的资源id值
                    resId = SkinUtils.getResId(view.getContext(), new int[]{attrId})[0];

                } else {//@开头  "@ID"
                    resId = Integer.parseInt(attributeValue.substring(1));
                }
                if (resId != 0) {
                    //可以替换的属性
                    SkinPair skinPair = new SkinPair(attributeName, resId);
                    skinPairs.add(skinPair);
                }
            }
        }
        if (!skinPairs.isEmpty() || view instanceof TextView) {
            SkinView skinView = new SkinView(view, skinPairs);
            skinView.applySkin(typeface);
            skinViews.add(skinView);
        }
    }

    public void applySkin(Typeface typeface) {
        for (SkinView skinView : skinViews) {
            skinView.applySkin(typeface);
        }
    }


    static class SkinView {
        View view;
        /**
         * 当前view支持换肤特性的属性与id键值对列表
         */
        List<SkinPair> skinPairs;

        public SkinView(View view, List<SkinPair> skinPairs) {
            this.view = view;
            this.skinPairs = skinPairs;
        }

        /**
         * 对当前view进行支持换肤的属性进行配置,应用原生或者皮肤包的资源.
         * @param typeface
         */
        public void applySkin(Typeface typeface) {
            applySkinTypeface(typeface);
            for (SkinPair skinPair : skinPairs) {
                Drawable left = null, top = null, right = null, bottom = null;
                switch (skinPair.attributeName) {
                    case "background":
                        Object background = SkinResources.getInstance().getBackground(skinPair
                                .resId);
                        //Color
                        if (background instanceof Integer) {
                            view.setBackgroundColor((Integer) background);
                        } else {
                            ViewCompat.setBackground(view, (Drawable) background);
                        }
                        break;
                    case "src":
                        background = SkinResources.getInstance().getBackground(skinPair
                                .resId);
                        if (background instanceof Integer) {
                            ((ImageView) view).setImageDrawable(new ColorDrawable((Integer)
                                    background));
                        } else {
                            ((ImageView) view).setImageDrawable((Drawable) background);
                        }
                        break;
                    case "textColor":
                        ((TextView) view).setTextColor(SkinResources.getInstance().getColorStateList
                                (skinPair.resId));
                        break;
                    case "drawableLeft":
                        left = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "drawableTop":
                        top = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "drawableRight":
                        right = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "drawableBottom":
                        bottom = SkinResources.getInstance().getDrawable(skinPair.resId);
                        break;
                    case "skinTypeface":
                        Typeface typeface1 = SkinResources.getInstance().getTypeface(skinPair.resId);
                        applySkinTypeface(typeface1);
                        break;
                    default:
                        break;
                }
                if (null != left || null != right || null != top || null != bottom) {
                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(left, top, right,
                            bottom);
                }
            }
        }

        private void applySkinTypeface(Typeface typeface) {
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            }
        }
    }

    static class SkinPair {
        /**
         * 属性名称,例如:background,src,textColor等
         */
        String attributeName;
        /**
         * 资源ID值
         */
        int resId;

        public SkinPair(String attributeName, int resId) {
            this.attributeName = attributeName;
            this.resId = resId;
        }
    }
}
