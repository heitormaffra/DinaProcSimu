package br.cesjf.dps

import com.sun.beans.decoder.FalseElementHandler;

class Developer {
	
	String name

    static constraints = {
		name size: 5..50, blank: false, unique: true
    }
}
