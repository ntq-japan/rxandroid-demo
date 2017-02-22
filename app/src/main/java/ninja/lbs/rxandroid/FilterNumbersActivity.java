package ninja.lbs.rxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ninja.lbs.rxandroid.view.CustomLog;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class FilterNumbersActivity extends Activity {
    private Subscription subscriptionFilterNumbers;
    private Subscription subscriptionTakeTwoValue;
    private Subscription subscriptionFirst;
    private Subscription subscriptionLast;
    private Subscription subscriptionDistinct;

    private CustomLog mCustomLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_filter_numbers);
        mCustomLog = (CustomLog) findViewById(R.id.custom_log);

        View filterEvenNumbers = findViewById(R.id.filter_even_number_button);
        filterEvenNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionFilterNumbers = Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .filter(new Func1<Integer, Boolean>() {
                            @Override
                            public Boolean call(Integer integer) {
                                String message = "filterEvenNumbers call(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                                return integer % 2 == 0;
                            }
                        })
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                String message = "filterEvenNumbers onCompleted()";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                String message = "filterEvenNumbers onError(Throwable)";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                String message = "filterEvenNumbers onNext(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });

        View forEachButton = findViewById(R.id.for_each_button);
        forEachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .forEach(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                String message = "forEachButton onNext(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });

        View takeTwoValue = findViewById(R.id.take_two_value_button);
        takeTwoValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionTakeTwoValue = Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .take(2)
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                String message = "takeTwoValue onCompleted()";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                String message = "takeTwoValue onError(Throwable)";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                String message = "takeTwoValue onNext(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });

        View first = findViewById(R.id.first_button);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionFirst = Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .first()
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                String message = "first onCompleted()";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                String message = "first onError(Throwable)";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                String message = "first onNext(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });

        View last = findViewById(R.id.last_button);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionLast = Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .first()
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                String message = "last onCompleted()";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                String message = "last onError(Throwable)";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                String message = "last onNext(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });

        View distinct = findViewById(R.id.distinct_button);
        distinct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionDistinct = Observable
                        .just(1, 9, 0, 0, 1, 5, 7, 0)
                        .distinct()
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                String message = "distinct onCompleted()";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                String message = "distinct onError(Throwable)";
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                String message = "distinct onNext(Integer): " + integer;
                                Log.e("FilterNumbersActivity", message);
                                mCustomLog.append(message + "\n");
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (subscriptionFilterNumbers != null && subscriptionFilterNumbers.isUnsubscribed()) {
            subscriptionFilterNumbers.unsubscribe();
        }
        if (subscriptionTakeTwoValue != null && subscriptionTakeTwoValue.isUnsubscribed()) {
            subscriptionTakeTwoValue.unsubscribe();
        }
        if (subscriptionFirst != null && subscriptionFirst.isUnsubscribed()) {
            subscriptionFirst.unsubscribe();
        }
        if (subscriptionLast != null && subscriptionLast.isUnsubscribed()) {
            subscriptionLast.unsubscribe();
        }
        if (subscriptionDistinct != null && subscriptionDistinct.isUnsubscribed()) {
            subscriptionDistinct.unsubscribe();
        }
        super.onDestroy();
    }
}
