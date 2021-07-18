package com.maximoprog.lomasgo.utils;

import android.content.Intent;
import android.net.Uri;

public class GoogleMaps {
    public static Intent cargarRuta(Double inicioLat, Double inicioLong, Double finLat, Double finLong) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + inicioLat + "," + inicioLong + "&daddr=" + finLat + "," + finLong));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        return intent;
    }
}
