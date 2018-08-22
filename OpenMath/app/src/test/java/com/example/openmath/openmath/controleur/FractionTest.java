package com.example.openmath.openmath.controleur;

import com.example.openmath.openmath.modele.Fraction;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FractionTest {
    @Test
    public void addition() {


        Fraction f1 = new Fraction(2, 3);   // local fraction objects
        Fraction f2 = new Fraction(4, 5);   // used to test methods



        Fraction result = new Fraction();

        result = f1.add(f2);

        assertTrue(result.toString().equals("22/15"));
    }
    @Test
    public void soustraction() {
        Fraction f1 = new Fraction(4, 5);   // local fraction objects
        Fraction f2 = new Fraction(2, 3);   // used to test methods



        Fraction result = new Fraction();

        result = f1.subtract(f2);

        assertTrue(result.toString().equals("2/15"));
    }

    @Test
    public void multiplication() {
        Fraction f1 = new Fraction(4, 5);
        Fraction f2 = new Fraction(2, 3);



        Fraction result = new Fraction();

        result = f1.multiply(f2);

        assertTrue(result.toString().equals("8/15"));
    }

    @Test
    public void division() {
        Fraction f1 = new Fraction(4, 5);
        Fraction f2 = new Fraction(2, 3);
        Fraction result = new Fraction();
        result = f1.divide(f2);
        assertTrue(result.toString().equals("6/5"));
    }

    @Test
    public void simplification() {
        Fraction f1 = new Fraction(100, 60);
        Fraction result = new Fraction();

        result = f1.reduce();

        assertTrue(result.toString().equals("5/3"));
    }
    @Test
    public void toStringTest(){
        Fraction fraction = new Fraction(100, 1);
        assertEquals(fraction.toString(),"100");
    }
}
