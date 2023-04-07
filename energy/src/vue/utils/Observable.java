package vue.utils;

import utils.Observer;

public interface Observable {

	void subscribe(Observer observer);
	void notifyObserver();
}
