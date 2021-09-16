export interface CapacitorRtmpRtspStreamPlugin {
    echo(options: { value: string }): Promise<{ value: string }>;
}
