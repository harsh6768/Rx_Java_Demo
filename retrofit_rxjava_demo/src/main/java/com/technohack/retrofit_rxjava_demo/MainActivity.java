package com.technohack.retrofit_rxjava_demo;

import android.os.Bundle;
import android.util.Log;

import com.technohack.retrofit_rxjava_demo.model.PostModel;
import com.technohack.retrofit_rxjava_demo.retrofit.JsonPlaceHolderApi;
import com.technohack.retrofit_rxjava_demo.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;

    private CompositeDisposable  compositeDisposable;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable=new CompositeDisposable();
        retrofit=RetrofitClient.getRetrofitInstance();

        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);


        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Log.i("TAG", "onCreate: BEFORE FETCH DATA METHOD!!!");

        fecthData();

        Log.i("TAG", "onCreate: AFTER FETCH DATA METHOD !!!");

    }

    private void fecthData() {

        //to use the disposable to handle the network data and run in into the io thread
        compositeDisposable.add(
                jsonPlaceHolderApi.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<PostModel>>() {
                            @Override
                            public void accept(List<PostModel> postModels) throws Exception {

                                Log.i("TAG", "accept: FetchData method Called!!!");
                                displayData(postModels);

                            }
                        })
           );

    }

    private void displayData(List<PostModel> postModels) {

        postAdapter=new PostAdapter(MainActivity.this,postModels);
        recyclerView.setAdapter(postAdapter);

    }

    @Override
    protected void onStop() {

        compositeDisposable.clear();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.dispose();
    }
}
