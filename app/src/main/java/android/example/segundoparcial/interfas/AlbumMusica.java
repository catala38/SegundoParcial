package android.example.segundoparcial.interfas;

import android.example.segundoparcial.entidades.BuscarCancion;
import android.example.segundoparcial.entidades.Playlist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlbumMusica {


    @GET("/2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<Playlist> getAlbum();


    @GET("2.0")
    Call<BuscarCancion> getBusqueda(@Query("method") String method, @Query("track") String track, @Query("api_key") String api_key,
                                    @Query("format") String format);
}
