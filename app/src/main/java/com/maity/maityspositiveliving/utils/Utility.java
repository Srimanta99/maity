package com.maity.maityspositiveliving.utils;

import android.content.Intent;

public class Utility {

    public static final String DOC = "application/msword";
    public static final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String IMAGE = "image/*";
    public static final String AUDIO = "audio/*";
    public static final String TEXT = "text/*";
    public static final String PDF = "application/pdf";
    public static final String XLS = "application/vnd.ms-excel";

    public static Intent getCustomFileChooserIntent(String ...types){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        // Filter to only show results that can be "opened"
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, types);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        intent.putExtra(Intent.EXTRA_FROM_STORAGE, true);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        return intent;
    }
}
