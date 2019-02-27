/*
 * Developed by udaysawhney on 9/2/19 10:08 AM
 * Last modified 9/2/19 10:02 AM
 * Github : https://github.com/udayrajsawhney/
 * Copyright (c) 2019. All rights reserved.
 */
package operators;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;

import java.util.HashSet;
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
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.takeWhile(e -> e.length() > 4).subscribe(System.out::println);
    }

    public void skipWhileOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.skipWhile(e -> e.length() > 4).subscribe(System.out::println);
    }

    public void distinctOp() {
        Observable<String> source = Observable.just("alpha", "alpha", "beta", "gamma", "omega", "delta");
        source.distinct(/*String::length*/).subscribe(System.out::println);
    }

    public void elementAtOp() {
        Observable<String> source = Observable.just("alpha", "alpha", "beta", "gamma", "omega", "delta");
        source.elementAt(2).subscribe(System.out::println);
    }

    public void mapOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.map(e -> e.length()).subscribe(System.out::println);
    }

    public void castOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.cast(Object.class)
                .subscribe(System.out::println);
    }

    public void startsWithOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.startWith("first string")
                .subscribe(System.out::println);
    }

    public void defaultIfEmptyOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        Observable.empty().defaultIfEmpty("default")
                .subscribe(System.out::println);
        Observable.empty().switchIfEmpty(source)
                .subscribe(System.out::println);
    }

    public void delayOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.delay(5, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Sleep(10000);
    }

    public void sortedOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.sorted((x, y) -> x.compareTo(y))/*Comparator.naturalOrder()*/
                .subscribe(System.out::println);
    }

    public void repeatOp() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma", "omega", "delta");
        source.repeat(2)
                .subscribe(System.out::println);
    }

    public void scanOp() {
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5, 6);
        source.scan((total, next) -> total + next)
                .subscribe(System.out::println);
    }

    public void reduceOp() {
        Observable<String> source = Observable.just("a", "b", "c");
        source.reduce((a, b) -> a + (b.equals("") ? "" : "," + b))
                .subscribe(System.out::println);
    }

    public void allOp() {
        Observable<String> source = Observable.just("a", "b", "c");
        source.all(i -> i.length() == 1)
                .subscribe(System.out::println);
    }

    public void anyOp() {
        Observable<String> source = Observable.just("a", "bc", "def");
        source.any(i -> i.length() == 1)
                .subscribe(System.out::println);
    }

    public void countOp() {
        Observable<String> source = Observable.just("a", "bc", "def");
        source.count()
                .subscribe(System.out::println);
    }

    public void containsOp() {
        Observable<String> source = Observable.just("a", "bc", "def");
        source.contains("ghij")
                .subscribe(System.out::println);
    }

    public void toListOp() {
        Observable<String> source = Observable.just("a", "bc", "def");
        source.toList()
                .subscribe(System.out::println);
    }

    public void toSortedListOp() {
        Observable<String> source = Observable.just("a", "bc", "def", "ghi");
        source.toSortedList()
                .subscribe(System.out::println);
    }

    public void toMapOp() {
        Observable<String> source = Observable.just("a", "b", "bc", "def", "ghi");
        source.toMap(String::length)
                .subscribe(System.out::println);
    }

    public void toMultiMapOp() {
        Observable<String> source = Observable.just("a", "b", "bc", "def", "ghi");
        source.toMultimap(String::length) /* . toMultiMap(i->i.charAt(0),String::length,Hashap::new) */
                .subscribe(System.out::println);
    }

    public void collectOp() {
        Observable.just("alpha", "beta", "gamma", "beta")
                .collect(HashSet::new, HashSet::add)
                .subscribe(System.out::println);
    }

    public void onErrorReturnItemOp() {
        Observable.just(1, 8, 6, 7, 0, 9, 3)
                .map(i -> 5 / i).onErrorReturnItem(-1)
                .subscribe(System.out::println);
    }

    public void onErrorResumeNextOp() {
        Observable.just(1, 8, 6, 7, 0, 9, 3)
                .map(i -> 5 / i).onErrorResumeNext(Observable.just(5, 6, 7))
                .subscribe(System.out::println);
    }

    public void retryOp() {
        Observable.just(1, 8, 6, 7, 0, 9, 3)
                .map(i -> 5 / i).retry(2)
                .subscribe(System.out::println);
    }

    public void actionOp() {
        Observable.just(1, 8, 6, 7, 0, 9, 3)
                .doOnNext(e -> System.out.println("Element is coming"))
                .doOnComplete(() -> System.out.println("Almost complete with emissions"))
                .doOnError(e -> System.out.println("Operation Failed"))
                .map(i -> 5 / i)
                .subscribe(System.out::println);

    }

    public void mergeOp() {
        Observable<String> source1 = Observable.just("Alpha", "beta");
        Observable<String> source2 = Observable.just("Alpha2", "beta2");
        Observable<String> source3 = Observable.just("Alpha3", "beta3");
        Observable<String> source4 = Observable.just("Alpha4", "beta4");
        Observable<String> source5 = Observable.just("Alpha5", "beta5");
        Observable.merge(source1, source2, source3).subscribe(System.out::println);
        //Observable.mergeArray(source1,source2,source3,source4,source5).subscribe(System.out::println);
        //source1.mergeWith(source2).subscribe(System.out::println);
    }

    public void flatMapOp() {
        Observable<String> source1 = Observable.just("Alpha", "beta", "gamma", "delta");
        source1.flatMap(s -> Observable.fromArray(s.split(""))).subscribe(System.out::println);
    }

    public void concatOp() {
        Observable<String> source1 = Observable.just("Alpha", "beta");
        Observable<String> source2 = Observable.just("Alpha2", "beta2");
        Observable.concat(source1, source2).subscribe(System.out::println);
        source1.concatWith(source2).subscribe(System.out::println);
    }

    public void zipOp() {
        Observable<Long> source1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> source2 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable.ambArray(source1, source2).subscribe(System.out::println);
        Sleep(5000);
        //Observable.zip(source1,source2,(e1,e2)->e1+"-"+e2).subscribe(System.out::println);
    }

    public void combineLatestOp() {
        Observable<Long> source1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> source2 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable.combineLatest(source1, source2, (e1, e2) -> e1 + "-" + e2).subscribe(System.out::println);
        Sleep(5000);
        // source1.withLatestFrom(source2,(e1,e2)->e1+"-"+e2).subscribe(System.out::println);
    }

    public void groupByOp() {
        Observable<String> colors = Observable.just("black", "yellow", "orange", "brown", "purple", "green", "blue", "grey", "coral", "corn");
        Observable<GroupedObservable<Character, String>> groups = colors.groupBy(s -> s.charAt(0));
        groups.flatMapSingle(g -> g.toList()).subscribe(System.out::println);
        groups.flatMapSingle(g -> g.reduce("", (x, y) -> x.equals("") ? y : x + "," + y).map(s -> g.getKey() + ":" + s))
                .subscribe(System.out::println);
    }

    public void MultiCastOp() {
        ConnectableObservable<Integer> source = Observable.range(1, 3).map(i -> i + 5).publish();
        source.subscribe(i -> System.out.println("Observer 1 : " + i));
        source.subscribe(i -> System.out.println("Observer 2 : " + i));
        source.connect();

        Observable<Integer> source1 = Observable.range(1, 3).map(i -> i + 5).publish().autoConnect(2);
        source1.subscribe(i -> System.out.println("Observer 1 : " + i));
        source1.subscribe(i -> System.out.println("Observer 1 : " + i));
    }

    public void shareOp() {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS).share();

        source.take(3).subscribe(i -> System.out.println("Observer 1 : " + i));
        Sleep(3000);
        source.subscribe(i -> System.out.println("Observer 1 : " + i));
        Sleep(3000);
    }

    public void BlockingOp() {
        Observable.interval(1,TimeUnit.SECONDS).take(10)
                .blockingSubscribe(System.out::println,Throwable::printStackTrace,()->System.out.println("observer 1 done"));
        Observable.interval(1200,TimeUnit.MICROSECONDS).take(5)
                .blockingSubscribe(System.out::println,Throwable::printStackTrace,()->System.out.println("observer 2 done"));
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
        operators.groupByOp();
    }
}
