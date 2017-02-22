package ninja.lbs.rxandroid.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ninja.lbs.rxandroid.R;

/**
 * Customize application log
 */
public class CustomLog extends RelativeLayout {
    private TextView mTextView;
    private ScrollView mScrollConstain;

    // Log String builder
    private StringBuilder mStringBuilder = new StringBuilder();

    public CustomLog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // Initial default view
        initView(context, attrs);
    }

    public CustomLog(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        // Initial default view
        initView(context, attrs);
    }

    public CustomLog(Context context) {
        super(context, null, 0);

        // Initial default view
        initView(context, null);
    }

    /**
     * Initial view by default
     *
     * @param context Context of the current view
     * @param attrs   Input attribute
     */
    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.widget_log, this);
        }

        mTextView = (TextView) findViewById(R.id.text_view);
        mScrollConstain = (ScrollView) findViewById(R.id.scroll_view);
    }

    public void clear() {
        mStringBuilder = new StringBuilder();
        mTextView.setText("");
    }

    public void append(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            mStringBuilder.append(text);

            mTextView.setText(mStringBuilder.toString());
            mScrollConstain.post(new Runnable() {
                @Override
                public void run() {
                    mScrollConstain.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
    }

    public void insert(int index, CharSequence text) {
        int size = mStringBuilder.length();
        if (!TextUtils.isEmpty(text) && index > 0 && index < size) {
            mStringBuilder.insert(index, text);

            mTextView.setText(mStringBuilder.toString());
        }
    }

    public CharSequence getText() {
        return mTextView.getText();
    }
}
