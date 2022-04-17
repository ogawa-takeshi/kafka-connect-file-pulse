/*
 * Copyright 2019-2021 StreamThoughts.
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
package io.streamthoughts.kafka.connect.filepulse.internal;

import java.util.Objects;

public class StringUtils {

    public static final int INDEX_NOT_FOUND = -1;
    public static final String EMPTY = "";

    /**
     * @see String#split(String).
     */
    public static boolean isFastSplit(final String regex) {
        char ch = 0;
        return
            ((regex.length() == 1 && ".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) == -1) ||
                (regex.length() == 2 &&
                    regex.charAt(0) == '\\' &&
                    (((ch = regex.charAt(1))-'0')|('9'-ch)) < 0 &&
                    ((ch-'a')|('z'-ch)) < 0 &&
                    ((ch-'A')|('Z'-ch)) < 0)) &&
                (ch < Character.MIN_HIGH_SURROGATE ||
                        ch > Character.MAX_LOW_SURROGATE);
    }

    public static boolean isNotBlank(final String string) {
        return !isBlank(string);
    }

    public static boolean isBlank(final String string) {
        return Objects.isNull(string) || string.isBlank();
    }

    public static String substringAfterLast(final String str, final int separator) {
        if (isBlank(str)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == INDEX_NOT_FOUND || pos == str.length() - 1) {
            return EMPTY;
        }
        return str.substring(pos + 1);
    }

    public static String removeEnd(final String str, final String remove) {
        if (isBlank(str) || isBlank(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }
}
