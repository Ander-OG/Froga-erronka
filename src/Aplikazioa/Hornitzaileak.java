package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hornitzaileak {

	
	//Kudeatzailea eta arduraduna
	public void hornitzaileaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira hornitzaileen aukerak:");
		System.out.println("1. Hornitzaileak bistaratu");
		System.out.println("2. Hornitzailea txertatu");
		System.out.println("3. Hornitzailearen informazioa aldatu");
		System.out.println("4. Hornitzailea ezabatu");
		System.out.print("Zure aukera: ");
		int HornitzaileaLangilea = sc.nextInt();
		switch (HornitzaileaLangilea) {
		case 1:
			hornitzaileakErakutsi();
			break;
		case 2:
			hornitzaileakSartu();
			break;
		case 3:
			hornitzaileakEditatu();
			break;
		case 4:
			hornitzaileakEzabatu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	public void hornitzaileakErakutsi () {
		Conexioa conexion = new Conexioa();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM hornitzaileak");
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String enpresa = rs.getString(2);
				String email = rs.getString(3);
				String telefonoa = rs.getString(4);

				
				
				System.out.println(id+" - "+enpresa+" - "+email+" - "+telefonoa);
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
	public void hornitzaileakSartu () {

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Sartu enpresa: ");
        String enpresa = sc.next();
        
        System.out.print("Sartu email: ");
        String email = sc.next();
        
        System.out.print("Sartu telefonoa: ");
        String telefonoa = sc.next();

        Conexioa conexion = new Conexioa();
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = conexion.conectar();

            String sql = "INSERT INTO hornitzaileak (enpresa, email, telefonoa) VALUES (?, ?, ?)";

            ps = cn.prepareStatement(sql);
           
            ps.setString(1, enpresa);
            ps.setString(2, email);
            ps.setString(3, telefonoa);
           

            // Ejecutar el INSERT
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Hornitzailea ondo gehitu da datu-basean.");
            } else {
                System.out.println("Ez da hornitzailea gehitu.");
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
	public void hornitzaileakEditatu () {

        Conexioa conexion = new Conexioa();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);

        try {
            cn = conexion.conectar();


            System.out.println("\nHornitzaileen zerrenda:");
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM hornitzaileak");

            while (rs.next()) {
                int id = rs.getInt("id");
                String enpresa = rs.getString("enpresa");
                String email = rs.getString("email");
                String telefonoa = rs.getString("telefonoa");
                System.out.println(id + " - " + enpresa + " - " + email + " - " + telefonoa);
            }

            System.out.print("\nSartu aldatu nahi duzun hornitzailearen ID-a: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Zer aldatu nahi duzu?");
            System.out.println("1 - Enpresaren izena");
            System.out.println("3 - Email");
            System.out.println("4 - Telefonoa");
            System.out.print("Aukera: ");
            int aukera = sc.nextInt();
            sc.nextLine();

            String campo = "";
            switch (aukera) {
                case 1: campo = "enpresa"; break;
                case 2: campo = "email"; break;
                case 3: campo = "telefonoa"; break;
                default:
                    System.out.println("Aukera ez da zuzena.");
                    return;
            }

            System.out.print("Sartu balio berria: ");
            String balioBerria = sc.nextLine();

            String sql = "UPDATE hornitzaileak SET " + campo + " = ? WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, balioBerria);
            ps.setInt(2, id);

            int lerroKop = ps.executeUpdate();

            if (lerroKop > 0) {
                System.out.println("Hornitzailea ONDO eguneratu da.");
            } else {
                System.out.println("Ez da aurkitu ID hori duen hornitzailea.");
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
	public void hornitzaileakEzabatu () {
		 
	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        Statement st = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        Scanner sc = new Scanner(System.in);
	 
	        try {
	            cn = conexion.conectar();
	 
	            
	            System.out.println("\n Hornitzaileen zerrenda:");
	            st = cn.createStatement();
	            rs = st.executeQuery("SELECT * FROM hornitzaileak");
	 
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String enpresa = rs.getString("enpresa");
	                String email = rs.getString("email");
	                String telefonoa = rs.getString("telefonoa");
	                
	                
	                System.out.println(id + " - " + enpresa + " - " + email + " - " + telefonoa);
	            }
	 
	            
	            System.out.print("\nSartu ezabatu nahi duzun hornitzailearen ID-a: ");
	            int id = sc.nextInt();
	 
	            
	            String sql = "DELETE FROM hornitzaileak WHERE id = ?";
	            ps = cn.prepareStatement(sql);
	            ps.setInt(1, id);
	 
	            int lerroKop = ps.executeUpdate();
	 
	            if (lerroKop > 0) {
	                System.out.println("Hornitzailea ONDO ezabatu da.");
	            } else {
	                System.out.println("Ez da aurkitu ID hori duen hornitzailea.");
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
