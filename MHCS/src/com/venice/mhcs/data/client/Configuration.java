package com.venice.mhcs.data.client;

import java.util.ArrayList;
import com.venice.mhcs.data.client.Module;

/*
 * Class intended to store configurations for modules
 * both Minimum and Full. Stores ArrayLists of modules
 * 
 * @author Nathan Gunnarson
 * 
 */
public class Configuration {
	
	/*
	 * Sets min1 if it is minimum
	 * @param An ArrayList of Modules
	 * @return true if isMinimum and set successful, false if not
	 */
	public boolean setMinimum1(ArrayList<Module> list){
		if(isMinimum(list)){
			min1 = list;
			return true;
		}
		else return false;
	}
	
	/*
	 * Sets min2 if it is minimum
	 * @param An ArrayList of Modules
	 * @return true if isMinimum and set successful, false if not
	 */
	public boolean setMinimum2(ArrayList<Module> list){
		if(isMinimum(list)){
			min2 = list;
			return true;
		}
		else return false;
	}
	
	public ArrayList<Module> getMinimum1(){
		return min1;
	}
	
	public ArrayList<Module> getMinimum2(){
		return min2;
	}
	
	public void setFull1(ArrayList<Module> list){
		full1 = list;
	}
	
	public void setFull2(ArrayList<Module> list){
		full2 = list;
	}
	
	public ArrayList<Module> getFull1(){
		return full1;
	}
	
	public ArrayList<Module> getFull2(){
		return full2;
	}
	
	/*
	 *Tests to make sure the list of modules contains
	 *enough to make a minimum configuration
	 *@param An ArrayList of modules
	 *@return true if minimum, false if not minimum
	 */
	public boolean isMinimum(ArrayList<Module> config){
		int airlock = 0, canteen = 0, control = 0, dorm = 0, foodwater = 0, plain = 0, power = 0, sanitation = 0;
		for(int i = 0; i < config.size() ; i++){
			Module test = config.get(i);
			if(test.getModuleType() == ModuleType.AIRLOCK) airlock++;
			else if(test.getModuleType() == ModuleType.CANTEEN) canteen++;
			else if(test.getModuleType() == ModuleType.CONTROL) control++;
			else if(test.getModuleType() == ModuleType.DORMITORY) dorm++;
			else if(test.getModuleType() == ModuleType.FOOD_WATER) foodwater++;
			//else if(test.getModuleType() == ModuleType.GYM_RELAXATION) gym++;
			//else if(test.getModuleType() == ModuleType.MEDICAL) medical++;		//Commented to be used 
			else if(test.getModuleType() == ModuleType.PLAIN) plain++;				//for Full Config Test
			else if(test.getModuleType() == ModuleType.POWER) power++;
			else if(test.getModuleType() == ModuleType.SANITATION) sanitation++;
			else {}
		}
		if(airlock >= 1 && control >= 1 && power >= 1 && foodwater >= 1 && dorm >= 1 
			&& canteen >= 1 && sanitation >= 1 && plain >= 3) return true;
		else return false;
	}
	
	
	/*
	 * Modifies coordinates of module to place it above another
	 * @param x the x coordinate of module to be below newly placed module
	 * @param y the y coordinate of module to be below newly placed module
	 * @return the module with modified coordinates to be above the x,y coordinates
	 */
	private Module attachTop(int x, int y, Module attachMod){
		int newY = y + 1;
		attachMod.setY(Integer.toString(newY));
		attachMod.setX(Integer.toString(x));
		return attachMod;
	}
	
	/*
	 * Modifies coordinates of module to place it below another
	 * @param x the x coordinate of module to be above newly placed module
	 * @param y the y coordinate of module to be above newly placed module
	 * @return the module with modified coordinates to be below the x,y coordinates
	 */
	private Module attachBottom(int x, int y, Module attachMod){
		int newY = y - 1;
		attachMod.setY(Integer.toString(newY));
		attachMod.setX(Integer.toString(x));
		return attachMod;
	}
	
