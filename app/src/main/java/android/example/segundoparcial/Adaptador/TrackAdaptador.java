package android.example.segundoparcial.Adaptador;

import android.content.Context;
import android.example.segundoparcial.R;
import android.example.segundoparcial.entidades.Artist;
import android.example.segundoparcial.entidades.Track;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrackAdaptador extends RecyclerView.Adapter<TrackAdaptador.TrackAdaptadorViewHolder> {

   public final List<Track> trackList;
   private LayoutInflater layoutInflater;

    public TrackAdaptador(List<Track> trackList, Context context) {
        this.trackList = trackList;
        layoutInflater = layoutInflater.from(context);
    }


    @NonNull
    @Override
    public TrackAdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_music, null);
        return new TrackAdaptadorViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackAdaptadorViewHolder holder, int position) {
     Track track = trackList.get(position);
     holder.nombre.setText(track.name);
     holder.duration.setText(track.duration);
     holder.artista.setText(track.artist.name);
    }

    @Override
    public int getItemCount() {

        return trackList.size();
    }

    public class TrackAdaptadorViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView artista;
        private TextView duration;
        private TrackAdaptador trackAdaptador;
        public TrackAdaptadorViewHolder(@NonNull View itemView, TrackAdaptador trackAdaptador) {
            super(itemView);
            nombre = itemView.findViewById(R.id.idNombre);
            artista = itemView.findViewById(R.id.idArtis);
            duration = itemView.findViewById(R.id.idDuracion);
            this.trackAdaptador = trackAdaptador;
        }
    }
}
