package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BearWithAnnotation extends Bear{
	
	@Autowired
	private Cave cave;

	@Override
	public String toString() {
		return "BearWithAnnotation [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}

	
	
}
