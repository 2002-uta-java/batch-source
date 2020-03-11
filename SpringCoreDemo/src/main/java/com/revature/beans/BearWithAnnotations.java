package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// need to enable component scan to use annotations (in beans.xlm
@Component
public class BearWithAnnotations extends Bear {
	@Autowired
	private Cave cave;

	@Override
	public String toString() {
		return "BearWithAnnotations [cave=" + cave + ", id=" + id + ", name=" + name + "]";
	}
}
