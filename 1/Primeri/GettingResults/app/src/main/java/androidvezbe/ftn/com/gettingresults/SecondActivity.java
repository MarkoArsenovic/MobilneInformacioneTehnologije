package androidvezbe.ftn.com.gettingresults;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = (TextView)findViewById(R.id.textViewText);
        textView.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }

    public void onClickClose(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.REQUEST_RESULT,"Povratna vrednost!");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
