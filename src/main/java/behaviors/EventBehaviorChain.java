package behaviors;

import java.util.LinkedList;

import org.json.simple.JSONObject;

import saveandload.EventBehaviorLoader;
import saveandload.Saveable;

public class EventBehaviorChain implements Saveable
{
	private LinkedList<EventBehavior> chain;
	
	public EventBehaviorChain()
	{
		chain = new LinkedList<>();
	}
	
	public LinkedList<EventBehavior> getChain()
	{
		return chain;
	}
	
	public void add(EventBehavior action)
	{
		chain.add(action);
	}
	
	public void clear()
	{
		chain.clear();
	}
	
	public void remove(EventBehavior action)
	{
		chain.remove(action);
	}
	
	public EventBehavior get(int index)
	{
		return chain.get(index);
	}
	
	public int size()
	{
		return chain.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","EventBehaviorChain");
		int size = size();
		json.put("size",size);
		for (int i=0; i<size; i++)
		{
			json.put(i,chain.get(i).save());
		}
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		clear();
		int size = ((Long)saveJSON.get("size")).intValue();
		for (int i=0; i<size; i++)
		{
			EventBehavior e = EventBehaviorLoader.load((JSONObject)saveJSON.get(i));
			add(e);
		}
	}
	
	

}
