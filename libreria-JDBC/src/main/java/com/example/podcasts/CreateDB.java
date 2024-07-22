package com.example.podcasts;

import java.sql.*;
import java.util.Scanner;



public class CreateDB {
    public static void main(String[] args) {
    try {
        //aqui haremos la conexión y las consultas
        // 1-decirle el driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2- conectar con DB específica. Crear objeto de Conne
        Connection conexion1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria","root","");
        System.out.println("***CORRECTOOO ***");


        // 3- crear objeto Statement, que es el que se encargará de hacer consultas
        Statement stat1 = conexion1.createStatement();

        // 4 - Ejecutar instrucciones stat1 y mediante métodos executeQuery() y executeUpdate()
        // primero lo leemos todo y lo guardamos en onjeto ResultSet
        ResultSet resultado1 = stat1.executeQuery("SELECT * FROM libros");


        // 5- recorrer el resultado mediante bucle
        System.out.println("--------------\nLISTADO LIBROS\n--------------");
        while(resultado1.next()){
            System.out.println("- Id: " + resultado1.getString("id") + " | " + resultado1.getString("titulo") + " (" + resultado1.getString("autor") + ") | " + resultado1.getString("year"));
        }
        String titulo;
        String autor;
        int year;
        int idLibro=0;

        Scanner teclado = new Scanner(System.in);
        
        boolean active = true;
        while (active) {
            System.out.println("--------------\n");
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Añadir un libro\n2. Modificar un libro\n3. Eliminar un libro\n4. Ver listado\n5. SALIR");
            int menuOption=teclado.nextInt();
            teclado.nextLine();
            System.out.println("----------------\n\n");
            switch (menuOption) {
                case 1:
                    // 6 - INSERTAR datos en la tabla con un método executeUpdate()
                        System.out.println("Indica el título");
                        titulo = teclado.nextLine();
                        System.out.println("\nIndica el autor:");
                        autor = teclado.nextLine();
                        System.out.println("\nIndica el año:");
                        year = teclado.nextInt();
                        teclado.nextLine();
                        String insertar = "INSERT INTO libros (titulo, autor, year) values (?, ?, ?)";
                        PreparedStatement prepInsert = conexion1.prepareStatement(insertar);
                        prepInsert.setString(1, titulo);
                        prepInsert.setString(2, autor);
                        prepInsert.setInt(3, year);
                        prepInsert.execute();
                        System.out.println("Libro insertado !!");
                        System.out.println("----------------\n\n");
                        

                    
                    break;
                case 2:
                    // 7 - Actualizar datos UPADTE
                        System.out.println("Introduce el ID del libro:");
                        idLibro = verificarID(idLibro, teclado);
                        System.out.println("\n¿Qué datos quieres modificar?");
                        System.out.println("1. Título\n2. Autor\n3. Año lanzamiento\n4. Cancelar");
                        int valorModif=teclado.nextInt();
                        teclado.nextLine();
                        switch (valorModif) {
                            case 1:
                                System.out.println("\nIntroduce el nuevo título:");
                                titulo = teclado.nextLine();
                                String actualizarNombre = "UPDATE libros SET titulo=? where id=?";
                                PreparedStatement prepUT = conexion1.prepareStatement(actualizarNombre);
                                prepUT.setString(1, titulo);
                                prepUT.setInt(2, idLibro);
                                prepUT.executeUpdate();
                                System.out.println("datos actualizados");
                                System.out.println("----------------\n\n");                                 
                                break;
                            case 2:
                                System.out.println("\nIntroduce el autor:");
                                autor = teclado.nextLine();
                                //String actualizarDesc = "UPDATE Podcast set descripcion='"+descPodcast+"' where idPodcast='"+idPodcast+"'";
                                String actualizarDesc = "UPDATE libros SET autor=? where id=?";
                                PreparedStatement prepUDesc = conexion1.prepareStatement(actualizarDesc);
                                prepUDesc.setString(1, autor);
                                prepUDesc.setInt(2, idLibro);
                                prepUDesc.executeUpdate();
                                System.out.println("datos actualizados");
                                System.out.println("----------------\n\n");                                
                                break;
                            case 3:
                                System.out.println("\nIntroduce el año de publicación del libro:");
                                year = teclado.nextInt();
                                teclado.nextLine();
                                //String actualizarDur = "UPDATE Podcast set duracion='"+duracionPodcast+"' where idPodcast='"+idPodcast+"'";
                                String actualizarDur = "UPDATE libros SET year=? where id=?";
                                PreparedStatement prepUTime = conexion1.prepareStatement(actualizarDur);
                                prepUTime.setInt(1, year);
                                prepUTime.setInt(2, idLibro);
                                prepUTime.executeUpdate();
                                System.out.println("datos actualizados");
                                System.out.println("----------------\n\n");
                                break;
                            case 4:
                                
                                break;
                        
                            default:
                                break;
                        }
                        
                    break;
                case 3:
                    // 8 - Borrar registros DELELTE
                    System.out.println("¿Que id quieres eliminar?");
                        idLibro = teclado.nextInt();
                        teclado.nextLine();
                        //String eliminar = "DELETE from Podcast where idPodcast='"+idPodcast+"'";
                        String eliminar = "DELETE from libros where id=?";
                        PreparedStatement prepDelete = conexion1.prepareStatement(eliminar);
                        prepDelete.setInt(1, idLibro);
                        prepDelete.executeUpdate();
                        System.out.println("Elemento eliminado");
                        System.out.println("----------------\n\n");
                    break;
                
                case 4:
                        System.out.println("--------------\nLISTADO LIBROS\n--------------");
                        verTodo(stat1);
                        System.out.println("--------------\n");
                    break;
                case 5:
                    System.out.println("Hasta pronto!");
                    active=false;
                    break;

                default:
                System.out.println("Error... vuelve a probar");
                    break;
            }
        }

        

        teclado.close();
        conexion1.close();
    }catch(Exception e){
        //mensaje de error
        System.out.println("***oohhhh ***");
    }



}


public static void verTodo(Statement stat1) throws SQLException {
    ResultSet resultado1 = stat1.executeQuery("SELECT * FROM libros");
    while(resultado1.next()) {
        System.out.println("- Id: " + resultado1.getString("id") + " | " + resultado1.getString("titulo") + " (" + resultado1.getString("autor") + ") | " + resultado1.getString("year"));
        
    }
}

public static int verificarID(int idLibro, Scanner teclado){
    try {
        idLibro = teclado.nextInt();
        teclado.nextLine();
    }
    catch (Exception e){
        System.out.println("Error... Escribe un numero");
        teclado.nextLine();
        verificarID( idLibro, teclado);
    }
    return idLibro;
}

}