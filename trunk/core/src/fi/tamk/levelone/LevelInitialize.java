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
    Array<RectangleMapObject> rectangleUpObjects;
    Array<RectangleMapObject> rectangleDownObjects;
    Array<RectangleMapObject> enemySpawnPointObjects;
    Array<RectangleMapObject> santaSpawnPointObject;
    Array<RectangleMapObject> wallObjects;
    TiledMap tiledMap;

    public LevelInitialize() {

    }

    public void getObjects(TiledMap tiledMap) {
        this.tiledMap = tiledMap;

        MapLayer stairwayUpLayer = tiledMap.getLayers().get("stairway_up");
        MapObjects stairwayUpObjects = stairwayUpLayer.getObjects();
        rectangleUpObjects = stairwayUpObjects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : rectangleUpObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();
            rectangle.setX(rectangle.getX() / 100f);
            rectangle.setY(rectangle.getY() / 100f);
            rectangle.setWidth(rectangle.getWidth() / 100f);
            rectangle.setHeight(rectangle.getHeight() / 100f);
        }

        MapLayer stairwayDownLayer = tiledMap.getLayers().get("stairway_down");
        MapObjects stairwayDownObjects = stairwayDownLayer.getObjects();
        rectangleDownObjects = stairwayDownObjects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : rectangleDownObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();
            rectangle.setX(rectangle.getX() / 100f);
            rectangle.setY(rectangle.getY() / 100f);
            rectangle.setWidth(rectangle.getWidth() / 100f);
            rectangle.setHeight(rectangle.getHeight() / 100f);
        }

        MapLayer enemySpawnPointsLayer = tiledMap.getLayers().get("enemy_spawnPoints");
        MapObjects enemySpawnObjects = enemySpawnPointsLayer.getObjects();
        enemySpawnPointObjects = enemySpawnObjects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : enemySpawnPointObjects) {
            Rectangle rectangle = rectangleObject.getRectangle();
            rectangle.setX(rectangle.getX() / 100f);
            rectangle.setY(rectangle.getY() / 100f);
            rectangle.setWidth(rectangle.getWidth() / 100f);
            rectangle.setHeight(rectangle.getHeight() / 100f);
        }

        MapLayer santaSpawnPointLayer = tiledMap.getLayers().get("santa_spawnPoint");
        MapObjects santaSpawnObject = santaSpawnPointLayer.getObjects();
        santaSpawnPointObject = santaSpawnObject.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : santaSpawnPointObject) {
            Rectangle rectangle = rectangleObject.getRectangle();
            rectangle.setX(rectangle.getX() / 100f);
            rectangle.setY(rectangle.getY() / 100f);
            rectangle.setWidth(rectangle.getWidth() / 100f);
            rectangle.setHeight(rectangle.getHeight() / 100f);
        }

        MapLayer wallObjectsLayer = tiledMap.getLayers().get("wall_objects");
        MapObjects wallLayerObjects = wallObjectsLayer.getObjects();
        wallObjects = wallLayerObjects.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : wallObjects) {
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

    public Array<RectangleMapObject> getSantaSpawnPointObject () {
        return santaSpawnPointObject;
    }

    public Array<RectangleMapObject> getRectangleWallObjects () {
        return wallObjects;
    }
}
