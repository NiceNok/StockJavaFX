package sample;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import sample.childClasses.MapEvent;

public class GoogleMap extends Parent {


    public  GoogleMap() {


        initMap();
        initCommunication();
        getChildren().add(webView);
        startJumping();
        setMarkerPosition(50.4546,30.5238);
        //switchTerrain();
        setMapCenter(50.4546,30.5238);


    }

   public void initMap()
    {
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("html/map.html").toExternalForm());
        ready = false;
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
        {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                final Worker.State oldState,
                                final Worker.State newState)
            {
                if (newState == Worker.State.SUCCEEDED)
                {
                    ready = true;
                }
            }
        });
    }

    public void initCommunication() {
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
        {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                final Worker.State oldState,
                                final Worker.State newState)
            {
                if (newState == Worker.State.SUCCEEDED)
                {
                    doc = (JSObject) webEngine.executeScript("window");
                    doc.setMember("app", GoogleMap.this);
                }
            }
        });
    }

    public static void invokeJS( final String str) {
        if(ready) {
            doc.eval(str);
        }
        else {
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
            {
                @Override
                public  void changed(final ObservableValue<? extends Worker.State> observableValue,
                                    final Worker.State oldState,
                                    final Worker.State newState)
                {
                    if (newState == Worker.State.SUCCEEDED)
                    {
                        doc.eval(str);
                    }
                }
            });
        }
    }

    public void setOnMapLatLngChanged(EventHandler<MapEvent> eventHandler) {
        onMapLatLngChanged = eventHandler;
    }

    public void handle(double lat, double lng) {
        if(onMapLatLngChanged != null) {
            MapEvent event = new MapEvent(this, lat, lng);
            onMapLatLngChanged.handle(event);
        }
    }

    public static void setMarkerPosition(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMarkerPosition(" + sLat + ", " + sLng + ")");
    }

    public void setMapCenter(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMapCenter(" + sLat + ", " + sLng + ")");
    }

    public static void switchSatellite() {
        invokeJS("switchSatellite()");
    }

    public static void switchRoadmap() {
        invokeJS("switchRoadmap()");
    }

    public static void switchHybrid() {
        invokeJS("switchHybrid()");
    }

    public static void switchTerrain() {
        invokeJS("switchTerrain()");
    }

    public static void startJumping() {
        invokeJS("startJumping()");
    }

    public static void stopJumping() {
        invokeJS("stopJumping()");
    }

    public static void setHeight(double h) {
        webView.setPrefHeight(h);
    }

    public static void setWidth(double w) {
        webView.setPrefWidth(w);
    }

    public ReadOnlyDoubleProperty widthProperty() {
        return webView.widthProperty();
    }

    private static JSObject doc;
    private EventHandler<MapEvent> onMapLatLngChanged;
    private static WebView webView;
    private static WebEngine webEngine;
    private static boolean ready;
}
