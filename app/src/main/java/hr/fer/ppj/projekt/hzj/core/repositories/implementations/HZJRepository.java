package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.List;

import hr.fer.ppj.projekt.hzj.core.repositories.IHZJRepository;

/**
 * Created by ANTE on 21.5.2016..
 */
public abstract class HZJRepository<T> implements IHZJRepository<T> {
    protected Context context;

    public HZJRepository(Context context) {
        this.context = context;
    }

    // THIS METHODS BELOW WE DO NOT USE DUE TO USING RETROFIT API
    // in this methods we use queries to database where T is database Entity
    @Override
    public void add(T object) {

    }

    @Override
    public void addSome(List<T> objects) {

    }

    @Override
    public void remove(T object) {

    }

    @Override
    public void removeSome(List<T> objects) {

    }

    @Override
    public T get(int position) {

        return null;
    }

    @Override
    public List<T> getAll() {

        return null;
    }

    @Override
    public void fetchAt(int index) {

    }

    @Override
    public void fetchAll() {

    }

    @Override
    public void update(int index) {

    }

    @Override
    public void updateAll() {

    }
}
