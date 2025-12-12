package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Langileak {

	//Arduraduna
	public void langileaKudeatu2(Scanner sc) {
		System.out.println("\nHauek dira langileen aukerak:");
		System.out.println("1. Langileak bistaratu");
		System.out.println("2. Langilearen informazioa aldatu");
		System.out.print("Zure aukera: ");
		int LangileaLangilea2 = sc.nextInt();
		switch (LangileaLangilea2) {
		case 1:
			langileakErakutsi();
			break;
		case 2:
			langileakEditatu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	
	//Kudeatzailea
	public void langileaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira langileen aukerak:");
		System.out.println("1. Langileak bistaratu");
		System.out.println("2. Langilea txertatu");
		System.out.println("3. Langilearen informazioa aldatu");
		System.out.println("4. Langilea ezabatu");
		System.out.print("Zure aukera: ");
		int LangileaLangilea = sc.nextInt();
		switch (LangileaLangilea) {
		case 1:
			langileakErakutsi();
			break;
		case 2:
			langileakSartu();
			break;
		case 3:
			langileakEditatu();
			break;
		case 4:
			langileakEzabatu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	 public void langileakEzabatu () {
		 
	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        Statement st = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        Scanner sc = new Scanner(System.in);
	 
	        try {
	            cn = conexion.conectar();
	 
	            
	            System.out.println("\n Langileen zerrenda:");
	            st = cn.createStatement();
	            rs = st.executeQuery("SELECT * FROM langileak");
	 
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String rol = rs.getString("rol");
	                String izena = rs.getString("izena");
	                String abizena = rs.getString("abizena");
	                String NAN = rs.getString("NAN");
	                String email = rs.getString("email");

	                
	                
	                System.out.println(id + " - " + rol + " - " + izena + " - " + abizena + " - " + NAN + " - " + email);
	            }
	 
	            
	            System.out.print("\nSartu ezabatu nahi duzun langilearen ID-a: ");
	            int id = sc.nextInt();
	 
	            
	            String sql = "DELETE FROM langileak WHERE id = ?";
	            ps = cn.prepareStatement(sql);
	            ps.setInt(1, id);
	 
	            int lerroKop = ps.executeUpdate();
	 
	            if (lerroKop > 0) {
	                System.out.println("Langilea ONDO ezabatu da.");
	            } else {
	                System.out.println("Ez da aurkitu ID hori duen langilea.");
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
	 public void langileakSartu () {


	        Scanner sc = new Scanner(System.in);

	        System.out.print("Sartu rola: ");
	        String rol = sc.next();

	        System.out.print("Sartu izena: ");
	        String izena = sc.next();
	        
	        System.out.print("Sartu abizena: ");
	        String abizena = sc.next();
	        
	        System.out.print("Sartu NAN-a: ");
	        String nan = sc.next();
	        
	        System.out.print("Sartu email-a: ");
	        String email = sc.next();
	        
	        System.out.print("Sartu pasahitza: ");
	        String pasahitza = sc.next();

	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        PreparedStatement ps = null;

	        try {
	            cn = conexion.conectar();

	            String sql = "INSERT INTO langileak (rol, izena, abizena, nan, email, pasahitza ) VALUES (?, ?, ?, ?, ?, ?)";

	            ps = cn.prepareStatement(sql);
	           
	            ps.setString(1, rol);
	            ps.setString(2, izena);
	            ps.setString(3, abizena);
	            ps.setString(4, nan);
	            ps.setString(5, email);
	            ps.setString(6, pasahitza);
	           
	            
	            int filasAfectadas = ps.executeUpdate();

	            if (filasAfectadas > 0) {
	                System.out.println("Langilea ondo gehitu da datu-basean.");
	            } else {
	                System.out.println("Ez da langilea gehitu.");
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
	 public void langileakErakutsi () {
			Conexioa conexion = new Conexioa();
			Connection cn = null;
			Statement stm = null;
			ResultSet rs = null;
			
			try {
				cn = conexion.conectar();
				stm = cn.createStatement();
				rs = stm.executeQuery("SELECT * FROM langileak");
				
				while (rs.next()) {
					int id = rs.getInt(1);
					String rol = rs.getString(2);
					String izena = rs.getString(3);
					String abizena = rs.getString(4);
					String nan = rs.getString(5);
					String email = rs.getString(6);
					String pasahitza = rs.getString(7);

					
					
					System.out.println(id+" - "+rol+" - "+izena+" - "+abizena+" - "+nan+" - "+email+" - "+pasahitza);
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
	 public void langileakEditatu () {

	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        Statement st = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        Scanner sc = new Scanner(System.in);

	        try {
	            cn = conexion.conectar();


	            System.out.println("\nLangileen zerrenda:");
	            st = cn.createStatement();
	            rs = st.executeQuery("SELECT * FROM langileak");

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String rol = rs.getString("rol");
	                String izena = rs.getString("izena");
	                String abizena = rs.getString("abizena");
	                String NAN = rs.getString("NAN");
	                String email = rs.getString("email");
	                
	                System.out.println(id + " - " + rol + " - " + izena + " - " + abizena + " - " + NAN + " - " + email);
	            }

	            System.out.print("\nSartu aldatu nahi duzun langilearen ID-a: ");
	            int id = sc.nextInt();
	            sc.nextLine();

	            System.out.println("Zer aldatu nahi duzu?");
	            System.out.println("1 - Rol-a");
	            System.out.println("2 - Izena");
	            System.out.println("3 - Abizena");
	            System.out.println("4 - NAN");
	            System.out.println("5 - Email");
	            System.out.print("Aukera: ");
	            int aukera = sc.nextInt();
	            sc.nextLine();

	            String campo = "";
	            switch (aukera) {
	                case 1: campo = "rol"; break;
	                case 2: campo = "izena"; break;
	                case 3: campo = "abizena"; break;
	                case 4: campo = "NAN"; break;
	                case 5: campo = "email"; break;
	                default:
	                    System.out.println("Aukera ez da zuzena.");
	                    return;
	            }

	            System.out.print("Sartu balio berria: ");
	            String balioBerria = sc.nextLine();

	            String sql = "UPDATE langileak SET " + campo + " = ? WHERE id = ?";
	            ps = cn.prepareStatement(sql);
	            ps.setString(1, balioBerria);
	            ps.setInt(2, id);

	            int lerroKop = ps.executeUpdate();

	            if (lerroKop > 0) {
	                System.out.println("Langilea ONDO eguneratu da.");
	            } else {
	                System.out.println("Ez da aurkitu ID hori duen langilea.");
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
}
