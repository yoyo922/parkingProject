package layout;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by peter on 20/07/17.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startlocation;

    public List<LatLng> points;
}
