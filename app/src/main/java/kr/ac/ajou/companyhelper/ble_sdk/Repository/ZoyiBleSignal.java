package kr.ac.ajou.companyhelper.ble_sdk.Repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by huy on 15. 2. 17..
 */
public class ZoyiBleSignal extends ZoyiSignal {
    private static final String ZOYI_UUID = "2db0";
    private List<AdBle> adBles;
    private String mac;
    private String name;

    public ZoyiBleSignal() {
        super();
        adBles = new ArrayList<AdBle>();
    }

    public ZoyiBleSignal(byte[] scanRecord, int rssi) {
        this.rssi = rssi;
        this.ts = new Date().getTime();
        adBles = parseScanRecord(scanRecord);
        for (AdBle adBle : adBles) {
            if (adBle.getType() == null) continue;
            switch (adBle.getType()) {
                case MANUFACTUR_DATA:
                    mac = adBle.toString();
                    break;
                case NAME:
                    name = new String(adBle.getData());
                    break;
                default:
                    break;
            }
        }

        this.mac = mac.toLowerCase().replaceAll(":", "");
    }

    public String getMac() {
        return mac;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("%s %s %d %d %s", mac, name, rssi, ts, this.mac);
    }

    public static Boolean isZoyiBle(byte[] scanRecord) {
        int idx = 0;
        while (idx < scanRecord.length) {
            byte length = scanRecord[idx++];
            if (length == 0) break;
            byte byteType = scanRecord[idx];
            if (byteType == 0) break;
            AdBle.LeType type = AdBle.LeType.toLeType(byteType);

            // UUID 16 bit
            if (type == AdBle.LeType.UUID16) {
                Log.i("isZoyiBle", String.format("%02x%02x", scanRecord[idx + 1], scanRecord[idx + 2]));
                return ZOYI_UUID.equals(String.format("%02x%02x", scanRecord[idx + 1], scanRecord[idx + 2]));
            }
            idx += length;
        }
        return false;
    }

    private List<AdBle> parseScanRecord(byte[] scanRecord) {
        List<AdBle> records = new ArrayList<AdBle>();
        int idx = 0;
        while (idx < scanRecord.length) {
            byte length = scanRecord[idx++];
            if (length == 0) break;

            byte byteType = scanRecord[idx];
            if (byteType == 0) break;
            AdBle.LeType type = AdBle.LeType.toLeType(byteType);

            byte[] data = Arrays.copyOfRange(scanRecord, idx + 1, idx + length);

            records.add(new AdBle(length, type, data));
            idx += length;
        }

        return records;
    }
}
