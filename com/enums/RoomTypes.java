package com.enums;

public enum RoomTypes {

    SINGLE(100, "Single", BedTypes.SINGLE, true, false, false),
    DOUBLE(250, "Double", BedTypes.DOUBLE, false, false, true),
    DELUXE(400, "Deluxe", BedTypes.MASTER, true, true, true),
    VIPSUITE(1100, "VIP Suite", BedTypes.MASTER, true, true, true);

    final private int ratePerNight;
    final public String inString;
    final private BedTypes bedType;
    final private Boolean smokingIsAllowed, withView, wifiEnabled;

    RoomTypes(int rateP, String inString, BedTypes bedType, Boolean sm, Boolean wv, Boolean wifiEnabled) {
        this.ratePerNight = rateP;
        this.inString = inString;
        this.bedType = bedType;
        this.smokingIsAllowed = sm;
        this.wifiEnabled = wifiEnabled;
        this.withView = wv;
    }

    public int getRatePerNight() {
        return ratePerNight;
    }

    public BedTypes getBedType() {
        return bedType;
    }

    public Boolean getSmokingIsAllowed() {
        return smokingIsAllowed;
    }

    public Boolean getWifiEnabled() {
        return wifiEnabled;
    }

    public Boolean getWithView() {
        return withView;
    }
}
