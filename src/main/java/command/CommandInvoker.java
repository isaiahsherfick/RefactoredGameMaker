package command;


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
		Command toUndo = commandStack.pop();
		toUndo.unexecute();
		undoneCommands.push(toUndo);
	}
	
	public void redo()
	{
		Command toRedo = undoneCommands.pop();
		toRedo.execute();
		commandStack.push(toRedo);
	}
	
}
