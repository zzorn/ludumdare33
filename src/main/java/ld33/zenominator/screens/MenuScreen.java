package ld33.zenominator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ld33.zenominator.Zenominator;
import ld33.zenominator.screen.ScreenBase;

/**
 *
 */
public class MenuScreen extends ScreenBase<Zenominator> {

    private Stage stage;
    private Table table;

    @Override protected void createScreen(final Zenominator game, int width, int height) {
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label nameLabel = new Label("Hello World", game.skin);
        table.add(nameLabel);

        table.row();

        TextButton button = new TextButton("Start Game", game.skin);
        table.add(button);
        button.addListener(new InputListener() {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.speechService.say("Welcome to the game.  You just lost it.");
                return false;
            }
        });
    }

    @Override public InputProcessor getInputProcessor() {
        return stage;
    }

    @Override protected void doRender(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);

        stage.draw();
    }

    @Override protected void onResize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void dispose() {
        stage.dispose();
    }
}
