import { WebPlugin } from '@capacitor/core';

import type { CapacitorRtspRtmpStreamPlugin, RTMPOptions, RTSPOptions } from './definitions';

export class CapacitorRtspRtmpStreamWeb
  extends WebPlugin
  implements CapacitorRtspRtmpStreamPlugin {

  async rtmp(options: RTMPOptions): Promise<void> {
    console.log('RTMP', options);
    return Promise.resolve(undefined);
  }

  async rtsp(options: RTSPOptions): Promise<void> {
    console.log('RTSP', options);
    return Promise.resolve(undefined);
  }
}
