package android.example.segundoparcial;

import android.app.Activity;
import android.example.segundoparcial.entidades.Artist;
import android.example.segundoparcial.entidades.Track;
import android.example.segundoparcial.interfas.AlbumMusica;
import android.example.segundoparcial.entidades.BuscarCancion;
import android.example.segundoparcial.entidades.Results;
import android.example.segundoparcial.entidades.Track2;
import android.example.segundoparcial.entidades.Trackmatches;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMusicActivity extends AppCompatActivity {
    private Button btnBuscar;
    private EditText nombre,artista, buscarCancion;
    Trackmatches trackmatches;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmusic);

        nombre =(EditText)findViewById(R.id.txtCancion);
        artista=(EditText)findViewById(R.id.txtArtista);
        buscarCancion = (EditText)findViewById(R.id.txtBuscar);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              cancionNombre();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //temporal
        getMenuInflater().inflate(R.menu.menu_guardar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                AddMusic();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cancionNombre(){
        retrofit = new Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/").addConverterFactory(GsonConverterFactory.create()).build();
        AlbumMusica albumMusica = retrofit.create(AlbumMusica.class);
        String aux = buscarCancion.getText().toString();
        Call<BuscarCancion> call = albumMusica.getBusqueda("track.search",aux,"b284db959637031077380e7e2c6f2775","json");
        try{
            call.enqueue(new Callback<BuscarCancion>() {
                @Override
                public void onResponse(Call<BuscarCancion> call, Response<BuscarCancion> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    BuscarCancion buscarCancion = response.body();
                    Results results = buscarCancion.getResults();
                    trackmatches = results.getTrackmatches();
                    List<Track2> track2List = trackmatches.getTrack();
                    nombre.setText(track2List.get(0).getName());
                    artista.setText(track2List.get(0).getArtist());
                }

                @Override
                public void onFailure(Call<BuscarCancion> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"ERROR: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (IllegalStateException | JsonSyntaxException exception){
        }
    }

   private void AddMusic(){
     Track track = new Track();
     Artist artist = new Artist();

     track.name = nombre.getText().toString();
     track.duration = "00:00";
     artist.name = artista.getText().toString();
     track.artist = artist;

     MainActivity.listTrack.add(track);
    }


}


