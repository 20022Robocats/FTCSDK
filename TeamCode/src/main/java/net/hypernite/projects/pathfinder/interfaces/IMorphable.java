package net.hypernite.projects.pathfinder.interfaces;

@SuppressWarnings("unused")
public interface IMorphable<THIS,OTHER> {
    THIS morph(OTHER other);
    OTHER morph();
}
