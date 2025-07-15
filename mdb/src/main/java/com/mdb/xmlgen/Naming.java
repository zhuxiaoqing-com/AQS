package com.mdb.xmlgen;

import org.w3c.dom.Element;

public abstract class Naming {

	private String name;

	public Naming() {
	}

	public Naming(Element self) {
		String name = self.getAttribute("name");
		if (name == null)
			throw new RuntimeException("name is null");
		this.name = name;
	}

	public Naming(String name) {
		if (name == null)
			throw new RuntimeException("name is null");
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
