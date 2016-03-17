package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

/**
 * Created by Aprona on 24.2.2016.
 */
public class GameScreen implements Screen {
    public float WINDOW_WIDTH = 8f;
    public float WINDOW_HEIGHT = 4.8f;
    private float WORLD_WIDTH_METERS = 1600f / 100;
    private float WORLD_HEIGHT_METERS = 928f / 100;

    public Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    public SafetySanta safetySanta;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public Hud hud;
    public boolean floorChangeInProgress = false;

    public LevelInitialize initialize;

    public GameScreen(Main main) {
        this.game = main;
        this.batch = game.getBatch();
        this.camera = game.getCamera();

        tiledMap = (new TmxMapLoader()).load("level_1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/100f);

        initialize = new LevelInitialize(this);
        hud = new Hud(this);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        for (Enemy guard : enemies) {
            guard.update();
        }

        hud.update();
        safetySanta.update();

        batch.begin();
        safetySanta.checkActions();

        resetColors();

        for (Enemy guard : enemies) {
            guard.draw(batch);
        }

        hud.draw(batch);

        batch.end();

        //if (!floorChangeInProgress) {
        //    safetySanta.moveSantaKeyboard();
        //}

        moveCamera();
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

        camera.update();
    }

    @Override
    public void show() {

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
        tiledMap.dispose();
        initialize = null;
        hud = null;

    }

    public float getWORLD_WIDTH_METERS () {
        return WORLD_WIDTH_METERS;
    }

    public void clearScreen () {
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void resetColors () {
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public ArrayList getEnemies () {
        return enemies;
    }

    public TiledMap getTiledMap () {
        return tiledMap;
    }
}
