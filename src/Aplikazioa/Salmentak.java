package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Salmentak {

	
	//Langilea
	public void salmentaKudeatu3(Scanner sc) {
		System.out.println("\nHauek dira salmenten aukerak:");
		System.out.println("1. Salmentak bistaratu");
		System.out.print("Zure aukera: ");
		int SalmentaLangilea3 = sc.nextInt();

		switch (SalmentaLangilea3) {
		case 1:
			salmentakErakutsi();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	
	//Kudeatzailea eta arduraduna
	public void salmentaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira salmenten aukerak:");
		System.out.println("1. Salmentak bistaratu");
		System.out.println("2. Salmenta txertatu");
		System.out.print("Zure aukera: ");
		int SalmentaLangilea = sc.nextInt();

		switch (SalmentaLangilea) {
		case 1:
			salmentakErakutsi();
			break;
		case 2:
			salmentakSartu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	public void salmentakErakutsi () {
		Conexioa conexion = new Conexioa();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM salmentak");
			
			while (rs.next()) {
				int id = rs.getInt(1);
				Double kantitatea = rs.getDouble(2);
		        String data = rs.getString(3);
				String produktua = rs.getString(4);
				String kondizioa = rs.getString(5);
				String bezeroa = rs.getString(6);

				
				
				System.out.println(id+" - "+kantitatea+" - "+data+" - "+produktua+" - "+kondizioa+ " - " +bezeroa);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		
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
	public void salmentakSartu () {

        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Sartu kantitatea: ");
        int kantitatea = sc.nextInt();
        
        System.out.print("Sartu data (yyyy-MM-dd): ");
        String dataStr = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = null;
        try {
            utilDate = sdf.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Data formatua ez da zuzena. Erabili yyyy-MM-dd formatua.");
            return;
        }
        java.sql.Date data = new java.sql.Date(utilDate.getTime());

        System.out.print("Sartu produktua: ");
        String produktua = sc.next();
        
        System.out.print("Sartu kondizioa: ");
        String kondizioa = sc.next();
        
        System.out.print("Sartu bezeroa: ");
        String bezeroa = sc.next();

        
        Conexioa conexion = new Conexioa();
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = conexion.conectar();

            
            String sql = "INSERT INTO salmentak (kantitatea, data, produktua, kondizioa, bezeroa) VALUES (?, ?, ?, ?, ?)";

            ps = cn.prepareStatement(sql);
           
            ps.setInt(1, kantitatea);
            ps.setDate(2, data);
            ps.setString(3, produktua);
            ps.setString(4, kondizioa);
            ps.setString(5, bezeroa);
           

            
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Salmenta ondo gehitu da datu-basean.");
            } else {
                System.out.println("Ez da salmenta gehitu.");
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
}
