package agh.ics.oop;

import java.util.Map;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IMapElement {
    public Vector2d getPosition();
    public String toString();
    public String getResources();
    public String getLabel();

}