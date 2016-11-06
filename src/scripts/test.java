package scripts;


import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

 

@ScriptManifest(authors = { "Boolean" }, category = "Woodcutting", name = "woodcutter")

public class test extends Script {
	@Override
	public void run() {
		println("hello world");
	}
}