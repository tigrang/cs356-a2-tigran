package com.tigrang.mvc.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO: Optimize
 *
 * @param <K>
 * @param <V>
 */
public class IndexedHashMap<K, V> extends LinkedHashMap<K, V> {

	public int indexOf(Object value) {
		int index = -1;
		for (Map.Entry<? extends K, ? extends V> entry : entrySet()) {
			index++;
			if (entry.getValue().equals(value)) {
				return index;
			}
		}
		return -1;
	}

	public V at(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		int i = 0;
		for (Map.Entry<? extends K, ? extends V> entry : entrySet()) {
			if (index == i++) {
				return entry.getValue();
			}
		}
		return null;
	}

}
