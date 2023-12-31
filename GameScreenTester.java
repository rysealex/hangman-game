import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.Result;
import static org.junit.runner.JUnitCore.runClasses;

public class GameScreenTester {

    public static void main(String[] args)
    {
        Result result = runClasses(GameScreenTester.class);
        System.out.println("Tests run = " + result.getRunCount() +
                "\nTests failed = " + result.getFailures());
    } // method main

    protected String randomWord;
    protected String underScores;
    protected String userInput;
    private String guessedLetters;

    @Before
    public void generateWord()
    {
        randomWord = "word";
    } // method generateWord

    @Before
    public void buildUnderscores()
    {
        underScores = "";

        for (int i = 0; i < randomWord.length(); i++)
        {
            underScores += "_ ";
        }
    } // method buildUnderscores

    @Before
    public void getGuessedLetters()
    {
        guessedLetters = "wd";
    } // method getGuessedLetters

    @Test
    public void updateUnderscoresTest()
    {
        String prevUnderScores = underScores;
        userInput = "w";

        int[] indexes = new int[randomWord.length()];
        int j = 0;
        for (int i = 0; i < randomWord.length(); i++)
        {
            if (randomWord.charAt(i) == randomWord.charAt(0))
            {
                indexes[j] = i + i;
                j++;
            }
        }
        j = 0;
        StringBuilder sb = new StringBuilder(prevUnderScores);
        for (int i = 0; i < sb.length(); i++)
        {
            if (i == indexes[j])
            {
                sb.setCharAt(i, randomWord.charAt(0));
                j++;
            }
        }

        underScores = sb.toString();
        String expected = "w _ _ _ ";

        assertEquals(expected, underScores);
    } // method updateUnderscoresTest

    @Test
    public void checkUnderscoresTest1()
    {
        underScores = "w o r d ";
        boolean check = false;

        for (int i = 0; i < underScores.length(); i++)
        {
            if (underScores.charAt(i) == '_')
            {
                check = true;
                break;
            }
        }

        boolean expected = false;

        assertEquals(expected, check);
    } // method checkUnderscoresTest1

    @Test
    public void checkUnderscoresTest2()
    {
        underScores = "w _ _ d ";
        boolean check = false;

        for (int i = 0; i < underScores.length(); i++)
        {
            if (underScores.charAt(i) == '_')
            {
                check = true;
                break;
            }
        }

        boolean expected = true;

        assertEquals(expected, check);
    } // method checkUnderscoresTest2

    @Test
    public void isLowerCaseLetterTest1()
    {
        userInput = "w";
        boolean check;

        check = Character.isLowerCase(userInput.charAt(0)) && userInput.length() == 1;
        boolean expected = true;

        assertEquals(expected, check);
    } // method isLowerCaseLetterTest1

    @Test
    public void isLowerCaseLetterTest2()
    {
        userInput = "W";
        boolean check;

        check = Character.isLowerCase(userInput.charAt(0)) && userInput.length() == 1;
        boolean expected = false;

        assertEquals(expected, check);
    } // method isLowerCaseLetterTest2

    @Test
    public void isLowerCaseLetterTest3()
    {
        userInput = "1";
        boolean check;

        check = Character.isLowerCase(userInput.charAt(0)) && userInput.length() == 1;
        boolean expected = false;

        assertEquals(expected, check);
    } // method isLowerCaseLetterTest3

    @Test
    public void isUniqueTest1()
    {
        userInput = "w";
        boolean check;

        check = !guessedLetters.contains(userInput);
        boolean expected = false;

        assertEquals(expected, check);
    } // method isUniqueTest1

    @Test
    public void isUniqueTest2()
    {
        userInput = "o";
        boolean check;

        check = !guessedLetters.contains(userInput);
        boolean expected = true;

        assertEquals(expected, check);
    } // method isUniqueTest2

    @Test
    public void isCorrectTest1()
    {
        userInput = "w";
        boolean check;

        check = randomWord.contains(userInput);
        boolean expected = true;

        assertEquals(expected, check);
    } // method isCorrectTest1

    @Test
    public void isCorrectTest2()
    {
        userInput = "s";
        boolean check;

        check = randomWord.contains(userInput);
        boolean expected = false;

        assertEquals(expected, check);
    } // method isCorrectTest2
}