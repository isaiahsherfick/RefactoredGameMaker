package controller;

import java.util.ArrayList;

import sound.Explosion;
import sound.FireSound;
import sound.Sound;

public class SoundButtonController {
	
	//TODO
	//This seems like something we can actually keep most of
	//It'll probably be one of the few files we simply add to
	//Isaiah
	public static ArrayList<Sound> allSounds = new ArrayList<Sound>();
	
	public SoundButtonController() {
		allSounds.clear();
		allSounds.add((Sound)new Explosion());
		allSounds.add((Sound)new FireSound());
	}
	
	public ArrayList<Sound> getAllSounds(){
		return allSounds;
	}
	
}
