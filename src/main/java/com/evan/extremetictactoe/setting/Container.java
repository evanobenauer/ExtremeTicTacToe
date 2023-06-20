package com.evan.extremetictactoe.setting;

/**
 * This class is a container class for a single value. This value can be changed and retrieved. This class is very
 * useful for using outside variables within lambda statements.
 */
public class Container<T> {

    private T value;

    /**
     * Default Constructor
     * @param value
     */
    public Container(T value) {
        this.value = value;
    }

    public Container() {
        this(null);
    }


    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "[Container: " + get() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Container<?>)) return false;
        Container<?> container = (Container<?>) obj;
        return container.get().equals(get());
    }
}
