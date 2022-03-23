export interface CapacitorRtspRtmpStreamPlugin {
  rtmp(options: RTMPOptions): Promise<void>;
  rtsp(options: RTSPOptions): Promise<void>;
}

export interface RTMPOptions {
  host: string;
  username: string;
  password: string;
}

export interface RTSPOptions {
  host: string;
  username: string;
  password: string;
}