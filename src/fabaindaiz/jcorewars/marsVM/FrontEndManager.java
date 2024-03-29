/*-
 * Copyright (c) 1998 Brian Haskin jr.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.listener.CycleListener;
import fabaindaiz.jcorewars.listener.RoundListener;
import fabaindaiz.jcorewars.listener.StepListener;

import java.awt.*;

/**
 * Interface for the object managing the frontend components.
 */
public interface FrontEndManager {
    /**
     * Called to register a StepListener to receive step results
     *
     * @param l - StepListener to register.
     */
    void registerStepListener(StepListener l);

    /**
     * Called to register a CycleListener to receive cycle results
     *
     * @param c - CycleListener to register.
     */
    void registerCycleListener(CycleListener c);

    /**
     * Called to register a RoundListener to receive round results
     *
     * @param r - RoundListener to register.
     */
    void registerRoundListener(RoundListener r);

    /**
     * Gets the warrior main color
     *
     * @param num Warrior number reference
     * @return Color
     */
    Color getColor(int num);

    /**
     * Gets the warrior death color
     *
     * @param num Warrior number reference
     * @return Color
     */
    Color getDColor(int num);

    /**
     * Gets the warrior normal colors
     *
     * @param num Warrior number reference
     * @return Colors array
     */
    Color[] getDynColors(int num);

    /**
     * Gets the warrior execution colors
     *
     * @param num Warrior number reference
     * @return Colors array
     */
    Color[] getColors(int num);
}
