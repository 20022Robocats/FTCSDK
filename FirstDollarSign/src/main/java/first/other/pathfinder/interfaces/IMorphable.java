package first.other.pathfinder.interfaces;

@SuppressWarnings("unused")
public interface IMorphable<THIS,OTHER> {
    THIS morph(OTHER other);
    OTHER morph();
}
