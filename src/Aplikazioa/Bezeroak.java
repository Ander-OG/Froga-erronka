package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bezeroak {
	
	
	//Kudeatzailea, langilea eta arduraduna
	public void bezeroaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira bezeroen aukerak:");
		System.out.println("1. Bezeroak bistaratu");
		System.out.println("2. Bezeroa txertatu");
		System.out.println("3. Bezeroaren informazioa aldatu");
		System.out.println("4. Bezeroa ezabatu");
		System.out.print("Zure aukera: ");
		int BezeroaLangilea = sc.nextInt();

		switch (BezeroaLangilea) {
		case 1:
			bezeroakErakutsi();
			break;
		case 2:
			bezeroakSartu();
			break;
		case 3:
			bezeroakEditatu();
			break;
		case 4:
			bezeroakEzabatu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	public void bezeroakErakutsi () {
		Conexioa conexion = new Conexioa();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM bezeroak");
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String izena = rs.getString(2);
				String abizena = rs.getString(3);
				String email = rs.getString(4);
				String telefonoa = rs.getString(5);

				
				
				System.out.println(id+" - "+izena+" - "+abizena+" - "+email+" - "+telefonoa);
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
	public void bezeroakSartu () {

	        
	        Scanner sc = new Scanner(System.in);
	        
	        System.out.print("Sartu izena: ");
	        String izena = sc.next();;

	        System.out.print("Sartu abizena: ");
	        String abizena = sc.next();
	        
	        System.out.print("Sartu email: ");
	        String email = sc.next();
	        
	        System.out.print("Sartu telefonoa: ");
	        String telefonoa = sc.next();

	        
	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        PreparedStatement ps = null;

	        try {
	            cn = conexion.conectar();

	            
	            String sql = "INSERT INTO bezeroak (izena, abizena, email, telefonoa) VALUES (?, ?, ?, ?)";

	            ps = cn.prepareStatement(sql);
	           
	            ps.setString(1, izena);
	            ps.setString(2, abizena);
	            ps.setString(3, email);
	            ps.setString(4, telefonoa);
	           

	            
	            int filasAfectadas = ps.executeUpdate();

	            if (filasAfectadas > 0) {
	                System.out.println("Bezeroa ondo gehitu da datu-basean.");
	            } else {
	                System.out.println("Ez da bezeroa gehitu.");
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
    public void bezeroakEditatu () {

        Conexioa conexion = new Conexioa();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);

        try {
            cn = conexion.conectar();


            System.out.println("\nBezeroen zerrenda:");
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM bezeroak");

            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                String abizena = rs.getString("abizena");
                String email = rs.getString("email");
                String telefonoa = rs.getString("telefonoa");
                System.out.println(id + " - " + izena + " - " + abizena + " - " + email + " - " + telefonoa);
            }

            System.out.print("\nSartu aldatu nahi duzun bezeroaren ID-a: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Zer aldatu nahi duzu?");
            System.out.println("1 - Izena");
            System.out.println("2 - Abizena");
            System.out.println("3 - Email");
            System.out.println("4 - Telefonoa");
            System.out.print("Aukera: ");
            int aukera = sc.nextInt();
            sc.nextLine();

            String campo = "";
            switch (aukera) {
                case 1: campo = "izena"; break;
                case 2: campo = "abizena"; break;
                case 3: campo = "email"; break;
                case 4: campo = "telefonoa"; break;
                default:
                    System.out.println("Aukera ez da zuzena.");
                    return;
            }

            System.out.print("Sartu balio berria: ");
            String balioBerria = sc.nextLine();

            String sql = "UPDATE bezeroak SET " + campo + " = ? WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, balioBerria);
            ps.setInt(2, id);

            int lerroKop = ps.executeUpdate();

            if (lerroKop > 0) {
                System.out.println("Bezeroa ONDO eguneratu da.");
            } else {
                System.out.println("Ez da aurkitu ID hori duen bezeroa.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea eguneratzean: " + e.getMessage());
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
	public void bezeroakEzabatu () {
		 
        Conexioa conexion = new Conexioa();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);
 
        try {
            cn = conexion.conectar();
 
            
            System.out.println("\n Bezeroen zerrenda:");
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM bezeroak");
 
            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                String abizena = rs.getString("abizena");
                String email = rs.getString("email");
                String telefonoa = rs.getString("telefonoa");
                
                
                System.out.println(id + " - " + izena + " - " + abizena + " - " + email + " - " + telefonoa);
            }
 
            
            System.out.print("\nSartu ezabatu nahi duzun bezeroaren ID-a: ");
            int id = sc.nextInt();
 
            
            String sql = "DELETE FROM bezeroak WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
 
            int lerroKop = ps.executeUpdate();
 
            if (lerroKop > 0) {
                System.out.println("Bezeroa ONDO ezabatu da.");
            } else {
                System.out.println("Ez da aurkitu ID hori duen bezeroa.");
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
