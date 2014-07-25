package com.example.xproduct.observer;

import java.util.Observable;

public class MyObservable extends Observable {

	private int position = 0;

	public void setPosition(int position) {
		this.position = position;
		setChanged();
		notifyObservers();
	}

	public int getPosition() {
		return position;
	}

}
