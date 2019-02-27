package alternatives;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Alternative {

    public void bufferOp(){
        Observable.interval(300, TimeUnit.MILLISECONDS).buffer(1,TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Sleep(10000);
    }

    public void boundaryBasedbufferOp(){
        Observable.interval(300, TimeUnit.MILLISECONDS).buffer(Observable.interval(1,TimeUnit.SECONDS))
                .subscribe(System.out::println);
        Sleep(10000);
    }

    public void windowOp(){
        Observable.range(0,50)
                .window(10).flatMapSingle(e->e.count())
                .subscribe(System.out::println);
        Sleep(10000);
    }

    public void switchingOp(){
        Observable<String> items= Observable.interval(300,TimeUnit.MILLISECONDS)
                .map(e->e+"switched to the first observable");

        Observable.interval(1,TimeUnit.SECONDS)
                .switchMap(i->items.doOnDispose(()->System.out.println("first Observable is being disposed of")))
                .subscribe(System.out::println);

        Sleep(5000);
    }

    public static void Sleep(long time) {
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Alternative alternative = new Alternative();
        alternative.windowOp();
    }
}
