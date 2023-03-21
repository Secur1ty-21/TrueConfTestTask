package ru.yamost.trueconftest.presentation;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import ru.yamost.trueconftest.R;
import ru.yamost.trueconftest.presentation.animation.AnimationView;
import ru.yamost.trueconftest.presentation.animation.TextViewAnimation;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private FrameLayout screen;
    private Locale locale;
    private final Locale russianLocale = new Locale("ru");
    private final Locale englishLocale = Locale.ENGLISH;
    private AnimationView textViewAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.animTextView);
        screen = findViewById(R.id.screen);
        textView.setOnClickListener(v -> onClickAnimText());
        screen.setOnTouchListener(onTouchScreen);
        locale = getResources().getConfiguration().getLocales().get(0);
    }

    private void onClickAnimText() {
        textViewAnimation.stopAnimation();
    }

    private final View.OnTouchListener onTouchScreen = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.performClick();
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                changeTextColorWithLocale(textView);
                if (textViewAnimation == null) {
                    textViewAnimation = new TextViewAnimation(textView, screen.getBottom());
                }
                textViewAnimation.reloadAnimationFromPosition(event.getX(), event.getY());
            }
            return true;
        }
    };

    private void changeTextColorWithLocale(TextView textView) {
        if (locale.getLanguage().equals(englishLocale.getLanguage())) {
            textView.setTextColor(getColor(R.color.red));
        } else if (locale.getLanguage().equals(russianLocale.getLanguage())) {
            textView.setTextColor(getColor(R.color.blue));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textViewAnimation.killAnimationThread();
    }
}