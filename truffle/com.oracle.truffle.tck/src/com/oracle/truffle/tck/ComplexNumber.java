/*
 * Copyright (c) 2015, 2015, Oracle and/or its affiliates. All rights reserved.
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
package com.oracle.truffle.tck;

import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;

final class ComplexNumber implements TruffleObject {

    public static final String REAL_IDENTIFIER = "real";
    public static final String IMAGINARY_IDENTIFIER = "imaginary";

    private double real;
    private double imag;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imag = imaginary;
    }

    @Override
    public ForeignAccess getForeignAccess() {
        return ComplexNumberMessageResolutionForeign.createAccess();
    }

    void set(String identifier, double value) {
        switch (identifier) {
            case REAL_IDENTIFIER:
                this.real = value;
                break;
            case IMAGINARY_IDENTIFIER:
                this.imag = value;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    double get(String identifier) {
        switch (identifier) {
            case REAL_IDENTIFIER:
                return this.real;
            case IMAGINARY_IDENTIFIER:
                return this.imag;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static boolean isInstance(TruffleObject obj) {
        return obj instanceof ComplexNumber;
    }

}
