package com.technohack.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private String sayHello="Hello From RxJava ";

    private Observable<String> observable;
    private Observer<String> observer;

    private TextView hello1;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello1=findViewById(R.id.main_textId);

        //Observable emits the data
        observable=Observable.just(sayHello);


        //TODO ADDING SCHEDULERS INTO THE OBSERVABLE
        // TODO by using the scheduler.io it will take the data emits USING IO THREAD
        observable.subscribeOn(Schedulers.io());

        //TODO then it emits data into the main thread
        observable.observeOn(AndroidSchedulers.mainThread());


        //observer take data emitted by data by observable

        observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.d("TAG", "onSubscribe: Invoked !!!");

                //TODO setting the data to disposable
                disposable=d;

            }

            //Observable data will pass into the onNext method
            @Override
            public void onNext(String s) {

                Log.d("TAG", "onNext: invoked !!!");

                //set text getting from observer
                hello1.setText(s);

            }

            @Override
            public void onError(Throwable e) {

                Log.d("TAG", "onError: invoked!!!");
            }

            @Override
            public void onComplete() {

                Log.d("TAG", "onComplete: invoked");

            }
        };


        // If observable don't subscribe observer
        //subscribe
        observable.subscribe(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //TODO    when a user click back button  without loading the data  then it will dispose the data
        // TODO  and app won't crash
        disposable.dispose();
    }
}
