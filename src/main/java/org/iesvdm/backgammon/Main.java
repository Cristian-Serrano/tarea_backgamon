package org.iesvdm.backgammon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        //Blancas y Negras lanzan para decidir quién empieza
        game.roll();

        int contadorJugada = 0;
        while (!game.isEnded()) {
            contadorJugada++;
            game.roll();

            System.out.println("Jugador");
            System.out.println(game.getPlayer());

            System.out.println("Dado 1: " + game.getDice().getDiceOne());
            System.out.println("Dado 2: " + game.getDice().getDiceTwo());


            boolean seguir = true;
            while(seguir) {

                BoardDrawerCL.draw(game.getBoard());
                System.out.println("Menú Jugada " + contadorJugada);
                System.out.println("1. Mover normal.");
                System.out.println("2. Mover de la barra.");
                System.out.println("3. Pasar turno s/n");
                System.out.println("======================");
                System.out.println("Introduzca opción [1|2|3]:");

                int opcion = scanner.nextInt();
                int cuenta = 0;
                switch (opcion) {
                   case 1:

                       System.out.println("Mover desde:");
                       int desde = scanner.nextInt();

                       System.out.println("Cuenta de movimiento (uno de los dados o suma)");
                       cuenta = scanner.nextInt();

                       if (game.canMove(desde-1, cuenta)) {
                           try {
                               game.move(desde-1, cuenta);
                           } catch (WrongMoveException e) {
                               e.printStackTrace();
                           }
                       } else {
                           System.out.println("Movimiento no permitido!");
                       }

                       break;
                   case 2:

                       System.out.println("Mover desde la barra cuenta de movimiento:");
                       cuenta = scanner.nextInt();

                       if (game.canPut(cuenta)) {
                           try {
                               game.put(cuenta);
                           } catch (WrongMoveException e) {
                               e.printStackTrace();
                           }
                       }  else {
                           System.out.println("Movimiento no permitido!");
                       }

                       break;
                    case 3:

                        seguir = false;
                        break;

                    default:
                        System.out.println("Opción no válida!");
                }

                System.out.println("Dado 1:" + game.getDice().getDiceOne());
                System.out.println("Dado 2:" + game.getDice().getDiceTwo());

                seguir = seguir && game.getDice().isRolled();

            }
        }

    }
}