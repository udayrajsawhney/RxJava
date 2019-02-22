/*
 * Developed by udaysawhney on 9/2/19 10:08 AM
 * Last modified 9/2/19 10:02 AM
 * Github : https://github.com/udayrajsawhney/
 * Copyright (c) 2019. All rights reserved.
 */
package operators;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static io.reactivex.internal.operators.observable.ObservableBlockingSubscribe.subscribe;

public class Operators {
    public void filterOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega");
        source.filter(e -> e.length() > 4).subscribe(System.out::println);
    }

    public void takeOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega");
        source.take(2).subscribe(System.out::println);
    }

    public void skipOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega");
        source.skip(2).subscribe(System.out::println);
    }

    public void firstOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega");
        source.first("default").subscribe(System.out::println);
    }

    public void takeWhileOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.takeWhile(e->e.length()>4).subscribe(System.out::println);
    }

    public void skipWhileOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.skipWhile(e->e.length()>4).subscribe(System.out::println);
    }

    public void distinctOp() {
        Observable<String> source = Observable.just("alpha","alpha", "beta", "gamma", "omega","delta");
        source.distinct(/*String::length*/).subscribe(System.out::println);
    }

    public void elementAtOp() {
        Observable<String> source = Observable.just("alpha","alpha", "beta", "gamma", "omega","delta");
        source.elementAt(2).subscribe(System.out::println);
    }

    public void mapOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.map(e->e.length()).subscribe(System.out::println);
    }

    public void castOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.cast(Object.class)
                .subscribe(System.out::println);
    }

    public void startsWithOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.startWith("first string")
                .subscribe(System.out::println);
    }

    public void defaultIfEmptyOp(){
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        Observable.empty().defaultIfEmpty("default")
                .subscribe(System.out::println);
        Observable.empty().switchIfEmpty(source)
                .subscribe(System.out::println);
    }

    public void delayOp(){
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.delay(5, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Sleep(10000);
    }

    public void sortedOp(){
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.sorted((x, y) -> x.compareTo(y))/*Comparator.naturalOrder()*/
                .subscribe(System.out::println);
    }

    public void repeatOp(){
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega","delta");
        source.repeat(2)
                .subscribe(System.out::println);
    }

    public void scanOp(){
        Observable<Integer> source = Observable.just(1,2,3,4,5,6);
        source.scan((total,next)->total+next)
                .subscribe(System.out::println);
    }

    public void reduceOp(){
        Observable<String> source = Observable.just("a","b","c");
        source.reduce((a,b)->a+(b.equals("")?"":","+b))
                .subscribe(System.out::println);
    }

    public static void Sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Operators operators = new Operators();
        operators.scanOp();
    }
}
