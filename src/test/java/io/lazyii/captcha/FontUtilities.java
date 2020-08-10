package io.lazyii.captcha;

/**
 * Created by admin on 2020/8/10 9:34:00.
 */
/*
 * Copyright (c) 2008, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.PrivilegedAction;


/**
 * A collection of utility methods.
 */
public final class FontUtilities {
    
    public static boolean isSolaris;
    
    public static boolean isLinux;
    
    public static boolean isMacOSX;
    
    public static boolean isSolaris8;
    
    public static boolean isSolaris9;
    
    public static boolean isOpenSolaris;
    
    public static boolean useT2K;
    
    public static boolean isWindows;
    
    public static boolean isOpenJDK;
    
    static final String LUCIDA_FILE_NAME = "LucidaSansRegular.ttf";
    
    // This static initializer block figures out the OS constants.
    static {
        
        AccessController.doPrivileged(new PrivilegedAction () {
            public Object run() {
                String osName = System.getProperty("os.name", "unknownOS");
                isSolaris = osName.startsWith("SunOS");
                
                isLinux = osName.startsWith("Linux");
                
                isMacOSX = osName.contains("OS X");
                
                String t2kStr = System.getProperty("sun.java2d.font.scaler");
                if (t2kStr != null) {
                    useT2K = "t2k".equals(t2kStr);
                } else {
                    useT2K = false;
                }
                if (isSolaris) {
                    String version = System.getProperty("os.version", "0.0");
                    isSolaris8 = version.startsWith("5.8");
                    isSolaris9 = version.startsWith("5.9");
                    float ver = Float.parseFloat(version);
                    if (ver > 5.10f) {
                        File f = new File("/etc/release");
                        String line = null;
                        try {
                            FileInputStream fis = new FileInputStream(f);
                            InputStreamReader isr = new InputStreamReader(
                                    fis, "ISO-8859-1");
                            BufferedReader br = new BufferedReader(isr);
                            line = br.readLine();
                            fis.close();
                        } catch (Exception ex) {
                            // Nothing to do here.
                        }
                        if (line != null && line.indexOf("OpenSolaris") >= 0) {
                            isOpenSolaris = true;
                        } else {
                            isOpenSolaris = false;
                        }
                    } else {
                        isOpenSolaris = false;
                    }
                } else {
                    isSolaris8 = false;
                    isSolaris9 = false;
                    isOpenSolaris = false;
                }
                isWindows = osName.startsWith("Windows");
                String jreLibDirName = System.getProperty("java.home", "")
                        + File.separator + "lib";
                String jreFontDirName =
                        jreLibDirName + File.separator + "fonts";
                File lucidaFile = new File(jreFontDirName + File.separator
                        + LUCIDA_FILE_NAME);
                isOpenJDK = !lucidaFile.exists();
                
                return null;
            }
        });
    }
    
    /* A small "map" from GTK/fontconfig names to the equivalent JDK
     * logical font name.
     */
    private static final String[][] nameMap = {
            {"sans",       "sansserif"},
            {"sans-serif", "sansserif"},
            {"serif",      "serif"},
            {"monospace",  "monospaced"}
    };
    
    public static String mapFcName(String name) {
        for (int i = 0; i < nameMap.length; i++) {
            if (name.equals(nameMap[i][0])) {
                return nameMap[i][1];
            }
        }
        return null;
    }
    

    
}
