package fabaindaiz.jcorewars;/*-
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

/*
  reads in the file specified on the command line and
  produces a listing.
 */

import fabaindaiz.jcorewars.loader.Assembler;
import fabaindaiz.jcorewars.memory.Memory;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Represent a main class which test assembler parse of redcode
 */
public class AsTest {

    /**
     * Execute a assemble parse test
     *
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("usage: AsTest filename");
            return;
        }

        try {
            FileReader file = new FileReader(args[0]);
            Assembler parser = new Assembler(file, 100);

            Memory[] warrior;

            if (!parser.assemble()) {
                System.out.println("error in warrior file, possibly near instruction " + parser.length());
                warrior = parser.getWarrior();
                System.out.println("last instruction " + warrior[warrior.length - 1]);
                return;
            }

            System.out.println(";name " + parser.getName());
            System.out.println(";author " + parser.getAuthor() + "\n");
            System.out.println("ORG	" + parser.getOffset());
            warrior = parser.getWarrior();

            for (Memory memory : warrior) {
                System.out.println(memory);
            }

        } catch (FileNotFoundException e) {
            System.out.println("file could not be opened");
        }
    }
}
