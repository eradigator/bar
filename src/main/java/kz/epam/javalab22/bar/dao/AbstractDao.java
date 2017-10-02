package kz.epam.javalab22.bar.dao;

public abstract class AbstractDao<T> {
    public abstract T update(T entity);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity);
}
