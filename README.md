# capacitor-rtsp-rtmp-stream

Cross-platform plugin for Capacitor to easily stream RTSP or RTMP.

## Install

Install the package from the repository(by now):
```bash
npm install capacitor-rtsp-rtmp-stream
npx cap sync
ionic capacitor copy android && ionic capacitor sync android
```

On iOS, not supported.

On Android:
- Add the jitpack maven repository to your projects build's gradle script:
```
maven {
	url 'https://jitpack.io'
}
```

## API

<docgen-index>

* [`rtmp(...)`](#rtmp)
* [`rtsp(...)`](#rtsp)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### rtmp(...)

```typescript
rtmp(options: RTMPOptions) => Promise<void>
```

| Param         | Type                                                |
| ------------- | --------------------------------------------------- |
| **`options`** | <code><a href="#rtmpoptions">RTMPOptions</a></code> |

--------------------


### rtsp(...)

```typescript
rtsp(options: RTSPOptions) => Promise<void>
```

| Param         | Type                                                |
| ------------- | --------------------------------------------------- |
| **`options`** | <code><a href="#rtspoptions">RTSPOptions</a></code> |

--------------------


### Interfaces


#### RTMPOptions

| Prop           | Type                |
| -------------- | ------------------- |
| **`host`**     | <code>string</code> |
| **`username`** | <code>string</code> |
| **`password`** | <code>string</code> |


#### RTSPOptions

| Prop           | Type                |
| -------------- | ------------------- |
| **`host`**     | <code>string</code> |
| **`username`** | <code>string</code> |
| **`password`** | <code>string</code> |

</docgen-api>
