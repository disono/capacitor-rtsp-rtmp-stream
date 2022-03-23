import { registerPlugin } from '@capacitor/core';

import type { CapacitorRtspRtmpStreamPlugin } from './definitions';

const CapacitorRtspRtmpStream = registerPlugin<CapacitorRtspRtmpStreamPlugin>(
  'CapacitorRtspRtmpStream',
  {
    web: () => import('./web').then(m => new m.CapacitorRtspRtmpStreamWeb()),
  },
);

export * from './definitions';
export { CapacitorRtspRtmpStream };
