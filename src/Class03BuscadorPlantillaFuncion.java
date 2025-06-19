import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Class03BuscadorPlantillaFuncion {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
                //CONEXION
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString = "jdbc:mysql://localhost:3366/hospital";
                Connection cn = DriverManager.getConnection(connectionString,"root", "root");

                //CONSULTAS
                System.out.println("Escriba función a consultar: ");
                String f = teclado.nextLine();
                //SIEMPRE COMILLAS SIMPLES cuando realizamos consulta SQL
                String sql = "select APELLIDO, FUNCION, SALARIO from PLANTILLA where FUNCION='" + f + " ' order by SALARIO";
                System.out.println(sql);
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    String func = rs.getString("FUNCION");
                    String apellido = rs.getString("APELLIDO");
                    String salario = rs.getString("SALARIO");
                    System.out.println(func + "-" + apellido + "-" +  salario);
                }
                // LIBERAR OBJETOS DE LA CONSULTA(rs) Y LA CONEXIÓN(cn) SIEMPRE.
                rs.close();
                cn.close();


        } catch (Exception e) {
            System.out.println("ERROR GRAVE: " + e) ;
        }

    }
}
