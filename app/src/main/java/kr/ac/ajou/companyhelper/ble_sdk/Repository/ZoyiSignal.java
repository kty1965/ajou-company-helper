package kr.ac.ajou.companyhelper.ble_sdk.Repository;


import java.util.Date;

/**
 * Created by huy on 15. 2. 24..
 */
public abstract class ZoyiSignal {
    protected int rssi;
    protected long ts;
    protected String mac;

    protected ZoyiSignal(int rssi, long ts, String mac) {
        this.rssi = rssi;
        this.ts = ts;
        this.mac = mac.toLowerCase().replaceAll(":", "");
    }

    protected ZoyiSignal() {
        this.rssi = Integer.MAX_VALUE;
        this.ts = new Date().getTime();
        this.mac = "000000000000";
    }


    public int getRssi() {
        return rssi;
    }

    public long getTs() {
        return ts;
    }

    public String getMac() {
        return mac;
    }
}
