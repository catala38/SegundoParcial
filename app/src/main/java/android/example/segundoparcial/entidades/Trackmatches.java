package android.example.segundoparcial.entidades;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trackmatches {

    @SerializedName("track")
    @Expose
    private List<Track2> track = null;

    public List<Track2> getTrack() {
        return track;
    }

    public void setTrack(List<Track2> track) {
        this.track = track;
    }

}