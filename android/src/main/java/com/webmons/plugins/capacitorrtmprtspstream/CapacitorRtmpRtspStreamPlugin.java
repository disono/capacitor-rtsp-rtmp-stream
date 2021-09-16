package com.webmons.plugins.capacitorrtmprtspstream;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorRtmpRtspStream")
public class CapacitorRtmpRtspStreamPlugin extends Plugin {

    private CapacitorRtmpRtspStream implementation = new CapacitorRtmpRtspStream();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }
}
