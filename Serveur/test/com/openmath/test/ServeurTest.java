package com.openmath.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.openmath.serveur.Connection;
import com.openmath.serveur.ServeurConnection;

public class ServeurTest {
	Connection serveur = new Connection();
	
	@Test
	public void verifIdTest() {
		assertTrue(serveur.verifId("Alexandre", "mdpalex"));
		assertTrue(serveur.verifId("Swabahadine", "mdpswabah"));
	}
}
