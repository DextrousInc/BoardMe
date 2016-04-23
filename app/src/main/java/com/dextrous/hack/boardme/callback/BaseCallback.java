package com.dextrous.hack.boardme.callback;

import android.app.ProgressDialog;
import android.content.Context;

import com.dextrous.hack.boardme.util.AndroidUtil;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.MSG_PROGRESS_DIALOG_MESSAGE;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.MSG_PROGRESS_DIALOG_TITLE;

public class BaseCallback {

    protected Context context;
    protected ProgressDialog progressDialog;

    public BaseCallback(Context context) {
        this.context = context;
       if(this.context != null) {
           this.progressDialog = AndroidUtil.showProgressDialog(context,
                   MSG_PROGRESS_DIALOG_TITLE,
                   MSG_PROGRESS_DIALOG_MESSAGE);
       }
    }
    protected void hideDialog() {
        if(this.progressDialog != null){
            this.progressDialog.hide();
        }
    }
}