	/*
	 * Modifies coordinates of module to place it left of another
	 * @param x the x coordinate of module to be right of newly placed module
	 * @param y the y coordinate of module to be right of newly placed module
	 * @return the module with modified coordinates to be left of the x,y coordinates
	 */
	private Module attachLeft(int x, int y, Module attachMod){
		int newX = x - 1;
		attachMod.setX(Integer.toString(newX));
		attachMod.setY(Integer.toString(y));
		return attachMod;
	}
	
	/*
	 * Modifies coordinates of module to place it right of another
	 * @param x the x coordinate of module to be left of newly placed module
	 * @param y the y coordinate of module to be left of newly placed module
	 * @return the module with modified coordinates to be right of the x,y coordinates
	 */
	private Module attachRight(int x, int y, Module attachMod){
		int newX = x + 1;
		attachMod.setX(Integer.toString(newX));
		attachMod.setY(Integer.toString(y));
		return attachMod;
	}

	/**
	 * 
	 * @param x - X coordinate for middle of where plain modules will be set
	 * @param y - Y coordinate for middle of where plains modules be set
	 * @param modList - ArrayList of plain modules
	 * @return List of plain modules with adjusted coordinates around midPoint x,y
	 */
	private ArrayList<Module> setPlains(int x, int y, ArrayList<Module> modList){
		ArrayList<Module> pList = new ArrayList<Module>();
		
		for(int i = 0; i < modList.size(); i++){
			if(modList.get(i).getModuleType() == ModuleType.PLAIN){
				pList.add(modList.remove(i));
				i--;
			}
		}
		
		for(int i = 0; i < pList.size(); i++){
			if(i == 0){
				pList.get(i).setX(Integer.toString(x));
				pList.get(i).setY(Integer.toString(y));
			}
			else if(i == 1){
				pList.get(i).setX(Integer.toString(x+1));
				pList.get(i).setY(Integer.toString(y));
			}
			else if(i == 2){
				pList.get(i).setX(Integer.toString(x-1));
				pList.get(i).setY(Integer.toString(y));
			}
		}
		
		for(int i = 0; i < pList.size(); i++){
			modList.add(pList.get(i));
		}
		return modList;
	}
	
	/**
	 * finds the x coordinate of midpoint for the min configuration
	 * @param minList - ArrayList of minConfig
	 * @return x coordinate
	 */
	private int findXSite(ArrayList<Module> minList){
		int tempX = 0;
		int site = 0;
		for(int i = 0; i < minList.size(); i++){
			tempX += Integer.parseInt(minList.get(i).getX());
		}
		
		site = tempX/minList.size();
		
		if(site == 1) site += 2;     //adjustments to be within landing zone
		else if(site == 2) site += 1;
		else if(site == 100) site -= 2;
		else if(site == 99) site -= 1;
		
		return site;
	}
	
	/**
	 * finds the y coordinate of midpoint for the min configuration
	 * @param minList - ArrayList of minConfig
	 * @return y coordinate
	 */
	private int findYSite(ArrayList<Module> minList){
		int tempY = 0;
		int site = 0;
		for(int i = 0; i < minList.size(); i++){
			tempY += Integer.parseInt(minList.get(i).getY());
		}
		
		site = tempY/minList.size();
		
		if(site == 1) site += 2;     //adjustments to be within landing zone
		else if(site == 2) site += 1;
		else if(site == 50) site -= 2;
		else if(site == 49) site -= 1;
		
		return site;
	}
	
