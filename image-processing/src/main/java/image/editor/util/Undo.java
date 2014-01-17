package image.editor.util;

import java.util.LinkedList;

public class Undo<E> {

    private final int maxSize;
    private int position;
    private LinkedList<E> list;

    public Undo(int maxSize) {
        this.maxSize = maxSize;
        this.list = new LinkedList<E>();
    }

    public void add(E e) {
        trim();

        if (list.size() >= maxSize)
            list.remove();
        else
            position++;
        list.add(e);
    }

    public E undo() {
        if (list.isEmpty())
            return null;

        if (position > 0)
            position--;
        return list.get(position);
    }

    public E redo() {
        if (list.isEmpty())
            return null;

        if (position >= list.size() - 1)
            return list.getLast();
        position++;
        return list.get(position);
    }

    private void trim() {
        if (list.isEmpty())
            return;

        if (position < list.size()) {
            position++;
            int count = list.size() - position;
            for (int i = 0; i < count; i++)
                list.removeLast();
        }
    }

}
