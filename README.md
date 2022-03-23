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

# Future Features
- [ ] iOS support
- [x] Android support

# Credits
[https://github.com/pedroSG94/rtmp-rtsp-stream-client-java](https://github.com/pedroSG94/rtmp-rtsp-stream-client-java)
```sh
Library for stream in RTMP and RTSP. All code in Java.
```

# Hire Us!
Do you like my work? I am a Senior Full Stack App Developer (Web, Desktop & Mobile App) & Sysadmin, you can hire me, open an issue or contact me!

Additionally, you could be interested in check or hire us through our [Webmons Apps](https://webmonsapps.com)

Do you still need more info about me or my work?

You could check my [Linkedin profile](https://www.linkedin.com/in/disono) and [Twitter profile](https://twitter.com/master_archie)