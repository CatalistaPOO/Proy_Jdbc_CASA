import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Class03BuscadorPlantillaFuncion {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try {
                //CONEXION:
                //llamada al driver, almacenar la ruta de la BBDD
                // para utilizar la clase Connection que tendrá el DriverManager utilizando la ruta, el usuario y la contraseña
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString = "jdbc:mysql://localhost:3366/hospital";//Ojo puerto
                Connection cn = DriverManager.getConnection(connectionString,"root", "root");

                //CONSULTAS: 
                //peticion al usuario para realizar consulta, ver consulta y almacenarla,
                //Statement para interactuar con SQL y ResultSet para recoger el resultado de la consulta en 
                //el bucle while que recorre las filas de la tabla
                System.out.println("Escriba función a consultar: ");
                String f = teclado.nextLine();
                teclado.close();
                //SIEMPRE COMILLAS SIMPLES cuando realizamos consulta SQL
                String sql = "select FUNCION, APELLIDO, SALARIO from PLANTILLA where FUNCION='" + f + " ' order by SALARIO";
                System.out.println(sql);
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    String func = rs.getString("FUNCION");
                    String apellido = rs.getString("APELLIDO");
                    String salario = rs.getString("SALARIO");
                    System.out.println(func + "-" + apellido + "-" +  salario);
                }
                // LIBERAR OBJETO Statement DE LA CONSULTA(rs) Y LA CONEXIÓN(cn) SIEMPRE.
                rs.close();
                cn.close();

        } catch (Exception e) {
            System.out.println("ERROR GRAVE: " + e);
        }

    }
}
