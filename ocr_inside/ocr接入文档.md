# ocr接入文档

## 依赖

支持的基线版本至少要是32的
```java
    bundle 'com.mpaas.ocr:ocr-build:1.0.0.191121104429@jar'
    manifest "com.mpaas.ocr:ocr-build:1.0.0.191121104429:AndroidManifest@xml"

    bundle 'com.alipay.multimedia:xmedia-build:1.0.0.191120100744@jar'
    manifest 'com.alipay.multimedia:xmedia-build:1.0.0.191120100744:AndroidManifest@xml'

    bundle "com.alipay.android.phone.mobilecommon:xnn-build:1.0.0.191120102458@jar"
    manifest "com.alipay.android.phone.mobilecommon:xnn-build:1.0.0.191120102458:AndroidManifest@xml"
```

## license

申请license文件，并将其命令为mpaas_licence.properties放在portal工程的assets目录下，demo中也配置了license文件可以作为参考

## 接口说明

com.mpaas.ocr.api.MPOCR 类提供所有外部检测需要的接口

- 检测接口

```java
/**
    * 检测银行卡，默认能接受的最低可信度为0，即扫到结果就返回
    *
    * @param activity 用于启动检测页面
    * @param callback 检测结果的回调
    * @param provider 用于定制检测界面，相机预览上层的界面可以随意定制
    */   
    public static void startDetectBankCard(Activity activity, IDetectCallback callback, IDetectViewProvider provider)
```

```
/**
    * 检测银行卡
    *
    * @param activity         用于启动检测页面
    * @param lowestConfidence 检测结果可信度高于该指才认为是有效的
    * @param callback         检测结果返回的回调
    * @param provider         用于定制检测界面，相机预览上层的界面可以随意定制
    */
    public static void startDetectBankCard(Activity activity, float lowestConfidence, IDetectCallback callback, IDetectViewProvider provider)
```

```java
/**
     * 检测身份证正面，默认能接受的最低可信度为0，即扫到结果就返回
     *
     * @param activity 用于启动检测页面
     * @param callback 检测结果的回调
     * @param provider 用于定制检测界面，相机预览上层的界面可以随意定制
     */
    public static void startDetectIDCardFront(Activity activity, IDetectCallback callback, IDetectViewProvider provider)
```

```java
/**
     * 检测身份证正面，高于最低可信度才返回结果
     *
     * @param activity         用于启动检测页面
     * @param lowestConfidence 检测结果可信度高于该指才认为是有效的
     * @param callback         检测结果返回的回调
     * @param provider         用于定制检测界面，相机预览上层的界面可以随意定制
     */
    public static void startDetectIDCardFront(Activity activity, float lowestConfidence, IDetectCallback callback, IDetectViewProvider provider)
```

```java
/**
     * 检测身份证背面，默认能接受的最低可信度为0，即扫到结果就返回
     *
     * @param activity 用于启动检测页面
     * @param callback 检测结果的回调
     * @param provider 用于定制检测界面，相机预览上层的界面可以随意定制
     */
    public static void startDetectIDCardBack(Activity activity, IDetectCallback callback, IDetectViewProvider provider)
```

```java
/**
     * 检测身份证背面，高于最低可信度才返回结果
     *
     * @param activity         用于启动检测页面
     * @param lowestConfidence 检测结果可信度高于该指才认为是有效的
     * @param callback         检测结果返回的回调
     * @param provider         用于定制检测界面，相机预览上层的界面可以随意定制
     */
    public static void startDetectIDCardBack(Activity activity, float lowestConfidence, IDetectCallback callback, IDetectViewProvider provider)
```

- 返回结果

通过IDetectCallback接口返回检测结果

```java
public interface IDetectCallback {
    /**
     * 根据检测类型的不同转换成不同的对象返回
     * 若为身份证银行卡扫描，则返回的类型是OCRResult
     * @param result
     */
    void onResult(Object result);
}
```

