/*
 * Developed by udaysawhney on 9/2/19 10:08 AM
 * Last modified 9/2/19 9:51 AM
 * Github : https://github.com/udayrajsawhney/
 * Copyright (c) 2019. All rights reserved.
 */

import io.reactivex.*;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class basics {

    public void function1(){
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("first");
                emitter.onNext("second");
                emitter.onNext("third");
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        Observable<String> source2 = Observable.just("first","second","third");
    }

    public void function2(){
        Observable<String> source = Observable.just("first","second","third");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Emissions Complete");
            }
        };
        source.subscribe(observer);
    }

    public void function3() {
        Observable<String> source2 = Observable.just("first","second","third");
        source2.subscribe(System.out::println,Throwable::printStackTrace,()->System.out.println("Emissions Complete"));
    }

    public void function4() {
        Observable<String> source2 = Observable.just("first","second","third");
        source2.subscribe((e)->System.out.println("Observer 1 " + e));
        source2.subscribe((e)->System.out.println("Observer 2 " + e));

        System.out.println("----------------From Iterable factory----------------");

        List<String> list = Arrays.asList("java","kotlin","rxjava","rxandroid");
        Observable<String> source = Observable.fromIterable(list);

        source.subscribe((e)->System.out.println("Observer 1 " + e));
        source.subscribe((e)->System.out.println("Observer 2 " + e));
    }

    public void function5() {

        List<String> list = Arrays.asList("java","kotlin","rxjava","rxandroid");
        Observable<String> source = Observable.fromIterable(list);
        ConnectableObservable<String> hot = source.publish();

        hot.subscribe((e)->System.out.println("Observer 1 " + e));
        hot.subscribe((e)->System.out.println("Observer 2 " + e));

        hot.connect();
    }

    public void function6() {

        Observable.interval(200, TimeUnit.MILLISECONDS).subscribe(System.out::println);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void function7() {

        Observable.range(0,10).subscribe(System.out::println);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void function8() {
        Observable.empty();
        Observable.never();

        /*  Future<String> futureValue = ...;
            Observable.fromFuture(futureValue)
            .map(String::length)
            .subscribe(System.out::println); */
    }

    public void function9() {
        Observable.error(new Exception("Crash"))
                .subscribe(System.out::println,Throwable::printStackTrace,()->System.out.println("Emission Complete"));
    }

    private static int a = 0, b = 10;
    public void function10() {
        Observable<Integer> source = Observable.defer(()->Observable.range(a,b));
        source.subscribe(System.out::println);
        b=15;
        source.subscribe(e->System.out.println("Observer 2 "+e));
    }

    public void function11() {
        SingleObserver observer = new SingleObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }
        };
        Observable.just("a","b").first("first").subscribe(System.out::println);
    }

    public void function12() {
        MaybeObserver observer = new MaybeObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void function13() {
        Completable.fromRunnable(()->System.out.println("Calling on complete"))
        .subscribe(()->System.out.println("Done!"));
    }

    public static void Sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void function14() {
        Observable<Long> observable = Observable.interval(1,TimeUnit.SECONDS);
        Disposable disposable = observable.subscribe(System.out::println);
        Sleep(5000);
        disposable.dispose();
        Sleep(5000);
    }

    public void function15() {
        Observable<Long> observable = Observable.interval(1,TimeUnit.SECONDS);
        Observer<Integer> observer = new Observer<Integer>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable=d;
            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private static final CompositeDisposable disposables = new CompositeDisposable();
    public void function16() {
        Observable<Long> observable = Observable.interval(1,TimeUnit.SECONDS);

        Disposable disposable1 = observable.subscribe(time -> System.out.println("Observer 1 "+time));
        Disposable disposable2 = observable.subscribe(time -> System.out.println("Observer 2 "+time));

        disposables.addAll(disposable1,disposable2);
        disposables.dispose();
    }

    public  static  void main(String[] args) {
        basics obj = new basics();
        obj.function14();
    }
}
