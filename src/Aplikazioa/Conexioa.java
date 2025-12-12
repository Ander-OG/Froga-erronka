package Aplikazioa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexioa {
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://172.16.241.85:3306/db_1taldea?useSSL=false";
	private static final String USUARIO = "kudeatzailea";
	private static final String CLAVE = "1MG32025";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(URL,USUARIO,CLAVE);
			System.out.println("Conexion OK");
				
		}catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();			
		}
		
		return conexion;
	}
	
	
	public static void main(String[] args) {
		

	}
}