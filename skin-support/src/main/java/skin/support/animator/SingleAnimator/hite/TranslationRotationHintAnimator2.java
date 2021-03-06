package skin.support.animator.SingleAnimator.hite;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.SingleAnimatorImpl;
import skin.support.animator.SkinAnimator;

/**
 * Created by erfli on 2/25/17.
 */

public class TranslationRotationHintAnimator2 extends SingleAnimatorImpl {

    private ObjectAnimator animator;

    private TranslationRotationHintAnimator2() {
    }


    public static TranslationRotationHintAnimator2 getInstance() {
        return new TranslationRotationHintAnimator2();
    }

    @Override
    public SkinAnimator apply(@NonNull final View view, @Nullable final Action action) {
        animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1, 0),
                PropertyValuesHolder.ofFloat("rotation", 0, 360),
                PropertyValuesHolder.ofFloat("translationY", 0, view.getHeight()),
                PropertyValuesHolder.ofFloat("translationX", 0, view.getWidth()));
        animator.setDuration(5 * PRE_DURATION);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setAlpha(1);
                view.animate().translationY(0).start();
                view.animate().translationX(0).start();
                view.setVisibility(View.GONE);
                if (action != null) {
                    action.action();
                }
            }
        });
        return this;
    }

    @Override
    public void start() {
        animator.start();
    }
}
