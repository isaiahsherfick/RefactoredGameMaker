package pattern;

public interface Observable 
{
	public void register(Observer observer);
	public void unregister(Observer observer);

    //TODO why is this called tick()?
    //That just makes this restricted to clocks
    //change to update()
    //-Isaiah
	public void tick();

}
