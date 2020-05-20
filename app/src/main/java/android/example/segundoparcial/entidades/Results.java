package android.example.segundoparcial.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {


    @SerializedName("trackmatches")
    @Expose
    private Trackmatches trackmatches;

    public Trackmatches getTrackmatches() {
        return trackmatches;
    }

    public void setTrackmatches(Trackmatches trackmatches) {
        this.trackmatches = trackmatches;
    }


}