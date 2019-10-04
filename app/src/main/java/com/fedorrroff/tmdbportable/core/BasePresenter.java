package com.fedorrroff.tmdbportable.core;

public interface BasePresenter<T extends BaseFragment> {

    void attachView(final T view);

}
