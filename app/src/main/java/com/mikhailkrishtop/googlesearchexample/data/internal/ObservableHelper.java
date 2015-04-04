package com.mikhailkrishtop.googlesearchexample.data.internal;

import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mykhailo on 3/5/15.
 */
public class ObservableHelper {

    public static <T> Observable<T> ui(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("RxJava", throwable.getMessage());
                    }
                });
    }

}
