package behaviors;

import java.util.LinkedList;

import org.json.simple.JSONObject;

import saveandload.EventBehaviorLoader;
import saveandload.Saveable;
import sprite.Sprite;

public class EventBehaviorChain implements EventBehavior
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
		if (size == 0)
		{
			return;
		}
		for (Integer i=0; i<size; i++)
		{
			EventBehavior e = EventBehaviorLoader.load((JSONObject)saveJSON.get(i.toString()));
			add(e);
		}
	}

	@Override
	public void onMousePress(Sprite sprite) 
	{
		for (EventBehavior e : chain)
		{
			e.onMousePress(sprite);
		}
	}

	@Override
	public void onGameStart(Sprite sprite) 
	{
		for (EventBehavior e : chain)
		{
			e.onGameStart(sprite);
		}
	}

	@Override
	public void onMouseMove(Sprite sprite) 
	{
		for (EventBehavior e : chain)
		{
			e.onMouseMove(sprite);
		}
	}

	@Override
	public void onKeyPress(Sprite sprite) 
	{
		for (EventBehavior e : chain)
		{
			e.onKeyPress(sprite);
		}
	}

	@Override
	public void onGameTick(Sprite sprite) 
	{
		for (EventBehavior e : chain)
		{
			e.onGameTick(sprite);
		}
	}

	@Override
	public void onLevelIncrease(Sprite sprite) 
	{
		for (EventBehavior e : chain)
		{
			e.onLevelIncrease(sprite);
		}
	}

	@Override
	public EventBehaviorChain copy() 
	{
		EventBehaviorChain copy = new EventBehaviorChain();
		for (int i = 0; i<chain.size(); i++)
		{
			copy.add(chain.get(i).copy());
		}
		return copy;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof EventBehaviorChain)
		{
			EventBehaviorChain e = (EventBehaviorChain)o;
			if (e.size() != size())
			{
				return false;
			}
			for (int i=0; i<size(); i++)
			{
				if (!get(i).equals(e.get(i)))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
