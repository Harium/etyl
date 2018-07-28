package com.harium.etyl.layer;

import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.animation.OnFrameChangeListener;

/**
 * @author yuripourre
 */

public class AnimatedLayer extends ImageLayer {

    protected int needleX = 0;
    protected int needleY = 0;

    protected boolean once = false;
    protected boolean stopped = true;
    protected boolean animateHorizontally = true;
    protected boolean lockOnce = false;

    private int inc = 1;

    protected int frames = 1;
    protected int currentFrame = 0;
    protected int speed = 500;

    protected long startedAt = 0;
    protected long changedAt = 0;

    protected OnCompleteListener onAnimationFinishListener = DUMMY_ANIMATION_FINISH_LISTENER;

    protected OnFrameChangeListener onFrameChangeListener = DUMMY_FRAME_CHANGE_LISTENER;

    private static final OnCompleteListener DUMMY_ANIMATION_FINISH_LISTENER = new OnCompleteListener() {
        @Override
        public void onComplete(long now) {

        }
    };

    private static final OnFrameChangeListener DUMMY_FRAME_CHANGE_LISTENER = new OnFrameChangeListener() {
        @Override
        public void onFrameChange(int currentFrame) {

        }
    };

    /**
     * @param x
     * @param y
     */
    public AnimatedLayer(int x, int y) {
        this(x, y, 0, 0);
    }

    /**
     * @param x
     * @param y
     * @param srcW
     * @param srcH
     * @param path
     */
    public AnimatedLayer(int x, int y, int srcW, int srcH, String path) {
        super(x, y, path);

        this.srcW = srcW;
        this.srcH = srcH;
    }

    /**
     * @param x
     * @param y
     * @param srcW
     * @param srcH
     */
    public AnimatedLayer(int x, int y, int srcW, int srcH) {
        super(x, y);

        this.srcW = srcW;
        this.srcH = srcH;
    }

    public void restartAnimation() {
        stopped = false;
        resetAnimation();
    }

    public void resetAnimation() {
        srcX = needleX;
        srcY = needleY;
        currentFrame = 0;
    }

    public void setAnimateHorizontally(boolean animateHorizontally) {
        this.animateHorizontally = animateHorizontally;
    }

    public void animateWithFrame(int frame) {
        if (this.currentFrame != frame) {
            notifyFrameChangeListener(0, frame);
        }

        setFrame(frame);

        if (frame == frames - 1) {
            notifyAnimationFinishListener(0);
        }
    }

    public void animate(long now) {
        if (stopped) {
            startedAt = now;
            changedAt = now;

            restartAnimation();
        }

        if (now >= changedAt + speed) {
            changedAt = now;

            boolean hasNextFrame = nextFrame();

            notifyFrameChangeListener(now, currentFrame);

            if (!hasNextFrame) {
                notifyAnimationFinishListener(now);
            }
        }
    }

    // Notify Listener about the end of animation
    protected void notifyAnimationFinishListener(long now) {
        onAnimationFinishListener.onComplete(now);
    }

    // Notify Listener about the frame change
    protected void notifyFrameChangeListener(long now, int frame) {
        onFrameChangeListener.onFrameChange(frame);
    }

    public void animate() {
        nextFrame();
        stopped = false;
    }

    public void stopAnimation() {
        stopped = true;
    }

    public void animateOnce() {
        once = true;
        stopped = false;
        lockOnce = false;

        currentFrame = 0;
        setFrame(currentFrame);
    }

    public boolean nextFrame() {

        boolean hasNextFrame = true;

        if ((currentFrame < frames - 1) && (currentFrame >= 0)) {

            currentFrame += inc;

        } else {

            if (once) {
                lockOnce = true;
                //stopped = true;
                //setFrame(currentFrame);

            } else {
                currentFrame = 0;
            }

            hasNextFrame = false;

        }

        if (!stopped) {
            setFrame(currentFrame);
        }

        return hasNextFrame;
    }

    private void setFrame(int frame) {
        if (animateHorizontally) {
            setSrcX(needleX + srcW * frame);
        } else {
            setSrcY(needleY + srcH * frame);
        }
    }

    /**
     * @param stopped
     */
    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isStopped() {
        return stopped;
    }


    /**
     * Set Number of Frames
     *
     * @param frames
     */
    public void setFrames(int frames) {
        this.frames = frames;
    }

    public int getFrames() {
        return frames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public boolean getAnimateHorizontally() {
        return animateHorizontally;
    }

    public void setLockOnce(boolean lockOnce) {
        this.lockOnce = lockOnce;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNeedleX() {
        return needleX;
    }

    public void setNeedleX(int needleX) {
        this.needleX = needleX;
    }

    public int getNeedleY() {
        return needleY;
    }

    public void setNeedleY(int needleY) {
        this.needleY = needleY;
    }

    public OnCompleteListener getListener() {
        return onAnimationFinishListener;
    }

    public void setOnAnimationFinishListener(OnCompleteListener onAnimationFinishListener) {
        this.onAnimationFinishListener = onAnimationFinishListener;
    }

    public void setOnFrameChangeListener(OnFrameChangeListener onFrameChangeListener) {
        this.onFrameChangeListener = onFrameChangeListener;
    }

}