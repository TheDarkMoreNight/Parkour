package me.thedmnight.parkour.NoteBlockAPI;

import org.bukkit.Sound;

public class Instrument {

	public static Sound getInstrument(byte instrument) {
		switch (instrument) {
		case 0:
			return Sound.BLOCK_NOTE_HARP;
		case 1:
			return Sound.BLOCK_NOTE_BASS;
		case 2:
			return Sound.BLOCK_NOTE_BASEDRUM;
		case 3:
			return Sound.BLOCK_NOTE_SNARE;
		case 4:
			return Sound.BLOCK_NOTE_HAT;
		case 5:
			return Sound.BLOCK_NOTE_GUITAR;
		case 6:
			return Sound.BLOCK_NOTE_FLUTE;
		case 7:
			return Sound.BLOCK_NOTE_BELL;
		case 8:
			return Sound.BLOCK_NOTE_CHIME;
		case 9:
			return Sound.BLOCK_NOTE_XYLOPHONE;
		case 10:
			return Sound.BLOCK_NOTE_PLING;
		case 11:
			return Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
		case 12:
			return Sound.ENTITY_ITEM_PICKUP;
		default:
			return Sound.BLOCK_NOTE_PLING;
		}
	}

	public static org.bukkit.Instrument getBukkitInstrument(byte instrument) {
		switch (instrument) {
		case 0:
			return org.bukkit.Instrument.PIANO;
		case 1:
			return org.bukkit.Instrument.BASS_GUITAR;
		case 2:
			return org.bukkit.Instrument.BASS_DRUM;
		case 3:
			return org.bukkit.Instrument.SNARE_DRUM;
		case 4:
			return org.bukkit.Instrument.STICKS;
		default:
			return org.bukkit.Instrument.PIANO;
		}
	}
}
