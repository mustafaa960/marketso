package com.miq.sms.models.dao;

import javafx.collections.ObservableList;

public interface DaoList<T> {

    public ObservableList<T> loadAll() throws Exception;

    public int insert(T t) throws Exception;

    public int update(T t) throws Exception;

    public int delete(T t) throws Exception;

    public T getData(T t) throws Exception;

    public T getDataById(int id) throws Exception;
    public T getDataByName(String name) throws Exception;
}
