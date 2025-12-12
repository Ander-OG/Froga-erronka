package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Aplikazioa {

	public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	   
    	Scanner sc = new Scanner(System.in);
        System.out.println("MAHAIGAINEKO APLIKAZIOA");
        
        String rol = login();

        if (rol != null && !rol.isEmpty()) {
            System.out.println("\nSaioa ondo hasi da! Zure rola: " + rol);

            switch (rol.toLowerCase()) {
                case "kudeatzailea":
    	            menuKudeatzailea.kudeatzaileaMenua();
                    break;
                case "enkargatua":
                	menuArduraduna.arduradunaMenua();
                    break;
                case "langilea":
                    menuLangilea.langileaMenua();
                    break;
                default:
                    System.out.println("Errorea: rol ezezaguna (" + rol + ").");
                    break;
            }
        } else {
            System.out.println("NAN edo pasahitza okerra. Aplikazioa ixten...");
        }


    }

	public static String login() {
        System.out.print("\nSartu zure NAN-a: ");
        String nan = sc.nextLine();

        System.out.print("Sartu zure pasahitza: ");
        String pasahitza = sc.nextLine();

        Conexioa conexion = new Conexioa();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String rol = null;

        try {
            cn = conexion.conectar();
            String sql = "SELECT rol FROM langileak WHERE nan = ? AND pasahitza = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nan);
            ps.setString(2, pasahitza);
            rs = ps.executeQuery();

            if (rs.next()) {
                rol = rs.getString("rol");
            }

        } catch (SQLException e) {
            System.out.println("Errorea login egitean: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rol;
    }
}