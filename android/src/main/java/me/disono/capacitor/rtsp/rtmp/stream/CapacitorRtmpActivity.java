package me.disono.capacitor.rtsp.rtmp.stream;

import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.encoder.input.video.Camera1ApiManager;
import com.pedro.encoder.input.video.CameraOpenException;
import com.pedro.rtmp.utils.ConnectCheckerRtmp;
import com.pedro.rtplibrary.rtmp.RtmpCamera1;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class CapacitorRtmpActivity extends AppCompatActivity implements ConnectCheckerRtmp {
    private static String TAG = "CapacitorRtmpActivity";
    private SurfaceView surfaceView;
    private RtmpCamera1 rtmpCameral;
    private String host = null;
    private String username = null;
    private String password = null;

    private ImageButton icTorch;
    private ImageButton icSwitchCamera;
    private ImageButton icBroadcast;
    private Camera1ApiManager camera1ApiManager;
    private boolean isFlashOn = false;
    private boolean isStreamingOn = false;

    PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            mWakeLock = pm.newWakeLock(FLAG_KEEP_SCREEN_ON, TAG);
            mWakeLock.acquire(10);
        }

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        host = intent.getStringExtra("host");

        Log.d(TAG, "Running RTMP, " + username + "|" + password + "|" + host);

        uiListener();
    }

    private void uiListener() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            initUI();
        } else {
            AtomicBoolean isPermitted = new AtomicBoolean(true);
            final String[] PERMISSIONS = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
            };

            ActivityResultContracts.RequestMultiplePermissions multiplePermissionsContract = new ActivityResultContracts.RequestMultiplePermissions();
            ActivityResultLauncher<String[]> multiplePermissionLauncher = registerForActivityResult(multiplePermissionsContract, isGranted -> {
                if (isGranted.containsValue(false)) {
                    isPermitted.set(false);
                }
            });

            multiplePermissionLauncher.launch(PERMISSIONS);

            if (isPermitted.get()) {
                initUI();
            }
        }
    }

    private void initUI() {
        surfaceView = findViewById(R.id.surfaceView);
        rtmpCameral = new RtmpCamera1(surfaceView, this);
        camera1ApiManager = new Camera1ApiManager(surfaceView, rtmpCameral);

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                rtmpCameral.startPreview();
                Log.d(TAG, "surfaceChanged");
            }

            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated");
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed");
            }
        });

        icTorch = findViewById(R.id.ic_torch);
        icTorch.setOnClickListener(v -> toggleFlash());

        icSwitchCamera = findViewById(R.id.ic_switch_camera);
        icSwitchCamera.setOnClickListener(v -> toggleCameraFace());

        icBroadcast = findViewById(R.id.ic_broadcast);
        icBroadcast.setOnClickListener(v -> toggleStreaming());
    }

    private void toggleStreaming() {
        if (!rtmpCameral.isStreaming()) {
            startStreaming();
        } else {
            stopStreaming();
        }

        toggleBtnImgVideo();
    }

    private void startStreaming() {
        rtmpCameral.setAuthorization(username, password);

        if (rtmpCameral.prepareAudio() && rtmpCameral.prepareVideo()) {
            rtmpCameral.startStream(host);
            isStreamingOn = true;
        } else {
            Toast.makeText(this, "Error preparing stream, This device cant do it", Toast.LENGTH_SHORT).show();
            isStreamingOn = false;
        }
    }

    private void stopStreaming() {
        if (rtmpCameral.isStreaming()) {
            toggleFlash();
            isStreamingOn = false;

            rtmpCameral.stopStream();
            rtmpCameral.stopPreview();
        }
    }

    private void toggleFlash() {
        if (!isFlashOn) {
            try {
                camera1ApiManager.enableLantern();
                isFlashOn = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            camera1ApiManager.disableLantern();
            isFlashOn = false;
        }

        // changing button/switch image
        toggleBtnImgFlash();
    }

    private void toggleBtnImgFlash() {
        String icon = (!isFlashOn) ? "ic_flash_off_white" : "ic_flash_on_white";
        icTorch.setImageResource(getResources().getIdentifier(icon, "drawable", getPackageName()));
    }

    private void toggleCameraFace() {
        try {
            rtmpCameral.switchCamera();
        } catch (CameraOpenException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleBtnImgVideo() {
        String icon = (!isStreamingOn) ? "ic_videocam_white" : "ic_videocam_off_white";
        icBroadcast.setImageResource(getResources().getIdentifier(icon, "drawable", getPackageName()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopStreaming();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopStreaming();

        if (mWakeLock != null) {
            mWakeLock.release();
        }
    }

    @Override
    public void onAuthErrorRtmp() {
        runOnUiThread(() -> Toast.makeText(CapacitorRtmpActivity.this, "Auth error", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onAuthSuccessRtmp() {
        runOnUiThread(() -> Toast.makeText(CapacitorRtmpActivity.this, "Auth success", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onConnectionFailedRtmp(String reason) {
        runOnUiThread(() -> {
            Toast.makeText(CapacitorRtmpActivity.this,
                    "Connection failed, " + reason,
                    Toast.LENGTH_SHORT).show();
            stopStreaming();
        });
    }

    @Override
    public void onConnectionStartedRtmp(String rtmpUrl) {
        Log.d(TAG, "onConnectionStartedRtmp, " + rtmpUrl);
    }

    @Override
    public void onConnectionSuccessRtmp() {
        runOnUiThread(() -> Toast.makeText(CapacitorRtmpActivity.this, "Connection success", Toast.LENGTH_SHORT)
                .show());
    }

    @Override
    public void onDisconnectRtmp() {
        rtmpCameral.stopStream();
        rtmpCameral.stopPreview();
        isStreamingOn = false;

        camera1ApiManager.disableLantern();
        isFlashOn = false;

        icBroadcast.setImageResource(getResources().getIdentifier("ic_videocam_white", "drawable", getPackageName()));
        icTorch.setImageResource(getResources().getIdentifier("ic_flash_off_white", "drawable", getPackageName()));

        runOnUiThread(() -> Toast.makeText(CapacitorRtmpActivity.this, "Disconnected", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onNewBitrateRtmp(long bitrate) {
        Log.d(TAG, "onNewBitrateRtmp");
    }
}
