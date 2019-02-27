package transformativeoperators;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

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

    public void transformOp(){
        Observable.just("cat","dog","mouse","python").map(i->i.length())
                .filter(i->i>3).subscribe(System.out::println);

        Observable.just("cat","dog","mouse","python").compose(mapToNumber())
                .subscribe(System.out::println);
    }

    public static ObservableTransformer<String,Integer> mapToNumber(){
        return upstream -> upstream.map(i->i.length()).filter(i->i>3);
    }



    public static void main(String[] args){
        Transformative transform = new Transformative();
        transform.transformOp();
    }
}
