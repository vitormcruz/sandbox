package sandbox.magritte

//TODO @Delegate is not working properly. Make it do!
//TODO Should implement Description?
class DescriptionContainer implements Collection<Description>, Description{
    private Collection<Description> descriptions

    def DescriptionContainer(Description... descriptions) {
        this.descriptions = new ArrayList<Description>(descriptions as Collection)
    }

    @Override
    int size() {
        return descriptions.size()
    }

    @Override
    boolean isEmpty() {
        return descriptions.isEmpty()
    }

    @Override
    boolean contains(Object o) {
        return descriptions.contains(o)
    }

    @Override
    Iterator<Description> iterator() {
        return descriptions.iterator()
    }

    @Override
    Object[] toArray() {
        return descriptions.toArray()
    }

    @Override
    def <T> T[] toArray(T[] a) {
        return descriptions.toArray(a)
    }

    @Override
    boolean add(Description description) {
        return descriptions.add(description)
    }

    @Override
    boolean remove(Object o) {
        return descriptions.remove(o)
    }

    @Override
    boolean containsAll(Collection<?> c) {
        return descriptions.containsAll(c)
    }

    @Override
    boolean addAll(Collection<? extends Description> c) {
        return descriptions.addAll(c)
    }

    @Override
    boolean removeAll(Collection<?> c) {
        return descriptions.removeAll(c)
    }

    @Override
    boolean retainAll(Collection<?> c) {
        return retainAll(c)
    }

    @Override
    void clear() {
        descriptions.clear()
    }

    //TODO Containeres of real Magritte, from smalltalk, implement Description. I was not able to study it more carefully yet, but I will let it here to think more in the future.

    @Override
    Description acessor(String acessor) {
        return null
    }

    @Override
    Description beRequired() {
        return null
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return null
    }

    @Override
    Description label(Object label) {
        return null
    }
}
