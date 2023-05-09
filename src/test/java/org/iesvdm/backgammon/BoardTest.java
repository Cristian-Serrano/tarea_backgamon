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

    }

    @Test
    public void whenBoardInit_WhiteAndBlackBarEmpty() {

    }

    @Test
    public void whenPositionOutOfBoardCount0AndStoneNone() {

    }

    /**
     * método canMove bajo testeo
     */
    @Test
    public void whenMoveFromOutOfBoardCanNotMove() {

    }

    /**
     * método canMove bajo testeo
     */
    @Test
    public void whenMoveFromPositionWithoutStoneCanNotMove() {

    }

    /**
     * método move bajo testeo
     */
    @Test
    public void whenMoveCanNotMoveThrowsException() {

    }

    /**
     * Debe darse que en la posición negra debe haber sólo una piedra negra para mover blanca y comerla a la barra negra
     */
    @Test
    public void whenWhiteCanMoveToPositionBlack_MoveAndBlackBarIncrease() {

    }

    /**
     * Debe darse que en la posición negra debe haber sólo una piedra para mover blanca y comerla a la barra negra
     */
    @Test
    public void whenBlackCanMoveToPositionWhite_MoveAndWhiteBarIncrease() {

    }

    /**
     * Debe darse que en la barra blanca haya piedra, y que se pueda mover al conteo indicado y se realice el
     * movimiento
     */
    @Test
    public void whenWhiteInWhiteBarAndCanPutIt_PutAndWhiteBarDecreae() {

    }

    /**
     * Debe darse que en la barra negra haya piedra, y que se pueda mover al conteo indicado y se realice el
     * movimiento
     */
    @Test
    public void whenBlackInBlackBarAndCanPutIt_PutAndBlackBarDecreae() {

    }

    /**
     * Debe darse que en la barra de blanca no haya piedra
     */
    @Test
    public void whenWhiteBarEmpty_PutWhiteThrowsException() {

    }

    /**
     * Debe darse que en la barra de negra no haya piedra
     */
    @Test
    public void whenBlackBarEmpty_PutBlackThrowsException() {

    }

    /**
     * Debe darse que en la barra de blanca haya piedra
     */
    @Test
    public void whenWhiteBar_PutWhiteInBlackPositionThrowsException() {

    }

    /**
     * Debe darse que en la barra de negra no haya piedra
     */
    @Test
    public void whenBlackBar_PutBlackInWhitePositionThrowsException() {

    }

    @Test
    public void whenRemoveStoneFromEmptyPositionThrowsException() {

    }

    @Test
    public void whenRemoveStoneFromPositionWith1Stone_StonesInPosition0AndStoneTypeNone() {


    }

    @Test
    public void whenAddStoneToPositionOfDifferenteColorThrowsException() {

    }

    @Test
    public void whenAddStoneToEmptyPosition_StonesInPosition1AndStoneTypeColorThatStone() {

    }

    @Test
    public void whenWhiteTargetMoveGreaterThan23_homeWhiteIncrease() {

    }

    @Test
    public void whenBlackTargetMoveLessThan0_homeBlackIncrease() {

    }


}
