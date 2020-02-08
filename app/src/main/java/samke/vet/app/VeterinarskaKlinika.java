package samke.vet.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VeterinarskaKlinika implements Serializable {
    private String name, address, picture;
    private double lat, lng;
    private float distance;
    private List<String> description, type;

    public VeterinarskaKlinika() {
        this.description = new ArrayList<>();
    }

    public VeterinarskaKlinika(String name, String address, String picture, double lat, double lng, float distance, List<String> description, List<String> type) {
        this.name = name;
        this.address = address;
        this.picture = picture;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getTypesAsString(boolean commaSeparator) {
        String returnType = "";
        for (String t : type) {
            if(commaSeparator) {
                returnType += t + ", ";
            } else {
                returnType += t + "\n";
            }

        }
        if(commaSeparator) {
            returnType = returnType.substring(0, returnType.length()-2);
        }
        return returnType;
    }

}
