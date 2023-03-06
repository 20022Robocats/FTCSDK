package first.other.pathfinder.interfaces;

public interface IProperty<T> extends IGetter<T>, ISetter<T> {
    T update(T newValue);
}
