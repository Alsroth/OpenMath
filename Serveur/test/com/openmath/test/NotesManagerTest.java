package com.openmath.serveur;

import org.junit.*;
import java.util.*;
import org.json.*;


public class NotesManagerTest {

	


	@BeforeClass
	public static void setup() {
		NotesManager.addScoreForUser("testUser1", "testQC1", 1);
		NotesManager.addScoreForUser("testUser2", "testQC2", 3);

	}

	@Test
	public void addScoreForUserTest() throws JSONException {
		JSONObject un1 = NotesManager.getScoresForUser("testUser1");
		int n1 = un1.getInt("testQC1");
		NotesManager.addScoreForUser("testUser1", "testQC1", n1 + 1);
		JSONObject un2 = NotesManager.getScoresForUser("testUser1");
		int n2 = un2.getInt("testQC1");
		Assert.assertEquals(n1+1, n2);
	}



}