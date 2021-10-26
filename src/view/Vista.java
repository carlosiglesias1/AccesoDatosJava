package view;

import java.util.Scanner;

public class Vista {
    public int mostrarMenu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Selecciona una opción");
        System.out.println("Opción 1: ver la lista de empleados");
        System.out.println("Opción 2: subirle el sueldo a los empleados");
        int opcion = Integer.parseInt(teclado.nextLine());
        return opcion;
    }

    public boolean showMessage(String message){
        System.out.println(message);
        return true;
    }
}
