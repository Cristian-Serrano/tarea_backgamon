package org.iesvdm.backgammon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 20 tests para clase Board del juego de backgammon
 */
public class BoardTest {

    private Board boardTested;

    @BeforeEach
    public void beforeEach() {

        boardTested = new Board();

    }

    /**
     * Cuando se instancia (inicia el tablero) todas las piedras están en su posición inicial
     *
     * i = 0, count = 2, white
     * i = 11, count = 5, white
     * ... (ver código)
     * i = 23, count = 2, black
     * i = 12, count = 5, black
     * ... (ver código)
     */
    @Test
    public void whenBoardInitAllStoneInInitialPosition() {
        assertThat(boardTested.getStoneCount(0)).isEqualTo(2);
        assertThat(boardTested.getStone(0)).isEqualTo(Stone.WHITE);
        assertThat(boardTested.getStoneCount(5)).isEqualTo(3);
        assertThat(boardTested.getStone(5)).isEqualTo(Stone.BLACK);
        assertThat(boardTested.getStoneCount(7)).isEqualTo(5);
        assertThat(boardTested.getStone(7)).isEqualTo(Stone.BLACK);
        assertThat(boardTested.getStoneCount(11)).isEqualTo(5);
        assertThat(boardTested.getStone(11)).isEqualTo(Stone.WHITE);
        assertThat(boardTested.getStoneCount(12)).isEqualTo(5);
        assertThat(boardTested.getStone(12)).isEqualTo(Stone.BLACK);
        assertThat(boardTested.getStoneCount(16)).isEqualTo(5);
        assertThat(boardTested.getStone(16)).isEqualTo(Stone.WHITE);
        assertThat(boardTested.getStoneCount(18)).isEqualTo(3);
        assertThat(boardTested.getStone(18)).isEqualTo(Stone.WHITE);
        assertThat(boardTested.getStoneCount(23)).isEqualTo(2);
        assertThat(boardTested.getStone(23)).isEqualTo(Stone.BLACK);
    }

    @Test
    public void whenBoardInit_WhiteAndBlackBarEmpty() {
        assertThat(boardTested.getBarCount(Stone.Color.WHITE)).isEqualTo(0);
        assertThat(boardTested.getBarCount(Stone.Color.BLACK)).isEqualTo(0);
    }

    @Test
    public void whenPositionOutOfBoardCount0AndStoneNone() {
        assertThat(boardTested.getStoneCount(-1)).isEqualTo(0);
        assertThat(boardTested.getStone(-1)).isEqualTo(Stone.NONE);
        assertThat(boardTested.getStoneCount(25)).isEqualTo(0);
        assertThat(boardTested.getStone(25)).isEqualTo(Stone.NONE);

        //salta una excepcion que no deberia en 24
        assertThat(boardTested.getStoneCount(24)).isEqualTo(0);
        assertThat(boardTested.getStone(24)).isEqualTo(Stone.NONE);
    }

    /**
     * método canMove bajo testeo
     */
    @Test
    public void whenMoveFromOutOfBoardCanNotMove() {
        assertThat(boardTested.canMove(-1,1)).isEqualTo(false);
    }

    /**
     * método canMove bajo testeo
     */
    @Test
    public void whenMoveFromPositionWithoutStoneCanNotMove() {
        assertThat(boardTested.canMove(1,1)).isEqualTo(false);
    }

    /**
     * método move bajo testeo
     */
    @Test
    public void whenMoveCanNotMoveThrowsException() {

        WrongMoveException WrongMoveException = assertThrows(WrongMoveException.class, () -> {
            boardTested.move(0,5);
        });
        assertThat(WrongMoveException.getMessage()).isEqualTo("Wrong move!");
    }

    /**
     * Debe darse que en la posición negra debe haber sólo una piedra negra para mover blanca y comerla a la barra negra
     */
    @Test
    public void whenWhiteCanMoveToPositionBlack_MoveAndBlackBarIncrease() {
        try {
            boardTested.move(23,1);
            assertThat(boardTested.canMove(18,4)).isEqualTo(true);
            boardTested.move(18,4);
            assertThat(boardTested.getBarCount(Stone.Color.BLACK)).isEqualTo(1);
        }catch (WrongMoveException w){
            System.out.println(w);
        }

    }

