package ld33.zenominator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import ld33.zenominator.Zenominator;
import ld33.zenominator.screen.ScreenBase;
import ld33.zenominator.utils.ButtonListener;
import ld33.zenominator.utils.GdxUtils;

/**
 *
 */
public class MenuScreen extends ScreenBase<Zenominator> {

    private Stage stage;
    private Table table;

    @Override protected void createScreen(final Zenominator game, int width, int height) {
        final Skin skin = game.skin;

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label nameLabel = new Label("XENOMINATOR", skin);
        table.add(nameLabel).row();

        // Spacing.. must be other ways to do it.
        table.add(new Label("", skin)).row();

        table.add(GdxUtils.createButton("Start Game", skin, new ButtonListener() {
            @Override public void onClicked(Button button) {
                game.setCurrentScreen(game.gameScreen);
            }
        })).row();

        table.add(GdxUtils.createButton("Quit", skin, new ButtonListener() {
            @Override public void onClicked(Button button) {
                getGame().quit();
            }
        })).row();

    }

    @Override public InputProcessor getInputProcessor() {
        return stage;
    }

    @Override protected void doRender(Zenominator game, float deltaTime) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

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
