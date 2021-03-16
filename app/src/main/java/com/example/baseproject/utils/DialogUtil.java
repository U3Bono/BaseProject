package com.example.baseproject.utils;

/**
 * 描述：Dialog工具
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {

    //提示弹出
    public static void showMessageDialog(Context context, String title, String message, final String bottom1, String bottom2, final MessageDialogBottom messageDialogBottom) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title == null) {
            return;
        } else {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        if (bottom1 != null) {
            builder.setPositiveButton(bottom1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    messageDialogBottom.bottom1();
                }
            });
        }
        if (bottom2 != null) {
            builder.setNegativeButton(bottom2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    messageDialogBottom.bottom2();
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //列表弹窗
    public static void showListDialog(Context context, String[] items, final ListDialogBottom listDialogBottom) {
        new AlertDialog.Builder(context).setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listDialogBottom.bottom();
            }
        }).show();
    }

    public interface MessageDialogBottom {
        void bottom1();

        void bottom2();
    }

    public interface ListDialogBottom {
        void bottom();
    }

}
