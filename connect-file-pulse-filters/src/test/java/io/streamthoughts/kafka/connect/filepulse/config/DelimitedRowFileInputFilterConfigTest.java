/*
 * Copyright 2019 StreamThoughts.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamthoughts.kafka.connect.filepulse.config;

import io.streamthoughts.kafka.connect.filepulse.data.StructSchema;
import io.streamthoughts.kafka.connect.filepulse.data.TypedField;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.streamthoughts.kafka.connect.filepulse.config.DelimitedRowFilterConfig.*;

public class DelimitedRowFileInputFilterConfigTest {

    @Test
    public void shouldCreateConfigWithDefaultValues() {
        DelimitedRowFilterConfig config = new DelimitedRowFilterConfig(new HashMap<>());
        Assert.assertEquals(READER_FIELD_SEPARATOR_DEFAULT, config.delimiter());
        Assert.assertNull(config.extractColumnName());
        Assert.assertEquals(READER_FIELD_TRIM_COLUMN_DEFAULT, config.isTrimColumn());
        Assert.assertEquals(READER_AUTO_GENERATE_COLUMN_NAME_DEFAULT, config.isAutoGenerateColumnNames());
        Assert.assertNull(config.schema());
    }

    @Test
    public void shouldCreateConfigGivenOverrideValues() {

        Map<String, String> props = new HashMap<String, String>() {{
            put(READER_FIELD_SEPARATOR_CONFIG, "|");
            put(READER_EXTRACT_COLUMN_NAME_CONFIG, "header");
            put(READER_FIELD_TRIM_COLUMN_CONFIG, "true");
        }};

        DelimitedRowFilterConfig config = new DelimitedRowFilterConfig(props);

        Assert.assertEquals("|", config.delimiter());
        Assert.assertEquals("header", config.extractColumnName());
        Assert.assertTrue(config.isTrimColumn());
        Assert.assertNull(config.schema());
    }

    @Test
    public void shouldCreateConfigGivenSchema() {

        Map<String, String> props = new HashMap<String, String>() {{
            put(READER_FIELD_COLUMNS_CONFIG, "field1:BOOLEAN;field2:INT32;field3:STRING");
        }};

        DelimitedRowFilterConfig config = new DelimitedRowFilterConfig(props);

        StructSchema schema = config.schema();
        Assert.assertNotNull(schema);

        List<TypedField> fields = schema.fields();
        Assert.assertEquals(3, fields.size());

        Assert.assertEquals("field1", fields.get(0).name());
        Assert.assertEquals("field2", fields.get(1).name());
        Assert.assertEquals("field3", fields.get(2).name());
    }

}