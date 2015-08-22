package ld33.zenominator.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 */
public final class GdxUtils {


    public static TextButton createButton(final String text, Skin skin, final ButtonListener listener) {
        final TextButton textButton = new TextButton(text, skin);

        textButton.addListener(new InputListener() {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
                listener.onClicked(textButton);
                return false;
            }
        });

        return textButton;
    }


    private GdxUtils() {
    }
}
