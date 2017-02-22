package ninja.lbs.rxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import ninja.lbs.rxandroid.view.CustomLog;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class EditTextActivity extends Activity {
    private Subscription subscriptionTextChange;
    private Subscription subscriptionTextChangeFilter;

    private CustomLog mCustomLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_edit_text);
        mCustomLog = (CustomLog) findViewById(R.id.custom_log);

        final EditText textChange = (EditText) findViewById(R.id.edit_text_change_button);
        subscriptionTextChange = RxTextView.textChangeEvents(textChange)
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String message = "textChange call(TextViewTextChangeEvent): " + textViewTextChangeEvent.text();
                        Log.e("EditTextActivity", message);
                        mCustomLog.append(message + "\n");
                    }
                });

        final TextView textChangeFilter = (TextView) findViewById(R.id.edit_text_filter_before_change_button);
        subscriptionTextChangeFilter = RxTextView.textChangeEvents(textChangeFilter)
                .filter(new Func1<TextViewTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String message = "textChangeFilter Boolean call(TextViewTextChangeEvent): " + textViewTextChangeEvent.text();
                        Log.e("EditTextActivity", message);
                        mCustomLog.append(message + "\n");
                        return textViewTextChangeEvent.text().length() % 2 == 0;
                    }
                })
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String message = "textChangeFilter call(TextViewTextChangeEvent): " + textViewTextChangeEvent.text();
                        Log.e("EditTextActivity", message);
                        mCustomLog.append(message + "\n");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (subscriptionTextChange != null && subscriptionTextChange.isUnsubscribed()) {
            subscriptionTextChange.unsubscribe();
        }
        if (subscriptionTextChangeFilter != null && subscriptionTextChangeFilter.isUnsubscribed()) {
            subscriptionTextChangeFilter.unsubscribe();
        }
        super.onDestroy();
    }
}
