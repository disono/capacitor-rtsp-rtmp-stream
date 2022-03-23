package me.disono.capacitor.rtsp.rtmp.stream;

import android.content.Intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorRtspRtmpStream")
public class CapacitorRtspRtmpStreamPlugin extends Plugin {
    @PluginMethod()
    public void rtmp(PluginCall call) {
        Intent intent = new Intent(getActivity(), CapacitorRtmpActivity.class);
        intent.putExtra("username", call.getString("username"));
        intent.putExtra("password", call.getString("password"));
        intent.putExtra("host", call.getString("host"));
        getActivity().startActivity(intent);

        call.resolve();
    }

    @PluginMethod()
    public void rtsp(PluginCall call) {
        Intent intent = new Intent(getActivity(), CapacitorRtspActivity.class);
        intent.putExtra("username", call.getString("username"));
        intent.putExtra("password", call.getString("password"));
        intent.putExtra("host", call.getString("host"));
        getActivity().startActivity(intent);

        call.resolve();
    }
}
