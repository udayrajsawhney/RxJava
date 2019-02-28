package testing;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class Testing {

    private int count = 0;
    public void intervalBlocking(){
        Observable<Long> source = Observable.interval(300, TimeUnit.MILLISECONDS).take(10);
        source.blockingSubscribe(i->count++);
        // TODO add junit5 testing code
    }

    public static void main(String[] args){

    }
}
