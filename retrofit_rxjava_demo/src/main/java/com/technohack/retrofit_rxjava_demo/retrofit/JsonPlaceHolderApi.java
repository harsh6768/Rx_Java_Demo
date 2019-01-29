package com.technohack.retrofit_rxjava_demo.retrofit;


import com.technohack.retrofit_rxjava_demo.model.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Observable<List<PostModel>> getPosts();


}
