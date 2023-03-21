package ru.yamost.trueconftest.presentation.animation;

public interface AnimationView {
    void stopAnimation();
    void startAnimation();
    void killAnimationThread();
    void reverseAnimation();
    AnimationView setAnimationSpeed(float speed);
    void restartAnimationFromPosition(float positionX, float positionY);
    AnimationView setBottomParentBorder(float y);
}
