import {registerPlugin} from '@capacitor/core';

import type {CapacitorRtmpRtspStreamPlugin} from './definitions';

const CapacitorRtmpRtspStream = registerPlugin<CapacitorRtmpRtspStreamPlugin>(
    'CapacitorRtmpRtspStream',
    {
        web: () => import('./web').then(m => new m.CapacitorRtmpRtspStreamWeb()),
    },
);

export * from './definitions';
export {CapacitorRtmpRtspStream};
