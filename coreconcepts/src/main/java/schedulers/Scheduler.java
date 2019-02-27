package schedulers;


import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    public void schedulersOp() {
        Observable<String> source = Observable.just("black", "white", "red", "purple", "orange")
                .subscribeOn(Schedulers.computation());

        source.subscribe(s -> System.out.println("Observer 1:" + s + " on :" + LocalTime.now()));
        source.subscribe(s -> System.out.println("Observer 2:" + s + " on :" + LocalTime.now()));
        Sleep(3000);
    }

    public void customSchedulersOp() {
        Observable<String> source = Observable.just("black", "white", "red", "purple", "orange").subscribeOn(Schedulers.computation());

        source.subscribe(s -> System.out.println("Observer 1:" + s + " on :" + LocalTime.now()));
        source.subscribe(s -> System.out.println("Observer 2:" + s + " on :" + LocalTime.now()));

        ExecutorService service = Executors.newFixedThreadPool(10);
        io.reactivex.Scheduler scheduler = Schedulers.from(service);
        Sleep(3000);
    }

    public void parellelSchedulersOp() {
        Observable<String> source = Observable.just("black", "white", "red", "purple", "orange")
                .subscribeOn(Schedulers.computation());
        source.flatMap(s->Observable.just(s).subscribeOn(Schedulers.computation()))
                .subscribe(System.out::println);


        Sleep(3000);
    }

    public void flowableOP(){
        Flowable.range(0,50000000).doOnNext(s->System.out.println("emission number "+s+" is coming"))
                .subscribeOn(Schedulers.computation())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Sleep(200);
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Sleep(10000);
    }

    public void flowableCreateOP(){
        Flowable<String> flowableSource  = Flowable.create(source->{
           source.onNext("black");
           source.onNext("white");
        }, BackpressureStrategy.BUFFER);
    }

    public void backpressureOps(){
        Flowable<Long> source = Flowable.interval(1, TimeUnit.SECONDS);
        source.onBackpressureBuffer(10,()->System.out.println("overflow"), BackpressureOverflowStrategy.DROP_LATEST);
        source.onBackpressureLatest();
        source.onBackpressureDrop();
    }

    public static void Sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.flowableOP();
    }
}
