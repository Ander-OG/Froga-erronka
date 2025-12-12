package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Produktuak {

	
	//Kudeatzailea, langilea eta arduraduna
	public void produktuaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira produktuen aukerak:");
		System.out.println("1. Produktuak bistaratu");
		System.out.println("2. Produktua txertatu");
		System.out.println("3. Produktuaren informazioa aldatu");
		System.out.println("4. Produktua ezabatu");
		System.out.print("Zure aukera: ");
		int ProduktuaLangilea = sc.nextInt();
		
		switch (ProduktuaLangilea) {
		case 1:
			produktuakErakutsi();
			break;
		case 2:
			produktuakSartu();
			break;
		case 3:
			produktuakEditatu();
			break;
		case 4:
			produktuakEzabatu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	
	 public void produktuakEzabatu () {
		 
	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        Statement st = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        Scanner sc = new Scanner(System.in);
	 
	        try {
	            cn = conexion.conectar();
	 
	            
	            System.out.println("\n Produktuen zerrenda:");
	            st = cn.createStatement();
	            rs = st.executeQuery("SELECT * FROM produktuak");
	 
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String mota = rs.getString("mota");
	                String izena = rs.getString("izena");
	                Double prezioa = rs.getDouble("prezioa");
	                String kondizioa = rs.getString("kondizioa");
	                int stock = rs.getInt("stock");
	                
	                System.out.println(id + " - " + mota + " - " + izena + " - " + prezioa + " - " + kondizioa + " - " + stock);
	            }
	 
	            
	            System.out.print("\nSartu ezabatu nahi duzun produktuaren ID-a: ");
	            int id = sc.nextInt();
	 
	            
	            String sql = "DELETE FROM produktuak WHERE id = ?";
	            ps = cn.prepareStatement(sql);
	            ps.setInt(1, id);
	 
	            int lerroKop = ps.executeUpdate();
	 
	            if (lerroKop > 0) {
	                System.out.println("Produktua ONDO ezabatu da datu-basetik.");
	            } else {
	                System.out.println("Ez da aurkitu ID hori duen produktua.");
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
	 public void produktuakSartu () {

	        Scanner sc = new Scanner(System.in);

	        System.out.print("Sartu mota: ");
	        String mota = sc.next();

	        System.out.print("Sartu izena: ");
	        String izena = sc.next();
	        
	        System.out.print("Sartu prezioa: ");
	        int prezioa = sc.nextInt();
	        
	        System.out.print("Sartu kondizioa: ");
	        String kondizioa = sc.next();
	        
	        System.out.print("Sartu stock-a: ");
	        int stock = sc.nextInt();

	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        PreparedStatement ps = null;

	        try {
	            cn = conexion.conectar();

	            String sql = "INSERT INTO produktuak (mota, izena, prezioa, kondizioa, stock) VALUES (?, ?, ?, ?, ?)";

	            ps = cn.prepareStatement(sql);
	           
	            ps.setString(1, mota);
	            ps.setString(2, izena);
	            ps.setInt(3, prezioa);
	            ps.setString(4, kondizioa);
	            ps.setInt(5, stock);
	           

	            int filasAfectadas = ps.executeUpdate();

	            if (filasAfectadas > 0) {
	                System.out.println("Produktua ondo gehitu da datu-basean.");
	            } else {
	                System.out.println("Ez da produktua gehitu.");
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
	 public void produktuakErakutsi () {
			Conexioa conexion = new Conexioa();
			Connection cn = null;
			Statement stm = null;
			ResultSet rs = null;
			
			try {
				cn = conexion.conectar();
				stm = cn.createStatement();
				rs = stm.executeQuery("SELECT * FROM produktuak");
				
				while (rs.next()) {
					int id = rs.getInt(1);
					String mota = rs.getString(2);
					String izena = rs.getString(3);
					Double prezioa = rs.getDouble(4);
					String kondizioa = rs.getString(5);
					int stock = rs.getInt(6);
					
					
					System.out.println(id+" - "+mota+" - "+izena+" - "+prezioa+" - "+kondizioa+" - "+stock);
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
	 public void produktuakEditatu () {

	        Conexioa conexion = new Conexioa();
	        Connection cn = null;
	        Statement st = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        Scanner sc = new Scanner(System.in);

	        try {
	            cn = conexion.conectar();


	            System.out.println("\nProduktuen zerrenda:");
	            st = cn.createStatement();
	            rs = st.executeQuery("SELECT * FROM produktuak");

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String mota = rs.getString("mota");
	                String izena = rs.getString("izena");
	                Double prezioa = rs.getDouble("prezioa");
	                String kondizioa = rs.getString("kondizioa");
	                int stock = rs.getInt("stock");
	                
	                System.out.println(id + " - " + mota + " - " + izena + " - " + prezioa + " - " + kondizioa + " - " + stock);
	            }

	            System.out.print("\nSartu aldatu nahi duzun produktuaren ID-a: ");
	            int id = sc.nextInt();
	            sc.nextLine();

	            System.out.println("Zer aldatu nahi duzu?");
	            System.out.println("1 - Mota");
	            System.out.println("2 - Izena");
	            System.out.println("3 - Prezioa");
	            System.out.println("4 - Kondizioa");
	            System.out.println("5 - Stock");
	            System.out.print("Aukera: ");
	            int aukera = sc.nextInt();
	            sc.nextLine();

	            String campo = "";
	            switch (aukera) {
	                case 1: campo = "mota"; break;
	                case 2: campo = "izena"; break;
	                case 3: campo = "prezioa"; break;
	                case 4: campo = "kondizioa"; break;
	                case 5: campo = "stock"; break;
	                default:
	                    System.out.println("Aukera ez da zuzena.");
	                    return;
	            }

	            System.out.print("Sartu balio berria: ");
	            String balioBerria = sc.nextLine();

	            String sql = "UPDATE produktuak SET " + campo + " = ? WHERE id = ?";
	            ps = cn.prepareStatement(sql);
	            ps.setString(1, balioBerria);
	            ps.setInt(2, id);

	            int lerroKop = ps.executeUpdate();

	            if (lerroKop > 0) {
	                System.out.println("Produktua ONDO eguneratu da.");
	            } else {
	                System.out.println("Ez da aurkitu ID hori duen produktua.");
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
