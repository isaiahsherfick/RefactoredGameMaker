package command;

import java.util.EmptyStackException;
import java.util.Stack;

import command.Command;

public class CommandInvoker 
{
	private Stack<Command> commandStack;
	private Stack<Command> undoneCommands;
	
	public CommandInvoker()
	{
		commandStack = new Stack<>();
		undoneCommands = new Stack<>();
	}
	
	public void receiveCommand(Command c)
	{
		c.execute();
		commandStack.push(c);
	}
	
	public void undo()
	{
		try {
		Command toUndo = commandStack.pop();
		toUndo.unexecute();
		undoneCommands.push(toUndo);
		} catch(EmptyStackException e)
		{
			
		}
	}
	
	
	public void redo()
	{
		try {
		Command toRedo = undoneCommands.pop();
		toRedo.execute();
		commandStack.push(toRedo);
		} catch(EmptyStackException e)
		{
			
		}
	}
	
}
