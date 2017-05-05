package com.theironyard.charlotte.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NewScreen implements Screen {
    private Stage stage;
    private Game game;

    public NewScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        // handles input and stuff
        Gdx.input.setInputProcessor(stage);


        // the appearance of the textbutton we're about to use
        // Gloss over the details of the skin. We're just trying
        // to display a button.
        Skin uiSkin = new Skin(Gdx.files.internal("skins.json"));

        TextButton button = new TextButton("Start New Game", uiSkin);


        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));
            }
        });

        button.setX((Gdx.graphics.getWidth() / 2) - (button.getWidth() / 2));
        button.setY((Gdx.graphics.getHeight() / 2) - (button.getHeight() / 2));

        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
