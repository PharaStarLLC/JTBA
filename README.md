# JTBA - Java Telegram Bot API

## is a Java API Wrapper for the Telegram Bot API

```txt
JTBA - Developed with ♥ and Published by digitaldojo.tech
```

---

#### Add digitaldojo.tech Repository:

```groovy
// Repository - Groovy
repositories {
    maven {
        name = "digitaldojo-repo"
        url = "https://repo.digitaldojo.tech/repository/maven-snapshots/"
    }
}
```

```kt
// Repository - Kotlin DSL
repositories {
    maven {
        url = uri("https://repo.digitaldojo.tech/repository/maven-snapshots/")
    }

    // OR
    maven("https://repo.digitaldojo.tech/repository/maven-snapshots/")
}
```

```xml
<!-- Repository - Maven -->
<repositories>
    <repository>
        <id>digitaldojo-repo</id>
        <url>https://repo.digitaldojo.tech/repository/maven-snapshots/</url>
    </repository>
</repositories>
```

---

### Add Dependency:

```groovy
// Dependency - Groovy
implementation("tech.digitaldojo.jtba:jtba:1.0-SNAPSHOT")
```

```kt
// Dependency - Kotlin DSL
implementation("tech.digitaldojo.jtba:jtba:1.0-SNAPSHOT")
```

```xml
<!-- Dependency - Maven -->
<dependencies>
    <dependency>
        <groupId>tech.digitaldojo.jtba</groupId>
        <artifactId>jtba</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
