package com.org.omicron.haporiapplication.categoryScroll;

import android.graphics.drawable.Drawable;

import com.org.omicron.haporiapplication.R;

public class CategoryListItem {
    private String categoryName;
    private Drawable categoryLogo;

    public CategoryListItem(String categoryName, Drawable categoryLogo) {
        this.categoryName = categoryName;
        this.categoryLogo = categoryLogo;
    }

    public CategoryListItem(String categoryName) {
        this.categoryName = categoryName;
    }

    //Getters
    public String getCategoryName() {
        return categoryName;
    }

    public Drawable getCategoryImage(){
        return categoryLogo;
    }

    //Setters
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public void setCategoryImage(Drawable categoryImage){ this.categoryLogo = categoryImage; }
}
