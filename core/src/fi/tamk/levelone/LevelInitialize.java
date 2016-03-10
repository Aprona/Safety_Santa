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
}
