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
	
	Hashtable<String, ArrayList<String>> coords = 
			new Hashtable<String, ArrayList<String>>();

	@Override
	public void run() {
		final int NPC = 2805;
		RSNPC[] npcs = NPCs.find(NPC);
		int i = 0;
		int totalCycles = 0;
		FileWriter fw = null;
		
		try {
			File file = new File("/Users/harveyxu/Library/Application Support/tribot/text.txt");
			file.getParentFile().mkdirs();
			
			// if file doesnt exists, then create it
			if (!file.exists()) {
				System.out.println(file.getAbsoluteFile());
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		String npcId = "NPC" + i;
		while (true) {
			for (RSNPC npc : npcs) {
				if (!coords.containsKey(npcId)) {
					coords.put(npcId, new ArrayList<String>());
				}
				
				int[] xVals = npc.getWalkingQueueX();
				int[] yVals = npc.getWalkingQueueY();
				ArrayList<String> currCoordList = coords.get(npcId);
				String currLastCoord = "";
				boolean threshold = false;
				
				if (currCoordList.size() != 0) {
					currLastCoord = currCoordList.get(currCoordList.size() - 1);
				} 
				
				RSTile tile = npc.getPosition();
				currCoordList.add((tile.getX()-Game.getBaseX()) + "," + (tile.getY()-Game.getBaseY()));		
				System.out.println((tile.getX()-Game.getBaseX()) + "," + (tile.getY()-Game.getBaseY()));
				
				try {
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write((tile.getX()-Game.getBaseX()) + "," + (tile.getY()-Game.getBaseY()));
					bw.newLine();
					
					bw.flush();

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

				coords.remove("NPC" + i);
				coords.put("NPC" + i, currCoordList);
				
				i++;
				threshold = false;
			}
			sleep(200);
			i = 0;
			totalCycles++;
		}
	}
}