package ninja.lbs.rxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import ninja.lbs.rxandroid.view.CustomLog;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class GroupActivity extends Activity {
    private Subscription subscriptionGroupBy;
    private Subscription subscriptionGroupByList;
    private Subscription subscriptionMap;

    private CustomLog mCustomLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_group);
        mCustomLog = (CustomLog) findViewById(R.id.custom_log);

        View groupBy = findViewById(R.id.group_by_button);
        groupBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionGroupBy = Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .groupBy(new Func1<Integer, Boolean>() {
                            @Override
                            public Boolean call(Integer integer) {
                                String message = "groupBy call(Boolean, Integer): " + integer;
                                Log.e("GroupActivity", message);
                                mCustomLog.append(message + "\n");
                                return integer % 2 == 0;
                            }
                        })
                        .subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
                            @Override
                            public void call(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                                String message = "groupBy call(GroupedObservable<Boolean, Integer>)";
                                Log.e("GroupActivity", message);
                                mCustomLog.append(message + "\n");
                                subscriptionGroupByList = booleanIntegerGroupedObservable.toList().subscribe(new Subscriber<List<Integer>>() {
                                    @Override
                                    public void onCompleted() {
                                        String message = "groupBy onCompleted()";
                                        Log.e("GroupActivity", message);
                                        mCustomLog.append(message + "\n");
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        String message = "groupBy onError(Throwable)";
                                        Log.e("GroupActivity", message);
                                        mCustomLog.append(message + "\n");
                                    }

                                    @Override
                                    public void onNext(List<Integer> integer) {
                                        String message = "groupBy onNext(Integer): " + integer;
                                        Log.e("GroupActivity", message);
                                        mCustomLog.append(message + "\n");
                                    }
                                });
                            }
                        });
            }
        });

        View map = findViewById(R.id.map_button);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionMap = Observable
                        .just("Rikimaru", "Amida")
                        .map(new Func1<String, Integer>() {
                            @Override
                            public Integer call(String string) {
                                String message = "map call(): " + string;
                                Log.e("GroupActivity", message);
                                mCustomLog.append(message + "\n");
                                return string.hashCode();
                            }
                        })
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                String message = "map onCompleted()";
                                Log.e("GroupActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                String message = "map onError(Throwable)";
                                Log.e("GroupActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                String message = "map onNext(Integer): " + integer;
                                Log.e("GroupActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (subscriptionGroupBy != null && subscriptionGroupBy.isUnsubscribed()) {
            subscriptionGroupBy.unsubscribe();
        }
        if (subscriptionGroupByList != null && subscriptionGroupByList.isUnsubscribed()) {
            subscriptionGroupByList.unsubscribe();
        }
        if (subscriptionMap != null && subscriptionMap.isUnsubscribed()) {
            subscriptionMap.unsubscribe();
        }
        super.onDestroy();
    }
}
