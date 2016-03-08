package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Aprona on 24.2.2016.
 */
public class GameScreen implements Screen {
    private float WINDOW_WIDTH = 8f;
    private float WINDOW_HEIGHT = 4.8f;
    private float WORLD_WIDTH_METERS = 4096f / 100;
    private float WORLD_HEIGHT_METERS = 2048 / 100;

    private Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SafetySanta safetySanta;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private float PADDING = 0.2f;

    private Texture buttonActionImg = new Texture ("button_action.png");
    public Rectangle buttonActionRect = new Rectangle(
            (8f - buttonActionImg.getWidth() / 100f - PADDING),
            (0f + PADDING),
            buttonActionImg.getWidth() / 100f,
            buttonActionImg.getHeight() / 100f);
    private Texture buttonLeftImg = new Texture ("button_left.png");
    public  Rectangle buttonLeftRect = new Rectangle(
            (0 + PADDING),
            (0 + PADDING),
            buttonLeftImg.getWidth() / 100f,
            buttonLeftImg.getHeight() / 100f);
    private Texture buttonRightImg = new Texture ("button_right.png");
    public Rectangle buttonRightRect = new Rectangle(
            1f,
            (0 + PADDING),
            buttonRightImg.getWidth() / 100f,
            buttonRightImg.getHeight() / 100f);

    Array<RectangleMapObject> rectangleUpObjects;
    Array<RectangleMapObject> rectangleDownObjects;


    public GameScreen(Main g) {
        game = g;
        batch = this.game.getBatch();
        tiledMap = (new TmxMapLoader()).load("background.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/100f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 8f, 4.8F);
        safetySanta = new SafetySanta(this);

        safetySanta.setX(0.64f);
        safetySanta.setY(0.96f);

        MapLayer stairwayUpLayer = tiledMap.getLayers().get("stairway_up");
        MapObjects stairwayUpObjects = stairwayUpLayer.getObjects();
        rectangleUpObjects = stairwayUpObjects.getByType(RectangleMapObject.class);
        MapLayer stairwayDownLayer = tiledMap.getLayers().get("stairway_down");
        MapObjects stairwayDownObjects = stairwayDownLayer.getObjects();
        rectangleDownObjects = stairwayDownObjects.getByType(RectangleMapObject.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();

        buttonUpdate();
        safetySanta.moveSantaKeyboard();

        checkInput();
        checkSantaPosition();

        moveCamera();

        batch.begin();
        drawButtons();
        safetySanta.santaDraw(batch);
        batch.end();
    }

    public void checkSantaPosition () {
        for (RectangleMapObject rectangleObject : rectangleUpObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();

            Gdx.app.log("rect_x", String.valueOf(rectangle.getX()));
            Gdx.app.log("rect_width", String.valueOf(safetySanta.getX()));


            if (rectangle.overlaps(safetySanta.getSantaRectangle())) {
                Gdx.app.log("up", "");
                safetySanta.setX(safetySanta.getY() + 2.24f);
            }
        }

        for (RectangleMapObject rectangleObject : rectangleDownObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (safetySanta.getSantaRectangle().overlaps(rectangle)) {
                Gdx.app.log("down", "");
                safetySanta.setX(safetySanta.getY() - 2.24f);
            }
        }
    }

    public void checkInput () {
        if (Gdx.input.isTouched()) {
            float realX = Gdx.input.getX();
            float realY = Gdx.input.getY();

            Vector3 touchPos = new Vector3(realX, realY, 0);
            camera.unproject(touchPos);
            safetySanta.santaUpdate(touchPos);
        }
    }

    public void drawButtons () {
        batch.draw(
                buttonActionImg,
                buttonActionRect.x,
                buttonActionRect.y,
                buttonActionImg.getWidth() / 100f,
                buttonActionImg.getHeight() / 100f);
        batch.draw(
                buttonLeftImg,
                buttonLeftRect.x,
                buttonLeftRect.y,
                buttonLeftImg.getWidth() / 100f,
                buttonRightImg.getHeight() / 100f);
        batch.draw(
                buttonRightImg,
                buttonRightRect.x,
                buttonRightRect.y,
                buttonRightImg.getWidth() / 100f,
                buttonRightImg.getHeight() / 100f);
    }

    private void checkCollisions () {
        MapLayer collisionObjectLayer = (MapLayer)tiledMap.getLayers().get("wall_objects");
        MapObjects mapObjects = collisionObjectLayer.getObjects();
        Array<RectangleMapObject> rectangleObjects = mapObjects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : rectangleObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();
            /*if () {

            }*/
        }
    }

    private void moveCamera () {
        camera.position.x = safetySanta.getX() + (safetySanta.getRectangleWidth() / 2);
        camera.position.y = safetySanta.getY() + (safetySanta.getRectangleHeight() / 2);

        if (camera.position.x > (WORLD_WIDTH_METERS - WINDOW_WIDTH / 2)) {
            camera.position.x = (WORLD_WIDTH_METERS - WINDOW_WIDTH / 2);
        }

        if (camera.position.x < WINDOW_WIDTH / 2) {
            camera.position.x = WINDOW_WIDTH / 2;
        }

        if (camera.position.y > (WORLD_HEIGHT_METERS - WINDOW_HEIGHT / 2)) {
            camera.position.y = (WORLD_HEIGHT_METERS - WINDOW_HEIGHT / 2);
        }

        if (camera.position.y < WINDOW_HEIGHT / 2) {
            camera.position.y = WINDOW_HEIGHT / 2;
        }
    }

    public void buttonUpdate () {
        buttonLeftRect.x = camera.position.x - WINDOW_WIDTH / 2 + PADDING;
        buttonLeftRect.y = camera.position.y - WINDOW_HEIGHT / 2 + PADDING;
        buttonRightRect.x = camera.position.x - WINDOW_WIDTH / 2 + PADDING * 2 + buttonLeftImg.getWidth() / 100f;
        buttonRightRect.y = camera.position.y - WINDOW_HEIGHT / 2 + PADDING;
        buttonActionRect.x = camera.position.x + WINDOW_WIDTH / 2 - PADDING - buttonActionImg.getWidth() / 100f;
        buttonActionRect.y = camera.position.y - WINDOW_HEIGHT / 2 + PADDING;
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

    public void clearScreen () {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
