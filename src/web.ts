import {WebPlugin} from '@capacitor/core';

import type {CapacitorRtmpRtspStreamPlugin} from './definitions';

export class CapacitorRtmpRtspStreamWeb
    extends WebPlugin
    implements CapacitorRtmpRtspStreamPlugin {
    async echo(options: { value: string }): Promise<{ value: string }> {
        console.log('ECHO', options);
        return options;
    }
}
