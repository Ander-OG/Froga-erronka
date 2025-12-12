package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Soporteak {
	

	//Kudeatzailea, langilea eta arduraduna
	public void soporteaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira soporteen aukerak:");
		System.out.println("1. Soporteak bistaratu");
		System.out.println("2. Soportea txertatu");
		System.out.println("3. Soportea ezabatu");
		System.out.print("Zure aukera: ");
		int SoporteaLangilea = sc.nextInt();
		switch (SoporteaLangilea) {
		case 1:
			soporteakErakutsi();
			break;
		case 2:
			soporteakSartu();
			break;
		case 3:
			soporteakEzabatu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	public void soporteakSartu () {


    	Scanner sc = new Scanner(System.in);

        System.out.print("Sartu arazoa: ");
        String arazoa = sc.next();

        System.out.print("Sartu email: ");
        String email = sc.next();
        
        System.out.print("Sartu izena: ");
        String izena = sc.next();
        
        System.out.print("Sartu abizena: ");
        String abizena = sc.next();
        

        Conexioa conexion = new Conexioa();
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = conexion.conectar();


            String sql = "INSERT INTO soporteak (arazoa, email, izena, abizena) VALUES (?, ?, ?, ?)";

            ps = cn.prepareStatement(sql);
           
            ps.setString(1, arazoa);
            ps.setString(2, email);
            ps.setString(3, izena);
            ps.setString(4, abizena);


            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Arazoa ondo gehitu da datu-basean.");
            } else {
                System.out.println("Ez da arazoa gehitu.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea datuak sartzean: " + e.getMessage());
        } finally {

        	try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	public void soporteakErakutsi () {
		Conexioa conexion = new Conexioa();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM soporteak");
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String arazoa = rs.getString(2);
				String email = rs.getString(3);
				String izena = rs.getString(4);
				String abizena = rs.getString(5);

				
				
				System.out.println(id+" - "+arazoa+" - "+email+" - "+izena+" - "+abizena);
			}
			
		} catch (SQLException e) {
		
		} finally {
			try {
				if (rs!= null) {
					rs.close();
				}
				
				if (stm!=null) {
					stm.close();
				}
				
				if (cn!=null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void soporteakEzabatu () {
		 
        Conexioa conexion = new Conexioa();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);
 
        try {
            cn = conexion.conectar();
 
            
            System.out.println("\n Soporteen zerrenda:");
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM soporteak");
 
            while (rs.next()) {
                int id = rs.getInt("id");
                String arazoa = rs.getString("arazoa");
                String email = rs.getString("email");
                String izena = rs.getString("izena");
                String abizena = rs.getString("abizena");
                
                
                System.out.println(id + " - " + arazoa + " - " + email + " - " + izena + " - " + abizena);
            }
 
            
            System.out.print("\nSartu ezabatu nahi duzun soportearen ID-a: ");
            int id = sc.nextInt();
 
            
            String sql = "DELETE FROM soporteak WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
 
            int lerroKop = ps.executeUpdate();
 
            if (lerroKop > 0) {
                System.out.println("Soportea ONDO ezabatu da.");
            } else {
                System.out.println("Ez da aurkitu ID hori duen soportea.");
            }
 
        } catch (SQLException e) {
            System.out.println("Errorea ezabatzean: " + e.getMessage());
        } finally {
            
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
}