	/**
	 * Takes a configuration that is yet to be built, accesses a list to be previously set,
	 * alters list, sets it, and returns configuration with module list of updated coordinates 
	 * @param min - Configuration with a set minimum list to be built
	 * @return configuration with altered minimum list
	 */
	public Configuration buildMin1(Configuration min){
		ArrayList<Module> minList = min.getMinimum1();
		int x = findXSite(minList);
		int y = findYSite(minList);


		if(min.isMinimum(minList)){
			minList = setPlains(x, y, minList);
			for(int i = 0; i < minList.size();i++){
				
				Module curMod = minList.get(i);
				
				if(curMod.getModuleType() == ModuleType.AIRLOCK){
					curMod = attachLeft(x, y, curMod);
					curMod = attachLeft(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				
				else if(curMod.getModuleType() == ModuleType.CANTEEN){
					curMod = attachLeft(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.DORMITORY){
					curMod = attachTop(x, y, curMod);
				}
				else if(curMod.getModuleType() == ModuleType.CONTROL){
					curMod = attachRight(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.SANITATION){
					curMod = attachRight(x, y, curMod);
					curMod = attachRight(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.POWER){
					curMod = attachRight(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.FOOD_WATER){
					curMod = attachLeft(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
			minList.remove(i);
			minList.add(i, curMod);
			}
		}
	
			min.setMinimum1(minList);
			return min;
	}

	/**
	 * Takes a configuration that is yet to be built, accesses a list to be previously set,
	 * alters list, sets it, and returns configuration with module list of updated coordinates 
	 * @param min - Configuration with a set minimum list to be built
	 * @return configuration with altered minimum list
	 */
	public Configuration buildMin2(Configuration min){
		ArrayList<Module> minList = min.getMinimum2();
		int x = findXSite(minList);
		int y = findYSite(minList);
		if(min.isMinimum(minList)){
			minList = setPlains(x, y, minList);
			for(int i = 0; i < minList.size();i++){
				
				Module curMod = minList.get(i);
				
				if(curMod.getModuleType() == ModuleType.AIRLOCK){
					curMod = attachLeft(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.CANTEEN){
					curMod = attachRight(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.DORMITORY){
					curMod = attachRight(x, y, curMod);
					curMod = attachRight(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.CONTROL){
					curMod = attachTop(x, y, curMod);
				}
				else if(curMod.getModuleType() == ModuleType.SANITATION){
					curMod = attachLeft(x, y, curMod);
					curMod = attachLeft(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.POWER){
					curMod = attachLeft(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.FOOD_WATER){
					curMod = attachRight(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
			minList.remove(i);
			minList.add(i, curMod);
			}
		}
		
			min.setMinimum2(minList);
			return min;
	}
	
	/**
	 * Takes 
	 * @param full - ArrayList of modules to be used for full configuration
	 * @return x coord
	 */
	private int findX(ArrayList<Module> full){
		int tempX = 0;
		int site = 0;
		for(int i = 0; i < full.size(); i++){
			tempX += Integer.parseInt(full.get(i).getX());		
		}
		site = tempX/full.size();
		
		if(site == 5) site++;
		if(site == 4) site += 2;
		if(site == 3) site += 3;
		if(site == 2) site += 4;
		if(site == 1) site += 5;
		
		if(site == 95) site--;
		if(site == 96) site -= 2;
		if(site == 97) site -= 3;
		if(site == 98) site -= 4;
		if(site == 99) site -= 5;
		if(site == 100) site -= 6;
		
		return site;
	}
	
	private int findY(ArrayList<Module> full){
		int tempY = 0;
		int site = 0;
		for(int i = 0; i < full.size(); i++){
			tempY += Integer.parseInt(full.get(i).getX());		
		}
		site = tempY/full.size();
		
		if(site == 6) site++;
		if(site == 5) site += 2;
		if(site == 4) site += 3;
		if(site == 3) site += 4;
		if(site == 2) site += 5;
		if(site == 1) site += 6;
		
		if(site == 45) site--;
		if(site == 46) site -= 2;
		if(site == 47) site -= 3;
		if(site == 48) site -= 4;
		if(site == 49) site -= 5;
		if(site == 50) site -= 6;
		
		return site;
	}
	
	private ArrayList<Module> getTypeList(ArrayList<Module> fullList, ModuleType type){
		ArrayList<Module> tList = new ArrayList<Module>();
		for(int i = 0; i < fullList.size(); i++){
			if(fullList.get(i).getModuleType() == type){
				tList.add(fullList.remove(i));
				i--;
			}
		}
		return tList;
	}
	
	public Configuration buildFull1(Configuration full){
		ArrayList<Module> fullList = full.getFull1();
		ArrayList<Module> config = new ArrayList<Module>();
		int x = findX(fullList);
		int y = findY(fullList);
		
		ArrayList<Module> pList = getTypeList(fullList, ModuleType.PLAIN);
		ArrayList<Module> aList = getTypeList(fullList, ModuleType.AIRLOCK);
		ArrayList<Module> canList = getTypeList(fullList, ModuleType.CANTEEN);
		ArrayList<Module> conList = getTypeList(fullList, ModuleType.CONTROL);
		ArrayList<Module> dList = getTypeList(fullList, ModuleType.DORMITORY);
		ArrayList<Module> fwList = getTypeList(fullList, ModuleType.FOOD_WATER);
		ArrayList<Module> gList = getTypeList(fullList, ModuleType.GYM_RELAXATION);
		ArrayList<Module> mList = getTypeList(fullList, ModuleType.MEDICAL);
		ArrayList<Module> powList = getTypeList(fullList, ModuleType.POWER);
		ArrayList<Module> sList = getTypeList(fullList, ModuleType.SANITATION);
		
		config.addAll(buildSpine(pList,aList, x, y));
		
		ArrayList<Module> hall = buildTopHallway(pList, x-4, y+3);
		config.addAll(hall);
		config.addAll(buildDorm(hall,dList,sList,gList,fwList,canList,x-4,y+3));
		
		ArrayList<Module> hall2 = buildTopHallway(pList, x, y+3);
		config.addAll(hall2);
		config.addAll(buildDorm(hall2,dList,sList,gList,fwList,canList,x,y+3));
		
		ArrayList<Module> hall3 = buildTopHallway(pList, x+4, y+3);
		config.addAll(hall3);
		config.addAll(buildDorm(hall3,dList,sList,gList,fwList,canList,x+4,y+3));
		
		ArrayList<Module> hall4 = buildBottomHallway(pList, x-3, y-3);
		config.addAll(hall4);
		config.addAll(buildWestWing(hall4,dList,sList,fwList,canList,gList,mList,x-3,y-3));
		
		ArrayList<Module> hall5 = buildBottomHallway(pList, x+1, y-3);
		config.addAll(hall5);
		config.addAll(buildControl(conList,powList,mList,aList,sList,hall5,x+1,y-3));
		
		ArrayList<Module> hall6 = buildBottomHallway(pList, x+5, y-3);
		config.addAll(hall6);
		config.addAll(buildEastWing(hall6,dList,sList,mList,aList,fwList,x+5,y-3));
		
		full.setFull1(config);
		return full;
	}
	
	private ArrayList<Module> buildSpine(ArrayList<Module> plain, ArrayList<Module> air, int x, int y){
		ArrayList<Module> spine = new ArrayList<Module>();
		for(int i  = 0; i < 10; i++){
			if(i == 0) pullAndPlace(spine, plain, x, y);
			else if(i == 1) pullAndPlace(spine, plain, x+1, y);
			else if(i == 2) pullAndPlace(spine, plain, x-1, y);
			else if(i == 3) pullAndPlace(spine, plain, x+2, y);
			else if(i == 4) pullAndPlace(spine, plain, x-2, y);
			else if(i == 5) pullAndPlace(spine, plain, x+3, y);
			else if(i == 6) pullAndPlace(spine, plain, x-3, y);
			else if(i == 7) pullAndPlace(spine, plain, x+4, y);
			else if(i == 8) pullAndPlace(spine, plain, x-4, y);
			else pullAndPlace(spine, plain, x+5, y);
		}
		pullAndPlace(spine, air, x+6, y);
		pullAndPlace(spine, air, x-5, y);
		return spine;
	}
	
	private ArrayList<Module> buildDorm(ArrayList<Module> hall, ArrayList<Module> dorm, ArrayList<Module> sani, ArrayList<Module> gym, 
			ArrayList<Module> fw, ArrayList<Module> can, int x, int y){
		
		ArrayList<Module> dormWing = new ArrayList<Module>();
		int pCount = hall.size();
	
		if(pCount == 3 || pCount == 4){
			pullAndPlace(dormWing, dorm, x-1, y);
			pullAndPlace(dormWing, dorm, x+1, y);
		}
		else if(pCount == 5){
			pullAndPlace(dormWing, dorm, x-1, y);
			pullAndPlace(dormWing, dorm, x+1, y);
			pullAndPlace(dormWing, dorm, x-1, y+2);
			pullAndPlace(dormWing, dorm, x, y+3);
		}
		
		if(pCount == 4){
			pullAndPlace(dormWing, sani, x-1, y+1);
		}
		else if(pCount == 5){
			pullAndPlace(dormWing, sani, x-1,y+1);
			pullAndPlace(dormWing, sani, x+1,y+2);
		}
		
		if(pCount >= 4){
			pullAndPlace(dormWing, gym, x+1, y+1);
		}
		
		if(pCount == 1){
			pullAndPlace(dormWing, fw, x+1, y-2);
		}
		else if(pCount >= 2){
			pullAndPlace(dormWing, fw, x+1, y-2);
			pullAndPlace(dormWing, fw, x+1, y-1);
			pullAndPlace(dormWing, fw, x-1, y-1);
		}
		
		if(pCount >= 1){
			pullAndPlace(dormWing, can, x-1, y-2);
		}
		
		return dormWing;
	}
	
	private void pullAndPlace(ArrayList<Module> addList, ArrayList<Module> removeList, int x, int y){
		if(!removeList.isEmpty()){
			Module temp = removeList.remove(0);
			temp.setX(Integer.toString(x));
			temp.setY(Integer.toString(y));  
			addList.add(temp);
		}
	}
	
	private ArrayList<Module> buildControl(ArrayList<Module> cont, ArrayList<Module> pow, ArrayList<Module> med,
			ArrayList<Module> air, ArrayList<Module> sani, ArrayList<Module> plain, int x, int y){
		
		ArrayList<Module> contWing = new ArrayList<Module>();
		int pCount = plain.size();
		
		if(pCount == 2 || pCount == 3){
			pullAndPlace(contWing, cont, x+1, y+1);
			pullAndPlace(contWing, cont, x-1, y+1);
		}
		if(pCount >= 4){
			pullAndPlace(contWing, cont, x+1, y+1);
			pullAndPlace(contWing, cont, x-1, y+1);
			pullAndPlace(contWing, cont, x-1, y-1);
			pullAndPlace(contWing, cont, x+1, y-1);
		}
		if(pCount == 1 || pCount == 3){
			pullAndPlace(contWing, pow, x+1, y+2);
			pullAndPlace(contWing, pow, x-1, y+2);
		}
		if(pCount >= 3){
			pullAndPlace(contWing, pow, x+1, y+2);
			pullAndPlace(contWing, pow, x-1, y+2);
			pullAndPlace(contWing, pow, x+1, y);
			pullAndPlace(contWing, pow, x-1, y);
		}
		if(pCount == 5){
			pullAndPlace(contWing, med, x-1, y-2);
			pullAndPlace(contWing, sani, x+1, y-2);
			pullAndPlace(contWing, air, x, y-3);
		}
		return contWing;
	}
	
	private ArrayList<Module> buildWestWing(ArrayList<Module> plain, ArrayList<Module> dorm, ArrayList<Module> sani,
			ArrayList<Module> fw, ArrayList<Module> can, ArrayList<Module> gym, ArrayList<Module> med, int x, int y){
		
		ArrayList<Module> westWing = new ArrayList<Module>();
		int pCount = plain.size();
		
		if(pCount == 3){
			pullAndPlace(westWing, dorm, x+1, y);
			pullAndPlace(westWing, dorm, x-1, y);
		}
		if(pCount == 4){
			pullAndPlace(westWing, dorm, x+1, y);
			pullAndPlace(westWing, dorm, x-1, y);
			pullAndPlace(westWing, dorm, x+1, y-1);
			pullAndPlace(westWing, dorm, x-1, y-1);
		}
		if(pCount == 5){
			pullAndPlace(westWing, dorm, x+1, y);
			pullAndPlace(westWing, dorm, x-1, y);
			pullAndPlace(westWing, dorm, x+1, y-1);
			pullAndPlace(westWing, dorm, x-1, y-1);
			pullAndPlace(westWing, dorm, x-1, y-2);
			pullAndPlace(westWing, fw, x, y-3);
			pullAndPlace(westWing, can, x+1, y-2);
		}
		if(pCount == 1){
			pullAndPlace(westWing, sani, x+1, y+2);
		}
		if(pCount >= 1){
			pullAndPlace(westWing, med, x-1, y+2);
		}
		if(pCount >= 2){
			pullAndPlace(westWing, sani, x+1, y+2);
			pullAndPlace(westWing, sani, x-1, y+1);
			pullAndPlace(westWing, gym, x+1, y+1);
		}
		return westWing;
	
	}
	
	private ArrayList<Module> buildEastWing(ArrayList<Module> plain, ArrayList<Module> dorm, ArrayList<Module> sani,
			ArrayList<Module> med, ArrayList<Module> air, ArrayList<Module> fw, int x, int y){
		
		ArrayList<Module> eastWing = new ArrayList<Module>();
		int pCount = plain.size();
		
		if(pCount >= 1){
			pullAndPlace(eastWing, sani, x-1, y+2);
			pullAndPlace(eastWing, med, x+1, y+2);
		}
		if(pCount == 2){
			pullAndPlace(eastWing, dorm, x-1, y+1);
			pullAndPlace(eastWing, dorm, x+1, y+1);
		}
		if(pCount >=3){
			pullAndPlace(eastWing, dorm, x-1, y+1);
			pullAndPlace(eastWing, dorm, x+1, y+1);
			pullAndPlace(eastWing, dorm, x+1, y);
		}
		if(pCount == 4){
			pullAndPlace(eastWing, fw, x+1, y-1);
			pullAndPlace(eastWing, fw, x-1, y-1);
		}
		if(pCount ==5){
			pullAndPlace(eastWing, fw, x+1, y-1);
			pullAndPlace(eastWing, fw, x-1, y-1);
			pullAndPlace(eastWing, fw, x+1, y-2);
			pullAndPlace(eastWing, air, x, y-3);
			pullAndPlace(eastWing, med, x-1, y-2);
		}
		return eastWing;
	}
		
	private ArrayList<Module> buildTopHallway(ArrayList<Module> plain, int x, int y){
	ArrayList<Module> hall = new ArrayList<Module>();
		
		for(int i = 0; i < 5; i++){
			if(!plain.isEmpty()){			
				if(i == 0){
					plain.get(0).setX(Integer.toString(x));
					plain.get(0).setY(Integer.toString(y-2));
					hall.add(plain.remove(0));
				}
				else if(i == 1){
					plain.get(0).setX(Integer.toString(x));
					plain.get(0).setY(Integer.toString(y-1));
					hall.add(plain.remove(0));
				}
				else if(i == 2){
					plain.get(0).setX(Integer.toString(x));
					plain.get(0).setY(Integer.toString(y));
					hall.add(plain.remove(0));
				}
				else if(i == 3){
					plain.get(0).setX(Integer.toString(x));
					plain.get(0).setY(Integer.toString(y+1));
					hall.add(plain.remove(0));
				}
				else{
					plain.get(0).setX(Integer.toString(x));
					plain.get(0).setY(Integer.toString(y+2));
					hall.add(plain.remove(0));
				}
			}
		}
		return hall;
	}
	
	private ArrayList<Module> buildBottomHallway(ArrayList<Module> plain, int x, int y){
		ArrayList<Module> hall = new ArrayList<Module>();
			
			for(int i = 0; i < 5; i++){
				if(!plain.isEmpty()){			
					if(i == 0){
						plain.get(0).setX(Integer.toString(x));
						plain.get(0).setY(Integer.toString(y+2));
						hall.add(plain.remove(0));
					}
					else if(i == 1){
						plain.get(0).setX(Integer.toString(x));
						plain.get(0).setY(Integer.toString(y+1));
						hall.add(plain.remove(0));
					}
					else if(i == 2){
						plain.get(0).setX(Integer.toString(x));
						plain.get(0).setY(Integer.toString(y));
						hall.add(plain.remove(0));
					}
					else if(i == 3){
						plain.get(0).setX(Integer.toString(x));
						plain.get(0).setY(Integer.toString(y-1));
						hall.add(plain.remove(0));
					}
					else{
						plain.get(0).setX(Integer.toString(x));
						plain.get(0).setY(Integer.toString(y-2));
						hall.add(plain.remove(0));
					}
				}
			}
			return hall;
		}
	
	private ArrayList<Module> min1 = new ArrayList<Module>();
	private ArrayList<Module> min2 = new ArrayList<Module>();
	private ArrayList<Module> full1 = new ArrayList<Module>();
	private ArrayList<Module> full2 = new ArrayList<Module>();
}
