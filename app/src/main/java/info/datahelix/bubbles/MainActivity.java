package info.datahelix.bubbles;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ConstraintLayout layout;
    private RandomCirclesView randomCirclesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        layout = (ConstraintLayout) findViewById(R.id.layout);
        randomCirclesView = new RandomCirclesView(this);
        layout.addView(randomCirclesView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == button.getId()) {
            randomCirclesView.drawCircle();
        }
    }

}