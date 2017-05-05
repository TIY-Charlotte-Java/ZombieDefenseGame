package com.theironyard.charlotte.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MainScreen implements Screen {
    private Stage stage;
    private MapRenderer mapRenderer;
    private TextureRegion dude;
    private Image dudeImage;
    private Game game;
    private TiledMap map;

    public MainScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextureRegion[][] mapText = TextureRegion.split(new Texture("art.png"), 32, 32);
        map = new TmxMapLoader().load("map.tmx");

        dude = mapText[26][1];
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);

        OrthographicCamera test = new OrthographicCamera();
        test.setToOrtho(false, 30, 30);

        mapRenderer.setView(test);

        dudeImage = new Image(dude) {
            int v = 10;

            @Override
            public void act(float delta) {
                super.act(delta);

                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    if (!runsIntoStuff(getX(), getY() + v)) {
                        setPosition(getX(), getY() + v);
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    if (!runsIntoStuff(getX() - v, getY())) {
                        setPosition(getX() - v, getY());
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    if (!runsIntoStuff(getX(), getY() - v)) {
                        setPosition(getX(), getY() - v);
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    if (!runsIntoStuff(getX() + v, getY())) {
                        setPosition(getX() + v, getY());
                    }
                }

                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                    game.setScreen(new NewScreen(game));
                }

            }
        };



        dudeImage.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

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

    private boolean runsIntoStuff(float x, float y) {
        Rectangle rect = new Rectangle(x, y, dudeImage.getWidth(), dudeImage.getHeight());

        MapLayers layers = map.getLayers();

        MapLayer boxLayer = layers.get("Boxes");

        MapObjects objects = boxLayer.getObjects();

        for (MapObject obj : objects) {
            if (obj instanceof RectangleMapObject) {
                RectangleMapObject rectObj = (RectangleMapObject) obj;

                if (Intersector.overlaps(rect, rectObj.getRectangle())) {
                    return true;
                }
            } else if (obj instanceof PolygonMapObject) {
                PolygonMapObject polyObj = (PolygonMapObject)obj;

//                Intersector.overlapConvexPolygons(polyObj, )
            }
        }

        return false;
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
