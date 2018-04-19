package util;

public class LocationUtil {
    private static final double EARTH_RADIUS = 6378.137; //unit:km
    
    private static final String UNIT_KM = "km";
    private static final String UNIT_M = "m";
    
    private static double getRadian(double d) {    
        return d * Math.PI / 180.0;    
    }    
    
    /**
     * 
     * @param lat1 - latitude of point 1
     * @param lng1 - longitude of point 1
     * @param lat2 - latitude of point 2
     * @param lng2 - longitude of point 2
     * @return distance between point 1 and 2 in unit
     */
    public static double getDistance(double lat1, double lng1, double lat2,double lng2) {
        double radLat1 = getRadian(lat1);    
        double radLat2 = getRadian(lat2);    
        double a = radLat1 - radLat2;    
        double b = getRadian(lng1) - getRadian(lng2);    
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)    
                + Math.cos(radLat1) * Math.cos(radLat2)    
                * Math.pow(Math.sin(b / 2), 2)));    
        s = s * EARTH_RADIUS;    
        s = Math.round(s * 10000d) / 10000d; 
        System.out.println("Distance in unit km:"+s);
        return s;    
    }
    
    public static double getDistance(double lat1, double lng1, double lat2,double lng2, String unit) {
        double distance = getDistance( lat1,  lng1,  lat2, lng2);
        switch(unit){
        case UNIT_KM:
            return distance;
        case UNIT_M:
            return distance*1000;
        default:
            return distance;
        }
    }
    
    //with string type as input
    public static double getDistance(String lat1, String lng1, String lat2,String lng2) {
        return getDistance( Double.valueOf(lat1).doubleValue(),  Double.valueOf(lng1).doubleValue(),
                Double.valueOf(lat2).doubleValue(), Double.valueOf(lng2).doubleValue());
    }
    
    public static double getDistance(String lat1, String lng1, String lat2,String lng2, String unit) {
        double distance = getDistance( Double.valueOf(lat1).doubleValue(),  Double.valueOf(lng1).doubleValue(),
                Double.valueOf(lat2).doubleValue(), Double.valueOf(lng2).doubleValue());
        switch(unit){
        case UNIT_KM:
            return distance;
        case UNIT_M:
            return distance*1000;
        default:
            return distance;
        }
    }
    
     
    public static void main(String[] args){
        getDistance(Double.valueOf("49.93359").doubleValue(),
                Double.valueOf("13.864602").doubleValue(),
                Double.valueOf("49.782281").doubleValue(),
                Double.valueOf("13.971284").doubleValue()
                );
        
    }
}
