package com.example.zaboon.pause_toi;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

public class MapsActivity extends FragmentActivity {
    private GoogleMap googleMap;
    private final LatLng EPITECH = new LatLng(43.311098, 5.369033);
    private final LatLng KIEL = new LatLng(43.311098, 5.369033);
    private Marker marker;
    private Hashtable<String, String> markers;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        initImageLoader();
        markers = new Hashtable<String, String>();
        imageLoader = ImageLoader.getInstance();

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.tree)        //    Display Stub Image
                .showImageForEmptyUri(R.drawable.tree)    //    If Empty image found
                .cacheInMemory()
                .cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();

        if (googleMap != null) {

            googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

/*            final Marker hamburg = googleMap.addMarker(new MarkerOptions().position(EPITECH)
                    .title("Epitech"));
            markers.put(hamburg.getId(), "https://wallpaperscraft.com/image/nature_park_beautiful_landscape_84256_2560x1080.jpg");*/

            final Marker kiel = googleMap.addMarker(new MarkerOptions().position(KIEL).title("Epitech").snippet("Epitech is not cool").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));
            markers.put(kiel.getId(), "http://www.yodot.com/images/jpeg-images-sm.png");

            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.310894, 5.369021)).title("Epitech").snippet("Un endroit trop OKLM").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.526314, 5.445431)).title("Rotonde").snippet("La fontaine de l'amour").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.521072, 5.448428)).title("Parc Jourdan").snippet("Le parc Jourdan est un jardin public d'Aix-en-Provence, situé à proximité immédiate du centre-ville, derrière la faculté de droit de l'université Aix-Marseille III. C'est l'un des plus grands parcs de la ville et certainement le plus emblématique, et l'un des lieux de promenade préfèrés des Aixois.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.521812, 5.466270)).title("Promenade de la Torse").snippet("Située dans les quartiers est de la Ville, aux abords du ruisseau de la Torse, la promenade de la Torse est comprise entre la Route de Cézanne et la Route de Nice.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.530331, 5.457883)).title("Parc Rambot").snippet("Le Parc Rambot est situé à l’est de la ville, face à l’Ecole des Arts et Métiers.\nL’accès se fait par le Boulevard des Arts et Métiers ou par l’Avenue Sainte Victoire.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.280621, 5.394722)).title("Parc du 26e centenaire").snippet("Le parc du 26e Centenaire est un jardin public de Marseille, aménagé sur l'ancien site de la gare du Prado à proximité de Castellane en plein cœur de la ville. Les travaux ont commencé à l'automne 1999 et le parc fut inauguré par le maire, Jean-Claude Gaudin, le 23 juin 2001. Son nom fait référence à la fondation de Marseille, il y a XXVI siècles par les Grecs sous le nom de Massalia.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.261730, 5.370886)).title("Plage du Prado").snippet("Les plages du Prado sont un aménagement artificiel du littoral créé dans la fin des années 1970 par Gaston Defferre à Marseille. Les plages ont été érigées sur 20 hectares pris sur la mer, à l'aide des remblais issus de la construction des deux lignes de métro marseillaises. Leur construction est due en partie au fait qu'avant cela, les jours de mistral, les vagues de la mer venaient mourir au bord de la chaussée, arrosant copieusement passants et véhicules.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.259712, 5.380436)).title("Parc Borély").snippet("Le parc Borély est un des parcs de la ville de Marseille, situé dans le VIIIe arrondissement, à proximité des plages du Prado, et de la Corniche.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.529743, 5.448503)).title("Thé Mandarine").snippet("En mêlant restaurant, salon de thé et boutique nous avons voulu créer un lieu de convivialité, chaleureux, intimiste et original. Un lieu où l’on se sente « comme à la maison » où accueil et qualité sont au coeur de nos préoccupations.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.302071, 5.389748)).title("Teavora").snippet("Confortablement installés sur un matelas étoffé de coussins, vous pouvez déguster un thé aux saveurs d'ailleurs, un bon chocolat chaud ou encore un lassi. Votre gourmandise sera satisfaite par la sélection de tartes et gâteaux faits maison.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.244690, 5.371280)).title("Plage de la Pointe Rouge").snippet("La plage de la Pointe Rouge est l'une des rares plages de sable de Marseille.\\r\\nSitué après les plages du Prado et avant la route pour les Calanques, elle est très fréquentée en été : les restaurants « pieds dans le sable » et les petits cabanons en location bordent cette agréable plage majoritairement fréquentée par des familles.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");
            markers.put((googleMap.addMarker(new MarkerOptions().position(new LatLng(43.305367, 5.397866)).title("Parc Longchamp").snippet("Protégé au titre des Monuments Historiques, le Parc Longchamp, en cours de recomposition, s'est constitué au fil du temps, par la stratification des différents projets, imaginés ou réalisés sur le site, depuis le XIXème siècle.").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)))).getId(), "http://www.yodot.com/images/jpeg-images-sm.png");

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EPITECH, 15));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            googleMap.setMyLocationEnabled(true);
        }
    }

    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private View view;

        public CustomInfoWindowAdapter() {
            view = getLayoutInflater().inflate(R.layout.custom_info_window,
                    null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            if (MapsActivity.this.marker != null
                    && MapsActivity.this.marker.isInfoWindowShown()) {
                MapsActivity.this.marker.hideInfoWindow();
                MapsActivity.this.marker.showInfoWindow();
            }
            return null;
        }

        @Override
        public View getInfoWindow(final Marker marker) {
            MapsActivity.this.marker = marker;

            String url = null;

            if (marker.getId() != null && markers != null && markers.size() > 0) {
                if (markers.get(marker.getId()) != null &&
                        markers.get(marker.getId()) != null) {
                    url = markers.get(marker.getId());
                }
            }
            final ImageView image = ((ImageView) view.findViewById(R.id.badge));

            if (url != null && !url.equalsIgnoreCase("null")
                    && !url.equalsIgnoreCase("")) {
                imageLoader.displayImage(url, image, options,
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri,
                                                          View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view,
                                        loadedImage);
                                getInfoContents(marker);
                            }
                        });
            } else {
                image.setImageResource(R.drawable.tree);
            }

            final String title = marker.getTitle();
            final TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                titleUi.setText(title);
            } else {
                titleUi.setText("");
            }

            final String snippet = marker.getSnippet();
            final TextView snippetUi = ((TextView) view
                    .findViewById(R.id.snippet));
            if (snippet != null) {
                snippetUi.setText(snippet);
            } else {
                snippetUi.setText("");
            }

            return view;
        }
    }

    private void initImageLoader() {
        int memoryCacheSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            int memClass = ((ActivityManager)
                    getSystemService(Context.ACTIVITY_SERVICE))
                    .getMemoryClass();
            memoryCacheSize = (memClass / 8) * 1024 * 1024;
        } else {
            memoryCacheSize = 2 * 1024 * 1024;
        }

        final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(memoryCacheSize)
                .memoryCache(new FIFOLimitedMemoryCache(memoryCacheSize - 1000000))
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
/*
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private HttpURLConnection connection;
    private URL url;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(43.311098, 5.369033)).title("Marker").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)).title("Parc de la jouvence").snippet("TOTO"));
    }
}
*/
