package com.prog2game.handlers;

import com.badlogic.gdx.Gdx;

public class AnimationHandler {

    //-Public Methods:

    public static float scroll(float startingPosition, float finalPosition, float speed) {
        float delta_1 = Gdx.graphics.getDeltaTime();
        if (startingPosition < finalPosition) {
            startingPosition += delta_1 * speed;
        } else if (startingPosition > finalPosition + 1) {
            startingPosition -= delta_1 * speed;
        }
        return startingPosition;
    }

}
