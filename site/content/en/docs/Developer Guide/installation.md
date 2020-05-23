---
date: 2020-05-21
title: "Installation"
linkTitle: "Installation"
weight: 10
description: >
  How to install Connect File Pulse
---


**Connect FilePulse** can be installed either the [Releases Page](https://github.com/streamthoughts/kafka-connect-file-pulse/releases) Github repository or from the [Confluent Hub](https://www.confluent.io/hub/streamthoughts/kafka-connect-file-pulse).

{{% alert title="Caution" color="warn" %}}
You should note that connector downloaded on Confluent Hub may not reflect the latest available version.
{{% /alert %}}

**Confluent Hub CLI installation**

Use the [Confluent Hub client](https://docs.confluent.io/current/confluent-hub/client.html) to install this connector with:

```bash
confluent-hub install streamthoughts/kafka-connect-file-pulse:1.2.1
```

**Download Installation**

Download the distribution ZIP file for the latest available version.

**Example :**
```bash
export VERSION=1.2.2
curl -sSL https://github.com/streamthoughts/kafka-connect-file-pulse/releases/download/v$VERSION/streamthoughts-kafka-connect-file-pulse-$VERSION.zip
```

Extract it into one of the directories that is listed on the `plugin.path` worker configuration property.

{{% alert title="Important" color="info" %}}
When you run Connect workers in **distributed mode**, the connector-plugin must be installed **on each of machines** running Kafka Connect.
{{% /alert %}}


