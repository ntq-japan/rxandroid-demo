# RxAndroid-demo
Demonstrate project for RxAndroid
#### I.Filter number
##### 1.Filter even number
Filter even numbers
```
View filterEvenNumbers = findViewById(R.id.filter_even_number_button);
filterEvenNumbers.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        subscriptionFilterNumbers = Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        // Each times data changed, this function are going to be call
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        // When it have no more data changed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // When it have an error
                    }

                    @Override
                    public void onNext(Integer integer) {
                    	// When get the return from the filter::call() method
                    }
                });
    }
});

// Return: 246810
```
##### 2.For each list number
Iterating with "forEach"
```
View forEachButton = findViewById(R.id.for_each_button);
forEachButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .forEach(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        // Each times data changed, this function are going to be call
                    }
                });
    }
});

// Return: 12345678910
```
##### 3.Take only N value from start
Take only the first 2 values emitted
```
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
                        // When it have no more data changed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // When it have an error
                    }

                    @Override
                    public void onNext(Integer integer) {
                    	// When get the return from the filter::call() method
                    }
                });
    }
});

// Return: 12
```
##### 4.Take only first value
Take only the first value emitted
```
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
                        // When it have no more data changed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // When it have an error
                    }

                    @Override
                    public void onNext(Integer integer) {
                    	// When get the return from the filter::call() method
                    }
                });
    }
});

// Return 1
```
##### 5.Take only last value
Take only the last value emitted
```
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
                        // When it have no more data changed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // When it have an error
                    }

                    @Override
                    public void onNext(Integer integer) {
                    	// When get the return from the filter::call() method
                    }
                });
    }
});

// Return 10
```
##### 6.Take only unique value
Remove duplicate values emitted
```
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

// Return: 19057
```
#### II.Group data
##### 1.Group by value with the same propertive
Group by value with the same propertive
```
View groupBy = findViewById(R.id.group_by_button);
groupBy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        subscriptionGroupBy = Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .groupBy(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                		// Each times data changed, this function are going to be call
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
                    @Override
                    public void call(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                    	// When get the return from the filter::call() method and create new subscriber for new group
                        subscriptionGroupByList = booleanIntegerGroupedObservable.toList().subscribe(new Subscriber<List<Integer>>() {
                            @Override
                            public void onCompleted() {
                        		// When it have no more data changed
                            }

                            @Override
                            public void onError(Throwable e) {
                        		// When it have an error
                            }

                            @Override
                            public void onNext(List<Integer> integer) {
                    			// When get the return from the filter::call() method
                            }
                        });
                    }
                });
    }
});

// Return [13579] and [246810]
```
##### 2.Pase value to another data type before notify
Does not have to emit items of the same type as the source Observable
```
View map = findViewById(R.id.map_button);
map.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        subscriptionMap = Observable
                .just("Rikimaru", "Amida")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String string) {
                // Each times data changed, this function are going to be call
                        return string.hashCode();
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        // When it have no more data changed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // When it have an error
                    }

                    @Override
                    public void onNext(Integer integer) {
            			// When get the return from the filter::call() method
                    }
                });
    }
});

// Return: -1256201652 and 63380186
```
#### III.CLick event
##### Set onlick's subcriber
Remember that do not set onclick after subcriber
```
Observable<Void> clickEventFirstObservable = RxView.clicks(clickFirst);
subscriptionClickFirst = clickEventFirstObservable.subscribe(new Action1<Void>() {
    @Override
    public void call(Void button) {
        // Each times data changed, this function are going to be call
    }
});
```
#### IV.Edit text changed event
##### 1.Listening the on text change listener
Note that nerver set AllTextCap propertive for the TextView instance.
```
EditText textChange = (EditText) findViewById(R.id.edit_text_change_button);
subscriptionTextChange = RxTextView.textChangeEvents(textChange)
        .subscribe(new Action1<TextViewTextChangeEvent>() {
            @Override
            public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                // Each times data changed, this function are going to be call
            }
        });
```
##### 2.Listening the on text change listener with a filter
```
TextView textChangeFilter = (TextView) findViewById(R.id.edit_text_filter_before_change_button);
subscriptionTextChangeFilter = RxTextView.textChangeEvents(textChangeFilter)
        .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
            @Override
            public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                // Each times data changed, this function are going to be call
                return textViewTextChangeEvent.text().length() % 2 == 0;
            }
        })
        .subscribe(new Action1<TextViewTextChangeEvent>() {
            @Override
            public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
            	// When get the return from the filter::call() method
            }
        });
```

#### V.Credit
##### 1.Mr.César Ferreira[^1]
##### 2.Realm blog[^2]
[^1]: [Party tricks with RxJava, RxAndroid & Retrolambda](https://medium.com/swlh/party-tricks-with-rxjava-rxandroid-retrolambda-1b06ed7cd29c#.s8f4i2f1s)
[^2]: [Reactive Android UI Programming with RxBinding](https://realm.io/news/donn-felker-reactive-android-ui-programming-with-rxbinding/)