    /**
     * Debe darse que en la posición negra debe haber sólo una piedra para mover blanca y comerla a la barra negra
     */
    @Test
    public void whenBlackCanMoveToPositionWhite_MoveAndWhiteBarIncrease() {
        try {
            boardTested.move(18,4);
            assertThat(boardTested.canMove(23,1)).isEqualTo(true);
            boardTested.move(23,1);
            assertThat(boardTested.getBarCount(Stone.Color.WHITE)).isEqualTo(1);
        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    /**
     * Debe darse que en la barra blanca haya piedra, y que se pueda mover al conteo indicado y se realice el
     * movimiento
     */
    @Test
    public void whenWhiteInWhiteBarAndCanPutIt_PutAndWhiteBarDecreae() {
        try {
            boardTested.move(18,4);
            boardTested.move(23,1);
            //ahora hay una piedra BLANCA en la barra
            boardTested.put(Stone.Color.WHITE,2);

            assertThat(boardTested.getStoneCount(1)).isEqualTo(1);
            assertThat(boardTested.getStone(1)).isEqualTo(Stone.WHITE);

            assertThat(boardTested.getBarCount(Stone.Color.WHITE)).isEqualTo(0);

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    /**
     * Debe darse que en la barra negra haya piedra, y que se pueda mover al conteo indicado y se realice el
     * movimiento
     */
    @Test
    public void whenBlackInBlackBarAndCanPutIt_PutAndBlackBarDecreae() {
        try {
            boardTested.move(23,1);
            boardTested.move(18,4);

            //ahora hay una piedra NEGRA en la barra
            boardTested.put(Stone.Color.BLACK,7);

            assertThat(boardTested.getStoneCount(17)).isEqualTo(1);
            assertThat(boardTested.getStone(17)).isEqualTo(Stone.BLACK);

            assertThat(boardTested.getBarCount(Stone.Color.BLACK)).isEqualTo(0);

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    /**
     * Debe darse que en la barra de blanca no haya piedra
     */
    @Test
    public void whenWhiteBarEmpty_PutWhiteThrowsException() {
        WrongMoveException WrongMoveException = assertThrows(WrongMoveException.class, () -> {
            boardTested.put(Stone.Color.WHITE,3);
        });
        assertThat(WrongMoveException.getMessage()).isEqualTo("Wrong move!");
    }

    /**
     * Debe darse que en la barra de negra no haya piedra
     */
    @Test
    public void whenBlackBarEmpty_PutBlackThrowsException() {
        WrongMoveException WrongMoveException = assertThrows(WrongMoveException.class, () -> {
            boardTested.put(Stone.Color.BLACK,2);
        });
        assertThat(WrongMoveException.getMessage()).isEqualTo("Wrong move!");
    }

    /**
     * Debe darse que en la barra de blanca haya piedra
     */
    @Test
    public void whenWhiteBar_PutWhiteInBlackPositionThrowsException() {
        try {
            boardTested.move(18,4);
            boardTested.move(23,1);
            //ahora hay una piedra BLANCA en la barra
            boardTested.put(Stone.Color.WHITE,2);

            //pongo la piedra en donde habia una piedra NEGRA
            WrongMoveException WrongMoveException = assertThrows(WrongMoveException.class, () -> {
                boardTested.put(Stone.Color.WHITE,23);
            });
            assertThat(WrongMoveException.getMessage()).isEqualTo("Wrong move!");

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    /**
     * Debe darse que en la barra de negra no haya piedra
     */
    @Test
    public void whenBlackBar_PutBlackInWhitePositionThrowsException() {
        try {
            boardTested.move(23,1);
            boardTested.move(18,4);

            //ahora hay una piedra NEGRA en la barra
            boardTested.put(Stone.Color.BLACK,7);

            //pongo la piedra en donde habia una piedra BLANCA
            WrongMoveException WrongMoveException = assertThrows(WrongMoveException.class, () -> {
                boardTested.put(Stone.Color.BLACK,1);
            });
            assertThat(WrongMoveException.getMessage()).isEqualTo("Wrong move!");

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    @Test
    public void whenRemoveStoneFromEmptyPositionThrowsException() {
        //el metodo es privado asi que no se puede llamar,
        //si se cambia a public se puede DESCOMENTAR el siguiente código:
        /*
        IllegalArgumentException IllegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            boardTested.removeStone(1);
        });
        assertThat(IllegalArgumentException.getMessage()).isEqualTo("Removing stone from zero at " + 1);
        */

        //al intentar que la funcion move llame a removeStone, nunca llega porque
        // salta la excepcion WrongMoveException del metodo move
    }

    @Test
    public void whenRemoveStoneFromPositionWith1Stone_StonesInPosition0AndStoneTypeNone() {
        try {
            boardTested.move(23,1);

            //ahora al mover desde 23, se ejecutará un removeStone de 23
            boardTested.move(23,2);

            assertThat(boardTested.getStoneCount(23)).isEqualTo(0);
            assertThat(boardTested.getStone(23)).isEqualTo(Stone.NONE);

        }catch (WrongMoveException w){
            System.out.println(w);
        }

    }

    @Test
    public void whenAddStoneToPositionOfDifferenteColorThrowsException() {
        //el metodo es privado asi que no se puede llamar,
        //si se cambia a public se puede DESCOMENTAR el siguiente código:
        /*
        IllegalArgumentException IllegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            boardTested.addStone(23, Stone.Color.WHITE);
        });
        assertThat(IllegalArgumentException.getMessage()).isEqualTo("Adding wrong color of stone to " + 23);
        */
    }


    @Test
    public void whenAddStoneToEmptyPosition_StonesInPosition1AndStoneTypeColorThatStone() {
        try {
            //el lugar 23 está previamente vacío

            boardTested.move(23,1);

            assertThat(boardTested.getStoneCount(22)).isEqualTo(1);
            assertThat(boardTested.getStone(22)).isEqualTo(Stone.BLACK);

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    @Test
    public void whenWhiteTargetMoveGreaterThan23_homeWhiteIncrease() {
        try {
            boardTested.move(18,6);
            //ahora hay 1 en home
            int piedrasBlancasEnHome = boardTested.getHome(Stone.Color.WHITE);

            boardTested.move(18,6);

            //compruebo que incrementa
            assertThat(boardTested.getHome(Stone.Color.WHITE)).isEqualTo(piedrasBlancasEnHome+1);

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }

    @Test
    public void whenBlackTargetMoveLessThan0_homeBlackIncrease() {
        try {
            boardTested.move(5,6);
            //ahora hay 1 en home
            int piedrasNegrasEnHome = boardTested.getHome(Stone.Color.BLACK);

            boardTested.move(5,6);

            //compruebo que incrementa
            assertThat(boardTested.getHome(Stone.Color.BLACK)).isEqualTo(piedrasNegrasEnHome+1);

        }catch (WrongMoveException w){
            System.out.println(w);
        }
    }
}
