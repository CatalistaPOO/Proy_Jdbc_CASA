import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Class02BuscadorEmpleadosDept {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in); 
        System.out.println("Introduzca numero de departamento");
        String idDepartamento = teclado.nextLine();
        
        //Usando las clases de SQL usaremos try/catch
        try {
            //Pasos para conectar a la BBDD
            Class.forName("com.mysql.cj.jdbc.Driver");//buscamos driver (abrir workbench) usando el driver con la clase Class del jbdc
            String connectionString = "jdbc:mysql://localhost:3366/hospital";//conectamos a la BBDD(tipo jdbc, lugar y puerto, nombre BBDD) almacenado en variable.
            Connection cn = DriverManager.getConnection(connectionString, "root", "root");//crea el objeto conexión (con usuario y password)

            //Una vez conectados a la BBDD, relalizamos las consultas
            //la consulta no debe contener un  espacio de la ultima palabra para evitar errores cuando concatenas datos
            String sql = "select APELLIDO, OFICIO from EMP where DEPT_NO=" + idDepartamento;
            System.out.println(sql);
            Statement st = cn.createStatement();//ojo la clase Statement de sql no java (ver imports)
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                String apellido = rs.getString("APELLIDO");
                String oficio = rs.getString("OFICIO");
                System.out.println(apellido + " - " + oficio);
            }
            // Liberar la consulta (rs) y la conexión (cn) SIEMPRE.
            rs.close();
            cn.close();

        } catch (Exception e) {//recogemos cualquier excepción que se produzca usando la clase Connection
            System.out.println("ERROR grave: " + e);
        }
    }
}