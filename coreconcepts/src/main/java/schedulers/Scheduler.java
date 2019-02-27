package schedulers;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static void Sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.parellelSchedulersOp();
    }
}
