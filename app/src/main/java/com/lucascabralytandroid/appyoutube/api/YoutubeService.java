package com.lucascabralytandroid.appyoutube.api;

import com.lucascabralytandroid.appyoutube.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YoutubeService {


    @GET("search")
    Call<Resultado> recuperarVideos(
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") String maxResults,
            @Query("key") String key,
            @Query("channelId") String channelId
    );


    /*
    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyB69fW6BK83Wkak-_ndgtmJttJyWgs9WJU
    &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg

    // https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResults=20&key=AIzaSyB69fW6BK83Wkak-_ndgtmJttJyWgs9WJU&channelId=UCVHFbqXqoYvEWM1Ddxl0QDg

    @GET("search?part={snippet}")
    recuperarVideos(@Path("snippet") String snippet ); **/

}
