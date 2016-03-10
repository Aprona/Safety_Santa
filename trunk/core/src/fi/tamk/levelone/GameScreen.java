package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

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
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    private float PADDING = 0.2f;

    private Texture   buttonActionImg;
    public  Rectangle buttonActionRect;
    private Texture   buttonLeftImg;
    public  Rectangle buttonLeftRect;
    private Texture   buttonRightImg;
    public  Rectangle buttonRightRect;

    private boolean canChangeFloor = true;
    private boolean changeFloor = false;
    private boolean floorUp;
    LevelInitialize initialize = new LevelInitialize();




    public GameScreen(Main g) {
        this.game = g;
        this.batch = game.getBatch();
        this.camera = game.getCamera(); //new OrthographicCamera();
        // this.camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
        tiledMap = (new TmxMapLoader()).load("background.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/100f);
        createButtons();

        safetySanta = new SafetySanta(this);
        safetySanta.setX(0.64f);
        safetySanta.setY(0.96f);
        initialize.getObjects(tiledMap);



        createEnemies();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.setProjectionMatrix(camera.combined);

        checkSantaPosition();
        for (Enemy guard : enemies) {
            guard.enemyUpdate();
        }

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        buttonUpdate();
        batch.begin();

        for (Enemy guard : enemies) {
            guard.enemyDraw(batch);
        }

        drawButtons();
        if (!changeFloor) {
            safetySanta.santaDraw(batch);
        } else {
            if (floorUp) {
                safetySanta.changeFloorUp(batch);
            } else {
                safetySanta.changeFloorDown(batch);
            }
        }
        batch.end();
        checkInput();
        safetySanta.moveSantaKeyboard();
        moveCamera();
        camera.update();

    }

    public void checkSantaPosition () {
        for (RectangleMapObject rectangleObject : initialize.getRectangleUpObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();
            Gdx.app.log("rect_x", String.valueOf(rectangle.getX()));
            Gdx.app.log("rect_width", String.valueOf(safetySanta.getX()));

            if (canChangeFloor) {
                if (rectangle.overlaps(safetySanta.getSantaRectangle()) &&
                        Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                    Gdx.app.log("up", "");
                    //safetySanta.setY(safetySanta.getY() + 2.56f);
                    canChangeFloor = false;
                    changeFloor = true;
                    floorUp = true;
                }
            }
        }

        for (RectangleMapObject rectangleObject : initialize.getRectangleDownObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();

            if (canChangeFloor) {
                if (safetySanta.getSantaRectangle().overlaps(rectangle) &&
                        Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                    Gdx.app.log("down", "");
                    //safetySanta.setY(safetySanta.getY() - 2.56f);
                    canChangeFloor = false;
                    changeFloor = true;
                    floorUp = false;
                }
            }
        }

        if (!canChangeFloor) {
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    canChangeFloor = true;
                }
            }, 3);
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

    public float getWORLD_WIDTH_METERS () {
        return WORLD_WIDTH_METERS;
    }

    public void clearScreen () {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void createButtons() {

        buttonActionImg = new Texture ("button_action.png");
        buttonActionRect = new Rectangle(
                (8f - buttonActionImg.getWidth() / 100f - PADDING),
                (0f + PADDING),
                buttonActionImg.getWidth() / 100f,
                buttonActionImg.getHeight() / 100f);

        buttonLeftImg = new Texture ("button_left.png");
        buttonLeftRect = new Rectangle(
                (0 + PADDING),
                (0 + PADDING),
                buttonLeftImg.getWidth() / 100f,
                buttonLeftImg.getHeight() / 100f);

        buttonRightImg = new Texture ("button_right.png");
        buttonRightRect = new Rectangle(
                1f,
                (0 + PADDING),
                buttonRightImg.getWidth() / 100f,
                buttonRightImg.getHeight() / 100f);
    }

    public void createEnemies () {

        for (RectangleMapObject rectangleObject : initialize.getEnemySpawnPointObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();
            // Gdx.app.log("rect_x", String.valueOf(rectangle.getX()));
            // Gdx.app.log("rect_width", String.valueOf(safetySanta.getX()));

            Enemy guard = new Enemy(this, rectangle.getX(), rectangle.getY());
            enemies.add(guard);

        }
    }
}
