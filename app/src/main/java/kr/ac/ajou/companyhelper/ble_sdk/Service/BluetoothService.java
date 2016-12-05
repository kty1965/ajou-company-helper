package kr.ac.ajou.companyhelper.ble_sdk.Service;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by huy on 15. 2. 17..
 */
public class BluetoothService {
    public static Boolean hasBLE(Context context) {
        if (Build.VERSION.SDK_INT < 18) return false;
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static BluetoothAdapter getBluetoothAdapter(Context context) {
        if (Build.VERSION.SDK_INT < 18) return null;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        return bluetoothManager.getAdapter();
    }

    public static Boolean checkBluetooth(Context context) {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter(context);

        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }
}
