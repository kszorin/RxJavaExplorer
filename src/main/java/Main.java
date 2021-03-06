import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by zorin on 07.04.17.
 */
public class Main {
    public static void main(String[] args) {
            Observable.OnSubscribe<String> onSubscriber = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Observable.OnSubscribe.call(): actions was completed");
            }
        };
        Observable<String> observable = Observable.from(new String[]{"1", "2", "3"});

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Subscriber.onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(observer);

        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(a -> {
            a*=10;
            System.out.println(a);
        });

        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
        subject.onNext(5);
        subject.onCompleted();
        subject.onNext(4);
        subject.onNext(5);

    }
}
