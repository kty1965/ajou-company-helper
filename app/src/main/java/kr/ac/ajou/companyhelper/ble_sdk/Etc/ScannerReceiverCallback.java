package kr.ac.ajou.companyhelper.ble_sdk.Etc;


import kr.ac.ajou.companyhelper.ble_sdk.Repository.ZoyiSignal;

/**
 * Created by huy on 15. 2. 23..
 */
public abstract interface ScannerReceiverCallback {
    public void onSuccess(ZoyiSignal value);
}
