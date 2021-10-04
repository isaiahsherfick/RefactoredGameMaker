package controller;

import java.util.ArrayList;

import sound.Explosion;
import sound.FireSound;
import sound.Sound;

public class SoundButtonController {
	
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
