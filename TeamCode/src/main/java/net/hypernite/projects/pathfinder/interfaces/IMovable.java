package net.hypernite.projects.pathfinder.interfaces;

public interface IMovable<C> extends IProperty<C> {
    void moveX(int amount);
    void moveZ(int amount);
    int x();
    int z();
}
