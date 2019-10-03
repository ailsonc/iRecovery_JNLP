/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.irecovery.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 *
 * @author ailso
 */
class StreamReader extends Thread {
    private final InputStream is;
    private final StringWriter sw = new StringWriter();

    public StreamReader(final InputStream is) {
	this.is = is;
    }

    @Override
    public void run() {
	try {
            int c;
            while ((c = is.read()) != -1)
            sw.write(c);
	} catch (final IOException e) {
	}
    }

    public String getResult() {
	return sw.toString();
    }
}
