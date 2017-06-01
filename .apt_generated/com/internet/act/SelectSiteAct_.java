//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.internet.act;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.internet.http.api.ApiException;
import com.internet.http.data.response.GetSiteListResponse;
import com.internet.turnright.b.R.id;
import com.internet.turnright.b.R.layout;
import com.internet.view.HeaderView;
import com.internet.view.SearchSiteView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SelectSiteAct_
    extends SelectSiteAct
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
        setContentView(layout.act_select_site);
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

    public static SelectSiteAct_.IntentBuilder_ intent(Context context) {
        return new SelectSiteAct_.IntentBuilder_(context);
    }

    public static SelectSiteAct_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new SelectSiteAct_.IntentBuilder_(fragment);
    }

    public static SelectSiteAct_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new SelectSiteAct_.IntentBuilder_(supportFragment);
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
        listView = ((ListView) hasViews.findViewById(id.listView));
        text_search = ((TextView) hasViews.findViewById(id.text_search));
        layout_search = ((SearchSiteView) hasViews.findViewById(id.layout_search));
        view_header = ((HeaderView) hasViews.findViewById(id.view_header));
        edit_search = ((EditText) hasViews.findViewById(id.edit_search));
        {
            View view = hasViews.findViewById(id.view_search);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectSiteAct_.this.clickSearch();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.tv_ok);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectSiteAct_.this.click(view);
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.text_search);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectSiteAct_.this.clickEnsureSearch();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(id.text_cancel);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SelectSiteAct_.this.clickSearchCancel();
                    }

                }
                );
            }
        }
        init();
    }

    @Override
    public void closeLoading() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.closeLoading();
            }

        }
        );
    }

    @Override
    public void showLoading() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.showLoading();
            }

        }
        );
    }

    @Override
    public void showLoading(final String arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.showLoading(arg0);
            }

        }
        );
    }

    @Override
    public void showToast(final String arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.showToast(arg0);
            }

        }
        );
    }

    @Override
    public void onApiException(final ApiException arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.onApiException(arg0);
            }

        }
        );
    }

    @Override
    public void closeInputKeyboard() {
        handler_.postDelayed(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.closeInputKeyboard();
            }

        }
        , 200L);
    }

    @Override
    public void fullScreen(final boolean arg0) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.fullScreen(arg0);
            }

        }
        );
    }

    @Override
    public void freshList(final GetSiteListResponse response) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                SelectSiteAct_.super.freshList(response);
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
                    SelectSiteAct_.super.doBack();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void getSiteList() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    SelectSiteAct_.super.getSiteList();
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
            intent_ = new Intent(context, SelectSiteAct_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, SelectSiteAct_.class);
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, SelectSiteAct_.class);
        }

        public Intent get() {
            return intent_;
        }

        public SelectSiteAct_.IntentBuilder_ flags(int flags) {
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
