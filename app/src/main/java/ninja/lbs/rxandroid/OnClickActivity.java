package ninja.lbs.rxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import ninja.lbs.rxandroid.view.CustomLog;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

public class OnClickActivity extends Activity {
    private Subscription subscriptionClickFirst;
    private Subscription subscriptionClickLate;
    private Subscription subscriptionClickOldStyle;

    private CustomLog mCustomLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_click);
        mCustomLog = (CustomLog) findViewById(R.id.custom_log);

        View clickFirst = findViewById(R.id.click_first_button);
        clickFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "clickFirst onClick(View)";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }
        });
        Observable<Void> clickEventFirstObservable = RxView.clicks(clickFirst);
        subscriptionClickFirst = clickEventFirstObservable.subscribe(new Action1<Void>() {
            @Override
            public void call(Void button) {
                String message = "clickEventFirstObservable call(Void)";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }
        });

        View clickLate = findViewById(R.id.click_late_button);
        Observable<Void> clickEventLateObservable = RxView.clicks(clickLate);
        subscriptionClickLate = clickEventLateObservable.subscribe(new Action1<Void>() {
            @Override
            public void call(Void button) {
                String message = "clickEventLateObservable call(Void)";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }
        });
        clickLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "clickLate onClick(View)";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }
        });

        Observable<View> clickEventOldStyleObservable = Observable.create(new Observable.OnSubscribe<View>() {
            @Override
            public void call(final Subscriber<? super View> subscriber) {
                View clickOldStyle = findViewById(R.id.click_old_style_button);
                clickOldStyle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (subscriber.isUnsubscribed()) return;
                        subscriber.onNext(v);
                    }
                });
            }
        });
        subscriptionClickOldStyle = clickEventOldStyleObservable.subscribe(new Subscriber<View>() {
            @Override
            public void onCompleted() {
                String message = "clickEventOldStyleObservable onCompleted()";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }

            @Override
            public void onError(Throwable e) {
                String message = "clickEventOldStyleObservable onError(Throwable)";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }

            @Override
            public void onNext(View view) {
                String message = "clickEventOldStyleObservable onNext(View)";
                Log.e("OnClickActivity", message);
                mCustomLog.append(message + "\n");
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (subscriptionClickFirst != null && subscriptionClickFirst.isUnsubscribed()) {
            subscriptionClickFirst.unsubscribe();
        }
        if (subscriptionClickLate != null && subscriptionClickLate.isUnsubscribed()) {
            subscriptionClickLate.unsubscribe();
        }
        if (subscriptionClickOldStyle != null && subscriptionClickOldStyle.isUnsubscribed()) {
            subscriptionClickOldStyle.unsubscribe();
        }
        super.onDestroy();
    }
}
