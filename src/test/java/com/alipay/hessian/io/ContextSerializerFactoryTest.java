/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.hessian.io;

import com.alipay.hessian.Constants;
import com.caucho.hessian.io.ContextSerializerFactory;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author qilong.zql
 * @since 4.0.3
 */
public class ContextSerializerFactoryTest {

    // won't throw ClassCastException
    @Test
    public void testCastException() throws Throwable {
        System.setProperty(Constants.RESOLVE_PARENT_CONTEXT_SERIALIZER_FACTORY, "false");
        URL[] urls = ((URLClassLoader) this.getClass().getClassLoader()).getURLs();
        ClassLoaderA cla = new ClassLoaderA(urls, null);
        Class classContextSerializerFactory = cla.loadClass(ContextSerializerFactory.class.getCanonicalName());
        Method method = classContextSerializerFactory.getDeclaredMethod("create", ClassLoader.class);
        method.invoke(null, cla);
    }

    static class ClassLoaderA extends URLClassLoader {
        public ClassLoaderA(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }
    }
}