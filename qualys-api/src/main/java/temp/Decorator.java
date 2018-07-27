package temp;

/**
 * Converts on object into another.
 * 
 * @param <W> source object class.
 * @param <O> decorated object class.
 */
public interface Decorator<W, O> {

   /**
    * @param source {@link Object} data used to decorate.
    * @param target {@link Object} decorable object.
    * @return the decorated {@link Object}.
    * @throws java.lang.IllegalArgumentException if the source object is <code>null</code>.
    */
   O decorate(final W source, final O target);
}
