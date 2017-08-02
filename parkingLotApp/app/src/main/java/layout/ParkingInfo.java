package layout;

/**
 * Created by peter on 01/08/17.
 */

public class ParkingInfo {
    private  String lotId,isOcc,arrivalTime,leaveTime,purchasedTime;

    public ParkingInfo(String lotId, String isOcc, String arrivalTime, String purchasedTime, String leaveTime){
        this.setLotId(lotId);
        this.setIsOcc(isOcc);
        this.setArrivalTime(arrivalTime);
        this.setLeaveTime(leaveTime);
        this.setPurchasedTime(purchasedTime);

    }
    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getIsOcc() {
        return isOcc;
    }

    public void setIsOcc(String isOcc) {
        this.isOcc = isOcc;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getPurchasedTime() {
        return purchasedTime;
    }

    public void setPurchasedTime(String purchasedTime) {
        this.purchasedTime = purchasedTime;
    }
}
