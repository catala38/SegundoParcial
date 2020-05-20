package android.example.segundoparcial.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    @Expose
    public String name;

    public Artist(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}