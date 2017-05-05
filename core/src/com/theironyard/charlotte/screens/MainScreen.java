package com.theironyard.charlotte.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Ben on 5/4/17.
 */
public class MainScreen implements Screen {
    private Stage stage;
    private MapRenderer mapRenderer;
    private TextureRegion dude;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextureRegion[][] mapText = TextureRegion.split(new Texture("art.png"), 32, 32);
        TiledMap map = new TmxMapLoader().load("map.tmx");

        dude = mapText[26][1];

        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);

        OrthographicCamera test = new OrthographicCamera();
        test.setToOrtho(false, 30, 30);

        mapRenderer.setView(test);

        Image dudeImage = new Image(dude) {
            int v = 10;

            @Override
            public void act(float delta) {
                super.act(delta);

                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    setPosition(getX(), getY() + v);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    setPosition(getX() - v, getY());
                }

                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    setPosition(getX(), getY() - v);
                }

                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    setPosition(getX() + v, getY());
                }


            }
        };



        dudeImage.setPosition(0, 0);
        dudeImage.setHeight(64);
        dudeImage.setWidth(64);

        stage.addActor(dudeImage);

    }

    @Override
    public void render(float delta) {
        mapRenderer.render();
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
