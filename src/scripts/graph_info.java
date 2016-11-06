package scripts;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class graph_info extends Script {
	@Override
	public void run() {
		final int NPC = 2805;
		RSNPC[] npcs = NPCs.find(NPC);
		FileWriter fw = null;
		
		try {
			File file = new File("/Users/harveyxu/Library/Application Support/tribot/text.txt");
			file.getParentFile().mkdirs();
			
			// if file doesn't exist, then create it
			if (!file.exists()) {
				System.out.println(file.getAbsoluteFile());
				file.createNewFile();
			}
			
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		while (true) {
			for (RSNPC npc : npcs) {
				RSTile tile = npc.getPosition();
				
				try {
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write((tile.getX() - Game.getBaseX()) + "," + (tile.getY() - Game.getBaseY()));
					bw.newLine();
					bw.flush();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			sleep(200);
		}
	}
}