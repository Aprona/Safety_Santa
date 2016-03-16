package fi.tamk.levelone;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by c5vhaapa on 10.3.2016.
 */
public class LevelInitialize {
    GameScreen gameScreen;

    Array<RectangleMapObject> rectangleUpObjects;
    Array<RectangleMapObject> rectangleDownObjects;
    Array<RectangleMapObject> enemySpawnPointObjects;
    Array<RectangleMapObject> santaSpawnPointObject;
    Array<RectangleMapObject> wallObjects;
    TiledMap tiledMap;

    public LevelInitialize(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.tiledMap = this.gameScreen.getTiledMap();
        getObjects(tiledMap);
        createEnemies ();
        createSanta ();
    }

    public void createEnemies() {
        for (RectangleMapObject rectangleObject :getEnemySpawnPointObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();
            Enemy guard = new Enemy(gameScreen, rectangle.getX(), rectangle.getY());
            gameScreen.getEnemies().add(guard);
        }
    }

    public void createSanta()  {
        for (RectangleMapObject rectangleObject : getSantaSpawnPointObject()) {
            Rectangle rectangle = rectangleObject.getRectangle();
            gameScreen.safetySanta = new SafetySanta(gameScreen, gameScreen.game.getBatch());
            gameScreen.safetySanta.setX(rectangle.getX());
            gameScreen.safetySanta.setY(rectangle.getY());
        }
    }

    public void getObjects(TiledMap tiledMap) {

        MapLayer stairwayUpLayer = tiledMap.getLayers().get("stairway_up");
        MapObjects stairwayUpObjects = stairwayUpLayer.getObjects();
        rectangleUpObjects = stairwayUpObjects.getByType(RectangleMapObject.class);
        scaleObjects(rectangleUpObjects);

        MapLayer stairwayDownLayer = tiledMap.getLayers().get("stairway_down");
        MapObjects stairwayDownObjects = stairwayDownLayer.getObjects();
        rectangleDownObjects = stairwayDownObjects.getByType(RectangleMapObject.class);
        scaleObjects(rectangleDownObjects);

        MapLayer enemySpawnPointsLayer = tiledMap.getLayers().get("enemy_spawnPoints");
        MapObjects enemySpawnObjects = enemySpawnPointsLayer.getObjects();
        enemySpawnPointObjects = enemySpawnObjects.getByType(RectangleMapObject.class);
        scaleObjects(enemySpawnPointObjects);

        MapLayer santaSpawnPointLayer = tiledMap.getLayers().get("santa_spawnPoint");
        MapObjects santaSpawnObject = santaSpawnPointLayer.getObjects();
        santaSpawnPointObject = santaSpawnObject.getByType(RectangleMapObject.class);
        scaleObjects(santaSpawnPointObject);

        MapLayer wallObjectsLayer = tiledMap.getLayers().get("wall_objects");
        MapObjects wallLayerObjects = wallObjectsLayer.getObjects();
        wallObjects = wallLayerObjects.getByType(RectangleMapObject.class);
        scaleObjects(wallObjects);
    }

    private void scaleObjects(Array<RectangleMapObject> mapObjects) {
        for (RectangleMapObject rectangleObject : mapObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();
            rectangle.setX(rectangle.getX() / 100f);
            rectangle.setY(rectangle.getY() / 100f);
            rectangle.setWidth(rectangle.getWidth() / 100f);
            rectangle.setHeight(rectangle.getHeight() / 100f);
        }
    }

    public Array<RectangleMapObject> getRectangleUpObjects() {
        return rectangleUpObjects;
    }

    public Array<RectangleMapObject> getRectangleDownObjects() {
        return rectangleDownObjects;
    }

    public Array<RectangleMapObject> getEnemySpawnPointObjects() {
        return enemySpawnPointObjects;
    }

    public Array<RectangleMapObject> getSantaSpawnPointObject() {
        return santaSpawnPointObject;
    }

    public Array<RectangleMapObject> getRectangleWallObjects() {
        return wallObjects;
    }
}
