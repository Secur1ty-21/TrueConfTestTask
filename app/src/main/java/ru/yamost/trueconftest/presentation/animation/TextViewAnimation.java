package ru.yamost.trueconftest.presentation.animation;

import android.widget.TextView;

public class TextViewAnimation implements AnimationView {

    private final AnimationView viewAnimationThread;

    public TextViewAnimation(TextView textView, float parentBottomBorder) {
        viewAnimationThread = new ViewAnimationThread(textView)
                .setBottomParentBorder(parentBottomBorder)
                .setAnimationSpeed(5);
    }

    public TextViewAnimation(TextView textView) {
        viewAnimationThread = new ViewAnimationThread(textView)
                .setAnimationSpeed(5);
    }

    @Override
    public void stopAnimation() {
        viewAnimationThread.stopAnimation();
    }

    @Override
    public void startAnimation() {
        viewAnimationThread.startAnimation();
    }

    @Override
    public void killAnimationThread() {
        viewAnimationThread.killAnimationThread();
    }

    @Override
    public void reverseAnimation() {
        viewAnimationThread.reverseAnimation();
    }

    @Override
    public AnimationView setAnimationSpeed(float speed) {
        return viewAnimationThread.setAnimationSpeed(speed);
    }

    @Override
    public void restartAnimationFromPosition(float positionX, float positionY) {
        viewAnimationThread.restartAnimationFromPosition(positionX, positionY);
    }

    @Override
    public AnimationView setBottomParentBorder(float y) {
        return viewAnimationThread.setBottomParentBorder(y);
    }
}
