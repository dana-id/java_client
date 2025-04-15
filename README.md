# **DANA Java API Client**

A lightweight Java client library for integrating with the [DANA API](https://dana.id) — built to be flexible, developer-friendly, and ready for production.
### Latest Stable Version: 1.0.0

## 📦 Installation

### Maven

```xml
<dependency>
    <groupId>id.dana.tech</groupId>
    <artifactId>dana-java-api-client</artifactId>
    <version>{version}</version>
</dependency>
```

### Gradle (Groovy)

```groovy
implementation 'id.dana.tech:dana-java-api-client:{version}'
```

### Gradle (Kotlin DSL)

```kotlin
implementation("id.dana.tech:dana-java-api-client:{version}")
```

Replace `{version}` with the actual version you want to use.

---

## 🚀 Usage

The **DANA Java API Client** is designed for simplicity and ease of use. All API calls follow a consistent pattern, allowing you to focus on building rather than boilerplate.

### 🧩 Standard Call Format

```text
{{API_RESPONSE_CLASS}} response = DANAApiService.call(DANAApi.{{API_TYPE}}.{{API_NAME}}, {{API_REQUEST_OBJECT}});
```

- `{{API_TYPE}}` – The API category (e.g., `SNAP`, `OPEN_API`)
- `{{API_NAME}}` – The specific action to call (e.g., `APPLY_TOKEN`, `DIRECT_DEBIT_PAYMENT`).  
  👉 Full list available in `id.dana.tech.model.generated.constant.DANAApi`
- `{{API_REQUEST_OBJECT}}` – Your populated request payload object
- `{{API_RESPONSE_CLASS}}` – The expected response class

### ✅ Example

```java
DANAApiConfig config = DANAApiConfig.builder()
    .environment(DANAEnvironment.PRODUCTION)
    .clientId("client-id")
    .merchantPrivateKey("your-merchant-private-key-in-pkcs8-format")
    .danaPublicKey("dana-public-key-in-pkcs8-format")
    .channelId("channel-id")
    .connectTimeout(3000)
    .maxBodyLengthForLogging(1024)
    .build();

DirectDebitPaymentRequest request = new DirectDebitPaymentRequest();
/*
 * populate request data: 
 * request.setA("A");
 * request.setB("B");
 * request.setC("C");
 */
DirectDebitPaymentResponse response = DANAApiService.call(DANAApi.DirectDebit.DIRECT_DEBIT_PAYMENT, request);
```

> 💡 **Pro tip:** All request and response classes are plain Java objects (POJOs) with Lombok annotations for convenience.

---

## ⚙️ Configuration

The **DANA Java API Client** uses a **singleton pattern** for configuration management.  
This means you only need to configure it once, and the same instance will be reused across all API calls in your application.

You can set it up in two ways, depending on your preferred level of control:

### 🔧 Option 1: Explicit Config Definition

Manually configure the client using the builder pattern for full control:

```java
DANAApiConfig config = DANAApiConfig.builder()
    .environment(DANAEnvironment.PRODUCTION)
    .clientId("client-id")
    .merchantPrivateKey("your-merchant-private-key-in-pkcs8-format")
    .danaPublicKey("dana-public-key-in-pkcs8-format")
    .channelId("channel-id")
    .connectTimeout(1000)
    .maxBodyLengthForLogging(500)
    .build();
DirectDebitPaymentResponse response = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT, request);
```

### 🔧 Option 2: Pre-defined Config via Environment or System Properties

You can use **Environment variables** or **JVM system properties** for pre-defined and zero-code configuration:

```java
DANAApiConfig config = DANAApiConfig.get();
DirectDebitPaymentResponse response = DANAApiService.call(DANAApi.SNAP.DIRECT_DEBIT_PAYMENT, request);
```

> 💡 **Pro tip:** If you don't explicitly set up the configuration, the **DANA Java API Client** will lazily load it from environment variables or system properties.
>
> 1. If any required configuration is missing, the library will throw a `java.lang.IllegalArgumentException` indicating which field is not set.
> 2. Make sure the private and public keys are in valid **PKCS8** format — otherwise, you may encounter a `java.security.InvalidKeyException` or another `IllegalArgumentException`.


### 🔑 Configuration Reference

Following is the list of configuration options available for the **DANA Java API Client**.

| Config Field Name         | Default Value             | Pre-defined Config Name     | Required | Notes                                                                   |
|---------------------------|---------------------------|-----------------------------|----------|-------------------------------------------------------------------------|
| `environment`             | `DANAEnvironment.SANDBOX` | -                           | ✅        | Sets the library’s target environment for communicating with DANA’s API |
| `clientId`                | -                         | `DANA_CLIENT_ID`            | ✅        | Client ID registered in DANA service                                    |
| `merchantPrivateKey`      | -                         | `DANA_MERCHANT_PRIVATE_KEY` | ✅        | Private key for signing requests, pairs with public key in DANA         |
| `danaPublicKey`           | -                         | `DANA_PUBLIC_KEY`           | ✅        | DANA’s public key, used to verify callbacks like `finishNotify`         |
| `channelId`               | -                         | `DANA_CHANNEL_ID`           | ✅        | Channel identifier used to call DANA services                           |
| `connectTimeout`          | `3000`                    | `DANA_CONNECT_TIMEOUT`      | ❌        | Timeout in milliseconds for HTTP connection                             |
| `maxBodyLengthForLogging` | `1024`                    | `DANA_LOGGING_MAX_BODY`     | ❌        | Max length for request/response logging bodies                          |

---

##  📜 API Reference
The **DANA Java API Client** allows you to interact with DANA APIs using either **SNAP** or **DANA Open API** specifications.  
Request and response classes are auto-generated and follow the package naming convention below:

```text
id.dana.tech.model.generated.api.{{OBJECT_TYPE}}.{{API_TYPE}}.{{BUSINESS_CATEGORY}}.{{CLASS_NAME}}
```

- `{{OBJECT_TYPE}}` – (`request`, `response`)
- `{{API_TYPE}}` – (`snap`, `openapi`)
- `{{BUSINESS_CATEGORY}}` – (e.g., `accountbinding`, `directdebit`, etc).

> 💡 **Pro tip:** You can always find the full list of available API types and names in a version in `id.dana.tech.model.generated.constant.DANAApi` class.
>
> Latest versions will include APIs from previous versions unless explicitly stated otherwise.

The list of available APIs will be expanded incrementally with each version.  
Below is the update log for each release:

### Library Version: 1.0.0
| API_NAME               | Supported API_TYPE | REQUEST_CLASS_NAME          | RESPONSE_CLASS_NAME          | BUSINESS_CATEGORY |
|------------------------|--------------------|-----------------------------|------------------------------|-------------------|
| `APPLY_TOKEN`          | `SNAP`             | `ApplyTokenRequest`         | `ApplyTokenResponse`         | `AccountBinding`  |
| `DIRECT_DEBIT_PAYMENT` | `SNAP`             | `DirectDebitPaymentRequest` | `DirectDebitPaymentResponse` | `DirectDebit`     |
| `DIRECT_DEBIT_STATUS`  | `SNAP`             | `DirectDebitStatusRequest`  | `DirectDebitStatusResponse`  | `DirectDebit`     |
| `DIRECT_DEBIT_STATUS`  | `SNAP`             | `DirectDebitCancelRequest`  | `DirectDebitCancelResponse`  | `DirectDebit`     |

---

## 📄 License

This project is licensed under the Apache License, Version 2.0 - see the [LICENSE](LICENSE) file for details.

