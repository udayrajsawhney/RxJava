package transformativeoperators;


import io.reactivex.*;
import io.reactivex.disposables.Disposable;

public class Transformative {

    /*
    public static ObservableTransformer<String,Integer> mapToNumber(){
        return new ObservableTransformer<String, Integer>() {
            @Override
            public ObservableSource<Integer> apply(Observable<String> upstream) {
                return upstream.map(i->i.length()).filter(i->i>3);
            }
        };
    }*/

    public void transformOp() {
        Observable.just("cat", "dog", "mouse", "python").map(i -> i.length())
                .filter(i -> i > 3).subscribe(System.out::println);

        Observable.just("cat", "dog", "mouse", "python").compose(mapToNumber())
                .subscribe(System.out::println);
    }

    public static ObservableTransformer<String, Integer> mapToNumber() {
        return upstream -> upstream.map(i -> i.length()).filter(i -> i > 3);
    }

    public void customOp(){
        Observable.empty().cast(String.class).lift(defaultValue("new entry")).subscribe(System.out::println);
    }

    public static ObservableOperator<String, String> defaultValue(String value) {
        return new ObservableOperator<String, String>() {
            @Override
            public Observer<? super String> apply(Observer<? super String> observer) throws Exception {
                return new Observer<String>() {
                    boolean empty = true;

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        empty = false;
                        observer.onNext(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        if (empty){
                            observer.onNext(value);
                        } else {
                            observer.onComplete();
                        }
                    }
                };
            }
        };
    }

    public static void main(String[] args) {
        Transformative transform = new Transformative();
        transform.transformOp();
    }
}
