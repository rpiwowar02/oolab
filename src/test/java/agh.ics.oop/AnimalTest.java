/*package agh.ics.oop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;

class AnimalTest {
    Animal zwierz = new Animal();

    @BeforeAll
    public static void init(){

    }

    @org.junit.jupiter.api.Test
    void toStringTest() {
        Assertions.assertEquals("(2,2,Północ)",zwierz.toString());
    }

    @org.junit.jupiter.api.Test
    void isAt() {
        Vector2d pos1  = new Vector2d(2,2);
        Assertions.assertTrue(zwierz.isAt(pos1));
        Vector2d pos2  = new Vector2d(2,1);
        Assertions.assertFalse(zwierz.isAt(pos2));
        Vector2d pos3  = new Vector2d(1,2);
        Assertions.assertFalse(zwierz.isAt(pos3));
    }

    @org.junit.jupiter.api.Test
    void move() {
        String[] args = { "r", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        Vector2d[] poss = {new Vector2d(2,2),new Vector2d(3,2),new Vector2d(4,2),new Vector2d(4,2)};
        for (int i=0;i<4;i++){
            zwierz.Move(directions[i]);
            Assertions.assertTrue(zwierz.isAt(poss[i]));
        }
        //skoczek szachowy
        Animal skoczek = new Animal();
        String[][] args2 =  { {"f","f","l","f","r"}, {"b","r","f","f","l"}, {"b","b","r","f","l"},{"r","l","f","f","r"} };
        MoveDirection[][] directions2 = Arrays.stream(args2).map(OptionsParser::parse).toArray(MoveDirection[][]::new);
        Vector2d[] poss2 = {new Vector2d(4,1),new Vector2d(3,3),new Vector2d(4,1),new Vector2d(2,2)};
        for(int i=0;i<4;i++){
            for (String Jump: args2[i]) {
                for (MoveDirection move: directions2[i]) {
                    skoczek.Move(move);
                    }
                }
            }
        }
}*/