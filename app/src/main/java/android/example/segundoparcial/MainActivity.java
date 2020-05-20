package android.example.segundoparcial;

import android.content.Intent;
import android.example.segundoparcial.Adaptador.TrackAdaptador;
import android.example.segundoparcial.interfas.AlbumMusica;
import android.example.segundoparcial.entidades.Artist;
import android.example.segundoparcial.entidades.Playlist;
import android.example.segundoparcial.entidades.Track;
import android.example.segundoparcial.entidades.Tracks;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static List<Track> listTrack = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrackAdaptador trackAdaptador;
    Tracks tracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  AddMusicActivity.class);
                startActivity(intent);
            }
        });
        ConsultarAlmbunes();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  AddMusicActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ConsultarAlmbunes() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/").addConverterFactory(GsonConverterFactory.create()).build();
        AlbumMusica albumMusica = retrofit.create(AlbumMusica.class);
        Call<Playlist> call = albumMusica.getAlbum();
        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code"+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                Playlist playlist = response.body();
                tracks = playlist.getTracks();
                listTrack = tracks.getTrack();
                recyclerView = (RecyclerView) findViewById(R.id.recyclearMusic);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);

                trackAdaptador = new TrackAdaptador(listTrack,MainActivity.this);
                recyclerView.setAdapter(trackAdaptador);
            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable t) {

            }
        });

    }
}
