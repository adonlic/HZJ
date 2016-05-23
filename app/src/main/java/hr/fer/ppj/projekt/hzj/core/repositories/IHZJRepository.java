package hr.fer.ppj.projekt.hzj.core.repositories;

import java.util.List;

/**
 * Created by ANTE on 21.5.2016..
 */
public interface IHZJRepository<T> {
    void add(T object);
    void addSome(List<T> objects);

    void remove(T object);
    void removeSome(List<T> objects);



    // FIND OBJECTS (D = {ALL, SPECIFIC}\BY_EXPRESSION)
    T get(int position);
    List<T> getAll();
    // optionally Find method so we can find objects specific to some expression



    void fetchAt(int index);
    void fetchAll();

    void update(int index);
    void updateAll();
}
