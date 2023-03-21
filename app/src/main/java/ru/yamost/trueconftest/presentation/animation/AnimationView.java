package ru.yamost.trueconftest.presentation.animation;

public interface AnimationView {
    void stopAnimation();
    void startAnimation();
    void killAnimationThread();
    void reverseAnimation();
    AnimationView setAnimationSpeed(float speed);
    void reloadAnimationFromPosition(float positionX, float positionY);
}
