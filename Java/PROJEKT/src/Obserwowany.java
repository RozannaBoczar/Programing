public interface Obserwowany {
    public void subscribe( Obserwator o );
    public void unsubscribe( Obserwator o );
}
