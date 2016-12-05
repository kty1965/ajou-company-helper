package kr.ac.ajou.companyhelper.ble_sdk.Receiver;

import android.content.Context;

/**
 * Created by huy on 15. 2. 23..
 */
public interface ScannerReceiver {
    public void startScan(Context context);

    public void stopScan(Context context);
}
