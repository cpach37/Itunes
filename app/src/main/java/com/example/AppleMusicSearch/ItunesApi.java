package com.example.AppleMusicSearch;

import com.example.AppleMusicSearch.Itunes.Itunes;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ItunesApi {


    //Busqueda por term : https://itunes.apple.com/search?term=metallica

   @GET("/search")
   public Call<Itunes> getArtist(@QueryMap Map<String, String> options);



/* Busqueda por lookup:  https://itunes.apple.com/lookup?amgArtistId={1055684}&entity=song&limit=10&sort=top

   @GET("/lookup")
   public Call<Itunes> getArtistTopTenTracks(@QueryMap Map<String, String> options);

*/

}


