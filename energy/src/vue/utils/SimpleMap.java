package vue.utils;

public interface SimpleMap<K,V, P> {

    V getBehaviour(K key);
    
    P getNextElement();


}
