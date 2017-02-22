package ninja.lbs.rxandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        Intent intent = null;
        switch (id) {
            case R.id.filter_numbers_screen:
                intent = new Intent(this, FilterNumbersActivity.class);
                break;
            case R.id.group_screen:
                intent = new Intent(this, GroupActivity.class);
                break;
            case R.id.on_click_screen:
                intent = new Intent(this, OnClickActivity.class);
                break;
            case R.id.edit_text_screen:
                intent = new Intent(this, EditTextActivity.class);
                break;
            default:
                // Do nothing
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
