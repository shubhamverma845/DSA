public class FavoritesListMTF<E> extends FavoritesList<E> {

    @Override
    protected void moveUp(Position<Item<E>> p) {
        if (p != list.first()) {
            list.addLast(list.remove(p));
        }
    }

    @Override
    public Iterable<E> getFavorites(int k) {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("invalid k");
        }

        LinkedPositionalList<Item<E>> temp = new LinkedPositionalList<>();

        for (Item<E> item : list) {
            temp.addLast(item);
        }

        LinkedPositionalList<E> result = new LinkedPositionalList<>();

        for (int j = 0; j < k; j++) {
            Position<Item<E>> highPos = temp.first();
            Position<Item<E>> walk = temp.after(highPos);
            while (walk != null) {
                if (count(walk) > count(highPos)) {
                    highPos = walk;
                }

                walk = temp.after(walk);
            }

            result.addLast(value(highPos));
            temp.remove(highPos);
        }

        return result;

    }
}
