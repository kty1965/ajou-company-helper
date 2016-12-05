package kr.ac.ajou.companyhelper.activities.commute;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;
import kr.ac.ajou.companyhelper.ble_sdk.Etc.ScannerReceiverCallback;
import kr.ac.ajou.companyhelper.ble_sdk.Receiver.BleReceiver;
import kr.ac.ajou.companyhelper.ble_sdk.Receiver.ScannerReceiver;
import kr.ac.ajou.companyhelper.ble_sdk.Repository.ZoyiBleSignal;
import kr.ac.ajou.companyhelper.ble_sdk.Repository.ZoyiSignal;
import kr.ac.ajou.companyhelper.ble_sdk.Service.BluetoothService;
import kr.ac.ajou.companyhelper.models.daos.CommuteLogDao;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class CommuteScanActivity extends ApplicationActivity {
  private ProgressDialog mProgress;
  private Handler mHandler = new Handler();
  private boolean mScanning = false;
  private ScannerReceiver scannerReceiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_commute_scan);

    bluetoohSetup();
  }

  public void toggleScan(View v) {

    if (!BluetoothService.checkBluetooth(this)) {
      Toast.makeText(this, R.string.cannot_find_available_scanner, Toast.LENGTH_LONG).show();
      finish();
    }

    scannerReceiver = new BleReceiver(this, "f4fd2b108c32", new ScannerReceiverCallback() {
      @Override
      public void onSuccess(ZoyiSignal value) {
        validateRecord(value);
      }
    });

    mProgress = ProgressDialog.show(
        this,
        getString(R.string.title_dialog_scanning),
        getString(R.string.message_dialog_scanning),
        true,
        true,
        new DialogInterface.OnCancelListener(){
          @Override
          public void onCancel(DialogInterface dialog) {
            stopScanning();
            dialog.dismiss();
            mHandler.removeCallbacksAndMessages(null);
          }});

    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (mProgress.isShowing()) {
          stopScanning();
          mProgress.dismiss();
          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CommuteScanActivity.this);
          alertDialogBuilder
              .setTitle(getString(R.string.cannot_find_device_title))
              .setMessage(getString(R.string.cannot_find_device_message))
              .setCancelable(true)
              .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
              });
          alertDialogBuilder.create().show();
        }
      }
    }, 20000);



    if (mScanning) {
      stopScanning();
      mHandler.removeCallbacksAndMessages(null);
    } else {
      startScanning();
    }
  }

  public void startScanning() {
    mScanning = true;
    scannerReceiver.startScan(this);
  }
  public void stopScanning() {
    mScanning = false;
    scannerReceiver.stopScan(this);
  }

  public void validateRecord(ZoyiSignal record) {
    ZoyiSignal ZoyiSignal = (ZoyiSignal)record;
    Log.v(CommuteScanActivity.class.toString(), ZoyiSignal.toString());
    if (isValidateRecord(record)) {
      commuteSuccess(ZoyiSignal);
    }
    Log.d(CommuteScanActivity.class.getName(), record.toString());
  }

  public Boolean isValidateRecord(ZoyiSignal record) {
    if (record instanceof ZoyiBleSignal) {
      if (((ZoyiBleSignal) record).getRssi() >= -80) {
        return true;
      }
    }
    return false;
  }

  public void commuteSuccess(ZoyiSignal record) {
    TextView textView = (TextView)findViewById(R.id.commute_msg);
    String commuteSuccessMsg = getString(R.string.commute_success_message);
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy년 MM월 dd일 k시 m분");
    stopScanning();
    mHandler.removeCallbacksAndMessages(null);
    mProgress.dismiss();

    CommuteLogDao.create(currentEmployee(), new DateTime(record.getTs()));

    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder
        .setTitle(getString(R.string.commute_success_title))
        .setMessage(String.format("%s\n%s", new DateTime(record.getTs()).toString(fmt.withLocale(Locale.KOREA)), commuteSuccessMsg))
        .setCancelable(true)
        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            finish();
          }
        });
    alertDialogBuilder.create().show();
  }
}
