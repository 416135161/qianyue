//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.internet.act;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.internet.http.api.ApiException;
import com.internet.http.data.vo.StudentVO;
import com.internet.turnright.b.R.layout;
import com.internet.view.SearchStudentView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MyStudentAct_
    extends MyStudentAct
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.act_my_student);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static MyStudentAct_.IntentBuilder_ intent(Context context) {
        return new MyStudentAct_.IntentBuilder_(context);
    }

    public static MyStudentAct_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new MyStudentAct_.IntentBuilder_(fragment);
    }

    public static MyStudentAct_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new MyStudentAct_.IntentBuilder_(supportFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        layout_search = ((SearchStudentView) hasViews.findViewById(com.internet.turnright.b.R.id.layout_search));
        mListView = ((SwipeMenuListView) hasViews.findViewById(com.internet.turnright.b.R.id.mListView));
        edit_search = ((EditText) hasViews.findViewById(com.internet.turnright.b.R.id.edit_search));
        text_no_data = ((TextView) hasViews.findViewById(com.internet.turnright.b.R.id.text_no_data));
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.view_title_right);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickTitleRight();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.view_search);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickSearch();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.text_cancel);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickSearchCancel();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.view_title_left);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickTitleLeft();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.image_add);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickAdd();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.image_back);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickBack();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.internet.turnright.b.R.id.text_search);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MyStudentAct_.this.clickDoSearch();
                    }

                }
                );
            }
        }
        {
            AdapterView<?> view = ((AdapterView<?> ) hasViews.findViewById(com.internet.turnright.b.R.id.mListView));
            if (view!= null) {
                view.setOnItemClickListener(new OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MyStudentAct_.this.itemClick(position);
                    }

                }
                );
            }
        }
        init();
    }

    @Override
    public void showLoading() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.showLoading();
            }

        }
        );
    }

    @Override
    public void closeLoading() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.closeLoading();
            }

        }
        );
    }

    @Override
    public void onApiException(final ApiException arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.onApiException(arg0);
            }

        }
        );
    }

    @Override
    public void showLoading(final String arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.showLoading(arg0);
            }

        }
        );
    }

    @Override
    public void fullScreen(final boolean arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.fullScreen(arg0);
            }

        }
        );
    }

    @Override
    public void closeInputKeyboard() {
        handler_.postDelayed(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.closeInputKeyboard();
            }

        }
        , 200L);
    }

    @Override
    public void showToast(final String arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.showToast(arg0);
            }

        }
        );
    }

    @Override
    public void refreshList() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.refreshList();
            }

        }
        );
    }

    @Override
    public void hideEmpoty() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.hideEmpoty();
            }

        }
        );
    }

    @Override
    public void refreshListView(final ArrayList<StudentVO> list) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                MyStudentAct_.super.refreshListView(list);
            }

        }
        );
    }

    @Override
    public void doBack() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    MyStudentAct_.super.doBack();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void getStuentList(final Object noLoading) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    MyStudentAct_.super.getStuentList(noLoading);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void deleteStudent(final int position) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    MyStudentAct_.super.deleteStudent(position);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void getStuentList() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    MyStudentAct_.super.getStuentList();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, MyStudentAct_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, MyStudentAct_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, MyStudentAct_.class);
        }

        public Intent get() {
            return intent_;
        }

        public MyStudentAct_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent_, requestCode);
                } else {
                    if (context_ instanceof Activity) {
                        ((Activity) context_).startActivityForResult(intent_, requestCode);
                    } else {
                        context_.startActivity(intent_);
                    }
                }
            }
        }

    }

}
