package ru.yamost.trueconftest.presentation.animation;

import android.util.Log;
import android.view.View;

class ViewAnimationThread extends Thread implements AnimationView {

    private boolean isAnimationStopped = true;
    private boolean isInterrupted = false;
    private boolean isWasReload = false;
    private boolean isWasRun = false;
    private final View view;
    private float currentPositionY;
    private float step = 1;
    private float parentBorderY = -1f;

    public ViewAnimationThread(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted) {
                if (!isAnimationStopped) {
                    if (isOutOfBorderScreen()) {
                        reverseAnimation();
                    }
                    currentPositionY += step;
                    view.setY(currentPositionY);
                    Thread.sleep(20);
                } else if (isWasReload) {
                    Thread.sleep(5000);
                    isWasReload = false;
                    isAnimationStopped = false;
                }
            }
        } catch (InterruptedException exception) {
            Log.d("ViewAnimationThread", "Thread was interrupted");
        }
    }

    private boolean isOutOfBorderScreen() {
        return parentBorderY != -1 &&
                (currentPositionY >= parentBorderY || currentPositionY <= 0);
    }

    @Override
    public void stopAnimation() {
        isAnimationStopped = true;
    }

    @Override
    public void startAnimation() {
        if (!isWasRun) {
            start();
            isWasRun = true;
        }
    }

    @Override
    public void reloadAnimationFromPosition(float positionX, float positionY) {
        view.setX(positionX);
        view.setY(positionY);
        currentPositionY = positionY;
        isWasReload = true;
        isAnimationStopped = true;
        if (step < 0) {
            step *= -1;
        }
        startAnimation();
    }

    @Override
    public void killAnimationThread() {
        isInterrupted = true;
    }

    @Override
    public void reverseAnimation() {
        step *= -1;
    }

    @Override
    public AnimationView setAnimationSpeed(float speed) {
        if (speed > 0) {
            step = speed;
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public AnimationView setBottomParentBorder(float y) {
        if (y > 0) {
            parentBorderY = y;
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
