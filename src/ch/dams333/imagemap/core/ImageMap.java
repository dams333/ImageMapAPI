package ch.dams333.imagemap.core;

import java.util.ArrayList;
import java.util.UUID;

public class ImageMap {

    private UUID uuid;
    private String path;
    private ArrayList<Short> mapIDs;

    public ImageMap(UUID uuid, String path, ArrayList<Short> mapIDs) {
        this.uuid = uuid;
        this.path = path;
        this.mapIDs = mapIDs;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<Short> getMapIDs() {
        return mapIDs;
    }
}
