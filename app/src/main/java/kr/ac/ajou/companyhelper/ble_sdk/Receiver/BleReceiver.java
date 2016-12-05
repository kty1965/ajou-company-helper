package kr.ac.ajou.companyhelper.ble_sdk.Receiver;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import kr.ac.ajou.companyhelper.ble_sdk.Etc.ScannerReceiverCallback;
import kr.ac.ajou.companyhelper.ble_sdk.Repository.ZoyiBleSignal;
import kr.ac.ajou.companyhelper.ble_sdk.Service.BluetoothService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by huy on 15. 2. 23..
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BleReceiver implements BluetoothAdapter.LeScanCallback, ScannerReceiver {
    private Context mContext;
    private ScannerReceiverCallback mCallback;
    private Set<String> mTargetMacs;

    public BleReceiver(Context context, String targetMac, ScannerReceiverCallback callback) {
        this.mContext = context;
        this.mTargetMacs = new TreeSet<String>();
        mTargetMacs.add(targetMac.toLowerCase().replaceAll(":", ""));
        this.mCallback = callback;
    }

    public BleReceiver(Context context, List<String> targetMacs, ScannerReceiverCallback callback) {
        this.mContext = context;
        this.mTargetMacs = new TreeSet<String>();
        for (String targetMac : targetMacs) {
            mTargetMacs.add(targetMac.toLowerCase().replaceAll(":", ""));
        }
        this.mCallback = callback;
    }

    private Boolean isTargetMac(ZoyiBleSignal zoyiBleSignal) {
        return mTargetMacs.contains(zoyiBleSignal.getMac());
    }

    @Override
    public void onLeScan(BluetoothDevice bluetoothDevice, final int rssi, final byte[] scanRecord) {
      ((Activity) mContext).runOnUiThread(new Runnable() {
        @Override
        public void run() {
          if (!ZoyiBleSignal.isZoyiBle(scanRecord)) return;

          ZoyiBleSignal zoyiBleSignal = new ZoyiBleSignal(scanRecord, rssi);
          mCallback.onSuccess(zoyiBleSignal);
        }
      });
    }

    @Override
    public void startScan(Context context) {
        BluetoothService.getBluetoothAdapter(context).startLeScan(this);
    }

    @Override
    public void stopScan(Context context) {
        BluetoothService.getBluetoothAdapter(context).stopLeScan(this);
    }
}
