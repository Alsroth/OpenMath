package com.example.openmath.openmath.modele;

import java.util.Scanner;

/**
 * Created by swabahadine on 14/03/2018.
 */

public class Fraction
{
    // member variables
    private int numerator, denominator;  // stores the fraction data

    //Constructeur par defaut
    public Fraction()
    {
        numerator = denominator = 0;
    }
    // Constructeur
    public Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // getters & setters Numerateur
    public int getNumerator()
    {
        return numerator;
    }

    public void setNumerator(int num)
    {
        numerator=num;
    }

    // getters & setters Denominateur
    public int getDenominator()
    {
        return denominator;
    }


    public void setDenominator(int den)
    {
        denominator=den;
    }

    /*@autor Swabahadine Abdallah
     *Addition de la fraction avec une autre fraction
     @param Fraction b
     @return Fraction
     */
    public Fraction add(Fraction b)
    {
        // check preconditions
        if ((denominator == 0) || (b.denominator == 0))
            throw new IllegalArgumentException("invalid denominator");
        // find lowest common denominator
        int common = lcd(denominator, b.denominator);
        // convert fractions to lcd
        Fraction commonA = new Fraction();
        Fraction commonB = new Fraction();
        commonA = convert(common);
        commonB = b.convert(common);
        // create new fraction to return as sum
        Fraction sum = new Fraction();
        // calculate sum
        sum.numerator = commonA.numerator + commonB.numerator;
        sum.denominator = common;
        // reduce the resulting fraction
        sum = sum.reduce();
        return sum;
    }

    /*@autor Swabahadine Abdallah
     *Soustraction de la fraction avec une autre fraction
     @param Fraction b
     @return Fraction
     */
    public Fraction subtract(Fraction b)
    {
        // check preconditions
        if ((denominator == 0) || (b.denominator == 0))
            throw new IllegalArgumentException("invalid denominator");
        // find lowest common denominator
        int common = lcd(denominator, b.denominator);
        // convert fractions to lcd
        Fraction commonA = new Fraction();
        Fraction commonB = new Fraction();
        commonA = convert(common);
        commonB = b.convert(common);
        // create new fraction to return as difference
        Fraction diff = new Fraction();
        // calculate difference
        diff.numerator = commonA.numerator - commonB.numerator;
        diff.denominator = common;
        // reduce the resulting fraction
        diff = diff.reduce();
        return diff;
    }

    /*@autor Swabahadine Abdallah
     *Multiplication de la fraction avec une autre fraction
     @param Fraction b
     @return Fraction
     */
    public Fraction multiply(Fraction b)
    {
        // check preconditions
        if ((denominator == 0) || (b.denominator == 0))
            throw new IllegalArgumentException("invalid denominator");
        // create new fraction to return as product
        Fraction product = new Fraction();
        // calculate product
        product.numerator = numerator * b.numerator;
        product.denominator = denominator * b.denominator;
        // reduce the resulting fraction
        product = product.reduce();
        return product;
    }
    /*@autor Swabahadine Abdallah
         *Division de la fraction avec une autre fraction
         @param Fraction b
         @return Fraction
         */// Méthode pour diviser
    public Fraction divide(Fraction b)
    {
        // check preconditions
        if ((denominator == 0) || (b.numerator == 0))
            throw new IllegalArgumentException("invalid denominator");
        // create new fraction to return as result
        Fraction result = new Fraction();
        // calculate result
        result.numerator = numerator * b.denominator;
        result.denominator = denominator * b.numerator;
        // reduce the resulting fraction
        result = result.reduce();
        return result;
    }

    // Input
    public void input()
    {
        Scanner sc = new Scanner(System.in);
        // prompt user to enter numerator
        System.out.print("Please enter an integer for numerator: ");
        // get user input
        numerator = sc.nextInt();

        // prompt user to enter denominator in a loop to prevent
        // an invalid (zero) value for denominator
        do {
            System.out.print("Please enter a non-zero integer for denominator: ");
            denominator = sc.nextInt();
            // make sure it is non-zero
            if (denominator == 0)
                System.out.println("Invalid value.  Please try again.");
        } while (denominator == 0);
        setNumerator(numerator);
        setDenominator(denominator);
    }

    // output
    public void output()
    {
        System.out.print(this);
    }

    // affichage
    public String toString()
    {
        if(denominator == 1){
            return numerator+"";
        }
        String buffer = numerator + "/" + denominator;
        return buffer;
    }

/*****************************************************/
/* private methods used internally by Fraction class */
/*****************************************************/

    /*

     */
    private int lcd(int denom1, int denom2)
    {
        int factor = denom1;
        while ((denom1 % denom2) != 0)
            denom1 += factor;
        return denom1;
    }

    /**********************************************************
     Method:         gcd
     Purpose:        find greatest common denominator, used to reduce
     fractions
     Parameters:     two integers, denom1 and denom2
     Preconditions:  denom1 and denom2 must be positive integer values
     denom1 is assumed to be greater than denom2
     (denom1 > denom2 > 0)
     Postconditions: None
     Returns:        the greatest common denominator between denom1 and
     denom2
     Credits:        Thanks to Euclid for inventing the gcd algorithm,
     and to Prof. Joyce for explaining it to me.
     ***********************************************************/
    private int gcd(int denom1, int denom2)
    {
        int factor = denom2;
        while (denom2 != 0) {
            factor = denom2;
            denom2 = denom1 % denom2;
            denom1 = factor;
        }
        return denom1;
    }

    /**********************************************************
     Method:         convert
     Purpose:        convert a fraction to an equivalent one based on
     a lowest common denominator
     Parameters:     an integer common, the new denominator
     Preconditions:  the "this" fraction must contain valid data for
     numerator and denominator
     the integer value common is assumed to be greater
     than the "this" fraction's denominator
     Postconditions: None
     Returns:        A new fraction which is equivalent to the "this"
     fraction, but has been converted to the new
     denominator called common
     ***********************************************************/
    private Fraction convert(int common)
    {
        Fraction result = new Fraction();
        int factor = common / denominator;
        result.numerator = numerator * factor;
        result.denominator = common;
        return result;
    }

    /**
     * @autor Swabahadine Abdallah
     *Simplifie la fraction
     @return Fraction
     */
    public Fraction reduce()
    {
        Fraction result = new Fraction();
        int common = 0;
        // get absolute values for numerator and denominator
        int num = Math.abs(numerator);
        int den = Math.abs(denominator);
        // figure out which is less, numerator or denominator
        if (num > den)
            common = gcd(num, den);
        else if (num < den)
            common = gcd(den, num);
        else  // if both are the same, don't need to call gcd
            common = num;

        // set result based on common factor derived from gcd
        result.numerator = numerator / common;
        result.denominator = denominator / common;
        return result;
    }


}
