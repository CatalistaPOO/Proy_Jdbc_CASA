import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Class01ReadEmpleados {
    public static void main(String[] args) {

        try {
            //1· registramos la clase del Driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2· dependiendo de la base de datos necesitamos una cadena de conexion
            String connectionString = "jdbc:mysql://localhost:3366/hospital";//jdbc que usamos,servidor,ip,nombrebasedatos

            //3· Creamos la conexion mediante drivermanager
            Connection cn = DriverManager.getConnection(connectionString, "root", "root");//usuario y password de MySQL

            //4· Consulta sobre la Base de datos, seleccionamos la tabla sobre la que trabajamos.
            String sql = "Select * from EMP";

            //5· Creamos el tipo de statement dependiendo de la consulta (ej:tiene parametros?)
            Statement st = cn.createStatement();//Ojo!! Statement de sql, no de java: este objeto hace la interaccion con el SQL

            //6· Como es consulta select necesitamos un resultSet() y el metodo executeQuery()
            ResultSet rs = st.executeQuery(sql);

            //7· Recorremos los registros mediante un bucle while 
            //  apellidos: Mientras haya una fila más (rs.next salta a la siguiente fila), en el atributo apellido, muestralo en consola
            // equivale a hacer en MySQL: select * from apellidos
            while(rs.next()){
                String apellido = rs.getString("APELLIDO");
                System.out.println("Apellido: " + apellido);
            }
            // Liberamos los recursos SIEMPRE,por norma.
            rs.close();
            cn.close();

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException ex){
            System.out.println("Sql: " + ex);
        }
        
    }

}
