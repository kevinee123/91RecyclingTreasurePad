package com.asto.a91recyclingtreasurepad.util

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RxUtil {
    companion object {
        fun <T> applySchedulers(): Observable.Transformer<T, T> {
            return Observable.Transformer {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}
